package de.wroracer.chaoschallange.chaos;


import com.google.gson.Gson;
import de.wroracer.chaoschallange.ChaosChallange;
import de.wroracer.chaoschallange.chaos.actions.util.Action;
import de.wroracer.chaoschallange.chaos.actions.util.TimedAction;
import de.wroracer.chaoschallange.chaos.rest.RestResponse;
import de.wroracer.chaoschallange.chaos.rest.VotingAction;
import de.wroracer.chaoschallange.util.Constants;
import de.wroracer.chaoschallange.util.LoggerHelper;
import de.wroracer.chaoschallange.util.TimerUtil;
import de.wroracer.chaoschallange.vote.counter.TwitchVoteCounter;
import de.wroracer.chaoschallange.vote.counter.VoteCounter;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameRule;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.eclipse.jetty.util.Scanner;
import org.reflections.Reflections;
import spark.Filter;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static spark.Spark.*;
import static spark.Spark.get;

public class ActionManager implements Listener, LoggerHelper {

    public static ActionManager INSTANCE;

    private ChaosChallange plugin;

    private List<Action> actions;
    private List<Action> activeActions;

    private List<VoteCounter> voteCounters;

    private Scoreboard scoreboard;
    private Objective board;

    private int votes1;
    private int votes2;
    private int votes3;
    private int votes4;

    private TimerUtil timer;

    public ActionManager(ChaosChallange plugin){
        INSTANCE = this;
        this.plugin = plugin;
        this.actions = new ArrayList<>();
        this.activeActions = new ArrayList<>();
        voteCounters = Arrays.asList(new TwitchVoteCounter());

        timer = new TimerUtil();

        setGameRule(GameRule.DO_DAYLIGHT_CYCLE,false);
        setGameRule(GameRule.DO_FIRE_TICK,false);
        setGameRule(GameRule.DO_WEATHER_CYCLE,false);
        setGameRule(GameRule.DO_MOB_SPAWNING,false);
        setGameRule(GameRule.PLAYERS_SLEEPING_PERCENTAGE,50);
        setGameRule(GameRule.FALL_DAMAGE,false);
        setDifficulty(Difficulty.PEACEFUL);

        scoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard();
        board = scoreboard.registerNewObjective("chaosBoard", "dummy", "§6Vote-Board");
        if (plugin.getConfig().getBoolean("Chaos.scoreBoard"))
            board.setDisplaySlot(DisplaySlot.SIDEBAR);
        board.getScore(Constants.SCOREBOARD_WAITING).setScore(1);

        registerActions(getClass().getPackage().getName());

        registerSpark();
    }

    public void testAction(Action action){
        startAction(action);
    }

    public void addAction(Action action){
        actions.add(action);
    }

    public List<Action> getActions() {
        return actions;
    }

    private final List<String> voters = new ArrayList<>();
    private boolean isVoting = false;
    public void registerVote(int nbr, String name) {
        if (!isVoting)return;
        if (voters.contains(name))return;
        switch (nbr){
            case 1:
            case 6:
                votes1++;break;
            case 2:
            case 7:
                votes2++;break;
            case 3:
            case 8:
                votes3++;break;
            case 4:
            case 9:
                votes4++;break;
        }
        voters.add(name);
        plugin.getConfig().getInt("Chaos.Voting.Time");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(Constants.PREFIX+"§1"+player.getDisplayName() + "§9 joined the Challenge");
        if (plugin.getConfig().getBoolean("Chaos.scoreBoard"))
            player.setScoreboard(scoreboard);
    }

    private boolean isStarted = false;

    public void onDisable(){
        stop();
    }

    public void startChaos(){
        if (isStarted) {
            return;
        }
        scoreboard.resetScores(Constants.SCOREBOARD_WAITING);
        Bukkit.broadcastMessage(Constants.PREFIX+"§2Chaos Starting!");
        timer.start();
        setGameRule(GameRule.DO_DAYLIGHT_CYCLE,true);
        setGameRule(GameRule.DO_FIRE_TICK,true);
        setGameRule(GameRule.DO_WEATHER_CYCLE,true);
        setGameRule(GameRule.DO_MOB_SPAWNING,true);
        setGameRule(GameRule.FALL_DAMAGE,true);
        setDifficulty(Difficulty.HARD);
        for (VoteCounter counter : VoteCounter.getCounters()) {
            counter.onEnable();
        }
        isStarted = true;
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, this::startVoting,20);
    }

    public void stopChaos(){
        if (!isStarted) {
            return;
        }
        Bukkit.broadcastMessage(Constants.PREFIX+"§aChaos Stopping!");
        setGameRule(GameRule.DO_DAYLIGHT_CYCLE,false);
        setGameRule(GameRule.DO_FIRE_TICK,false);
        setGameRule(GameRule.DO_WEATHER_CYCLE,false);
        setGameRule(GameRule.DO_MOB_SPAWNING,false);
        setGameRule(GameRule.PLAYERS_SLEEPING_PERCENTAGE,50);
        setGameRule(GameRule.FALL_DAMAGE,false);
        setDifficulty(Difficulty.PEACEFUL);
        isStarted = false;
        for (VoteCounter counter : VoteCounter.getCounters()) {
            counter.onDisable();
        }
    }


    private boolean useOneTwoThree = true;
    private Action action1;
    private Action action2;
    private Action action3;
    private Action action4;

    private int timeForVote;

    private void startVoting(){
        try {
            scoreboard.getEntries().forEach(entry -> {
                scoreboard.resetScores(entry);
            });
        } catch (Exception ignored) {

        }
        if (!isStarted) {
            Bukkit.broadcastMessage(Constants.PREFIX+"§aChaos Stopped");
            timer.stop();
            return;
        }
        Random random = new Random();
        List<Action> tempActions = new ArrayList<>(actions);
        action1 = tempActions.get(random.nextInt(tempActions.size()));
        tempActions.remove(action1);
        action2 = tempActions.get(random.nextInt(tempActions.size()));
        tempActions.remove(action2);
        action3 = tempActions.get(random.nextInt(tempActions.size()));
        tempActions.remove(action3);
        action4 = tempActions.get(random.nextInt(tempActions.size()));
        tempActions.remove(action4);

        if (useOneTwoThree) {
            board.getScore(action1.getName()).setScore(1);
            board.getScore(action2.getName()).setScore(2);
            board.getScore(action3.getName()).setScore(3);
            board.getScore(Constants.RANDOM_NAME).setScore(4);
        } else {
            board.getScore(action1.getName()).setScore(6);
            board.getScore(action2.getName()).setScore(7);
            board.getScore(action3.getName()).setScore(8);
            board.getScore(Constants.RANDOM_NAME).setScore(9);
        }

        isVoting = true;

        timeForVote = plugin.getConfig().getInt("Chaos.Voting.Time");
        int shedId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin,()->{
            timeForVote-=1;
        },20,20);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin,()->{
            Bukkit.getScheduler().cancelTask(shedId);
            endVoting();
        },plugin.getConfig().getInt("Chaos.Voting.Time")*20L);
    }

    private void endVoting(){
        isVoting = false;
        List<Action> toSelect = new ArrayList<>();
        if (votes1>1||votes2>1||votes3>1||votes4>1){
            for (int i = 0;i!=votes1;i++){
                toSelect.add(action1);
            }
            for (int i = 0;i!=votes2;i++){
                toSelect.add(action2);
            }
            for (int i = 0;i!=votes3;i++){
                toSelect.add(action3);
            }
            for (int i = 0;i!=votes4;i++){
                toSelect.add(action4);
            }
        }else {
            toSelect.add(action1);
            toSelect.add(action2);
            toSelect.add(action3);
            toSelect.add(action4);
        }

        Random random = new Random();

        Action selected = toSelect.get(random.nextInt(toSelect.size()));

        useOneTwoThree = !useOneTwoThree;

        if (action1.equals(selected)) {
            Bukkit.broadcastMessage(Constants.PREFIX+"§a"+selected.getName()+" §3voted with §4"+votes1+"§8/§4"+(votes1+votes2+votes3+votes4)+" §3votes§8!");
        }else if (action2.equals(selected)){
            Bukkit.broadcastMessage(Constants.PREFIX+"§a"+selected.getName()+" §3voted with §4"+votes2+"§8/§4"+(votes1+votes2+votes3+votes4)+" §3votes§8!");
        }else if (action3.equals(selected)){
            Bukkit.broadcastMessage(Constants.PREFIX+"§a"+selected.getName()+" §3voted with §4"+votes3+"§8/§4"+(votes1+votes2+votes3+votes4)+" §3votes§8!");
        }else {
            Bukkit.broadcastMessage(Constants.PREFIX+"§a"+selected.getName()+" §3voted with §4"+votes4+"§8/§4"+(votes1+votes2+votes3+votes4)+" §3votes§8!");
        }

        votes4 = 0;
        votes1 = 0;
        votes2 = 0;
        votes3 = 0;
        voters.clear();

       startAction(selected);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, this::startVoting,20);
    }

    private void startAction(Action action){
        action.start();
        activeActions.add(action);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            action.stop();
            activeActions.remove(action);
            if (action instanceof TimedAction){
                Bukkit.broadcastMessage(Constants.PREFIX+"§c Action §4"+action.getName()+"§c ended§8!");
            }
        }, action.getActionTime() * 20L);
    }


    private void setGameRule(GameRule rule,Object value){
        Bukkit.getWorlds().forEach(w->w.setGameRule(rule,value));
    }
    private void setDifficulty(Difficulty difficulty){
        Bukkit.getWorlds().forEach(w->w.setDifficulty(difficulty));
    }

    public String getNextActionTime() {
        int minutes = (timeForVote / 60) % 60;
        int seconds = timeForVote % 60;

        return "" + String.format("%02d", minutes) + ":" +
                String.format("%02d", seconds);
    }

    private void registerActions(String packageName) {
        plugin.getLogger().info("Registering Actions");
        boolean isDebug = plugin.getConfig().isBoolean("debug");
        for (Class<? extends Action> clazz : new Reflections(packageName + ".actions").getSubTypesOf(Action.class)) {
            try {
                if (clazz.getPackage().getName().contains("util")) {
                    continue;
                }
                Action pluginCommand = (Action) clazz.getDeclaredConstructors()[0].newInstance();
                if (isDebug) {
                    plugin.getLogger().info("Registering Action" + clazz.getSimpleName());
                }
                pluginCommand.setManager(this);
                pluginCommand.setup();

                addAction(pluginCommand);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        log().info("Registering Commands Finished");
    }

    private void registerSpark() {
        port(plugin.getConfig().getInt("Chaos.rest.port"));

        after((Filter) (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET");
        });

        get("/", (request, response) -> "{\"Status\":\"Online\"}");

        get("/chaos", (request, response) -> {
            response.type("application/json");
            RestResponse restResponse = new RestResponse(activeActions, new VotingAction(action1, votes1),
                    new VotingAction(action2, votes2), new VotingAction(action3, votes3), votes4, timeForVote, isStarted,
                    plugin.getConfig().getInt("Chaos.Voting.Time"), !useOneTwoThree);
            return new Gson().toJson(restResponse);
        });
    }

    public int getVoteTime() {
       return plugin.getConfig().getInt("Chaos.Voting.Time");
    }

    public ChaosChallange getPlugin() {
        return plugin;
    }
}


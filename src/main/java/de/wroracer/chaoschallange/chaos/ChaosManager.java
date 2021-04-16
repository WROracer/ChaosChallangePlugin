package de.wroracer.chaoschallange.chaos;

import de.wroracer.chaoschallange.ChaosChallange;
import de.wroracer.chaoschallange.chaos.actions.Action;
import de.wroracer.chaoschallange.config.MainConfig;
import de.wroracer.chaoschallange.vote.counter.TwitchVoteCounter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChaosManager implements Listener {

    private ChaosChallange plugin;

    private List<Action> actions;

    private Scoreboard scoreboard;
    private Objective board;
    private BossBar bossBar;

    private TwitchVoteCounter twitchVoteCounter;

    public boolean isActivated;

    private final String randomName = "Random event";



    private Action action1;
    private Action action2;
    private Action action3;

    private Action lastAction;

    public List<Action> getActions() {
        return actions;
    }

    private int vote1;
    private int vote2;
    private int vote3;
    private int vote4;

    private int voteTime;

    private MainConfig conf;

    public ChaosChallange getPlugin() {
        return plugin;
    }

    public ChaosManager(ChaosChallange plugin){
        this.plugin = plugin;
        actions = new ArrayList<>();
        conf = new MainConfig();

        twitchVoteCounter = new TwitchVoteCounter(this,conf);

        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        board = scoreboard.registerNewObjective("board","dummy","§6Vote-Board");
        board.setDisplaySlot(DisplaySlot.SIDEBAR);
        board.getScore("Waiting").setScore(1);

        bossBar = Bukkit.createBossBar("Voting Time", BarColor.RED, BarStyle.SEGMENTED_20);
        bossBar.setTitle("Voting Time");
        bossBar.setColor(BarColor.RED);
        bossBar.setStyle(BarStyle.SEGMENTED_20);
        bossBar.setVisible(true);
        bossBar.removeAll();

        Bukkit.getOnlinePlayers().forEach(player -> {
            player.setScoreboard(scoreboard);
            bossBar.addPlayer(player);
        });

        voteTime = conf.getVotingTime();

        isActivated = false;

        lastAction = null;

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        event.setJoinMessage(player.getDisplayName()+" ist der Challange beigetreten");
        player.setScoreboard(scoreboard);
        bossBar.addPlayer(player);
    }

    public void addAction(Action action){
        actions.add(action);
    }

    public void activate(){
        isActivated = true;
        useOneTwoThree = true;
        scoreboard.resetScores("Waiting");
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, this::startVoting);
    }

    public void deactivate(){
        isActivated = false;
    }
    private void deac(){
        try {
            scoreboard.resetScores(action1.getName());
            scoreboard.resetScores(action2.getName());
            scoreboard.resetScores(action3.getName());
            scoreboard.resetScores(randomName);
        }catch (Exception ignored){

        }
        board.getScore("Waiting").setScore(1);
    }


    private int timeBosBar = 0;
    private void bossbarUpdate(){
        double prossec = (timeBosBar * 100.0f) / voteTime;
        bossBar.setProgress(prossec/100);
        timeBosBar--;
    }


    private boolean useOneTwoThree;

    public int getVoteTime() {
        return voteTime;
    }

    private void startVoting(){
        try {
            scoreboard.resetScores(action1.getName());
            scoreboard.resetScores(action2.getName());
            scoreboard.resetScores(action3.getName());
            scoreboard.resetScores(randomName);
        }catch (Exception ignored){

        }


        vote1 = 1;
        vote2 = 1;
        vote3 = 1;
        vote4 = 1;

        if (lastAction != null){
            lastAction.stop();
        }

        Random rnd = new Random();
        action1 = actions.get(rnd.nextInt(actions.size()));
        actions.remove(action1);
        action2 = actions.get(rnd.nextInt(actions.size()));
        actions.remove(action2);
        action3 = actions.get(rnd.nextInt(actions.size()));
        actions.remove(action3);

        if (useOneTwoThree){
            board.getScore(action1.getName()).setScore(1);
            board.getScore(action2.getName()).setScore(2);
            board.getScore(action3.getName()).setScore(3);
            board.getScore(randomName).setScore(4);
        }else {
            board.getScore(action1.getName()).setScore(6);
            board.getScore(action2.getName()).setScore(7);
            board.getScore(action3.getName()).setScore(8);
            board.getScore(randomName).setScore(9);
        }
        twitchVoteCounter.hasVoted.clear();
        if (isActivated) {
            timeBosBar = voteTime;
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, this::endVoting,voteTime*20);
            int id = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin,this::bossbarUpdate,20,20);
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin,()->{
                Bukkit.getScheduler().cancelTask(id);
            },voteTime*20);
        }else {
            deac();
        }
    }

    public void stop(){
        twitchVoteCounter.disconect();
        bossBar.removeAll();
    }

    public void vote(int nbr){
        switch (nbr){
            case 1:
            case 6:
                vote1++;System.out.println("Register vote 1"); break;
            case 2:
            case 7:
                vote2++;System.out.println("Register vote 2");break;
            case 3:
            case 8:
                vote3++;System.out.println("Register vote 3");break;
            case 4:
            case 9:
                vote4++;System.out.println("Register vote 4");break;
        }
    }

    private void endVoting(){
        Random rnd = new Random();
        Action action4 = actions.get(rnd.nextInt(actions.size()));
        List<Action> toSelect = new ArrayList<>();

        for (int i = vote1;i!=0;i--){
            toSelect.add(action1);
        }
        for (int i = vote2;i!=0;i--){
            toSelect.add(action2);
        }
        for (int i = vote3;i!=0;i--){
            toSelect.add(action3);
        }
        for (int i = vote4;i!=0;i--){
            toSelect.add(action4);
        }
        lastAction = toSelect.get(rnd.nextInt(toSelect.size()));
        lastAction.start();
        Bukkit.broadcastMessage("§2Event: §6"+lastAction.getName());
        actions.add(action1);
        actions.add(action2);
        actions.add(action3);
        useOneTwoThree = !useOneTwoThree;
        if (isActivated) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, this::startVoting);
        }else {
            deac();
        }
    }

}

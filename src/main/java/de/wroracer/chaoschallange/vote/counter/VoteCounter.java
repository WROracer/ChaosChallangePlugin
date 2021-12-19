package de.wroracer.chaoschallange.vote.counter;

import de.wroracer.chaoschallange.ChaosChallange;
import de.wroracer.chaoschallange.chaos.ActionManager;

public abstract class VoteCounter {

    public abstract boolean onEnable();
    public abstract boolean onDisable();

    public void registerVote(int nbr,String name){
        ActionManager.INSTANCE.registerVote(nbr,name);
    }

    public ChaosChallange getPlugin(){
        return ChaosChallange.INSTANCE;
    }

    public static VoteCounter[] getCounters(){
        return new VoteCounter[]{new TwitchVoteCounter()};
    }
}

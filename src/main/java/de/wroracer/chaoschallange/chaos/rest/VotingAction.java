package de.wroracer.chaoschallange.chaos.rest;

import de.wroracer.chaoschallange.chaos.actions.Action;

public class VotingAction {
    private Action action;
    private int votes;

    public VotingAction(Action action, int votes) {
        this.action = action;
        this.votes = votes;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}

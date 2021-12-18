package de.wroracer.chaoschallange.chaos.rest;

import de.wroracer.chaoschallange.chaos.actions.util.Action;

import java.util.List;

public class RestResponse {
    private List<Action> currentActions;
    private VotingAction action1;
    private VotingAction action2;
    private VotingAction action3;
    private int action4;
    private int timer;
    private int maxTime;
    private boolean isChaos;
    private boolean useSix;

    public RestResponse(List<Action> currentActions, VotingAction action1, VotingAction action2, VotingAction action3, int action4, int timer,boolean isChaos,int maxTime,boolean useSix) {
        this.currentActions = currentActions;
        this.action1 = action1;
        this.action2 = action2;
        this.action3 = action3;
        this.action4 = action4;
        this.timer = timer;
        this.isChaos = isChaos;
        this.maxTime = maxTime;
        this.useSix = useSix;
    }

    public List<Action> getCurrentActions() {
        return currentActions;
    }

    public void setCurrentActions(List<Action> currentActions) {
        this.currentActions = currentActions;
    }

    public VotingAction getAction1() {
        return action1;
    }

    public void setAction1(VotingAction action1) {
        this.action1 = action1;
    }

    public VotingAction getAction2() {
        return action2;
    }

    public void setAction2(VotingAction action2) {
        this.action2 = action2;
    }

    public VotingAction getAction3() {
        return action3;
    }

    public void setAction3(VotingAction action3) {
        this.action3 = action3;
    }

    public int getAction4() {
        return action4;
    }

    public void setAction4(int action4) {
        this.action4 = action4;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public boolean isChaos() {
        return isChaos;
    }

    public void setChaos(boolean chaos) {
        isChaos = chaos;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    public boolean isUseSix() {
        return useSix;
    }

    public void setUseSix(boolean useSix) {
        this.useSix = useSix;
    }
}

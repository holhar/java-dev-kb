package de.holhar.java_dev_kb.designpatterns.behavioral.state;

public class TvContext implements State {

    private State state;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public void doAction() {
        this.state.doAction();
    }
}

package de.holhar.java_dev_kb.designpatterns.behavioral.state;

public class TvRemote {

    public static void main(String[] args) {
        TvContext context = new TvContext();
        TvStartState startState = new TvStartState();
        TvStopState stopState = new TvStopState();

        context.setState(startState);
        context.doAction();

        context.setState(stopState);
        context.doAction();
    }
}

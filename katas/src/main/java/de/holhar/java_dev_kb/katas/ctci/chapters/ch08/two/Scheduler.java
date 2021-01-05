package de.holhar.java_dev_kb.katas.ctci.chapters.ch08.two;

public class Scheduler {

    private int freeResponderNum;
    private int freeManagerNum;
    private int freeDirectorNum;
    private static Scheduler instance;

    private Scheduler() {
    }

    public static Scheduler getInstance() {
        if (instance == null) {
            instance = new Scheduler();
        }
        return instance;
    }

    public void initScheduler(int responderNum, int managerNum, int directorNum) {
        freeResponderNum = responderNum;
        freeManagerNum = managerNum;
        freeDirectorNum = directorNum;
    }

    public void dispatchCall(Call call) {
        if (freeResponderNum > 0) {
            freeResponderNum--;
            call.setDispatched(true);
        } else if (freeManagerNum > 0) {
            freeManagerNum--;
            call.setDispatched(true);
        } else if (freeDirectorNum > 0) {
            freeDirectorNum--;
            call.setDispatched(true);
        }
    }

    public int getFreeResponderNum() {
        return freeResponderNum;
    }

    public void setFreeResponderNum(int freeResponderNum) {
        this.freeResponderNum = freeResponderNum;
    }

    public int getFreeManagerNum() {
        return freeManagerNum;
    }

    public void setFreeManagerNum(int freeManagerNum) {
        this.freeManagerNum = freeManagerNum;
    }

    public int getFreeDirectorNum() {
        return freeDirectorNum;
    }

    public void setFreeDirectorNum(int freeDirectorNum) {
        this.freeDirectorNum = freeDirectorNum;
    }
}

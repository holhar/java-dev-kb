package de.holhar.java_dev_kb.training.concurrency.ch06_task_execution.s2_executor_framework;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Class illustrating confusing Timer behavior.
 */
public class OutOfTime {
    public static void main(String[] args) throws InterruptedException {
        final Timer timer = new Timer();
        timer.schedule(new ThrowTask(), 1);
        Thread.sleep(1000);
        timer.schedule(new ThrowTask(), 1);
        Thread.sleep(5000);
    }

    static class ThrowTask extends TimerTask {
        public void run() {
            throw new RuntimeException();
        }
    }
}

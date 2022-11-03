package de.holhar.java_dev_kb.training.concurrency.ch06_task_execution.s6_2_executor_framework.s6_2_1_example_web_server_using_executor;

import java.util.concurrent.Executor;

/**
 * Executor that starts a new thread for each task.
 */
public class ThreadPerTaskExecutor implements Executor {
    public void execute(Runnable r) {
        new Thread(r).start();
    }
}

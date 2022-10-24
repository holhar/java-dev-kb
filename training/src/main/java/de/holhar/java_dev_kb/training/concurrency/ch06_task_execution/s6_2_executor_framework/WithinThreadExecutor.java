package de.holhar.java_dev_kb.training.concurrency.ch06_task_execution.s6_2_executor_framework;

import java.util.concurrent.Executor;

/**
 * Executor that executes tasks synchronously in the calling thread.
 */
public class WithinThreadExecutor implements Executor {
    public void execute(Runnable r) {
        r.run();
    }
}

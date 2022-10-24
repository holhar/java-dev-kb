package de.holhar.java_dev_kb.training.concurrency.ch07_cancellation_and_shutdown.s7_1_task_cancellation.s7_1_7;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/**
 * Encapsulating nonstandard cancellation in a task with newTaskFor.
 */
public interface CancellableTask<T> extends Callable<T> {
    void cancel();
    RunnableFuture<T> newTask();
}

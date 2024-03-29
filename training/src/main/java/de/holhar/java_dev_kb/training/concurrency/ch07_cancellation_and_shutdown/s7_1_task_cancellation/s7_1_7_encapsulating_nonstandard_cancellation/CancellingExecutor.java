package de.holhar.java_dev_kb.training.concurrency.ch07_cancellation_and_shutdown.s7_1_task_cancellation.s7_1_7_encapsulating_nonstandard_cancellation;

import de.holhar.java_dev_kb.training.concurrency.utils.ThreadSafe;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Encapsulating nonstandard cancellation in a task with newTaskFor.
 */
@ThreadSafe
public class CancellingExecutor extends ThreadPoolExecutor {

    public CancellingExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        if (callable instanceof CancellableTask) {
            return ((CancellableTask<T>) callable).newTask();
        } else {
            return super.newTaskFor(callable);
        }
    }
}

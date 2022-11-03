package de.holhar.java_dev_kb.training.concurrency.ch07_cancellation_and_shutdown.s7_1_task_cancellation.s7_1_3_responding_to_interruption;

import de.holhar.java_dev_kb.training.concurrency.utils.ExceptionUtils;
import org.springframework.scheduling.config.Task;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TaskInterruptionAndCancellation {

    private static final int NTHREADS = 100;
    private static final ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(NTHREADS);
    private static final ExecutorService taskExec = Executors.newFixedThreadPool(NTHREADS);

    /**
     * Noncancelable task that restores interruption before exit.
     */
    public Task getNextTask(BlockingDeque<Task> queue) {
        var interrupted = false;
        try {
            while (true) {
                try {
                    return queue.take();
                } catch (InterruptedException e) {
                    interrupted = true;
                    // fall through and retry
                }
            }
        } finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * 7.1.4 Example: timed run
     *
     * Addressing possible unchecked exceptions during task execution - Version 1:
     *
     * Scheduling an interrupt on a borrowed thread.
     * Don't do this.
     * It violates the rules: you should know a thread's interruption policy before interrupting it. Since timedRun
     * can be called from an arbitrary thread, it cannot know the calling thread's interruption policy.
     */
    public static void timedRunV1(Runnable r, long timeout, TimeUnit unit) {
        final var taskThread = Thread.currentThread();
        cancelExec.schedule(taskThread::interrupt, timeout, unit);
        r.run();
    }

    /**
     * 7.1.4 Example: timed run
     *
     * Addressing possible unchecked exceptions during task execution - Version 2:
     *
     * Interruption a task in a dedicated thread.
     */
    public static void timedRunV2(final Runnable r, long timeout, TimeUnit unit) throws InterruptedException {

        class RethrowableTask implements Runnable {
            private volatile Throwable t;

            public void run() {
                try {
                    r.run();
                } catch (Throwable t) {
                    this.t = t;
                }
            }

            void rethrow() {
                if (t != null) {
                    throw ExceptionUtils.launderThrowable(t);
                }
            }
        }

        var task = new RethrowableTask();
        final var taskThread = new Thread(task);
        taskThread.start();
        cancelExec.schedule(taskThread::interrupt, timeout, unit);
        taskThread.join(unit.toMillis(timeout));
        task.rethrow();
    }

    /**
     * 7.1.5 Cancellation via Future
     *
     * Addressing possible unchecked exceptions during task execution - Version 3:
     *
     * Cancelling a task using Future.
     */
    public static void timedRunV3(final Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
        Future<?> task = taskExec.submit(r);
        try {
            task.get(timeout, unit);
        } catch (TimeoutException e) {
            // task will be cancelled below
        } catch (ExecutionException e) {
            // exception thrown in task; rethrow
            throw ExceptionUtils.launderThrowable(e.getCause());
        } finally {
            // Harmless if task already completed
            task.cancel(true); // interrupt if running
        }
    }
}

package de.holhar.java_dev_kb.training.ocp8.ch07_concurrency.sec02_using_executor_service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class SchedulingTasks {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // Using a ScheduledExecutorService
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

        Runnable task1 = () -> println("Hello zoo");
        Callable<String> task2 = () -> "Monkey";

        // Basic usage of schedule method
        Future<?> result1 = service.schedule(task1, 5, TimeUnit.SECONDS);
        Future<String> result2 = service.schedule(task2, 8, TimeUnit.SECONDS);
        println(result2.get());

        // Submit a new task EVERY time at a fixed rate with an initial delay
        Runnable command1 = () -> println("fixed rate");
        service.scheduleAtFixedRate(command1, 6, 3, TimeUnit.SECONDS);

        // Submit a new task at a fixed rate, BUT only after the task prior is done
        Runnable command2 = () -> println("fixed delay");
        service.scheduleWithFixedDelay(command2, 0, 2, TimeUnit.SECONDS);
    }
}

package de.holhar.java_dev_kb.training.ocp8.ch07_concurrency.sec02_using_executor_service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class ApplyingPools {

    public static void main(String[] args) {
        // Creating a service with a fixed pool of 10 threads
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

        // Now another thread can take the task in case the work gets queued up.
        Runnable command = () -> println("doing stuff...");
        service.scheduleAtFixedRate(command, 2, 3, TimeUnit.SECONDS);
    }
}

package de.holhar.java_dev_kb.training.ocp8.ch07_concurrency.sec02_using_executor_service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class CheckResults {

    private static int counter;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();
            Future<?> result = service.submit(() -> {
                for (int i = 0; i < 500_000; i++) {
                    CheckResults.counter++;
                }
            });

            // No polling anymore - Great!
            result.get(3, TimeUnit.SECONDS);
            println("REACHED");
        } catch (TimeoutException e) {
            println("Not reached in time");
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }
}

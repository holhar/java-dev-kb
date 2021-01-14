package de.holhar.java_dev_kb.training.ocp8.ch07_concurrency.sec02_using_executor_service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class AddDataWaitingForFinishedTasks {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();
            Future<Integer> result = service.submit(() -> {
                Thread.sleep(5_000);
                return 49 - 45;
            });
            println(result.get());
        } finally {
            if (service != null) {
                // Comment out to see the task still be running...
                service.shutdown();
            }
        }
        if (service != null) {
            service.awaitTermination(10, TimeUnit.SECONDS);
            // Check whether all tasks are finished
            if (service.isTerminated()) {
                println("All tasks finished");
            } else {
                println("At least one task is still running");
            }
        }
    }
}

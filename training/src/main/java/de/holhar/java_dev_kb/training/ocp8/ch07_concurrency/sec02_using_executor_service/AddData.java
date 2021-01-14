package de.holhar.java_dev_kb.training.ocp8.ch07_concurrency.sec02_using_executor_service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class AddData {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();
            Future<Integer> result = service.submit(() -> 30 + 11);
            // get() method of Future returns matching generic type or null (unlike get() method when using Runnable)
            println(result.get());
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }
}

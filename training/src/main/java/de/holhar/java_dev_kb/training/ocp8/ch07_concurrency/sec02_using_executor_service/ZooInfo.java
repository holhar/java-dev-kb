package de.holhar.java_dev_kb.training.ocp8.ch07_concurrency.sec02_using_executor_service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class ZooInfo {

    public static void main(String[] args) {
        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();
            println("begin");
            service.execute(() -> println("Printing zoo inventory"));
            service.execute(() -> {
                for (int i = 0; i < 10; i++) {
                    println("Printing record: " + i);
                }
            });
            service.execute(() -> println("Printing zoo inventory"));
            println("end");
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }
}

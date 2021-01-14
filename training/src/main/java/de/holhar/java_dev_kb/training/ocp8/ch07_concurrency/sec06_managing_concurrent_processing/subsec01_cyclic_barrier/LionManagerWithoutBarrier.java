package de.holhar.java_dev_kb.training.ocp8.ch07_concurrency.sec06_managing_concurrent_processing.subsec01_cyclic_barrier;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class LionManagerWithoutBarrier {

    public static void main(String[] args) {
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(4);
            LionManagerWithoutBarrier manager = new LionManagerWithoutBarrier();
            for (int i = 0; i < 4; i++) {
                service.submit(() -> manager.performTask());
            }
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }

    public void removeAnimals() {
        println("Removing animals");
    }

    public void cleanPen() {
        println("Cleaning pen");
    }

    public void addAnimals() {
        println("Adding animals");
    }

    public void performTask() {
        removeAnimals();
        cleanPen();
        addAnimals();
    }
}

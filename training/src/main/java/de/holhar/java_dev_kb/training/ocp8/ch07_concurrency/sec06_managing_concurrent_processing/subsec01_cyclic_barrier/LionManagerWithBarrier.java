package de.holhar.java_dev_kb.training.ocp8.ch07_concurrency.sec06_managing_concurrent_processing.subsec01_cyclic_barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class LionManagerWithBarrier {

    public static void main(String[] args) {
        ExecutorService service = null;
        try {
            LionManagerWithBarrier manager = new LionManagerWithBarrier();
            service = Executors.newFixedThreadPool(4);
            // Parties must NOT be bigger than thread pool size!!
            CyclicBarrier c1 = new CyclicBarrier(4);
            CyclicBarrier c2 = new CyclicBarrier(4, () -> println("++ Pen cleaned!"));
            for (int i = 0; i < 4; i++) {
                service.submit(() -> manager.performTask(c1, c2));
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

    public void performTask(CyclicBarrier c1, CyclicBarrier c2) {
        try {
            removeAnimals();
            c1.await();
            cleanPen();
            c2.await();
            addAnimals();
        } catch (BrokenBarrierException | InterruptedException e) {
            // Handle exceptions
        }
    }
}

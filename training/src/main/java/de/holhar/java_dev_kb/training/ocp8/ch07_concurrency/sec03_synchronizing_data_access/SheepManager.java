package de.holhar.java_dev_kb.training.ocp8.ch07_concurrency.sec03_synchronizing_data_access;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class SheepManager {

    private final Object lock = new Object();
    private int sheepCount = 0;
    // Non-atomic variable - race conditions might happen, i.e. numbers appear more than once or in wrong order!!!
    private int sheepCountBad = 0;
    // Now atomic, but still there might be race conditions, i.e. numbers might appear in the wrong order!!!
    private AtomicInteger sheepCountBetter = new AtomicInteger(0);

    public static void main(String[] args) {
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(20);
            SheepManager manager = new SheepManager();
            for (int i = 0; i < 10; i++) {
                service.submit(manager::incrementAndReport);
                service.submit(SheepManager::printDaysWorkV1);
                service.submit(SheepManager::printDaysWorkV2);
            }
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }

    // Also static methods can be synchronized - you can use static synchronization if you need to order thread access
    // across all instances, rather than a single instance.
    private static void printDaysWorkV1() {
        synchronized (SheepManager.class) {
            println("Finished work, Dude!");
        }
    }

    private static synchronized void printDaysWorkV2() {
        println("Finished work, 4 real!!!!");
    }

    // The synchronized keyword could also be applied on the method:
    //private synchronized void incrementAndReport() {
    private void incrementAndReport() {
        //print((++sheepCountBad) + " ");
        //print(sheepCountBetter.incrementAndGet() + " ");

        // FINALLY: Avoid race conditions by synchronizing the block that accesses and print sheepCount
        // - we could have synchronized on any object, so long as it was the same object; the following works too:
        synchronized (lock) {
            //synchronized (this) {
            println((++sheepCount) + " ");
        }
    }
}

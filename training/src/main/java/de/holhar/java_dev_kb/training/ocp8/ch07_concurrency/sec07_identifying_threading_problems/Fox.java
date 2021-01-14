package de.holhar.java_dev_kb.training.ocp8.ch07_concurrency.sec07_identifying_threading_problems;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

/**
 * Deadlock example
 */
public class Fox {

    public static void main(String[] args) {
        Food food = new Food();
        Water water = new Water();
        Fox tails = new Fox();
        Fox foxy = new Fox();

        // Process data
        ExecutorService service = null;
        try {
            service = Executors.newScheduledThreadPool(10);
            service.submit(() -> foxy.eatAndDrink(food, water));
            service.submit(() -> tails.drinkAndEat(food, water));
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }

    public void eatAndDrink(Food food, Water water) {
        synchronized (food) {
            println("Got Food!");
            move();
            synchronized (water) {
                println("Got Water!");
            }
        }
    }

    public void drinkAndEat(Food food, Water water) {
        synchronized (water) {
            println("Got Water!");
            move();
            synchronized (food) {
                println("Got Food!");
            }
        }
    }

    public void move() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // Handle exception
        }
    }
}

class Food {
}

class Water {
}
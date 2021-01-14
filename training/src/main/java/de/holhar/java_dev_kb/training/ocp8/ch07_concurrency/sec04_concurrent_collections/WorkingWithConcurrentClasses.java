package de.holhar.java_dev_kb.training.ocp8.ch07_concurrency.sec04_concurrent_collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class WorkingWithConcurrentClasses {

    public static void main(String[] args) {

        Map<String, Integer> map = new ConcurrentHashMap<>();
        map.put("Zebra", 52);
        map.put("Elephant", 10);
        println(map.get("Elephant"));

        Queue<Integer> queue = new ConcurrentLinkedDeque<>();
        queue.offer(31);
        println(queue.peek());
        println(queue.poll());

        Deque<Integer> deque = new ConcurrentLinkedDeque<>();
        deque.offer(10);
        deque.push(4);
        println(deque.peek());
        println(deque.pop());

        // BlockingQueue
        try {
            BlockingQueue<Integer> blockingQueue = new LinkedBlockingDeque<>();

            blockingQueue.offer(39);
            blockingQueue.offer(3, 4, TimeUnit.SECONDS);

            println(blockingQueue.poll());
            println(blockingQueue.poll(10, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            // Handle interruption...
        }

        // BlockingDeque
        try {
            BlockingDeque<Integer> blockingDeque = new LinkedBlockingDeque<>();

            blockingDeque.offer(91);
            blockingDeque.offerFirst(5, 2, TimeUnit.MINUTES);
            blockingDeque.offerLast(47, 100, TimeUnit.MICROSECONDS);
            blockingDeque.offer(3, 4, TimeUnit.SECONDS);

            println(blockingDeque.poll());
            println(blockingDeque.poll(950, TimeUnit.MILLISECONDS));
            println(blockingDeque.pollFirst(200, TimeUnit.NANOSECONDS));
            println(blockingDeque.pollLast(1, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            // Handle interruption...
        }

        // CopyOnWriteArrayList
        List<Integer> list1 = new CopyOnWriteArrayList<>(Arrays.asList(4, 3, 52));
        for (Integer item : list1) {
            System.out.print(item + " ");
            list1.add(9);
        }
        println("");
        println("Size: " + list1.size());

        // Obtaining Synchronized Collections
        List<Integer> list2 = Collections.synchronizedList(new ArrayList<>(Arrays.asList(4, 3, 52)));
        synchronized (list2) {
            for (int data : list2) {
                System.out.print(data + " ");
            }
        }

        // Synchronized Collections throw an exception when they're modified within an iterator - FIXME: OR DOESN'T IT!?
        Map<String, Object> foodData2 = new HashMap<>();
        map.put("Penguin", 1);
        map.put("Flamingo", 2);
        Map<String, Object> synchronizedFoodData = Collections.synchronizedMap(foodData2);
        for (String key : synchronizedFoodData.keySet()) {
            synchronizedFoodData.remove(key);
        }
    }
}

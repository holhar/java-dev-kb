package de.holhar.java_dev_kb.training.ocp8.ch03_generics_and_collections.sec03_collections.subsec03_lists_sets_maps_queues;

import java.util.ArrayDeque;
import java.util.Queue;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class UsingQueueInterface {

    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayDeque<>();

        println(queue.add(5));       // true - add element to the back (can throw exception)
        println(queue.element());    // 5 - returns next element in queue or throws exception if empty queue

        println(queue.offer(10)); // true - add element to the back (no exception)
        println(queue.remove());     // 5 - removes and returns next element (can throw exception)
        println(queue.poll());       // 10 - removes and returns next element or returns null if empty (no exception)
        println(queue.peek());       // null - returns next element or null if empty
        // print(queue.pop());     // Only for stack - removes and returns next element
    }
}

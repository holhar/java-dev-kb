package de.holhar.java_dev_kb.katas.ctci.chapters.ch03;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MyQueueTest {

    @Test
    void basicQueueOperations() {
        MyQueue<Integer> queue = new MyQueue<>();

        assertTrue(queue.isEmpty());
        queue.add(100);
        queue.add(200);
        assertFalse(queue.isEmpty());
        assertEquals(Integer.valueOf(100), queue.peek());
        assertEquals(Integer.valueOf(200), queue.getLast());
        assertEquals(Integer.valueOf(100), queue.remove());
        assertEquals(Integer.valueOf(200), queue.peek());
        assertEquals(queue.peek(), queue.getLast());
        assertEquals(Integer.valueOf(200), queue.remove());
        assertTrue(queue.isEmpty());

        IllegalStateException e = assertThrows(IllegalStateException.class, queue::remove);
        assertEquals("queue is empty", e.getMessage());
    }
}

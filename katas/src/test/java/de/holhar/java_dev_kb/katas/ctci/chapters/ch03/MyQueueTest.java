package de.holhar.java_dev_kb.katas.ctci.chapters.ch03;

import de.holhar.java_dev_kb.katas.ctci.chapters.ch03.MyQueue;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyQueueTest {

    @Test(expected = IllegalStateException.class)
    public void basicQueueOperations() {
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

        try {
            queue.remove();
        } catch (IllegalStateException e) {
            assertEquals("queue is empty", e.getMessage());
            throw e;
        }
    }
}

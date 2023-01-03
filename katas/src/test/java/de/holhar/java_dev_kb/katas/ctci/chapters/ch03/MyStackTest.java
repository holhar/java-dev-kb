package de.holhar.java_dev_kb.katas.ctci.chapters.ch03;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class MyStackTest {

    @Test
    void basicStackOperations() {
        MyStack<Integer> stack = new MyStack<>();
        assertTrue(stack.isEmpty());
        stack.push(10);

        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());
        assertEquals(Integer.valueOf(10), stack.peek());
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());
        assertEquals(Integer.valueOf(10), stack.pop());
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());

        stack.push(100);
        stack.push(200);
        assertEquals(2, stack.size());

        stack.pop();
        assertEquals(Integer.valueOf(100), stack.pop());

        try {
            stack.pop();
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalStateException e) {
            assertEquals("stack is empty", e.getMessage());
        }
    }

    @Test
    void min() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(500);
        stack.push(300);
        stack.push(200);
        stack.push(1000);

        Integer actual = stack.min();

        assertEquals(Integer.valueOf(200), actual);
    }

    @Test
    void sort() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(500);
        stack.push(300);
        stack.push(1000);
        stack.push(300);
        stack.push(200);
        stack.push(1000);
        stack.push(300);

        stack.sort();

        assertEquals(Integer.valueOf(200), stack.pop());
        assertEquals(Integer.valueOf(300), stack.pop());
        assertEquals(Integer.valueOf(300), stack.pop());
        assertEquals(Integer.valueOf(300), stack.pop());
        assertEquals(Integer.valueOf(500), stack.pop());
        assertEquals(Integer.valueOf(1000), stack.pop());
        assertEquals(Integer.valueOf(1000), stack.pop());
    }
}

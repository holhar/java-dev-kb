package de.holhar.java_dev_kb.katas.ctci.chapters.ch03;

import org.junit.Test;

import static org.junit.Assert.*;

public class QueueViaStacksTest {

    @Test
    public void basicOperations() {
        QueueViaStacks<String> queueViaStacks = new QueueViaStacks<>();
        assertTrue(queueViaStacks.isEmpty());
        queueViaStacks.add("a");
        assertFalse(queueViaStacks.isEmpty());
        queueViaStacks.add("b");
        queueViaStacks.add("c");
        queueViaStacks.add("d");
        queueViaStacks.add("e");
        queueViaStacks.add("f");

        assertEquals("a", queueViaStacks.peek());
        assertEquals("f", queueViaStacks.getLast());
        assertEquals("a", queueViaStacks.remove());
        assertEquals("f", queueViaStacks.getLast());
        queueViaStacks.remove();
        queueViaStacks.remove();
        queueViaStacks.remove();
        queueViaStacks.remove();
        assertEquals("f", queueViaStacks.peek());
        assertEquals("f", queueViaStacks.getLast());
        queueViaStacks.remove();
        assertTrue(queueViaStacks.isEmpty());
    }
}

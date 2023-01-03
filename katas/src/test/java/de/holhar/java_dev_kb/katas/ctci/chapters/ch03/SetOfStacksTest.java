package de.holhar.java_dev_kb.katas.ctci.chapters.ch03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SetOfStacksTest {

    @Test
    void basicOperations() {
        SetOfStacks<String> setOfStacks = new SetOfStacks<>(3);

        assertEquals(1, setOfStacks.size());
        setOfStacks.push("a");
        setOfStacks.push("b");
        setOfStacks.push("c");
        setOfStacks.push("d");
        assertEquals(2, setOfStacks.size());
        setOfStacks.push("e");
        setOfStacks.push("f");
        setOfStacks.push("g");
        assertEquals(3, setOfStacks.size());
        setOfStacks.pop();
        setOfStacks.pop();
        setOfStacks.pop();
        setOfStacks.pop();
        setOfStacks.pop();
        setOfStacks.pop();
        assertEquals("a", setOfStacks.peek());

        setOfStacks.push("1");
        setOfStacks.push("2");
        setOfStacks.push("3");
        setOfStacks.push("4");
        setOfStacks.push("5");
        setOfStacks.push("6");
        setOfStacks.push("7");
        assertEquals("5", setOfStacks.popAt(1));
    }
}

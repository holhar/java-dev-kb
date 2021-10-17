package de.holhar.java_dev_kb.katas.ctci.chapters.ch03;

import de.holhar.java_dev_kb.katas.ctci.chapters.ch02.MyLinkedList;

/*
 * 3.3 Stack of Plates: Imagine a (literal) stack of plates. If the stack gets too high, it might topple. Therefore,
 * in real life, we would likely start a new stack when the previous stack exceeds some threshold. Implement a data
 * structure SetOfStacks that mimics this. SetOfStacks should be composed of several stacks and should create a new
 * stack once the previous one exceeds capacity. SetOfStacks.push() and SetOfStacks.pop() should behave identically
 * to a single stack (that is, pop() should return the same values as it would if there were just a single stack).
 *
 * FOLLOW UP
 *
 * Implement a function popAt(int index) which performs a pop operation on a specific sub-stack.
 */
public class SetOfStacks<E extends Comparable<E>> {

    private int currentStackIndex;
    private final int sizeThreshold;
    private final MyLinkedList<MyStack<E>> stacks;

    public SetOfStacks(int sizeThreshold) {
        this.sizeThreshold = sizeThreshold;
        this.currentStackIndex = 0;
        stacks = new MyLinkedList<>();
        stacks.add(new MyStack<>());
    }

    public int size() {
        return stacks.size();
    }

    public void push(E data) {
        if (stacks.get(currentStackIndex).size() >= sizeThreshold) {
            stacks.add(new MyStack<>());
            currentStackIndex++;
        }
        stacks.get(currentStackIndex).push(data);
    }

    public E peek() {
        return stacks.get(currentStackIndex).peek();
    }

    public E pop() {
        if (stacks.get(currentStackIndex).isEmpty()) {
            stacks.delete(currentStackIndex);
            currentStackIndex--;
        }
        return stacks.get(currentStackIndex).pop();
    }

    public E popAt(int index) {
        if (stacks.get(index) == null) {
            return null;
        }
        // TODO in fact, a recursive check is needed (but I'm too lazy right now)
        if (stacks.get(index).isEmpty()) {
            stacks.delete(index);
            return stacks.get(index - 1).pop();
        } else {
            return stacks.get(index).pop();
        }
    }
}

package de.holhar.java_dev_kb.katas.ctci.chapters.ch03;

/**
 * 3.4 Queue via Stacks: Implement a MyQueue class which implements a queue using two stacks.
 */
public class QueueViaStacks<E extends Comparable<E>> {

    private final MyStack<E> leader = new MyStack<>();
    private final MyStack<E> bucket = new MyStack<>();

    public void add(E data) {
        leader.push(data);
    }

    public E peek() {
        if (leader.isEmpty()) {
            return null;
        }
        pushToBucket();
        E data = bucket.peek();
        pushToLeader();
        return data;
    }

    public E getLast() {
        if (leader.isEmpty()) {
            return null;
        }
        return leader.peek();
    }

    public E remove() {
        if (leader.isEmpty()) {
            return null;
        }
        pushToBucket();
        E data = bucket.pop();
        pushToLeader();
        return data;
    }

    public boolean isEmpty() {
        return leader.isEmpty();
    }

    private void pushToBucket() {
        while (!leader.isEmpty()) {
            bucket.push(leader.pop());
        }
    }

    private void pushToLeader() {
        while (!bucket.isEmpty()) {
            leader.push(bucket.pop());
        }
    }
}

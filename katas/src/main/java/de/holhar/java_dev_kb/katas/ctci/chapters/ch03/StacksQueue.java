package de.holhar.java_dev_kb.katas.ctci.chapters.ch03;

// Question 5
public class StacksQueue<E> {
    CustomStack<E> stackNewest;
    CustomStack<E> stackOldest;

    public StacksQueue() {
        stackNewest = new CustomStack<>();
        stackOldest = new CustomStack<>();
    }

    public int size() {
        return stackNewest.size() + stackOldest.size();
    }

    public void add(E element) {
        stackNewest.push(element);
    }

    public void shiftStacks() {
        if (stackOldest.size() == 0) {
            while (stackNewest.size() > 0) {
                stackOldest.push(stackNewest.pop());
            }
        }
    }

    public Object peek() {
        shiftStacks();
        return stackOldest.peek();
    }

    public Object pop() {
        shiftStacks();
        return stackOldest.pop();
    }
}
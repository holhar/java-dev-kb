package de.holhar.java_dev_kb.katas.ctci.chapters.ch03;

/*
 * 3.1 Three in one: Describe how you could use a single array to implement three stacks.
 */
public class MyStackArray<E> {

    public static class Node<E> {

        private final E data;

        public Node(E data) {
            this.data = data;
        }

        public E getData() {
            return data;
        }
    }

    private final Node<E>[] stackArray;
    private final int size;
    private final int firstHeadIndex;
    private final int secondHeadIndex;
    private final int thirdHeadIndex;

    public MyStackArray() {
        this(300);
    }

    public MyStackArray(int size) {
        if (size%3 != 0) {
            throw new IllegalArgumentException("size must be divisible by 3");
        }
        this.size = size;
        // FIXME unchecked assignment
        stackArray = new Node[size];
        var stackSize = size/3;
        firstHeadIndex = 0;
        secondHeadIndex = firstHeadIndex + stackSize;
        thirdHeadIndex = secondHeadIndex + stackSize;
    }

    public void addToFirstStack(E data) {
        if (stackArray[firstHeadIndex] == null) {
            stackArray[firstHeadIndex] = new Node<>(data);
        } else {
            for (int i = firstHeadIndex; i < secondHeadIndex; i++) {
                if (stackArray[i] == null) {
                    stackArray[i] = new Node<>(data);
                    break;
                }
            }
        }
    }

    public void addToSecondStack(E data) {
        if (stackArray[secondHeadIndex] == null) {
            stackArray[secondHeadIndex] = new Node<>(data);
        } else {
            for (int i = secondHeadIndex; i < thirdHeadIndex; i++) {
                if (stackArray[i] == null) {
                    stackArray[i] = new Node<>(data);
                    break;
                }
            }
        }
    }

    public void addToThirdStack(E data) {
        if (stackArray[thirdHeadIndex] == null) {
            stackArray[thirdHeadIndex] = new Node<>(data);
        } else {
            for (int i = thirdHeadIndex; i < size; i++) {
                if (stackArray[i] == null) {
                    stackArray[i] = new Node<>(data);
                    break;
                }
            }
        }
    }
}

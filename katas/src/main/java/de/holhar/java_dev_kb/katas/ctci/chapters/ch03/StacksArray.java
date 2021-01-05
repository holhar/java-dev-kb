package de.holhar.java_dev_kb.katas.ctci.chapters.ch03;

// Question 1
public class StacksArray {

    int stackSize = 100;
    int[] buffer = new int[stackSize * 3];
    int[] stackPointer = {-1, -1, -1};

    public int absTopOfStack(int stackNum) {
        return stackNum * stackSize + stackPointer[stackNum];
    }

    public void push(int stackNum, int element) throws Exception {
        if (stackPointer[stackNum] + 1 == stackSize) {
            throw new Exception("Stack is full.");
        }
        stackPointer[stackNum]++;
        buffer[absTopOfStack(stackNum)] = element;
    }

    public int pop(int stackNum) throws Exception {
        if (isEmpty(stackNum)) {
            throw new Exception("Stack is empty.");
        }
        int value = buffer[absTopOfStack(stackNum)];
        buffer[absTopOfStack(stackNum)] = 0;
        stackPointer[stackNum]--;
        return value;
    }

    public int peek(int stackNum) throws Exception {
        if (isEmpty(stackNum)) {
            throw new Exception("Stack is empty.");
        }
        return buffer[absTopOfStack(stackNum)];
    }

    public boolean isEmpty(int stackNum) {
        return stackPointer[stackNum] == -1;
    }
}

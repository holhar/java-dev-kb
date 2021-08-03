package de.holhar.java_dev_kb.katas.ctci.chapters.ch02;

import java.util.Optional;

/**
 * Implementation of a singly linked list
 */
public class MyLinkedList<E> {

    /*
     * Basic implementation
     */
    public static class Node<E> {

        private Node<E> next;
        private final E data;

        public Node (E data) {
            this.data = data;
        }

        public E getData() {
            return data;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public Node<E> getNext() {
            return next;
        }
    }

    private Node<E> head;
    private int size;

    public int size() {
        return size;
    }

    public void add(E data) {
        if (head == null) {
            head = new Node<>(data);
            size++;
            return;
        }

        Node<E> end = head;
        while (end.getNext() != null) {
            end = end.getNext();
        }
        end.next = new Node<>(data);
        size++;
    }

    public E get(int index) {
        if (index == 0) {
            return head != null ? head.getData() : null;
        }
        var counter = 0;
        Node<E> n = head;
        Node<E> result = null;
        do {
            counter++;
            n = n.getNext();
            if (counter == index) {
                result = n;
                break;
            }
        } while (n.getNext() != null);
        return result != null ? result.getData() : null;
    }

    public void delete(int index) {
        Node<E> n = head;
        if (index == 0) {
            head = n.getNext();
            size--;
            return;
        }

        var counter = 0;
        while (n.getNext() != null) {
            if (counter + 1 == index) {
                if (n.getNext().getNext() != null) {
                    n.setNext(n.getNext().getNext());
                } else {
                    n.setNext(null);
                }
                size--;
                break;
            }
            counter++;
            n = n.getNext();
        }
    }

    /*
     * 2.1 Remove Dups: Write code to remove duplicates from an unsorted linked list.
     * How would you solve this problem if a temporary buffer is not allowed?
     */
    public void removeDuplicates() {
        if (head == null) {
            return;
        }

        Node<E> firstRunner = head;
        Node<E> secondRunner = head;
        Node<E> prevNode = new Node<>(null);

        while (firstRunner.getNext() != null) {
            while (secondRunner.getNext() != null) {
                if (firstRunner.equals(secondRunner)) {
                    prevNode = secondRunner;
                    secondRunner = secondRunner.getNext();
                    continue;
                }
                var gotRemoved = compareAndRemoveDataIfEquals(firstRunner, secondRunner, prevNode);
                if (!gotRemoved) {
                    prevNode = secondRunner;
                }
                secondRunner = secondRunner.getNext();
            }

            if (firstRunner.equals(secondRunner)) {
                prevNode = secondRunner;
                secondRunner = secondRunner.getNext();
                continue;
            }
            var gotRemoved = compareAndRemoveDataIfEquals(firstRunner, secondRunner, prevNode);
            if (!gotRemoved) {
                prevNode = secondRunner;
            }
            secondRunner = head;
            firstRunner = firstRunner.getNext();
        }
    }

    private boolean compareAndRemoveDataIfEquals(Node<E> firstRunner, Node<E> secondRunner, Node<E> prev) {
        if (firstRunner.getData().equals(secondRunner.getData())) {
            if (prev.getNext().getNext() != null) {
                prev.setNext(prev.getNext().getNext());
            } else {
                prev.setNext(null);
            }
            size--;
            return true;
        }
        return false;
    }

    /*
     * 2.2 Return Kth to Last: Implement an algorithm to find the kth to last element of a singly linked list.
     */
    public E getKthToLast(int index) {
        if (head == null) {
            return null;
        }

        var elementIndex = size - index;
        var counter = 0;

        Node<E> runnerNode = head;
        while (runnerNode.getNext() != null) {
            if (counter == elementIndex) {
                return runnerNode.getData();
            }
            counter++;
            runnerNode = runnerNode.getNext();
        }
        return null;
    }

    /*
     * 2.3 Delete Middle Node: Implement an algorithm to delete a node in the middle (i.e., any node but the first and
     * last node, not necessarily the exact middle) of a singly linked list, given only access to that node.
     */
    public void deleteMiddleNode(E data) {
        if (size < 3) {
            return;
        }
        Node<E> runnerNode = head.getNext();
        Node<E> prevNode = head;
        while (runnerNode.getNext() != null) {
            if (runnerNode.getData().equals(data)) {
                prevNode.setNext(runnerNode.getNext());
                size--;
            }
            prevNode = runnerNode;
            runnerNode = runnerNode.getNext();
        }
    }

    /*
     * 2.4 Partition: Write code to partition a linked list around a value x, such that all nodes less than x come
     * before all nodes greater than or equal to x. If x is contained within the list, the values of x only need
     * to be after the elements less than x (see below). The partition element x can appear anywhere in the
     * "right partition"; it does not need to appear between the left and right partitions.
     */
    public MyLinkedList<Integer> partition(Integer data) {
        if (!(head.getData() instanceof Integer)) {
            throw new IllegalStateException("Partitioning is only allowed for data of type 'Integer'");
        }
        final MyLinkedList<Integer> partitionedList = new MyLinkedList<>();
        Node<E> runnerNode = head;

        while (runnerNode.getNext() != null) {
            if (((Integer)runnerNode.getData()) < data) {
                partitionedList.add((Integer)runnerNode.getData());
            }
            runnerNode = runnerNode.getNext();
        }
        if (((Integer)runnerNode.getData()) < data) {
            partitionedList.add((Integer)runnerNode.getData());
        }

        runnerNode = head;
        while (runnerNode.getNext() != null) {
            if (((Integer)runnerNode.getData()) >= data) {
                partitionedList.add((Integer)runnerNode.getData());
            }
            runnerNode = runnerNode.getNext();
        }
        if (((Integer)runnerNode.getData()) >= data) {
            partitionedList.add((Integer)runnerNode.getData());
        }
        return partitionedList;
    }

    /*
     * 2.5.a Sum Lists: You have two numbers represented by a linked list, where each node contains a single
     * digit. The digits are stored in reverse order, such that the Vs digit is at the head of the list. Write a
     * function that adds the two numbers and returns the sum as a linked list.
     */
    public Integer sumListsReverse() {
        if (head == null || !(head.getData() instanceof Integer) || size < 3) {
            throw new IllegalStateException("'sumListsReverse' requires an initialized list with data type 'Integer'");
        }

        Node<E> runnerNode = head;
        var pivot = size/2;
        var counter = 0;
        var firstSummand = 0;
        var secondSummand = 0;
        var calculateFirstSummand = true;

        while (runnerNode.getNext() != null) {
            if (counter >= pivot && calculateFirstSummand) {
                calculateFirstSummand = false;
                counter = 0;
            }
            if (calculateFirstSummand) {
                firstSummand += (Integer)runnerNode.getData() * Math.pow(10, counter);
            } else {
                secondSummand += (Integer)runnerNode.getData() * Math.pow(10, counter);
            }
            counter++;
            runnerNode = runnerNode.getNext();
        }
        secondSummand += (Integer)runnerNode.getData() * Math.pow(10, counter);

        return firstSummand + secondSummand;
    }

    /*
     * 2.5.b Sum Lists Forward: Suppose the digits are stored in forward order. Repeat the above problem.
     */
    public Integer sumListsForward() {
        if (head == null || !(head.getData() instanceof Integer) || size < 3) {
            throw new IllegalStateException("'sumListsReverse' requires an initialized list with data type 'Integer'");
        }

        Node<E> runnerNode = head;
        var pivot = size/2;
        var counter = 0;
        var firstSummand = 0;
        var powerFirstSummand = pivot - 1;
        var secondSummand = 0;
        var powerSecondSummand = size - pivot - 1;
        var calculateFirstSummand = true;

        while (runnerNode.getNext() != null) {
            if (counter >= pivot && calculateFirstSummand) {
                calculateFirstSummand = false;
                counter = 0;
            }
            if (calculateFirstSummand) {
                firstSummand += (Integer)runnerNode.getData() * Math.pow(10, powerFirstSummand);
                powerFirstSummand--;
            } else {
                secondSummand += (Integer)runnerNode.getData() * Math.pow(10, powerSecondSummand);
                powerSecondSummand--;
            }
            runnerNode = runnerNode.getNext();
            counter++;
        }
        secondSummand += (Integer)runnerNode.getData() * Math.pow(10, powerSecondSummand);

        return firstSummand + secondSummand;
    }

    /*
     * 2.6 Palindrome: Implement a function to check if a linked list is a palindrome.
     */
    public boolean isPalindrome() {
        Node<E> runnerNode = head;
        var bufferList = new MyLinkedList<>();
        var midPoint = size/2;
        var counter = 0;
        var reverseCounter = -1;
        int ignorableIndex = getNodeIndexToIgnore();

        while (runnerNode.getNext() != null) {
            if (counter == ignorableIndex) {
                runnerNode = runnerNode.getNext();
                counter++;
                continue;
            } else if (counter < midPoint) {
                bufferList.add(runnerNode.getData());
                reverseCounter++;
            } else {
                if (!bufferList.get(reverseCounter).equals(runnerNode.getData())) {
                    return false;
                }
                reverseCounter--;
            }
            runnerNode = runnerNode.getNext();
            counter++;
        }
        if (!bufferList.get(reverseCounter).equals(runnerNode.getData())) {
            return false;
        }
        return true;
    }

    private int getNodeIndexToIgnore() {
        if (size%2 == 0) {
            return -1;
        } else {
            return size/2;
        }
    }

    /*
     * 2.7 Intersection; Given two (singly) linked lists, determine if the two lists intersect. Return the intersecting
     * node. Note that the intersection is defined based on reference, not value. That is, if the kth node of the
     * first linked list is the exact same node (by reference) as the jth node of the second linked list, then they
     * are intersecting.
     */
    public Optional<Node<E>> intersect(MyLinkedList<E> other) {
        if (head == null || other.head == null) {
            throw new IllegalStateException("'intersect' method requires both lists to be compared to be initialized");
        }

        Node<E> firstRunner = head;
        Node<E> secondRunner = other.head;
        while (firstRunner.getNext() != null) {
            while (secondRunner.getNext() != null) {
                if (nodeDataEquals(firstRunner, secondRunner)) return Optional.of(new Node<E>(firstRunner.getData()));
                secondRunner = secondRunner.getNext();
            }
            if (nodeDataEquals(firstRunner, secondRunner)) return Optional.of(new Node<E>(firstRunner.getData()));
            firstRunner = firstRunner.getNext();
            if (nodeDataEquals(firstRunner, secondRunner)) return Optional.of(new Node<E>(firstRunner.getData()));
            secondRunner = other.head;
        }
        return Optional.empty();
    }

    private boolean nodeDataEquals(Node<E> firstRunner, Node<E> secondRunner) {
        return firstRunner.getData().equals(secondRunner.getData());
    }

    /*
     * 2.8 Loop Detection: Given a circular linked list, implement an algorithm that returns the node at the
     * beginning of the loop.
     */
    public Node<E> isCircularList() {
        if (head == null || head.getNext() == null) {
            return null;
        }

        Node<E> firstRunner = head;
        Node<E> secondRunner = head.getNext();
        var counter = 0;

        while (firstRunner.getNext() != null) {
            while (secondRunner.getNext() != null && counter < size) {
                if (firstRunner == secondRunner) {
                    return firstRunner;
                }
                counter++;
                secondRunner = secondRunner.getNext();
            }
            firstRunner = firstRunner.getNext();
            counter = 0;
            secondRunner = firstRunner.getNext();
        }
        return null;
    }

    /*
     * Helper method for task 2.8 to invalidate the list by making it circular.
     */
    void makeListCircular(int index) {
        if (head == null) {
            return;
        }
        Node<E> runnerNode = head;
        var counter = 0;
        Node<E> referenceNode = null;
        while (runnerNode.getNext() != null) {
            if (counter == index) {
                referenceNode = runnerNode;
            }
            runnerNode = runnerNode.getNext();
            counter++;
        }
        if (counter == index) {
            referenceNode = runnerNode;
        }
        runnerNode.setNext(referenceNode);
    }

    /*
     * Helper method for task 2.8 to get the Node by given index.
     */
    Node<E> getNode(int index) {
        if (index == 0) {
            return head != null ? head : null;
        }
        var counter = 0;
        Node<E> n = head;
        Node<E> result = null;
        do {
            counter++;
            n = n.getNext();
            if (counter == index) {
                result = n;
                break;
            }
        } while (n.getNext() != null);
        return result;
    }
}

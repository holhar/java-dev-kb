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

    }
    public void add(E data) {
        return;
    }

    public E get(int index) {
        return null;
    }

    public void delete(int index) {
        return;
    }

    /*
     * 2.1 Remove Dups: Write code to remove duplicates from an unsorted linked list.
     * How would you solve this problem if a temporary buffer is not allowed?
     */
    public void removeDuplicates() {
        return;
    }

    private boolean compareAndRemoveDataIfEquals(Node<E> firstRunner, Node<E> secondRunner, Node<E> prev) {
        return true;
    }

    /*
     * 2.2 Return Kth to Last: Implement an algorithm to find the kth to last element of a singly linked list.
     */
    public E getKthToLast(int index) {
        return null;
    }

    /*
     * 2.3 Delete Middle Node: Implement an algorithm to delete a node in the middle (i.e., any node but the first and
     * last node, not necessarily the exact middle) of a singly linked list, given only access to that node.
     */
    public void deleteMiddleNode(E data) {
        return;
    }

    /*
     * 2.4 Partition: Write code to partition a linked list around a value x, such that all nodes less than x come
     * before all nodes greater than or equal to x. If x is contained within the list, the values of x only need
     * to be after the elements less than x (see below). The partition element x can appear anywhere in the
     * "right partition"; it does not need to appear between the left and right partitions.
     */
    public MyLinkedList<Integer> partition(Integer data) {
        return null;
    }

    /*
     * 2.5.a Sum Lists: You have two numbers represented by a linked list, where each node contains a single
     * digit. The digits are stored in reverse order, such that the Vs digit is at the head of the list. Write a
     * function that adds the two numbers and returns the sum as a linked list.
     */
    public Integer sumListsReverse() {
        return null;
    }

    /*
     * 2.5.b Sum Lists Forward: Suppose the digits are stored in forward order. Repeat the above problem.
     */
    public Integer sumListsForward() {
        return null;
    }

    /*
     * 2.6 Palindrome: Implement a function to check if a linked list is a palindrome.
     */
    public boolean isPalindrome() {
        return false;
    }

    /*
     * 2.7 Intersection; Given two (singly) linked lists, determine if the two lists intersect. Return the intersecting
     * node. Note that the intersection is defined based on reference, not value. That is, if the kth node of the
     * first linked list is the exact same node (by reference) as the jth node of the second linked list, then they
     * are intersecting.
     */
    public Optional<Node<E>> intersect(MyLinkedList<E> other) {
        return Optional.empty();
    }

    /*
     * 2.8 Loop Detection: Given a circular linked list, implement an algorithm that returns the node at the
     * beginning of the loop.
     */
    public Node<E> isCircularList() {
        return null;
    }

    /*
     * Helper method for task 2.8 to invalidate the list by making it circular.
     */
    void makeListCircular(int index) {
        return;
    }

    /*
     * Helper method for task 2.8 to get the Node by given index.
     */
    Node<E> getNode(int index) {
        return null;
    }
}

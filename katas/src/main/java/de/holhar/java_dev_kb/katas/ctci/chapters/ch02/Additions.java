package de.holhar.java_dev_kb.katas.ctci.chapters.ch02;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Additions {

    private Additions() {}

    /**
     * Removing duplicates of a certain value for the JDK LinkedList implementation.
     */
    public static List<Integer> removeDuplicates(List<Integer> list, int data) {
        boolean foundFirst = false;
        Iterator<Integer> it = list.iterator();

        while (it.hasNext()) {
            if (!foundFirst && it.next() == data) {
                foundFirst = true;
            }
            if (foundFirst && it.next() == data) {
                it.remove();
            }
        }
        return list;
    }
}
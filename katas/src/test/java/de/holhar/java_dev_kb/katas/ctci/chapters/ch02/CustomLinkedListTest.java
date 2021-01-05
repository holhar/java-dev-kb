package de.holhar.java_dev_kb.katas.ctci.chapters.ch02;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class CustomLinkedListTest {

    CustomLinkedList<Integer> list;

    @Before
    public void setup() {
        list = new CustomLinkedList<>();
        list.add(100);
        list.add(200);
        list.add(300);
        list.add(55);
        list.add(268);
    }

    @After
    public void tearDown() {
        list = null;
    }

    @Test
    public void testRemoveDuplicates() {
        LinkedList<Integer> input = new LinkedList<Integer>(Arrays.asList(1, 4, 6, 5, 2, 6, 87, 3, 4, 2, 4, 87, 9));
        LinkedList<Integer> output = new LinkedList<Integer>(Arrays.asList(1, 4, 6, 5, 2, 6, 87, 3, 2, 87, 9));
        assertEquals(output, Additions.removeDuplicates(input, 4));
    }

    @Test
    public void testremoveDuplicates() {
        // given the list from setup

        // when the list is extended in the following way
        list.add(200);
        list.add(200);
        list.add(55);
        list.add(100);

        // and when the duplicates are removed
        list.removeDuplicates();

        // then
        assertEquals(268, (int) list.get(4));
    }

    @Test
    public void testGetKthToLastElement() {
        // given the list from setup
        // when
        int kthToLastElement = list.getKthToLastElement(2);
        // then
        assertEquals(55, kthToLastElement);

        // when
        kthToLastElement = list.getKthToLastElement(3);
        // then
        assertEquals(300, kthToLastElement);

        // when
        kthToLastElement = list.getKthToLastElement(5);
        // then
        assertEquals(100, kthToLastElement);
    }

    @Test
    public void testDeleteNode() {
        // given the list from setup
        // when
        list.deleteNode(new Integer(300));
        // then
        assertEquals(100, (int) list.get(0));
        assertEquals(200, (int) list.get(1));
        assertEquals(55, (int) list.get(2));
        assertEquals(268, (int) list.get(3));
    }

    @Test
    @Ignore
    public void testPartition() {
        // given the list from setup
        // when the list is extended in the following way
        list.add(20);
        list.add(500);
        list.add(150);

        // TODO: Implement partition(...)
        // and when the list ist partitioned with 200 as pivot
        // list.partition(200);

        // then
        assertEquals(100, (int) list.get(0));
        assertEquals(55, (int) list.get(1));
        assertEquals(20, (int) list.get(2));
        assertEquals(150, (int) list.get(3));
        assertEquals(200, (int) list.get(4));
        assertEquals(300, (int) list.get(5));
        assertEquals(268, (int) list.get(6));
        assertEquals(500, (int) list.get(7));
    }
}

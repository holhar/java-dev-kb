package de.holhar.java_dev_kb.katas.ctci.chapters.ch02;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class MyLinkedListTest {

    private MyLinkedList<Integer> list;

    @Before
    public void setup() {
        list = new MyLinkedList<>();
        list.add(100);
        list.add(200);
        list.add(300);
        list.add(55);
        list.add(268);
        assertEquals(5, list.size());
    }

    @After
    public void tearDown() {
        list = null;
    }

    @Test
    public void get() {
        Integer actual = list.get(0);
        assertEquals(Integer.valueOf(100), actual);
        actual = list.get(3);
        assertEquals(Integer.valueOf(55), actual);
        actual = list.get(4);
        assertEquals(Integer.valueOf(268), actual);
    }

    @Test
    public void getNull() {
        final MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        final Integer actual = myLinkedList.get(0);
        assertNull(actual);
    }

    @Test
    public void delete() {
        list.delete(3);
        final Integer actual = list.get(3);
        assertEquals(Integer.valueOf(268), actual);
        assertEquals(4, list.size());
    }

    @Test
    public void removeDuplicates() {
    }

    @Test
    public void getKthToLast() {
    }

    @Test
    public void deleteMiddleNode_firstElementNotDeleted() {
    }

    @Test
    public void deleteMiddleNode_middleElement1() {
    }

    @Test
    public void deleteMiddleNode_middleElement2() {
    }

    @Test
    public void deleteMiddleNode_lastElementNotDeleted() {
    }

    @Test
    public void partition() {
    }

    @Test
    public void sumListsReverse1() {
    }

    @Test
    public void sumListsReverse2() {
    }

    @Test
    public void sumListsForward1() {
    }

    @Test
    public void sumListsForward2() {
    }

    @Test
    public void isPalindrome_isEven_isTrue() {
    }

    @Test
    public void isPalindrome_isOdd_isTrue() {
    }

    @Test
    public void isPalindrome_isEven_isFalse() {
    }

    @Test
    public void isPalindrome_isOdd_isFalse() {
    }

    @Test
    public void intersect_doIntersectAtBeginning() {
    }

    @Test
    public void intersect_doIntersectAtEnd() {
    }

    @Test
    public void intersect_doIntersectAtMiddle() {
    }

    @Test
    public void intersect_doNotIntersect() {
    }

    @Test
    public void isCircularList_isLooping1() {
    }

    @Test
    public void isCircularList_isLooping2() {
    }

    @Test
    public void isCircularList_isLooping3() {
    }

    @Test
    public void isCircularList_isNotLooping() {
    }
}

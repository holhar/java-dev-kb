package de.holhar.java_dev_kb.katas.ctci.chapters.ch02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyLinkedListTest {

    private MyLinkedList<Integer> list;

    @BeforeEach
    public void setup() {
        list = new MyLinkedList<>();
        list.add(100);
        list.add(200);
        list.add(300);
        list.add(55);
        list.add(268);
        assertEquals(5, list.size());
    }

    @AfterEach
    public void tearDown() {
        list = null;
    }

    @Test
    void get() {
        Integer actual = list.get(0);
        assertEquals(Integer.valueOf(100), actual);
        actual = list.get(3);
        assertEquals(Integer.valueOf(55), actual);
        actual = list.get(4);
        assertEquals(Integer.valueOf(268), actual);
    }

    @Test
    void getNull() {
        final MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        final Integer actual = myLinkedList.get(0);
        assertNull(actual);
    }

    @Test
    void delete() {
        list.delete(3);
        final Integer actual = list.get(3);
        assertEquals(Integer.valueOf(268), actual);
        assertEquals(4, list.size());
    }

    @Test
    void removeDuplicates() {
    }

    @Test
    void getKthToLast() {
    }

    @Test
    void deleteMiddleNode_firstElementNotDeleted() {
    }

    @Test
    void deleteMiddleNode_middleElement1() {
    }

    @Test
    void deleteMiddleNode_middleElement2() {
    }

    @Test
    void deleteMiddleNode_lastElementNotDeleted() {
    }

    @Test
    void partition() {
    }

    @Test
    void sumListsReverse1() {
    }

    @Test
    void sumListsReverse2() {
    }

    @Test
    void sumListsForward1() {
    }

    @Test
    void sumListsForward2() {
    }

    @Test
    void isPalindrome_isEven_isTrue() {
    }

    @Test
    void isPalindrome_isOdd_isTrue() {
    }

    @Test
    void isPalindrome_isEven_isFalse() {
    }

    @Test
    void isPalindrome_isOdd_isFalse() {
    }

    @Test
    void intersect_doIntersectAtBeginning() {
    }

    @Test
    void intersect_doIntersectAtEnd() {
    }

    @Test
    void intersect_doIntersectAtMiddle() {
    }

    @Test
    void intersect_doNotIntersect() {
    }

    @Test
    void isCircularList_isLooping1() {
    }

    @Test
    void isCircularList_isLooping2() {
    }

    @Test
    void isCircularList_isLooping3() {
    }

    @Test
    void isCircularList_isNotLooping() {
    }
}

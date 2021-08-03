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
        // given the list from setup

        // when the list is extended in the following way
        //list.add(100);
        //list.add(200);
        //list.add(300);
        //list.add(55);
        //list.add(268);
        list.add(200);
        list.add(200);
        list.add(55);
        list.add(100);
        assertEquals(9, list.size());

        // and when the duplicates are removed
        list.removeDuplicates();

        // then
        assertEquals(5, list.size());
        assertEquals(268, (int) list.get(4));
    }

    @Test
    public void getKthToLast() {
        // given the list from setup
        // when
        int kthToLastElement = list.getKthToLast(2);
        // then
        assertEquals(55, kthToLastElement);

        // when
        kthToLastElement = list.getKthToLast(3);
        // then
        assertEquals(300, kthToLastElement);

        // when
        kthToLastElement = list.getKthToLast(5);
        // then
        assertEquals(100, kthToLastElement);
    }

    @Test
    public void deleteMiddleNode_firstElementNotDeleted() {
        list.deleteMiddleNode(100);
        assertEquals(5, list.size());
        assertEquals(Integer.valueOf(100), list.get(0));
    }

    @Test
    public void deleteMiddleNode_middleElement1() {
        list.deleteMiddleNode(200);
        assertEquals(4, list.size());
        assertEquals(Integer.valueOf(300), list.get(1));
    }

    @Test
    public void deleteMiddleNode_middleElement2() {
        list.deleteMiddleNode(55);
        assertEquals(4, list.size());
        assertEquals(Integer.valueOf(268), list.get(3));
    }

    @Test
    public void deleteMiddleNode_lastElementNotDeleted() {
        list.deleteMiddleNode(268);
        assertEquals(5, list.size());
        assertEquals(Integer.valueOf(268), list.get(4));
    }

    @Test
    public void partition() {
        // given the list from setup
        // when the list is extended in the following way
        //list.add(100);
        //list.add(200);
        //list.add(300);
        //list.add(55);
        //list.add(268);
        list.add(20);
        list.add(500);
        list.add(150);

        // and when the list ist partitioned with 200 as pivot
        MyLinkedList<Integer> partitionedList = list.partition(200);

        // then
        assertEquals(8, partitionedList.size());
        assertEquals(100, (int) partitionedList.get(0));
        assertEquals(55, (int) partitionedList.get(1));
        assertEquals(20, (int) partitionedList.get(2));
        assertEquals(150, (int) partitionedList.get(3));
        assertEquals(200, (int) partitionedList.get(4));
        assertEquals(300, (int) partitionedList.get(5));
        assertEquals(268, (int) partitionedList.get(6));
        assertEquals(500, (int) partitionedList.get(7));
    }

    @Test
    public void sumListsReverse1() {
        // Given
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(7);
        list.add(1);
        list.add(6);
        list.add(5);
        list.add(9);
        list.add(2);

        // When
        Integer sum = list.sumListsReverse();

        // Then
        assertEquals(Integer.valueOf(912), sum);
    }

    @Test
    public void sumListsReverse2() {
        // Given
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(7);
        list.add(1);
        list.add(6);
        list.add(9);
        list.add(5);
        list.add(9);
        list.add(2);

        // When
        Integer sum = list.sumListsReverse();

        // Then
        assertEquals(Integer.valueOf(3576), sum);
    }

    @Test
    public void sumListsForward1() {
        // Given
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(6);
        list.add(1);
        list.add(7);
        list.add(2);
        list.add(9);
        list.add(5);

        // When
        Integer sum = list.sumListsForward();

        // Then
        assertEquals(Integer.valueOf(912), sum);
    }

    @Test
    public void sumListsForward2() {
        // Given
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(6);
        list.add(1);
        list.add(7);
        list.add(2);
        list.add(9);
        list.add(5);
        list.add(9);

        // When
        Integer sum = list.sumListsForward();

        // Then
        assertEquals(Integer.valueOf(3576), sum);
    }

    @Test
    public void isPalindrome_isEven_isTrue() {
        // Given
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("c");
        list.add("b");
        list.add("a");

        // When
        final boolean actual = list.isPalindrome();

        // Then
        assertTrue(actual);
    }

    @Test
    public void isPalindrome_isOdd_isTrue() {
        // Given
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("c");
        list.add("b");
        list.add("a");

        // When
        final boolean actual = list.isPalindrome();

        // Then
        assertTrue(actual);
    }

    @Test
    public void isPalindrome_isEven_isFalse() {
        // Given
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("c");
        list.add("b");
        list.add("d");

        // When
        final boolean actual = list.isPalindrome();

        // Then
        assertFalse(actual);
    }

    @Test
    public void isPalindrome_isOdd_isFalse() {
        // Given
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("c");
        list.add("b");
        list.add("d");

        // When
        final boolean actual = list.isPalindrome();

        // Then
        assertFalse(actual);
    }

    @Test
    public void intersect_doIntersectAtBeginning() {
        // Given
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        MyLinkedList<String> other = new MyLinkedList<>();
        other.add("a");
        other.add("g");
        other.add("h");
        other.add("i");
        other.add("j");

        // When
        final Optional<MyLinkedList.Node<String>> intersect = list.intersect(other);

        // Then
        assertTrue(intersect.isPresent());
        assertEquals(new MyLinkedList.Node<String>("a").getData(), intersect.get().getData());
    }

    @Test
    public void intersect_doIntersectAtEnd() {
        // Given
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        MyLinkedList<String> other = new MyLinkedList<>();
        other.add("g");
        other.add("h");
        other.add("i");
        other.add("j");
        other.add("f");

        // When
        final Optional<MyLinkedList.Node<String>> intersect = list.intersect(other);

        // Then
        assertTrue(intersect.isPresent());
        assertEquals(new MyLinkedList.Node<String>("f").getData(), intersect.get().getData());
    }

    @Test
    public void intersect_doIntersectAtMiddle() {
        // Given
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        MyLinkedList<String> other = new MyLinkedList<>();
        other.add("g");
        other.add("h");
        other.add("d");
        other.add("i");
        other.add("j");

        // When
        final Optional<MyLinkedList.Node<String>> intersect = list.intersect(other);

        // Then
        assertTrue(intersect.isPresent());
        assertEquals(new MyLinkedList.Node<String>("d").getData(), intersect.get().getData());
    }

    @Test
    public void intersect_doNotIntersect() {
        // Given
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        MyLinkedList<String> other = new MyLinkedList<>();
        other.add("g");
        other.add("h");
        other.add("i");
        other.add("j");
        other.add("k");

        // When
        final Optional<MyLinkedList.Node<String>> intersect = list.intersect(other);

        // Then
        assertFalse(intersect.isPresent());
    }

    @Test
    public void isCircularList_isLooping1() {
        list.makeListCircular(0);
        MyLinkedList.Node<Integer> actual = list.isCircularList();
        assertEquals(list.getNode(0), actual);
    }

    @Test
    public void isCircularList_isLooping2() {
        list.makeListCircular(2);
        MyLinkedList.Node<Integer> actual = list.isCircularList();
        assertEquals(list.getNode(2), actual);
    }

    @Test
    public void isCircularList_isLooping3() {
        list.makeListCircular(4);
        MyLinkedList.Node<Integer> actual = list.isCircularList();
        assertEquals(list.getNode(4), actual);
    }

    @Test
    public void isCircularList_isNotLooping() {
        assertNull(list.isCircularList());
    }
}

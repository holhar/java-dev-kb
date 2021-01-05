package de.holhar.java_dev_kb.katas.codewars.oop;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PaginationHelperTest {

    private PaginationHelper<String> paginationHelper;

    @Before
    public void init() {
        ArrayList<String> items = setUpArrayList();
        paginationHelper = new PaginationHelper<String>(items, 3);
    }

    private ArrayList<String> setUpArrayList() {
        ArrayList<String> items = new ArrayList<>();
        items.add("bla1");
        items.add("bla2");
        items.add("bla3");
        items.add("bla4");
        items.add("bla5");
        items.add("bla6");
        items.add("bla7");
        items.add("bla8");
        return items;
    }

    @Test
    public void testItemCount() {
        assertEquals(8, paginationHelper.itemCount());
    }

    @Test
    public void testPageCount() {
        assertEquals(3, paginationHelper.pageCount());
    }

    @Test
    public void testPageItemCount_fullPage() {
        assertEquals(3, paginationHelper.pageItemCount(1));
    }

    @Test
    public void testPageItemCount_lastPage() {
        assertEquals(2, paginationHelper.pageItemCount(2));
    }

    @Test
    public void testPageItemCount_notExistingPage() {
        assertEquals(-1, paginationHelper.pageItemCount(3));
    }

    @Test
    public void testPageIndex_elementInMiddle() {
        assertEquals(1, paginationHelper.pageIndex(4));
    }

    @Test
    public void testPageIndex_elementAtEnd() {
        assertEquals(2, paginationHelper.pageIndex(7));
    }

    @Test
    public void testPageIndex_elementNotPresent() {
        assertEquals(-1, paginationHelper.pageIndex(8));
    }
}

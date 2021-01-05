package de.holhar.java_dev_kb.katas.codewars.fundamentals.strings;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CatalogTest {

    private static String rows;

    @Before
    public void setup() {
        rows = setupRows();
    }

    @Test
    public void testCatalog_standardSearch() {
        String expected = "table saw > prx: $1099.99 qty: 5\nsaw > prx: $9 qty: 10";
        String actual = Catalog.catalog(rows, "saw");
        assertEquals(expected, actual);
    }

    @Test
    public void testCatalog_noResult() {
        String expected = "Nothing";
        String actual = Catalog.catalog(rows, "adsf");
        assertEquals(expected, actual);
    }

    private String setupRows() {
        return "<prod><name>drill</name><prx>99</prx><qty>5</qty></prod>\n" +
                "\n" +
                "<prod><name>hammer</name><prx>10</prx><qty>50</qty></prod>\n" +
                "\n" +
                "<prod><name>screwdriver</name><prx>5</prx><qty>51</qty></prod>\n" +
                "\n" +
                "<prod><name>table saw</name><prx>1099.99</prx><qty>5</qty></prod>\n" +
                "\n" +
                "<prod><name>saw</name><prx>9</prx><qty>10</qty></prod>";
    }

}
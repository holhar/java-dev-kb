package de.holhar.java_dev_kb.katas.ctci.chapters.ch03;

import org.junit.Test;

public class MyStackArrayTest {

    @Test
    public void basicMyStackArrayOperations() {
        MyStackArray<String> myStackArray = new MyStackArray<>();

        myStackArray.addToFirstStack("first1");
        myStackArray.addToFirstStack("first2");
        myStackArray.addToFirstStack("first3");
        myStackArray.addToFirstStack("first4");

        myStackArray.addToSecondStack("second1");
        myStackArray.addToSecondStack("second2");
        myStackArray.addToSecondStack("second3");

        myStackArray.addToThirdStack("third1");
        myStackArray.addToThirdStack("third2");
        myStackArray.addToThirdStack("third3");
    }
}
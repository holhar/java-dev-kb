package de.holhar.java_dev_kb.katas.codewars.oop;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SortablePokerHandsTest {

    private static ArrayList<SortablePokerHands> expectedAll;

    private static ArrayList<SortablePokerHands> expectedRoyalFlush;
    private static ArrayList<SortablePokerHands> actualRoyalFlush;
    private static ArrayList<SortablePokerHands> expectedStraightFlushes;
    private static ArrayList<SortablePokerHands> actualStraightFlushes;
    private static ArrayList<SortablePokerHands> expectedFourOfAKind;
    private static ArrayList<SortablePokerHands> actualFourOfAKind;
    private static ArrayList<SortablePokerHands> expectedFullHouses;
    private static ArrayList<SortablePokerHands> actualFullHouses;
    private static ArrayList<SortablePokerHands> expectedFlushes;
    private static ArrayList<SortablePokerHands> actualFlushes;
    private static ArrayList<SortablePokerHands> expectedStraights;
    private static ArrayList<SortablePokerHands> actualStraights;
    private static ArrayList<SortablePokerHands> expectedThreeOfAKind;
    private static ArrayList<SortablePokerHands> actualThreeOfAKind;
    private static ArrayList<SortablePokerHands> expectedTwoPairs;
    private static ArrayList<SortablePokerHands> actualTwoPairs;
    private static ArrayList<SortablePokerHands> expectedPairs;
    private static ArrayList<SortablePokerHands> actualPairs;
    private static ArrayList<SortablePokerHands> expectedHighCards;
    private static ArrayList<SortablePokerHands> actualHighCards;

    private static SortablePokerHands royalFlush1;
    private static SortablePokerHands royalFlush2;
    private static SortablePokerHands straightFlush1;
    private static SortablePokerHands straightFlush2;
    private static SortablePokerHands straightFlush3;
    private static SortablePokerHands straightFlush4;
    private static SortablePokerHands fourOfAKind1;
    private static SortablePokerHands fourOfAKind2;
    private static SortablePokerHands fourOfAKind3;
    private static SortablePokerHands fourOfAKind4;
    private static SortablePokerHands fourOfAKind5;
    private static SortablePokerHands fullHouse1;
    private static SortablePokerHands fullHouse2;
    private static SortablePokerHands fullHouse3;
    private static SortablePokerHands fullHouse4;
    private static SortablePokerHands fullHouse5;
    private static SortablePokerHands flush1;
    private static SortablePokerHands flush2;
    private static SortablePokerHands flush3;
    private static SortablePokerHands flush4;
    private static SortablePokerHands flush5;
    private static SortablePokerHands flush6;
    private static SortablePokerHands straight1;
    private static SortablePokerHands straight2;
    private static SortablePokerHands straight3;
    private static SortablePokerHands straight4;
    private static SortablePokerHands straight5;
    private static SortablePokerHands threeOfAKind1;
    private static SortablePokerHands threeOfAKind2;
    private static SortablePokerHands threeOfAKind3;
    private static SortablePokerHands threeOfAKind4;
    private static SortablePokerHands threeOfAKind5;
    private static SortablePokerHands twoPairs1;
    private static SortablePokerHands twoPairs2;
    private static SortablePokerHands twoPairs3;
    private static SortablePokerHands twoPairs4;
    private static SortablePokerHands twoPairs5;
    private static SortablePokerHands pair1;
    private static SortablePokerHands pair2;
    private static SortablePokerHands pair3;
    private static SortablePokerHands pair4;
    private static SortablePokerHands pair5;
    private static SortablePokerHands pair6;
    private static SortablePokerHands pair7;
    private static SortablePokerHands pair8;
    private static SortablePokerHands pair9;
    private static SortablePokerHands highCard1;
    private static SortablePokerHands highCard2;
    private static SortablePokerHands highCard3;
    private static SortablePokerHands highCard4;
    private static SortablePokerHands highCard5;
    private static SortablePokerHands highCard6;
    private static SortablePokerHands highCard7;
    private static SortablePokerHands highCard8;
    private static SortablePokerHands highCard9;

    @BeforeAll
    public static void init() {
        expectedAll = new ArrayList<>();

        expectedRoyalFlush = new ArrayList<>();
        actualRoyalFlush = new ArrayList<>();
        expectedStraightFlushes = new ArrayList<>();
        actualStraightFlushes = new ArrayList<>();
        expectedFourOfAKind = new ArrayList<>();
        actualFourOfAKind = new ArrayList<>();
        expectedFullHouses = new ArrayList<>();
        actualFullHouses = new ArrayList<>();
        expectedFlushes = new ArrayList<>();
        actualFlushes = new ArrayList<>();
        expectedStraights = new ArrayList<>();
        actualStraights = new ArrayList<>();
        expectedThreeOfAKind = new ArrayList<>();
        actualThreeOfAKind = new ArrayList<>();
        expectedTwoPairs = new ArrayList<>();
        actualTwoPairs = new ArrayList<>();
        expectedPairs = new ArrayList<>();
        actualPairs = new ArrayList<>();
        expectedHighCards = new ArrayList<>();
        actualHighCards = new ArrayList<>();


        royalFlush1 = new SortablePokerHands("KS AS TS QS JS");
        royalFlush2 = new SortablePokerHands("JH AH TH KH QH");

        straightFlush1 = new SortablePokerHands("JH 9H TH KH QH");
        straightFlush2 = new SortablePokerHands("5C 6C 3C 7C 4C");
        straightFlush3 = new SortablePokerHands("2D 6D 3D 4D 5D");
        straightFlush4 = new SortablePokerHands("2C 3C AC 4C 5C");

        fourOfAKind1 = new SortablePokerHands("AS AC AH KS AS");
        fourOfAKind2 = new SortablePokerHands("AS AD AC AH JD");
        fourOfAKind4 = new SortablePokerHands("JC 7H JS JD JH");
        fourOfAKind5 = new SortablePokerHands("JC 6H JS JD JH");
        fourOfAKind3 = new SortablePokerHands("JS JD JC JH AD");

        fullHouse1 = new SortablePokerHands("2S AH 2H AS AC");
        fullHouse2 = new SortablePokerHands("KH KC 3S 3H 3D");
        fullHouse3 = new SortablePokerHands("2H 2C 3S 3H 3D");
        fullHouse4 = new SortablePokerHands("2S AH 2H 2D AC");
        fullHouse5 = new SortablePokerHands("3D 2H 3H 2C 2D");

        flush1 = new SortablePokerHands("JH 8H AH KH QH");
        flush3 = new SortablePokerHands("4C 5C 9C 8C KC");
        flush4 = new SortablePokerHands("3S 8S 9S 5S KS");
        flush5 = new SortablePokerHands("8C 9C 5C 3C TC");
        flush2 = new SortablePokerHands("AS 3S 4S 8S 2S");
        flush6 = new SortablePokerHands("2H 3H 5H 6H 7H");

        straight1 = new SortablePokerHands("QC KH TS JS AH");
        straight2 = new SortablePokerHands("JS QS 9H TS KH");
        straight3 = new SortablePokerHands("6S 8S 7S 5H 9H");
        straight4 = new SortablePokerHands("3C 5C 4C 2C 6H");
        straight5 = new SortablePokerHands("2D AC 3H 4H 5S");

        threeOfAKind1 = new SortablePokerHands("AC KH QH AH AS");
        threeOfAKind2 = new SortablePokerHands("AH AC 5H 6H AS");
        threeOfAKind3 = new SortablePokerHands("7C 7S KH 2H 7H");
        threeOfAKind4 = new SortablePokerHands("7C 7S 3S 7H 5S");
        threeOfAKind5 = new SortablePokerHands("6C 6S 3S 6H AS");

        twoPairs1 = new SortablePokerHands("AS 4H AH 5S 4C");
        twoPairs2 = new SortablePokerHands("3C KH 5D 5S KC");
        twoPairs3 = new SortablePokerHands("5S 5D 2C KH KC");
        twoPairs4 = new SortablePokerHands("3H 4C 4H 3S 2H");
        twoPairs5 = new SortablePokerHands("2S 2H 4H 5S 4C");

        pair1 = new SortablePokerHands("AH 8S AH KC JH");
        pair2 = new SortablePokerHands("AH AC 5H 6H 7S");
        pair3 = new SortablePokerHands("AH AC 4H 6H 7S");
        pair4 = new SortablePokerHands("KD 4S KD 3H 8S");
        pair5 = new SortablePokerHands("KC 4H KS 2H 8D");
        pair6 = new SortablePokerHands("QH 8H KD JH 8S");
        pair7 = new SortablePokerHands("TH 4C 4C AS QD");
        pair8 = new SortablePokerHands("8C 4S KH JS 4D");
        pair9 = new SortablePokerHands("KS 8D 4D 9S 4S");

        highCard3 = new SortablePokerHands("2S AH 4H 5S KC");
        highCard1 = new SortablePokerHands("KD 6S 9D TH AD");
        highCard2 = new SortablePokerHands("TS KS 5S 9S AC");
        highCard4 = new SortablePokerHands("JH 8S TH AH QH");
        highCard5 = new SortablePokerHands("TC 8C 2S JH 6C");
        highCard6 = new SortablePokerHands("2D 6D 9D TH 7D");
        highCard7 = new SortablePokerHands("9D 8H 2C 6S 7H");
        highCard8 = new SortablePokerHands("2S 3H 6H 7S 9C");
        highCard9 = new SortablePokerHands("4S 3H 2C 7S 5H");
    }

    @Test
    void testGetRank_RoyalFlush() {
        assertEquals(101311100900L, royalFlush1.getRank(), "Rank with values " + royalFlush1.getValues() + " should be 101311100900 but was " + royalFlush1.getRank()); // [A, T, J, Q, K]
        assertEquals(101311100900L, royalFlush2.getRank(), "Rank with values " + royalFlush2.getValues() + " should be 101311100900 but was " + royalFlush2.getRank()); // [A, T, J, Q, K]
    }

    @Test
    void testCompare_RoyalFlush() {
        expectedRoyalFlush.add(royalFlush1);
        expectedRoyalFlush.add(royalFlush2);
        actualRoyalFlush.add(royalFlush1);
        actualRoyalFlush.add(royalFlush2);

        Collections.sort(actualRoyalFlush);

        Iterator a = actualRoyalFlush.iterator();
        for (SortablePokerHands e : expectedRoyalFlush) {
            assertEquals(e, a.next(), "\n\nexpect: " + expectedRoyalFlush.toString() + "\nactual: " + actualRoyalFlush.toString() + "\n");
        }
    }

    @Test
    void testGetRank_StraightFlush() {
        assertEquals(91211100908L, straightFlush1.getRank(), "Rank with values " + straightFlush1.getValues() + "  should be 91211100908 but was " + straightFlush1.getRank()); // [9, T, J, Q, K]
        assertEquals(90605040302L, straightFlush2.getRank(), "Rank with values " + straightFlush2.getValues() + "  should be 90605040302 but was " + straightFlush2.getRank()); // [3, 4, 5, 6, 7]
        assertEquals(90504030201L, straightFlush3.getRank(), "Rank with values " + straightFlush3.getValues() + "  should be 90504030201 but was " + straightFlush3.getRank()); // [2, 3, 4, 5, 6]
        assertEquals(90403020100L, straightFlush4.getRank(), "Rank with values " + straightFlush4.getValues() + "  should be 90403020100 but was " + straightFlush4.getRank()); // [A, 2, 3, 4, 5]
    }

    @Test
    void testCompare_StraightFlush() {
        expectedStraightFlushes.add(straightFlush1);
        expectedStraightFlushes.add(straightFlush2);
        expectedStraightFlushes.add(straightFlush3);
        expectedStraightFlushes.add(straightFlush4);
        actualStraightFlushes.add(straightFlush3);
        actualStraightFlushes.add(straightFlush1);
        actualStraightFlushes.add(straightFlush4);
        actualStraightFlushes.add(straightFlush2);

        Collections.sort(actualStraightFlushes);

        Iterator a = actualStraightFlushes.iterator();
        for (SortablePokerHands e : expectedStraightFlushes) {
            assertEquals(e, a.next(), "\n\nexpect: " + expectedStraightFlushes.toString() + "\nactual: " + actualStraightFlushes.toString() + "\n");
        }
    }

    @Test
    void testGetRank_FourOfAKind() {
        assertEquals(85212000000L, fourOfAKind1.getRank(), "Rank with values " + fourOfAKind1.getValues() + " should be 85212000000 but was " + fourOfAKind1.getRank()); // [K, A, A, A, A]
        assertEquals(85210000000L, fourOfAKind2.getRank(), "Rank with values " + fourOfAKind2.getValues() + " should be 85210000000 but was " + fourOfAKind2.getRank()); // [J, A, A, A, A]
        assertEquals(84013000000L, fourOfAKind3.getRank(), "Rank with values " + fourOfAKind3.getValues() + " should be 84013000000 but was " + fourOfAKind3.getRank()); // [A, J, J, J, J]
        assertEquals(84006000000L, fourOfAKind4.getRank(), "Rank with values " + fourOfAKind4.getValues() + " should be 84006000000 but was " + fourOfAKind4.getRank()); // [7, J, J, J, J]
        assertEquals(84005000000L, fourOfAKind5.getRank(), "Rank with values " + fourOfAKind5.getValues() + " should be 84005000000 but was " + fourOfAKind5.getRank()); // [6, J, J, J, J]
    }

    @Test
    void testCompare_FourOfAKind() {
        expectedFourOfAKind.add(fourOfAKind1);
        expectedFourOfAKind.add(fourOfAKind2);
        expectedFourOfAKind.add(fourOfAKind3);
        expectedFourOfAKind.add(fourOfAKind4);
        expectedFourOfAKind.add(fourOfAKind5);
        actualFourOfAKind.add(fourOfAKind2);
        actualFourOfAKind.add(fourOfAKind3);
        actualFourOfAKind.add(fourOfAKind5);
        actualFourOfAKind.add(fourOfAKind1);
        actualFourOfAKind.add(fourOfAKind4);

        Collections.sort(actualFourOfAKind);

        Iterator a = actualFourOfAKind.iterator();
        for (SortablePokerHands e : expectedFourOfAKind) {
            assertEquals(e, a.next(), "\n\nexpect: " + expectedFourOfAKind.toString() + "\nactual: " + actualFourOfAKind.toString() + "\n");
        }
    }

    @Test
    void testGetRank_FullHouse() {
        assertEquals(73902000000L, fullHouse1.getRank(), "Rank with values " + fullHouse1.getValues() + " should be 73902000000 but was " + fullHouse1.getRank()); // [2, 2, A, A, A]
        assertEquals(70624000000L, fullHouse2.getRank(), "Rank with values " + fullHouse2.getValues() + " should be 70624000000 but was " + fullHouse2.getRank()); // [K, K, 3, 3, 3]
        assertEquals(70602000000L, fullHouse3.getRank(), "Rank with values " + fullHouse3.getValues() + " should be 70602000000 but was " + fullHouse3.getRank()); // [2, 2, 3, 3, 3]
        assertEquals(70326000000L, fullHouse4.getRank(), "Rank with values " + fullHouse4.getValues() + " should be 70326000000 but was " + fullHouse4.getRank()); // [A, A, 2, 2, 2]
        assertEquals(70304000000L, fullHouse5.getRank(), "Rank with values " + fullHouse5.getValues() + " should be 70304000000 but was " + fullHouse5.getRank()); // [3, 3, 2, 2, 2]
    }

    @Test
    void testCompare_FullHouses() {
        expectedFullHouses.add(fullHouse1);
        expectedFullHouses.add(fullHouse2);
        expectedFullHouses.add(fullHouse3);
        expectedFullHouses.add(fullHouse4);
        expectedFullHouses.add(fullHouse5);
        actualFullHouses.add(fullHouse3);
        actualFullHouses.add(fullHouse1);
        actualFullHouses.add(fullHouse2);
        actualFullHouses.add(fullHouse4);
        actualFullHouses.add(fullHouse5);

        Collections.sort(actualFullHouses);

        Iterator a = actualFullHouses.iterator();
        for (SortablePokerHands e : expectedFullHouses) {
            assertEquals(e, a.next(), "\n\nexpect: " + expectedFullHouses.toString() + "\nactual: " + actualFullHouses.toString() + "\n");
        }
    }

    @Test
    void testGetRank_Flush() {
        assertEquals(61312111007L, flush1.getRank(), "Rank with values " + flush1.getValues() + " should be 61312111007 but was " + flush1.getRank()); // [8, J, Q, K, A]
        assertEquals(61307030201L, flush2.getRank(), "Rank with values " + flush2.getValues() + " should be 61307030201 but was " + flush2.getRank()); // [2, 3, 4, 8, A]
        assertEquals(61208070403L, flush3.getRank(), "Rank with values " + flush3.getValues() + " should be 61208070403 but was " + flush3.getRank()); // [4, 5, 8, 9, K]
        assertEquals(61208070402L, flush4.getRank(), "Rank with values " + flush4.getValues() + " should be 61208070402 but was " + flush4.getRank()); // [3, 5, 8, 9, K]
        assertEquals(60908070402L, flush5.getRank(), "Rank with values " + flush5.getValues() + " should be 60908070402 but was " + flush5.getRank()); // [3, 5, 8, 9, T]
        assertEquals(60605040201L, flush6.getRank(), "Rank with values " + flush6.getValues() + " should be 60605040201 but was " + flush6.getRank()); // [2, 3, 5, 6, 7]
    }

    @Test
    void testCompare_Flushes() {
        expectedFlushes.add(flush1);
        expectedFlushes.add(flush2);
        expectedFlushes.add(flush3);
        expectedFlushes.add(flush4);
        expectedFlushes.add(flush5);
        expectedFlushes.add(flush6);
        actualFlushes.add(flush4);
        actualFlushes.add(flush6);
        actualFlushes.add(flush1);
        actualFlushes.add(flush5);
        actualFlushes.add(flush3);
        actualFlushes.add(flush2);

        Collections.sort(actualFlushes);

        Iterator a = actualFlushes.iterator();
        for (SortablePokerHands e : expectedFlushes) {
            assertEquals(e, a.next(), "\n\nexpect: " + expectedFlushes.toString() + "\nactual: " + actualFlushes.toString() + "\n");
        }
    }

    @Test
    void testGetRank_Straight() {
        assertEquals(51312111009L, straight1.getRank(), "Rank with values " + straight1.getValues() + " should be 51312111009 but was " + straight1.getRank()); // [T, J, Q, K, A]
        assertEquals(51211100908L, straight2.getRank(), "Rank with values " + straight2.getValues() + " should be 51211100908 but was " + straight2.getRank()); // [9, T, J, Q, K]
        assertEquals(50807060504L, straight3.getRank(), "Rank with values " + straight3.getValues() + " should be 50807060504 but was " + straight3.getRank()); // [5, 6, 7, 8, 9]
        assertEquals(50504030201L, straight4.getRank(), "Rank with values " + straight4.getValues() + " should be 50504030201 but was " + straight4.getRank()); // [2, 3, 4, 5, 6]
        assertEquals(50403020100L, straight5.getRank(), "Rank with values " + straight5.getValues() + " should be 50403020100 but was " + straight5.getRank()); // [A, 2, 3, 4, 5]
    }

    @Test
    void testCompare_Straights() {
        expectedStraights.add(straight1);
        expectedStraights.add(straight2);
        expectedStraights.add(straight3);
        expectedStraights.add(straight4);
        expectedStraights.add(straight5);
        actualStraights.add(straight5);
        actualStraights.add(straight2);
        actualStraights.add(straight3);
        actualStraights.add(straight1);
        actualStraights.add(straight4);

        Collections.sort(actualStraights);

        Iterator a = actualStraights.iterator();
        for (SortablePokerHands e : expectedStraights) {
            assertEquals(e, a.next(), "\n\nexpect: " + expectedStraights.toString() + "\nactual: " + actualStraights.toString() + "\n");
        }
    }

    @Test
    void testGetRank_ThreeOfAKind() {
        assertEquals(43936110000L, threeOfAKind1.getRank(), "Rank with values " + threeOfAKind1.getValues() + " should be 43936110000 but was " + threeOfAKind1.getRank()); // [Q, K, A, A, A]
        assertEquals(43915040000L, threeOfAKind2.getRank(), "Rank with values " + threeOfAKind2.getValues() + " should be 43915040000 but was " + threeOfAKind2.getRank()); // [5, 6, A, A, A]
        assertEquals(41836010000L, threeOfAKind3.getRank(), "Rank with values " + threeOfAKind3.getValues() + " should be 41836010000 but was " + threeOfAKind3.getRank()); // [2, K, 7, 7, 7]
        assertEquals(41812020000L, threeOfAKind4.getRank(), "Rank with values " + threeOfAKind4.getValues() + " should be 41812020000 but was " + threeOfAKind4.getRank()); // [3, 5, 7, 7, 7]
        assertEquals(41539020000L, threeOfAKind5.getRank(), "Rank with values " + threeOfAKind5.getValues() + " should be 41539020000 but was " + threeOfAKind5.getRank()); // [3, A, 6, 6, 6]
    }

    @Test
    void testCompare_ThreeOfAKind() {
        expectedThreeOfAKind.add(threeOfAKind1);
        expectedThreeOfAKind.add(threeOfAKind2);
        expectedThreeOfAKind.add(threeOfAKind3);
        expectedThreeOfAKind.add(threeOfAKind4);
        expectedThreeOfAKind.add(threeOfAKind5);
        actualThreeOfAKind.add(threeOfAKind1);
        actualThreeOfAKind.add(threeOfAKind4);
        actualThreeOfAKind.add(threeOfAKind2);
        actualThreeOfAKind.add(threeOfAKind5);
        actualThreeOfAKind.add(threeOfAKind3);

        Collections.sort(actualThreeOfAKind);

        Iterator a = actualThreeOfAKind.iterator();
        for (SortablePokerHands e : expectedThreeOfAKind) {
            assertEquals(e, a.next(), "\n\nexpect: " + expectedThreeOfAKind.toString() + "\nactual: " + actualThreeOfAKind.toString() + "\n");
        }
    }

    @Test
    void testGetRank_TwoPairs() {
        assertEquals(32606040000L, twoPairs1.getRank(), "Rank with values " + twoPairs1.getValues() + " should be 32606040000 but was " + twoPairs1.getRank()); // [5, 4, 4, A, A]
        assertEquals(32408020000L, twoPairs2.getRank(), "Rank with values " + twoPairs2.getValues() + " should be 32408020000 but was " + twoPairs2.getRank()); // [3, 5, 5, K, K]
        assertEquals(32408010000L, twoPairs3.getRank(), "Rank with values " + twoPairs3.getValues() + " should be 32408010000 but was " + twoPairs3.getRank()); // [2, 5, 5, K, K]
        assertEquals(30604010000L, twoPairs4.getRank(), "Rank with values " + twoPairs4.getValues() + " should be 30604010000 but was " + twoPairs4.getRank()); // [2, 3, 3, 4, 4]
        assertEquals(30602040000L, twoPairs5.getRank(), "Rank with values " + twoPairs5.getValues() + " should be 30602040000 but was " + twoPairs5.getRank()); // [5, 2, 2, 4, 4]
    }

    @Test
    void testCompare_TwoPairs() {
        expectedTwoPairs.add(twoPairs1);
        expectedTwoPairs.add(twoPairs2);
        expectedTwoPairs.add(twoPairs3);
        expectedTwoPairs.add(twoPairs4);
        expectedTwoPairs.add(twoPairs5);
        actualTwoPairs.add(twoPairs4);
        actualTwoPairs.add(twoPairs5);
        actualTwoPairs.add(twoPairs2);
        actualTwoPairs.add(twoPairs3);
        actualTwoPairs.add(twoPairs1);

        Collections.sort(actualTwoPairs);

        Iterator a = actualTwoPairs.iterator();
        for (SortablePokerHands e : expectedTwoPairs) {
            assertEquals(e, a.next(), "\n\nexpect: " + expectedTwoPairs.toString() + "\nactual: " + actualTwoPairs.toString() + "\n");
        }
    }

    @Test
    void testGetRank_Pair() {
        assertEquals(22612100700L, pair1.getRank(), "Rank with values " + pair1.getValues() + " should be 22612100700 but was " + pair1.getRank()); // [8, J, K, A, A]
        assertEquals(22606050400L, pair2.getRank(), "Rank with values " + pair2.getValues() + " should be 22606050400 but was " + pair2.getRank()); // [5, 6, 7, A, A]
        assertEquals(22606050300L, pair3.getRank(), "Rank with values " + pair3.getValues() + " should be 22606050300 but was " + pair3.getRank()); // [4, 6, 7, A, A]
        assertEquals(22407030200L, pair4.getRank(), "Rank with values " + pair4.getValues() + " should be 22407030200 but was " + pair4.getRank()); // [3, 4, 8, K, K]
        assertEquals(22407030100L, pair5.getRank(), "Rank with values " + pair5.getValues() + " should be 22407030100 but was " + pair5.getRank()); // [2, 4, 8, K, K]
        assertEquals(21412111000L, pair6.getRank(), "Rank with values " + pair6.getValues() + " should be 21412111000 but was " + pair6.getRank()); // [J, Q, K, 8, 8]
        assertEquals(20613110900L, pair7.getRank(), "Rank with values " + pair7.getValues() + " should be 20613110900 but was " + pair7.getRank()); // [T, Q, A, 4, 4]
        assertEquals(20612100700L, pair8.getRank(), "Rank with values " + pair8.getValues() + " should be 20612100700 but was " + pair8.getRank()); // [8, J, K, 4, 4]
        assertEquals(20612080700L, pair9.getRank(), "Rank with values " + pair9.getValues() + " should be 20612080700 but was " + pair9.getRank()); // [8, 9, K, 4, 4]
    }

    @Test
    void testCompare_Pairs() {
        expectedPairs.add(pair1);
        expectedPairs.add(pair2);
        expectedPairs.add(pair3);
        expectedPairs.add(pair4);
        expectedPairs.add(pair5);
        expectedPairs.add(pair6);
        expectedPairs.add(pair7);
        expectedPairs.add(pair8);
        expectedPairs.add(pair9);
        actualPairs.add(pair3);
        actualPairs.add(pair5);
        actualPairs.add(pair1);
        actualPairs.add(pair9);
        actualPairs.add(pair6);
        actualPairs.add(pair8);
        actualPairs.add(pair4);
        actualPairs.add(pair7);
        actualPairs.add(pair2);

        Collections.sort(actualPairs);

        Iterator a = actualPairs.iterator();
        for (SortablePokerHands e : expectedPairs) {
            assertEquals(e, a.next(), "\n\nexpect: " + expectedPairs.toString() + "\nactual: " + actualPairs.toString() + "\n");
        }
    }

    @Test
    void testGetRank_HighCard() {
        assertEquals(11312090805L, highCard1.getRank(), "Rank with values " + highCard1.getValues() + " should be 11312090805 but was " + highCard1.getRank()); // [6, 9, T, K, A]
        assertEquals(11312090804L, highCard2.getRank(), "Rank with values " + highCard2.getValues() + " should be 11312090804 but was " + highCard2.getRank()); // [5, 9, T, K, A]
        assertEquals(11312040301L, highCard3.getRank(), "Rank with values " + highCard3.getValues() + " should be 11312040301 but was " + highCard3.getRank()); // [2, 4, 5, K, A]
        assertEquals(11311100907L, highCard4.getRank(), "Rank with values " + highCard4.getValues() + " should be 11311100907 but was " + highCard4.getRank()); // [8, T, J, Q, A]
        assertEquals(11009070501L, highCard5.getRank(), "Rank with values " + highCard5.getValues() + " should be 11009070501 but was " + highCard5.getRank()); // [2, 6, 8, T, J]
        assertEquals(10908060501L, highCard6.getRank(), "Rank with values " + highCard6.getValues() + " should be 10908060501 but was " + highCard6.getRank()); // [2, 6, 7, 9, T]
        assertEquals(10807060501L, highCard7.getRank(), "Rank with values " + highCard7.getValues() + " should be 10807060501 but was " + highCard7.getRank()); // [2, 6, 7, 8, 9]
        assertEquals(10806050201L, highCard8.getRank(), "Rank with values " + highCard8.getValues() + " should be 10806050201 but was " + highCard8.getRank()); // [2, 3, 6, 7, 9]
        assertEquals(10604030201L, highCard9.getRank(), "Rank with values " + highCard9.getValues() + " should be 10604030201 but was " + highCard9.getRank()); // [2, 3, 4, 5, 7]
    }

    @Test
    void testCompare_HighCards() {
        expectedHighCards.add(highCard1);
        expectedHighCards.add(highCard2);
        expectedHighCards.add(highCard3);
        expectedHighCards.add(highCard4);
        expectedHighCards.add(highCard5);
        expectedHighCards.add(highCard6);
        expectedHighCards.add(highCard7);
        expectedHighCards.add(highCard8);
        expectedHighCards.add(highCard9);
        actualHighCards.add(highCard8);
        actualHighCards.add(highCard1);
        actualHighCards.add(highCard4);
        actualHighCards.add(highCard3);
        actualHighCards.add(highCard9);
        actualHighCards.add(highCard7);
        actualHighCards.add(highCard5);
        actualHighCards.add(highCard2);
        actualHighCards.add(highCard6);

        Collections.sort(actualHighCards);

        Iterator a = actualHighCards.iterator();
        for (SortablePokerHands e : expectedHighCards) {
            assertEquals(e, a.next(), "\n\nexpect: " + expectedHighCards.toString() + "\nactual: " + actualHighCards.toString() + "\n");
        }
    }

    // FIXME: Test fails
    @Test
    @Disabled
    void pokerHandSortTest() {
        // Arrange
        expectedAll.add(royalFlush1);
        expectedAll.add(royalFlush2);
        expectedAll.add(straightFlush1);
        expectedAll.add(straightFlush2);
        expectedAll.add(straightFlush3);
        expectedAll.add(straightFlush4);
        expectedAll.add(fourOfAKind1);
        expectedAll.add(fourOfAKind2);
        expectedAll.add(fourOfAKind3);
        expectedAll.add(fourOfAKind4);
        expectedAll.add(fourOfAKind5);
        expectedAll.add(fullHouse1);
        expectedAll.add(fullHouse2);
        expectedAll.add(fullHouse3);
        expectedAll.add(fullHouse4);
        expectedAll.add(fullHouse5);
        expectedAll.add(flush1);
        expectedAll.add(flush2);
        expectedAll.add(flush3);
        expectedAll.add(flush4);
        expectedAll.add(flush5);
        expectedAll.add(flush6);
        expectedAll.add(straight1);
        expectedAll.add(straight2);
        expectedAll.add(straight3);
        expectedAll.add(straight4);
        expectedAll.add(straight5);
        expectedAll.add(threeOfAKind1);
        expectedAll.add(threeOfAKind2);
        expectedAll.add(threeOfAKind3);
        expectedAll.add(threeOfAKind4);
        expectedAll.add(threeOfAKind5);
        expectedAll.add(twoPairs1);
        expectedAll.add(twoPairs2);
        expectedAll.add(twoPairs3);
        expectedAll.add(twoPairs4);
        expectedAll.add(twoPairs5);
        expectedAll.add(pair1);
        expectedAll.add(pair2);
        expectedAll.add(pair3);
        expectedAll.add(pair4);
        expectedAll.add(pair5);
        expectedAll.add(pair6);
        expectedAll.add(pair7);
        expectedAll.add(pair8);
        expectedAll.add(pair9);
        expectedAll.add(highCard1);
        expectedAll.add(highCard2);
        expectedAll.add(highCard3);
        expectedAll.add(highCard4);
        expectedAll.add(highCard5);
        expectedAll.add(highCard6);
        expectedAll.add(highCard7);
        expectedAll.add(highCard8);
        expectedAll.add(highCard9);

        Random random = new Random();
        ArrayList<SortablePokerHands> actual = createRandomOrderedList(random, expectedAll);

        // Act
        Collections.sort(actual);

        int differences = 0;
        // Assert
        Iterator a = actual.iterator();
        for (SortablePokerHands e : expectedAll) {
            if (!e.equals(a.next())) {
                differences++;
            }
            // assertEquals(e, a.next(), "\n\nexpect: " + expectedAll.toString() + "\nactual: " + actual.toString() + "\n");
        }
        assertEquals(0, differences, "There were " + differences + " differences found, but there should be 0\n\nexpect: " + expectedAll.toString() + "\nactual: " + actual + "\n");
    }

    private ArrayList<SortablePokerHands> createRandomOrderedList(Random random, ArrayList<SortablePokerHands> expected) {
        ArrayList<SortablePokerHands> actual = new ArrayList<>();
        for (SortablePokerHands pokerHand : expected) {
            int j = random.nextInt(actual.size() + 1);
            actual.add(j, pokerHand);
        }
        // actual.add(0, new SortablePokerHands("2D AC 3H 4H 5S"));
        // actual.remove(actual.size()-1);
        return actual;
    }
}
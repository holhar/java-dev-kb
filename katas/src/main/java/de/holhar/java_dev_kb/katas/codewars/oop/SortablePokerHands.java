package de.holhar.java_dev_kb.katas.codewars.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Sortable Poker Hands (https://www.codewars.com/kata/586423aa39c5abfcec0001e6)
 * <p>
 * A famous casino is suddenly faced with a sharp decline of their revenues. They decide to offer Texas hold'em also
 * online. Can you help them by writing an algorithm that can rank poker hands?
 * <p>
 * Task:
 * <p>
 * Create a poker hand that has a constructor that accepts a string containing 5 cards:
 * <p>
 * PokerHand hand = new PokerHand("KS 2H 5C JD TD");
 * <p>
 * The characteristics of the string of cards are:
 * A space is used as card seperator
 * Each card consists of two characters
 * The first character is the value of the card, valid characters are:
 * `2, 3, 4, 5, 6, 7, 8, 9, T(en), J(ack), Q(ueen), K(ing), A(ce)`
 * The second character represents the suit, valid characters are:
 * `S(pades), H(earts), D(iamonds), C(lubs)`
 * <p>
 * The poker hands must be sortable by rank, the highest rank first:
 * <p>
 * ArrayList<PokerHand> hands = new ArrayList<PokerHand>();
 * hands.add(new PokerHand("KS 2H 5C JD TD"));
 * hands.add(new PokerHand("2C 3C AC 4C 5C"));
 * Collections.sort(hands);
 * <p>
 * Apply the Texas Hold'em rules for ranking the cards.
 * There is no ranking for the suits.
 * An ace can either rank high or rank low in a straight or straight flush. Example of a straight with a low ace:
 * `"5C 4D 3C 2S AS"`
 * <p>
 * <p>
 * Note: there are around 25000 random tests, so keep an eye on performances.
 * <p>
 * TODO refactor me!
 */
public class SortablePokerHands implements Comparable<SortablePokerHands> {

    private static final Pattern HAND_PATTERN = Pattern.compile("\\w\\w\\s\\w\\w\\s\\w\\w\\s\\w\\w\\s\\w\\w");

    private static final List<Character> cardValueOrder = Arrays.asList(
            CardValue.ACE.value,
            CardValue.TWO.value,
            CardValue.THREE.value,
            CardValue.FOUR.value,
            CardValue.FIVE.value,
            CardValue.SIX.value,
            CardValue.SEVEN.value,
            CardValue.EIGHT.value,
            CardValue.NINE.value,
            CardValue.TEN.value,
            CardValue.JACK.value,
            CardValue.QUEEN.value,
            CardValue.KING.value,
            CardValue.ACE.value
    );

    private static final List<Character> highStraightOrder = Arrays.asList(
            CardValue.TEN.value,
            CardValue.JACK.value,
            CardValue.QUEEN.value,
            CardValue.KING.value
    );

    private static final long FACTOR_EIGHT = 100_000_000L;
    private static final long FACTOR_SIX = 1_000_000L;
    private static final long FACTOR_FOUR = 10_000L;
    private static final long FACTOR_TWO = 100L;

    private String hand;
    private long rank;
    private List<Character> values;
    private List<Character> suits;

    public SortablePokerHands(String hand) {
        if (HAND_PATTERN.matcher(hand).matches()) {
            this.hand = hand;
            init();
        } else {
            throw new IllegalArgumentException("invalid hand given");
        }
    }

    private void init() {
        this.values = new ArrayList<>();
        this.suits = new ArrayList<>();
        getValuesAndSuits();
        sortValues();
        calculateRank();
    }

    @Override
    public int compareTo(SortablePokerHands otherHand) {
        if (this.getRank() < otherHand.getRank()) {
            return 1;
        }
        if (this.getRank() > otherHand.getRank()) {
            return -1;
        }
        return 0;
    }

    private void calculateRank() {
        if (isRoyalFlush()) {
            addScore(Rank.ROYAL_FLUSH.value);
            addScore(CardValue.ACE.score * FACTOR_EIGHT);
            addScore(getPlainCardValue(values.get(3)) * FACTOR_SIX);
            addScore(getPlainCardValue(values.get(2)) * FACTOR_FOUR);
            addScore(getPlainCardValue(values.get(1)) * FACTOR_TWO);
            addScore(getPlainCardValue(values.get(0)));
        } else if (isStraightFlush()) {
            addScore(Rank.STRAIGHT_FLUSH.value);
            addScore(getPlainCardValue(values.get(4)) * FACTOR_EIGHT);
            addScore(getPlainCardValue(values.get(3)) * FACTOR_SIX);
            addScore(getPlainCardValue(values.get(2)) * FACTOR_FOUR);
            addScore(getPlainCardValue(values.get(1)) * FACTOR_TWO);
            addScore(getPlainCardValue(values.get(0)));
        } else if (isFourOfAKind()) {
            addScore(Rank.FOUR_OF_A_KIND.value);
            sortFourOfAKind();

            if (values.get(1) == CardValue.ACE.value) {
                addScore(CardValue.ACE.score * FACTOR_EIGHT * 4);
            } else {
                addScore(getPlainCardValue(values.get(1)) * FACTOR_EIGHT * 4);
            }

            if (values.get(0) == CardValue.ACE.value) {
                addScore(CardValue.ACE.score * FACTOR_SIX);
            } else {
                addScore(getPlainCardValue(values.get(0)) * FACTOR_SIX);
            }
        } else if (isFullHouse()) {
            addScore(Rank.FULL_HOUSE.value);
            sortFullHouse();

            if (values.get(2) == CardValue.ACE.value) {
                addScore(CardValue.ACE.score * FACTOR_EIGHT * 3);
            } else {
                addScore(getPlainCardValue(values.get(2)) * FACTOR_EIGHT * 3);
            }

            if (values.get(0) == CardValue.ACE.value) {
                addScore(CardValue.ACE.score * FACTOR_SIX * 2);
            } else {
                addScore(getPlainCardValue(values.get(0)) * FACTOR_SIX * 2);
            }
        } else if (isFlush()) {
            addScore(Rank.FLUSH.value);
            replaceAceIfInHand(4);

            if (values.get(4) == CardValue.ACE.value) {
                addScore(CardValue.ACE.score * FACTOR_EIGHT);
            } else {
                addScore(getPlainCardValue(values.get(4)) * FACTOR_EIGHT);
            }
            addScore(getPlainCardValue(values.get(3)) * FACTOR_SIX);
            addScore(getPlainCardValue(values.get(2)) * FACTOR_FOUR);
            addScore(getPlainCardValue(values.get(1)) * FACTOR_TWO);
            addScore(getPlainCardValue(values.get(0)));
        } else if (isStraight()) {
            addScore(Rank.STRAIGHT.value);

            if (values.get(4) == CardValue.ACE.value
                    && values.get(3) == CardValue.KING.value) {
                addScore(CardValue.ACE.score * FACTOR_EIGHT);
            } else {
                addScore(getPlainCardValue(values.get(4)) * FACTOR_EIGHT);
            }

            addScore(getPlainCardValue(values.get(3)) * FACTOR_SIX);
            addScore(getPlainCardValue(values.get(2)) * FACTOR_FOUR);
            addScore(getPlainCardValue(values.get(1)) * FACTOR_TWO);
            addScore(getPlainCardValue(values.get(0)));
        } else if (isThreeOfAKind()) {
            addScore(Rank.THREE_OF_A_KIND.value);
            sortThreeOfAKind();
            replaceAceIfInHand(1);

            if (values.get(2) == CardValue.ACE.value) {
                addScore(CardValue.ACE.score * FACTOR_EIGHT * 3);
            } else {
                addScore(getPlainCardValue(values.get(2)) * FACTOR_EIGHT * 3);
            }

            if (values.get(1) == CardValue.ACE.value) {
                addScore(CardValue.ACE.score * FACTOR_SIX * 3);
            } else {
                addScore(getPlainCardValue(values.get(1)) * FACTOR_SIX * 3);
            }

            addScore(getPlainCardValue(values.get(0)) * FACTOR_FOUR);
        } else if (isTwoPairs()) {
            addScore(Rank.TWO_PAIRS.value);
            sortTwoPairs();

            if (values.get(3) == CardValue.ACE.value) {
                addScore(CardValue.ACE.score * FACTOR_EIGHT * 2);
            } else {
                addScore(getPlainCardValue(values.get(3)) * FACTOR_EIGHT * 2);
            }

            addScore(getPlainCardValue(values.get(1)) * FACTOR_SIX * 2);

            if (values.get(0) == CardValue.ACE.value) {
                addScore(CardValue.ACE.score * FACTOR_FOUR);
            } else {
                addScore(getPlainCardValue(values.get(0)) * FACTOR_FOUR);
            }
        } else if (isPair()) {
            addScore(Rank.PAIR.value);
            sortPair();
            replaceAceIfInHand(2);

            if (values.get(3) == CardValue.ACE.value) {
                addScore(CardValue.ACE.score * FACTOR_EIGHT * 2);
            } else {
                addScore(getPlainCardValue(values.get(3)) * FACTOR_EIGHT * 2);
            }

            if (values.get(2) == CardValue.ACE.value) {
                addScore(CardValue.ACE.score * FACTOR_SIX);
            } else {
                addScore(getPlainCardValue(values.get(2)) * FACTOR_SIX);
            }

            addScore(getPlainCardValue(values.get(1)) * FACTOR_FOUR);
            addScore(getPlainCardValue(values.get(0)) * FACTOR_TWO);
        } else {
            addScore(Rank.HIGH_CARD.value);
            replaceAceIfInHand(4);
            if (values.get(4) == CardValue.ACE.value) {
                addScore(CardValue.ACE.score * FACTOR_EIGHT);
            } else {
                addScore(getPlainCardValue(values.get(4)) * FACTOR_EIGHT);
            }
            addScore(getPlainCardValue(values.get(3)) * FACTOR_SIX);
            addScore(getPlainCardValue(values.get(2)) * FACTOR_FOUR);
            addScore(getPlainCardValue(values.get(1)) * FACTOR_TWO);
            addScore(getPlainCardValue(values.get(0)));
        }
    }

    private int getPlainCardValue(Character cardValue) {
        return cardValueOrder.indexOf(cardValue);
    }

    private boolean isRoyalFlush() {
        return suits.stream().allMatch(suits.get(0)::equals)
                && values.contains(CardValue.ACE.value)
                && values.contains(CardValue.KING.value)
                && values.contains(CardValue.QUEEN.value)
                && values.contains(CardValue.JACK.value)
                && values.contains(CardValue.TEN.value);
    }

    private boolean isStraightFlush() {
        boolean isStraight = checkIfStraight();
        return suits.stream().allMatch(suits.get(0)::equals)
                && isStraight;
    }

    private boolean isFourOfAKind() {
        return getMaxCount() == 4;
    }

    private boolean isFullHouse() {
        Map<Character, Integer> valMap = getValMap();
        return valMap.size() == 2;
    }

    private boolean isFlush() {
        return suits.stream().allMatch(suits.get(0)::equals);
    }

    private boolean isStraight() {
        return checkIfStraight();
    }

    private boolean isThreeOfAKind() {
        return getMaxCount() == 3;
    }

    private boolean isTwoPairs() {
        Map<Character, Integer> valMap = getValMap();
        int count = 0;
        for (Map.Entry<Character, Integer> entry : valMap.entrySet()) {
            if (entry.getValue() == 2) {
                count++;
            }
        }
        return count == 2;
    }

    private boolean isPair() {
        return getMaxCount() == 2;
    }

    private void getValuesAndSuits() {
        List<String> cards = Arrays.asList(hand.split(" "));
        for (String card : cards) {
            char[] vals = card.toCharArray();
            values.add(vals[0]);
            suits.add(vals[1]);
        }
    }

    private void sortValues() {
        values.sort((o1, o2) -> Integer.compare(cardValueOrder.indexOf(o1), cardValueOrder.indexOf(o2)));
    }

    private void sortPair() {
        Character pairVal = null;
        Character prev = null;
        Character now;

        Iterator<Character> it = values.iterator();
        while (it.hasNext()) {
            now = it.next();
            if (now.equals(prev)) {
                pairVal = now;
                break;
            }
            prev = now;
        }

        values.remove(pairVal);
        values.remove(pairVal);
        values.add(pairVal);
        values.add(pairVal);
    }

    private void sortTwoPairs() {
        Character pairValFirst = null;
        Character pairValSecond = null;
        Character prev = null;
        Character current;

        ListIterator<Character> it = values.listIterator();
        while (it.hasNext()) {
            current = it.next();
            if (current.equals(prev)) {
                if (pairValFirst == null) {
                    pairValFirst = current;
                } else {
                    pairValSecond = current;
                    break;
                }
            }
            prev = current;
        }


        if (pairValFirst < pairValSecond) {
            reorderElements(pairValFirst, pairValSecond);
        } else {
            reorderElements(pairValSecond, pairValFirst);
        }
    }

    private void reorderElements(Character first, Character second) {
        values.remove(first);
        values.remove(first);
        values.add(first);
        values.add(first);
        values.remove(second);
        values.remove(second);
        values.add(second);
        values.add(second);
    }

    private void sortThreeOfAKind() {
        Character tripletVal = null;
        Character prev = null;
        Character current;

        ListIterator<Character> it = values.listIterator();
        while (it.hasNext()) {
            current = it.next();
            if (current.equals(prev) && current.equals(it.next())) {
                tripletVal = current;
                break;
            }
            prev = current;
        }

        values.remove(tripletVal);
        values.remove(tripletVal);
        values.remove(tripletVal);
        values.add(tripletVal);
        values.add(tripletVal);
        values.add(tripletVal);
    }

    private void sortFullHouse() {
        Character tripleVal = null;
        Character prev = null;
        Character next = null;
        Character current;

        ListIterator<Character> it = values.listIterator();
        while (it.hasNext()) {
            if (it.hasPrevious())
                prev = it.previous();
            current = it.next();
            if (it.hasNext()) {
                next = it.next();
            }

            if (current.equals(prev) && current.equals(next)) {
                tripleVal = current;
                break;
            }

            prev = current;
        }

        values.remove(tripleVal);
        values.remove(tripleVal);
        values.remove(tripleVal);
        values.add(tripleVal);
        values.add(tripleVal);
        values.add(tripleVal);
    }

    private void sortFourOfAKind() {
        Character quadVal = null;
        Character current;

        ListIterator<Character> it = values.listIterator();
        while (it.hasNext()) {
            current = it.next();
            if (current.equals(it.next())) {
                quadVal = current;
                break;
            }
        }
        values.remove(quadVal);
        values.remove(quadVal);
        values.remove(quadVal);
        values.remove(quadVal);
        values.add(quadVal);
        values.add(quadVal);
        values.add(quadVal);
        values.add(quadVal);

    }

    private void replaceAceIfInHand(int targetPosition) {
        if (values.indexOf(CardValue.ACE.value) == 0) {
            values.remove(0);
            values.add(targetPosition, CardValue.ACE.value);
        }
    }

    private int getMaxCount() {
        int max = 0;
        Map<Character, Integer> valMap = getValMap();
        for (Map.Entry<Character, Integer> entry : valMap.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
            }
        }
        return max;
    }

    private Map<Character, Integer> getValMap() {
        Map<Character, Integer> valCount = new LinkedHashMap<>();
        for (Character val : values) {
            if (valCount.containsKey(val)) {
                valCount.put(val, valCount.get(val) + 1);
            } else {
                valCount.put(val, 1);
            }
        }
        return valCount;
    }

    private boolean checkIfStraight() {
        if (Collections.indexOfSubList(values, highStraightOrder) != -1
                && values.contains(CardValue.ACE.value)
                && values.indexOf(CardValue.ACE.value) == 0) {
            values.remove(0);
            values.add(CardValue.ACE.value);
        }
        return Collections.indexOfSubList(cardValueOrder, values) != -1;
    }

    String getValues() {
        return values.toString();
    }

    long getRank() {
        return rank;
    }

    private void addScore(long score) {
        this.rank += score;
    }

    @Override
    public String toString() {
        return "SortablePokerHands{" +
                "hand='" + hand + '\'' +
                '}';
    }

    enum Rank {

        HIGH_CARD(10_000_000_000L),
        PAIR(20_000_000_000L),
        TWO_PAIRS(30_000_000_000L),
        THREE_OF_A_KIND(40_000_000_000L),
        STRAIGHT(50_000_000_000L),
        FLUSH(60_000_000_000L),
        FULL_HOUSE(70_000_000_000L),
        FOUR_OF_A_KIND(80_000_000_000L),
        STRAIGHT_FLUSH(90_000_000_000L),
        ROYAL_FLUSH(100_000_000_000L);

        private long value;

        Rank(long v) {
            value = v;
        }
    }

    enum CardValue {
        TWO('2', 1L),
        THREE('3', 2L),
        FOUR('4', 3L),
        FIVE('5', 4L),
        SIX('6', 5L),
        SEVEN('7', 6L),
        EIGHT('8', 7L),
        NINE('9', 8L),
        TEN('T', 9L),
        JACK('J', 10L),
        QUEEN('Q', 11L),
        KING('K', 12L),
        ACE('A', 13L);

        private final char value;
        private final long score;

        CardValue(char v, long s) {
            value = v;
            score = s;
        }
    }
}

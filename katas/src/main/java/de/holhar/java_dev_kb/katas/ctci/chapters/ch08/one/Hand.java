package de.holhar.java_dev_kb.katas.ctci.chapters.ch08.one;

import java.util.ArrayList;
import java.util.List;

public class Hand<T extends Card> {

    private final List<T> cards = new ArrayList<>();

    public int score() {
        int score = 0;
        for (T card : cards) {
            score += card.value();
        }
        return score;
    }

    public void addCard(T card) {
        cards.add(card);
    }
}

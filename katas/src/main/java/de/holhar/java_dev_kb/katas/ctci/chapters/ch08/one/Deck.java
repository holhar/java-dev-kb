package de.holhar.java_dev_kb.katas.ctci.chapters.ch08.one;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck<T extends Card> {

    private int dealtIndex;
    private List<T> cards;

    public void setDeckOfCards(List<T> cards) {
        this.cards = cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public int remainingCards() {
        return cards.size() - dealtIndex;
    }

    @SuppressWarnings("unchecked")
    public T[] dealHand(int number) {
        ArrayList<T> cardsToDeal = new ArrayList<>();
        T card;
        for (int i = dealtIndex; i < number; i++) {
            card = cards.get(dealtIndex);
            cardsToDeal.add(card);
            card.markUnavailable();
            dealtIndex++;
        }
        return (T[]) cardsToDeal.toArray();
    }

    public T dealCard() {
        T card = cards.get(dealtIndex);
        card.markUnavailable();
        return card;
    }


}

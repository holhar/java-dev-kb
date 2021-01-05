package de.holhar.java_dev_kb.katas.ctci.chapters.ch08.one;

public abstract class Card {

    private boolean available = true;
    private Suit suit;
    private int faceValue;

    public Card(Suit suit, int faceValue) {
        this.suit = suit;
        this.faceValue = faceValue;
    }

    public abstract int value();

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public void markUnavailable() {
        available = false;
    }

    public int getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(int faceValue) {
        this.faceValue = faceValue;
    }

    public boolean isAvailable() {
        return available;
    }

    public void markAvailable() {
        available = true;
    }
}

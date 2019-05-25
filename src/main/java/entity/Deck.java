package main.java.entity;

import main.java.library.AssortedMethods;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Steven Shahbazi on 24/05/2019.
 */
public class Deck <T extends Card>{
    private ArrayList<T> cards; // all cards, dealt or not
    private int dealtIndex = 0; // marks first undealt card

    public void setDeckOfCards(ArrayList<T> deckOfCards) {
        cards = deckOfCards;
    }

    public void shuffle() {
        for (int i = 0; i < cards.size(); i++) {
            int rNumber = AssortedMethods.randomIntInRange(i, cards.size() - i - 1);
            T card1 = cards.get(i);
            T card2 = cards.get(rNumber);
            cards.set(i, card2);
            cards.set(rNumber, card1);
        }
    }

    public int remainingCards() {
        return cards.size() - dealtIndex;
    }

    public ArrayList<T> getRemainingCards() {
        ArrayList<T> remainingCards = new ArrayList<>();
        for (T card:cards) {
            if(card.isAvailable())
                remainingCards.add(card);
        }
        return remainingCards;
    }

    public T[] dealHand(int number) {
        if (remainingCards() < number) {
            return null;
        }

        T[] hand = (T[]) new Card[number];
        int count = 0;
        while (count < number) {
            T card = dealCard();
            if (card != null) {
                hand[count] = card;
                count++;
            }
        }

        return hand;
    }

    public T dealCard() {
        if (remainingCards() == 0) {
            return null;
        }

        T card = cards.get(dealtIndex);
        card.markUnavailable();
        dealtIndex++;

        return card;
    }
}

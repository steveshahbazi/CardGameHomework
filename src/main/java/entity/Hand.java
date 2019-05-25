package main.java.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Steven Shahbazi on 24/05/2019.
 */
public class Hand <T extends Card> {
    protected ArrayList<T> cards = new ArrayList<T>();

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

    public List<T> getCards(){
        return cards;
    }

    public void print() {
        for (Card card : cards) {
            card.print();
        }
    }
}

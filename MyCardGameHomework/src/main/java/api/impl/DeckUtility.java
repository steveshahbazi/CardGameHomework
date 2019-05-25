package main.java.api.impl;

import main.java.entity.Card;
import main.java.entity.Deck;
import main.java.entity.MyGameCard;
import main.java.entity.Suit;
import main.java.exception.NullDeckException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Steven Shahbazi on 24/05/2019.
 */
public class DeckUtility {
    public Deck buildDeck() {
        List<Card> cards = new ArrayList<>() ;
        for(int i = 1 ; i <= 13 ; i++){
            cards.addAll(buildSuits(i)) ;
        }

        Deck newDeck = new Deck() ;
        newDeck.setDeckOfCards((ArrayList) cards);
        return newDeck;
    }

    private List<Card> buildSuits(int cardNumber){
        List<Card> cards = new ArrayList<>() ;
        for(Suit suit : Suit.values()){
            Card newCard = new MyGameCard(cardNumber, suit) ;
            cards.add(newCard) ;
        }
        return cards;
    }

    public Card getCard(Deck deck) throws NullDeckException {
        if(deck == null){
            throw new IllegalArgumentException("Provided deck can not be null") ;
        }

        Card card = deck.dealCard();
        if(card == null){
            throw new NullDeckException("There is no cards left in the deck.") ;
        }
        return card;
    }
}

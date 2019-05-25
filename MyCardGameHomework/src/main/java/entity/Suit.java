package main.java.entity;

import main.java.exception.SuitNotFoundException;

/**
 * Created by Steven Shahbazi on 24/05/2019.
 */
public enum Suit {
    Club (0), Diamond (1), Heart (2), Spade (3);
    private int value;
    private Suit(int v) {value = v;}
    public int getValue() { return value; }
    public static Suit getSuitFromValue(int value) throws SuitNotFoundException {
        if (value == 0)
            return Club;
        if (value == 1)
            return Diamond;
        if (value == 2)
            return Heart;
        if (value == 3)
            return Spade;
        throw new SuitNotFoundException("Suit [" + value + "] is not a valid suit.") ;
    }
}

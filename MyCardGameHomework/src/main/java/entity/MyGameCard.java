package main.java.entity;

/**
 * Created by Steven Shahbazi on 24/05/2019.
 */
public class MyGameCard extends Card{
    public MyGameCard(int c, Suit s) {
        super(c, s);
    }

    @Override
    public int value() {
        return faceValue;
    }
}

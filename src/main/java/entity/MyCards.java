package main.java.entity;

/**
 * Created by Steven Shahbazi on 24/05/2019.
 */
public class MyCards {
    int count;
    MyGameCard card;

    public MyCards(MyGameCard card, int count){
        this.card = card;
        this.count = count;
    }

    public int getCount()
    {
        return count;
    }

    public MyGameCard getCard()
    {
        return card;
    }
}

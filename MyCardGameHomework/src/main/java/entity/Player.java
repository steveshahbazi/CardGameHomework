package main.java.entity;

/**
 * Created by Steven Shahbazi on 24/05/2019.
 */
public class Player {
    Hand hand;
    long id;

    Player(){
        hand = new Hand();
    }

    public Player(Long playerID) {
        super();
        id = playerID;
    }

    public int getScore(){
        if (hand == null)
            return 0;
        return hand.score();
    }

    public void setID(long id){
        this.id = id;
    }

    public long getID(){
        return id;
    }

    public Hand getHand(){
        return hand;
    }

    public void giveCard(Card card){
        if (hand == null)
            hand = new Hand();
        hand.addCard(card);
    }
}

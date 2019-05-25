package test.api.impl;

import main.java.api.GameManager;
import main.java.api.impl.GameManagerImpl;
import main.java.entity.Card;
import main.java.exception.*;
import org.junit.Assert;
import org.junit.Test;
import junit.framework.TestCase;

/**
 * Created by Steven Shahbazi on 25/05/2019.
 */
public class GameManagerImplTest {
    @Test
    public void createDeck(){
        GameManager gManager = GameManagerImpl.getInstance() ;
        long deckID = gManager.createDeck() ;
        Assert.assertNotNull("Error deckID can not be null.", deckID);
    }

    @Test
    public void getCard() throws DeckNotExistException, NullDeckException, DeckAlreadyExistException,
            GameNotExistException, PlayerNotExistException, PlayerAlreadyExistException {
        GameManager gManager = GameManagerImpl.getInstance();
        long gameID = gManager.createGame();
        long deckID = gManager.createDeck();
        long playerID = 12;
        gManager.addPlayer(gameID, playerID);
        gManager.addDeck(gameID, deckID);
        for(int i = 1 ; i <= 52 ; i++){
            Card card = gManager.dealCard(gameID, playerID, deckID) ;
            System.out.println("Retrieved card [" + card +"].");
            Assert.assertNotNull("A new deck must return a card", card);
        }
    }

    @Test
    public void getMoreCardsThanDeckSize() throws PlayerAlreadyExistException, GameNotExistException,
            NullDeckException, DeckNotExistException, PlayerNotExistException, DeckAlreadyExistException {
        GameManager gManager = GameManagerImpl.getInstance() ;
        long gameID = gManager.createGame();
        long deckID = gManager.createDeck();
        long playerID = 2;
        gManager.addPlayer(gameID, playerID);
        gManager.addDeck(gameID, deckID);
        for(int i = 1; i <= 53; i++){
            Card card = null;
            try {
                card = gManager.dealCard(gameID, playerID, deckID);
                Assert.assertNotNull("A new deck must return a card", card);
            } catch (DeckNotExistException | NullDeckException e) {
                Assert.assertNull("A new deck must return a card", card);
            }
        }
    }
}

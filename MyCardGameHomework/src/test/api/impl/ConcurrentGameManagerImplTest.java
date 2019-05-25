package test.api.impl;

import main.java.api.GameManager;
import main.java.api.impl.ConcurrentGameManagerImpl;
import main.java.entity.Card;
import main.java.entity.Hand;
import main.java.entity.Player;
import main.java.entity.Suit;
import main.java.exception.*;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

/**
 * Created by Saeed on 25/05/2019.
 */
public class ConcurrentGameManagerImplTest {

    @org.junit.Test
    public void testGetInstance() throws Exception {
        GameManager gManager = ConcurrentGameManagerImpl.getInstance();
        Assert.assertNotNull("The instance can not be null.");
    }

    @org.junit.Test
    public void testCreateGame() throws Exception {
        GameManager gManager = ConcurrentGameManagerImpl.getInstance();
        long gameID = gManager.createGame();
        Assert.assertNotNull("Error gameID can not be null.", gameID);
    }

    @org.junit.Test
    public void testDeleteGame() throws Exception {
        GameManager gManager = ConcurrentGameManagerImpl.getInstance();
        long gameID = gManager.createGame();
        gManager.deleteGame(gameID);
        Assert.assertNotNull("Error gameID can not be null.", gameID);
    }

    @org.junit.Test
    public void testCreateDeck() throws Exception {
        GameManager gManager = ConcurrentGameManagerImpl.getInstance();
        long deckID = gManager.createDeck();
        Assert.assertNotNull("Error deckID can not be null.", deckID);
    }

    @org.junit.Test
    public void testAddDeck() throws Exception {
        GameManager gManager = ConcurrentGameManagerImpl.getInstance();
        long deckID = gManager.createDeck();
        long gameID = gManager.createGame();
        gManager.addDeck(gameID, deckID);
        Assert.assertNotNull("Error deckID can not be null.", deckID);
    }

    @org.junit.Test
    public void testAddPlayer() throws Exception {
        GameManager gManager = ConcurrentGameManagerImpl.getInstance();
        long playerID = 10;
        long gameID = gManager.createGame();
        gManager.addPlayer(gameID, playerID);
        Assert.assertNotNull("Error playerID can not be null.", playerID);
    }

    @org.junit.Test
    public void testDeletePlayer() throws Exception {
        GameManager gManager = ConcurrentGameManagerImpl.getInstance();
        long playerID = 10;
        long gameID = gManager.createGame();
        gManager.addPlayer(gameID, playerID);
        gManager.deletePlayer(gameID, playerID);
        Assert.assertNotNull("Error playerID can not be null.", playerID);
    }

    @org.junit.Test
    public void testDealCard() throws DeckNotExistException, NullDeckException, DeckAlreadyExistException,
            GameNotExistException, PlayerNotExistException, PlayerAlreadyExistException {
        GameManager gManager = ConcurrentGameManagerImpl.getInstance();
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

    @org.junit.Test
    public void testGetCards() throws Exception {
        GameManager gManager = ConcurrentGameManagerImpl.getInstance();
        long gameID = gManager.createGame();
        long deckID = gManager.createDeck();
        long playerID = 12;
        gManager.addPlayer(gameID, playerID);
        gManager.addDeck(gameID, deckID);
        for(int i = 1 ; i <= 52 ; i++){
            Card card = gManager.dealCard(gameID, playerID, deckID) ;
            //System.out.println("Retrieved card [" + card +"].");
            Assert.assertNotNull("A new deck must return a card", card);
        }
        Hand cards = gManager.getCards(gameID, playerID);
        cards.print();
    }

    @org.junit.Test
    public void testGetPlayers() throws Exception {
        GameManager gManager = ConcurrentGameManagerImpl.getInstance();
        long gameID = gManager.createGame();
        long player1 = 1;
        long player2 = 2;
        long player3 = 3;
        long player4 = 4;
        long player5 = 5;
        long player6 = 6;
        gManager.addPlayer(gameID, player1);
        gManager.addPlayer(gameID, player2);
        gManager.addPlayer(gameID, player3);
        gManager.addPlayer(gameID, player4);
        gManager.addPlayer(gameID, player5);
        gManager.addPlayer(gameID, player6);
        List<Player> players = gManager.getPlayers(gameID);
        for (Player player: players) {
            System.out.println("Retrieved player [" + player.getID() +"].");
        }
    }

    @org.junit.Test
    public void testUndealtCardCount() throws Exception {
        GameManager gManager = ConcurrentGameManagerImpl.getInstance();
        long gameID = gManager.createGame();
        long deckID = gManager.createDeck();
        long playerID = 12;
        gManager.addPlayer(gameID, playerID);
        gManager.addDeck(gameID, deckID);
        gManager.dealCard(gameID, playerID, deckID);
        gManager.dealCard(gameID, playerID, deckID);
        gManager.dealCard(gameID, playerID, deckID);
        String[] strings = gManager.UndealtCardCount(gameID, deckID);
        System.out.println("Remaining cards: ");
        for (String str:strings) {
            System.out.println(str);
        }
    }

    @org.junit.Test
    public void testUndealtCardCount1() throws Exception {
        GameManager gManager = ConcurrentGameManagerImpl.getInstance();
        long gameID = gManager.createGame();
        long deckID = gManager.createDeck();
        long playerID = 12;
        gManager.addPlayer(gameID, playerID);
        gManager.addDeck(gameID, deckID);
        gManager.dealCard(gameID, playerID, deckID);
        gManager.dealCard(gameID, playerID, deckID);
        gManager.dealCard(gameID, playerID, deckID);
        Map<Suit, int[]> cards = gManager.UndealtCardCount(gameID);
        System.out.println("Remaining cards: ");
        for (Suit suit : cards.keySet()) {
            System.out.print("Suit: [" + suit + "] ");
            int [] cardNoArr = cards.get(suit);
            for (int i=0; i<cardNoArr.length; i++)
                System.out.print(cardNoArr[i] + ", ");
            System.out.println();
        }
    }

    @org.junit.Test
    public void testShuffle() throws Exception {
        GameManager gManager = ConcurrentGameManagerImpl.getInstance();
        long gameID = gManager.createGame();
        long deckID = gManager.createDeck();
        long playerID = 12;
        gManager.addDeck(gameID, deckID);
        gManager.shuffle(gameID, deckID);
        gManager.addPlayer(gameID, playerID);
        for(int i = 1 ; i <= 52 ; i++){
            Card card = gManager.dealCard(gameID, playerID, deckID) ;
            //System.out.println("Retrieved card [" + card +"].");
            Assert.assertNotNull("A new deck must return a card", card);
        }
        Hand cards = gManager.getCards(gameID, playerID);
        cards.print();
    }
}
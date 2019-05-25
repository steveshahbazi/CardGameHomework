package main.java.entity;

import main.java.exception.DeckAlreadyExistException;
import main.java.exception.DeckNotExistException;
import main.java.exception.PlayerAlreadyExistException;
import main.java.exception.PlayerNotExistException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Steven Shahbazi on 24/05/2019.
 */
public class Game {
    private Map<Long, Deck> decks = new ConcurrentHashMap<>();
    private long id;
    private Map<Long, Player> players = new ConcurrentHashMap<>();

    public void addPlayer(Long playerID) throws PlayerAlreadyExistException {
        if (players.containsKey(playerID)) {
            throw new PlayerAlreadyExistException("Player [" + playerID + "] is already exist.") ;
        }
        players.put(playerID, new Player(playerID));
    }

    public void addDeck(Long deckID, Deck deck) throws DeckAlreadyExistException {
        if (decks.containsKey(deckID)) {
            throw new DeckAlreadyExistException("Deck [" + deckID + "] is already in the game.") ;
        }
        decks.put(deckID, deck);
    }

    public void deletePlayer(long playerID) throws PlayerNotExistException{
        if (!players.containsKey(playerID)) {
            throw new PlayerNotExistException("Player [" + playerID + "] does not exist.") ;
        }
        players.remove(playerID);
    }

    public Deck getDeck(long deckID) throws DeckNotExistException {
        Deck deck = decks.get(deckID);
        if (deck == null) {
            throw new DeckNotExistException("Deck [" + deckID + "] does not exist in the game.") ;
        }
        return deck;
    }

    public Hand getCards(long playerID) throws PlayerNotExistException{
        if (!players.containsKey(playerID)) {
            throw new PlayerNotExistException("Player [" + playerID + "] does not exist.") ;
        }
        return players.get(playerID).getHand();
    }

    public void giveCard(long playerID, MyGameCard retrievedCard) throws PlayerNotExistException{
        Player player = players.get(playerID);
        if (player == null) {
            throw new PlayerNotExistException("Player [" + playerID + "] does not exist.") ;
        }
        player.giveCard(retrievedCard);
    }

    public Map<Long, Player> playerList() {
        return players;
    }

    public Map<Long, Deck> getAllDecks() {
        return decks;
    }
}

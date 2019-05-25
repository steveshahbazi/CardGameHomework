package main.java.api;

import main.java.entity.*;
import main.java.exception.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Steven Shahbazi on 24/05/2019.
 */
public interface GameManager {
    /**
     * Creates a game and assigns a unique identifier to it.
     * @return identifier of the game.
     */
    public long createGame();

    /**
     * Deletes a game which is specified by its ID.
     * @throws GameNotExistException if there is no game with the specified ID.
     */
    public void deleteGame(long gameID) throws GameNotExistException;

    /**
     * Creates a deck and assigns a unique identifier to it.
     * @return identifier of the deck
     */
    public long createDeck() ;

    /**
     * Adds a deck to the game specified by its ID.
     * @param gameID id of the game.
     * @param deckID id of the deck to be added to the game.
     * @return true on success and false on failure.
     * @throws GameNotExistException if there is no game with the specified ID.
     * @throws DeckAlreadyExistException if the deck is already there.
     * @throws DeckAlreadyExistException if the deck already assigned to the game.
     * @throws DeckNotExistException if the deck does not exist.
     */
    public void addDeck(long gameID, long deckID) throws GameNotExistException, DeckAlreadyExistException,
            DeckNotExistException;

    /**
     * Adds a player to the game specified by their IDs.
     * @param gameID id of the game.
     * @param playerID id of the player to be added to the game.
     * @return true on success and false on failure.
     * @throws GameNotExistException if there is no game with the specified ID.
     * @throws PlayerAlreadyExistException if the player already assigned to the game.
     */
    public void addPlayer(long gameID, long playerID) throws GameNotExistException, PlayerAlreadyExistException;

    /**
     * Remove a player from the game specified by their IDs.
     * @param gameID id of the game.
     * @param playerID id of the player to be removed from the game.
     * @return true on success and false on failure.
     * @throws GameNotExistException if there is no game with the specified ID.
     * @throws PlayerAlreadyExistException if the player already assigned to the game.
     * @throws PlayerNotExistException if the player does not exist.
     */
    public void deletePlayer(long gameID, long playerID) throws GameNotExistException, PlayerAlreadyExistException,
            PlayerNotExistException;

    /**
     * Gets a card from the deck specified by its ID (a game may have multiple decks).
     * @param gameID id of the game to be played.
     * @param deckID id of the deck to be dealt.
     * @param playerID id of the player to get a card.
     * @return A card from the deck.
     * @throws GameNotExistException if there is no game with the specified ID.
     * @throws DeckNotExistException if there is no deck with the specified ID.
     * @throws PlayerNotExistException if there is no player in the game with the specified ID.
     */
    public MyGameCard dealCard(long gameID, long playerID, long deckID) throws GameNotExistException,
            DeckNotExistException, PlayerNotExistException, NullDeckException;

    /**
     * Returns all cards already dealt to a player.
     * @param gameID id of the game being played by the player.
     * @param playerID id of the player to get the list of his/her cards.
     * @return List of the player cards.
     * @throws GameNotExistException if there is no game with the specified ID.
     * @throws PlayerNotExistException if there is no player in the game with the specified ID.
     */
    public Hand getCards(long gameID, long playerID) throws GameNotExistException, PlayerNotExistException;

    /**
     * Gets a sorted list of players playing a game based on their score.
     * @param gameID id of the game being played by the player.
     * @return List of the player cards.
     * @throws GameNotExistException if there is no game with the specified ID.
     */
    public List<Player> getPlayers(long gameID) throws GameNotExistException;

    /**
     * Return number of undealt cards in a single game deck.
     * @param gameID id of the game.
     * @param deckID id of the deck.
     * @return List of the number of undealt in different groups by their suit.
     * @throws GameNotExistException if there is no game with the specified ID.
     * @throws DeckNotExistException if there is no deck with the specified ID.
     */
    public String[] UndealtCardCount(long gameID, long deckID) throws GameNotExistException,
            DeckNotExistException;

    /**
     * Get the count of each card (suit and value) remaining in the game deck sorted by suit ( hearts, spades, clubs,
     * and diamonds) and face value from high value to low value (King, Queen, Jack, 10….2, Ace with value of 1)
     * @param gameID id of the game.
     * @return a sorted List of the number of undealt in different groups by their suit and value.
     * @throws GameNotExistException if there is no game with the specified ID.
     */
    public Map<Suit, int[]> UndealtCardCount(long gameID) throws GameNotExistException;

    /**
     * Shuffles a game deck.
     * @param gameID id of the game.
     * @param deckID, identifier of the deck to be shuffled.
     * @throws GameNotExistException If game does not exist.
     * @throws DeckNotExistException If deck does not exist.
     */
    public void shuffle(long gameID, long deckID) throws GameNotExistException, DeckNotExistException;
}

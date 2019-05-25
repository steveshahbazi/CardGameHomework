package main.java.api.impl;

import main.java.api.GameManager;
import main.java.entity.*;
import main.java.entity.Game;
import main.java.exception.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Steven Shahbazi on 25/05/2019.
 */
public class ConcurrentGameManagerImpl implements GameManager {
    private Map<Long, Deck> decks;
    private Map<Long, Game> games;
    private static ConcurrentGameManagerImpl gManager;
    private DeckUtility deckUtil;

    ConcurrentGameManagerImpl(){
        decks = new ConcurrentHashMap<>();
        games = new ConcurrentHashMap<>();
        deckUtil = new DeckUtility();
    }

    public static ConcurrentGameManagerImpl getInstance(){
        if(gManager == null){
            synchronized (ConcurrentGameManagerImpl.class) {
                if(gManager == null){
                    gManager =  new ConcurrentGameManagerImpl();
                    return gManager;
                }
            }
        }
        return gManager;
    }

    @Override
    public long createGame() {
        long creationTimeStamp = System.nanoTime() ;
        games.put(creationTimeStamp, new Game());
        return creationTimeStamp;
    }

    @Override
    public void deleteGame(long gameID) throws GameNotExistException {
        if (!games.containsKey(gameID))
            throw new GameNotExistException("Game [" + gameID + "] does not exist.") ;
        games.remove(gameID);
    }

    @Override
    public long createDeck() {
        Deck deck = deckUtil.buildDeck();
        long creationTimeStamp = System.nanoTime() ;
        decks.put(creationTimeStamp, deck) ;
        return creationTimeStamp;
    }

    @Override
    public void addDeck(long gameID, long deckID) throws GameNotExistException, DeckAlreadyExistException,
            DeckNotExistException {
        Game game = games.get(gameID);
        if (game == null) {
            throw new GameNotExistException("Game [" + gameID + "] does not exist.") ;
        }
        Deck deck = decks.get(deckID);
        if (deck == null) {
            throw new DeckNotExistException("Deck [" + deckID + "] does not exist.") ;
        }
        game.addDeck(deckID, deck);
    }

    @Override
    public void addPlayer(long gameID, long playerID) throws GameNotExistException, PlayerAlreadyExistException {
        Game game = games.get(gameID);
        if (game == null) {
            throw new GameNotExistException("Game [" + gameID + "] does not exist.") ;
        }
        game.addPlayer(playerID);
    }

    @Override
    public void deletePlayer(long gameID, long playerID) throws GameNotExistException, PlayerAlreadyExistException,
            PlayerNotExistException {
        Game game = games.get(gameID);
        if (game == null) {
            throw new GameNotExistException("Game [" + gameID + "] does not exist.") ;
        }
        game.deletePlayer(playerID);
    }

    @Override
    public MyGameCard dealCard(long gameID, long playerID, long deckID) throws GameNotExistException,
            DeckNotExistException, PlayerNotExistException, NullDeckException {
        Game game = games.get(gameID);
        if (game == null) {
            throw new GameNotExistException("Game [" + gameID + "] does not exist.") ;
        }

        Deck cachedDeck = game.getDeck(deckID);
        synchronized (cachedDeck) {
            MyGameCard retrievedCard = (MyGameCard) deckUtil.getCard(cachedDeck);
            game.giveCard(playerID, retrievedCard);
            return retrievedCard;
        }
    }

    @Override
    public Hand getCards(long gameID, long playerID) throws GameNotExistException,
            PlayerNotExistException {
        Game game = games.get(gameID);
        if (game == null) {
            throw new GameNotExistException("Game [" + gameID + "] does not exist.") ;
        }
        return game.getCards(playerID);
    }

    @Override
    public List<Player> getPlayers(long gameID) throws GameNotExistException {
        Game game = games.get(gameID);
        if (game == null) {
            throw new GameNotExistException("Game [" + gameID + "] does not exist.") ;
        }
        // not yet sorted
        List<Player> players = new ArrayList<>(game.playerList().values());
        synchronized (players) {
            Collections.sort(players, Comparator.comparing(Player::getScore));
            return players;
        }
    }

    @Override
    public String[] UndealtCardCount(long gameID, long deckID) throws GameNotExistException,
            DeckNotExistException {
        Game game = games.get(gameID);
        if (game == null) {
            throw new GameNotExistException("Game [" + gameID + "] does not exist.") ;
        }
        Deck cachedDeck = game.getDeck(deckID);
        synchronized (cachedDeck) {
            int diamondCount = 0, heartCount = 0, clubCount = 0, spadeCount = 0;
            ArrayList<MyGameCard> cards = cachedDeck.getRemainingCards();
            for (MyGameCard card : cards) {
                Suit suit = card.suit();
                switch (suit) {
                    case Club:
                        clubCount++;
                        break;
                    case Diamond:
                        diamondCount++;
                        break;
                    case Heart:
                        heartCount++;
                        break;
                    case Spade:
                        spadeCount++;
                        break;
                }
            }
            String[] result = new String[4];
            result[0] = clubCount + " clubs";
            result[1] = diamondCount + " diamonds";
            result[2] = heartCount + " hearts";
            result[3] = spadeCount + " spades";
            return result;
        }
    }

    @Override
    public Map<Suit, int[]> UndealtCardCount(long gameID) throws GameNotExistException {
        Game game = games.get(gameID);
        if (game == null) {
            throw new GameNotExistException("Game [" + gameID + "] does not exist.") ;
        }
        Map<Long, Deck> gameDecks = game.getAllDecks();
        synchronized (gameDecks) {
            byte cardTypeSize = Card.FACE_TYPE_COUNT;
            int[] clubCountArr = new int[cardTypeSize];
            int[] diamondCountArr = new int[cardTypeSize];
            int[] heartCountArr = new int[cardTypeSize];
            int[] spadeCountArr = new int[cardTypeSize];
            for (Long deckID : gameDecks.keySet()) {
                Deck cachedDeck = gameDecks.get(deckID);
                ArrayList<MyGameCard> cards = cachedDeck.getRemainingCards();
                for (MyGameCard card : cards) {
                    Suit suit = card.suit();
                    switch (suit) {
                        case Club:
                            clubCountArr[cardTypeSize - card.value()]++;
                            break;
                        case Diamond:
                            diamondCountArr[cardTypeSize - card.value()]++;
                            break;
                        case Heart:
                            heartCountArr[cardTypeSize - card.value()]++;
                            break;
                        case Spade:
                            spadeCountArr[cardTypeSize - card.value()]++;
                            break;
                    }
                }
            }
            Map<Suit, int[]> result = new ConcurrentHashMap<>();
            result.put(Suit.Club, clubCountArr);
            result.put(Suit.Diamond, diamondCountArr);
            result.put(Suit.Heart, heartCountArr);
            result.put(Suit.Spade, spadeCountArr);
            return result;
        }
    }

    @Override
    public void shuffle(long gameID, long deckID) throws GameNotExistException, DeckNotExistException {
        Game game = games.get(gameID);
        if (game == null) {
            throw new GameNotExistException("Game [" + gameID + "] does not exist.") ;
        }
        Deck deck = game.getDeck(deckID);
        synchronized (deck) {
            deck.shuffle();
        }
    }
}

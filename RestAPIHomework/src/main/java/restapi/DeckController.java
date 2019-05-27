package restapi;

import main.java.api.GameManager;
import org.springframework.web.bind.annotation.*;
import main.java.api.impl.*;
import main.java.entity.*;
import main.java.exception.*;

import java.util.List;
import java.util.Map;

@RestController
public class DeckController {
		private GameManager gManager = GameManagerImpl.getInstance();
	    private final static String SUCCESSFUL_SHUFFLE = "Deck was successfully shuffled." ;
	    private final static String SUCCESSFUL_DECK_ADDED = "Deck was successfully added." ;
	    private final static String SUCCESSFUL_PLAYER_ADDED = "Player was successfully added." ;
	    private final static String SUCCESSFUL_PLAYER_DELETED = "Player was successfully deleted." ;
	    private final static String SUCCESSFUL_DELETE = "Game was successfully deleted." ;

		@RequestMapping(method=RequestMethod.POST , value="/game")
		public Long createGame(){
			Long gameID = gManager.createGame();
			return gameID ;
		}

		@RequestMapping(method=RequestMethod.DELETE , value="/game")
		public String deleteGame(@RequestParam(value="gameID") Long gameID) throws GameNotExistException {
			gManager.deleteGame(gameID);
			return SUCCESSFUL_DELETE ;
		}

		@RequestMapping(method=RequestMethod.POST , value="/game/{gameID}/deck/{deckID}")
		public String addDeck(@PathVariable(value="gameID") Long gameID, @PathVariable(value="deckID") Long deckID)
				throws DeckNotExistException, GameNotExistException, DeckAlreadyExistException {
			gManager.addDeck(gameID, deckID);
			return SUCCESSFUL_DECK_ADDED ;
		}

		@RequestMapping(method=RequestMethod.POST , value="/game/player")
		public String addPlayer(@RequestParam(value="gameID") Long gameID, @RequestParam(value="playerID") Long playerID)
				throws GameNotExistException, PlayerAlreadyExistException {
			gManager.addPlayer(gameID, playerID);
			return SUCCESSFUL_PLAYER_ADDED;
		}

		@RequestMapping(method=RequestMethod.DELETE , value="/game/player")
		public String deletePlayer(@RequestParam(value="gameID") Long gameID, @RequestParam(value="playerID")
			Long playerID) throws GameNotExistException, PlayerNotExistException, PlayerAlreadyExistException {
			gManager.deletePlayer(gameID, playerID);
			return SUCCESSFUL_PLAYER_DELETED;
		}

	    @RequestMapping(method=RequestMethod.GET , value="/game/deck/player")
	    public Card dealCard(@RequestParam(value="gameID") Long gameID, @RequestParam(value="deckID") Long deckID,
							 @RequestParam(value="playerID") Long playerID) throws DeckNotExistException,
				NullDeckException, PlayerNotExistException, GameNotExistException {
	        Card retrievedCard = gManager.dealCard(gameID, playerID, deckID) ;
	        return retrievedCard;
	    }

		@RequestMapping(method=RequestMethod.GET , value="/game/card")
		public Hand getCards(@RequestParam(value="gameID") Long gameID, @RequestParam(value="playerID") Long playerID)
				throws PlayerNotExistException, GameNotExistException {
			Hand hand = gManager.getCards(gameID, playerID) ;
			return hand;
		}

		@RequestMapping(method=RequestMethod.GET , value="/game/player")
		public List<Player> getPlayers(@RequestParam(value="gameID") Long gameID)
				throws GameNotExistException {
			List<Player> players = gManager.getPlayers(gameID) ;
			return players;
		}
	    
	    @RequestMapping(method=RequestMethod.POST , value="/deck")
	    public Long createDeck() throws DeckNotExistException{
	    	Long deckID = gManager.createDeck() ;
	    	return deckID ;
	    }

		@RequestMapping(method=RequestMethod.GET , value="/game/{gameID}/card")
		public String[] undealtCardCountGame(@PathVariable(value="gameID") Long gameID,
										 @RequestParam(value="deckID") Long deckID)
				throws GameNotExistException, DeckNotExistException {
			String[] cards = gManager.UndealtCardCount(gameID, deckID) ;
			return cards;
		}

		@RequestMapping(method=RequestMethod.GET , value="/game/{gameID}")
		public Map<Suit, int[]> undealtCardCount(@PathVariable(value="gameID") Long gameID)
				throws GameNotExistException {
			Map<Suit, int[]> cards = gManager.UndealtCardCount(gameID) ;
			return cards;
		}
	    
	    @RequestMapping(method=RequestMethod.PUT , value="/game/{gameID}/deck/{deckID}/shuffle")
	    public String shuffleADeck(@PathVariable(value="gameID") Long gameID, @PathVariable(value="deckID") Long deckID)
				throws DeckNotExistException, GameNotExistException {
	    	gManager.shuffle(gameID, deckID);
	    	return SUCCESSFUL_SHUFFLE ;
	    }
	    
}

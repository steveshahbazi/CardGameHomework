In order to run this application run the following command:
	mvn clean install. this command needs to be executed in main root. 
	java -jar (name of the jar file in target folder). Example: java -jar MyCardGameHomework.jar

This project is written to satisfy the following stories:
You must provide the following operations:
	-- Create and delete a game
	-- Create a deck
	-- Add a deck to a game deck
	-- Please note that once a deck has been added to a game deck it cannot be removed.
	-- Add and remove players from a game
	-- Deal cards to a player in a game from the game deck
	Specifically, for a game deck containing only one deck of cards, a call to shuffle followed by 52 calls to dealCards(1) for the same player should result in the caller being provided all 52 cards of the deck in a random order. If the caller then makes a 53rd call to dealCard(1), no card is dealt. This approach is to be followed if the game deck contains more than one deck.
	-- Get the list of cards for a player
	-- Get the list of players in a game along with the total added value of all the cards each player holds; use face values of cards only. Then sort the list in descending order, from the player with the highest value hand to the player with the lowest value hand:
	For instance if player ‘A’ holds a 10 + King then her total value is 23 and player ‘B’ holds a 7 + Queen then his total value is 19,  so player ‘A’ will be listed first followed by player ‘B’.
	-- Get the count of how many cards per suit are left undealt in the game deck (example: 5 hearts, 3 spades, etc.)
	-- Get the count of each card (suit and value) remaining in the game deck sorted by suit ( hearts, spades, clubs, and diamonds) and face value from high value to low value (King, Queen, Jack, 10….2, Ace with value of 1)
	-- Shuffle the game deck (shoe)
	Shuffle returns no value, but results in the cards in the game deck being randomly permuted. Please do not use library-provided “shuffle” operations to implement this function. You may use library- provided random number generators in your solution.
	Shuffle can be called at any time

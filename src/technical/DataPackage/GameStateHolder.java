package technical.DataPackage;

import java.util.List;
import domain.player.*;
import domain.square.Square;
import domain.Board;
import domain.cards.*;

// TODO: Auto-generated Javadoc
/**
 * The Class GameStateHolder that keeps the track of the game on the fly.
 */
public class GameStateHolder {
	
	/** The players list that holds alive players in the game. */
	private List<Player> players;
	
	/** The squares list that holds the squares in the game. */
	private List<Square> squares ;
	
	/** The chance cards list that holds the chance cards that are on the board. */
	private List<ChanceCard> chanceCards ;
	
	/** The community chest cards that golds the community chest cards that are on the board. */
	private List<CommunityChestCard> communityChestCards;
	
	/** An integer value that keeps which player's turn it is currently. */
	private int turn ;

/**
 * Instantiates a new gameStateHolder object.
 */
public GameStateHolder() {
	
}

/**
 * Gets the last state of the game.
 *
 * @return the last state of the game
 * 
 */
public GameStateHolder getLastState() {
	return null;
}

/**
 * Saves current state of the game.
 * 
 * @effects 
 */
public void saveCurrentState() {
	
}

/**
 * Updates the current state of the game.
 * @modifies players, squares, chanceCards, communityChestCards, turn
 * @effects Gets data from Board class to temporarily save game data for future save/restore actions.
 */
public void update() {
	players = Board.getInstance().getPlayers();
	squares = Board.getInstance().getSquares();
	chanceCards = Board.getInstance().getChanceCards();
	communityChestCards = Board.getInstance().getCommunityChestCards();
	turn = Board.getInstance().getTurn();
}

/**
 * Gets the players list.
 *
 * @effects Returns the players list .
 * @return the players list
 */
public List<Player> getPlayers() {
	return players;
}

/**
 * Gets the squares list.
 *
 * @effects Returns the squares list.
 * @return the squares list
 */
public List<Square> getSquares() {
	return squares;
}

/**
 * Gets the chance cards list .
 *
 * @effects Returns the chance cards list.
 * @return the chance cards list
 */
public List<ChanceCard> getChanceCards() {
	return chanceCards;
}

/**
 * Gets the community chest cards list.
 *
 * @effects Returns the community chest cards list.
 * @return the community chest cards
 */
public List<CommunityChestCard> getCommunityChestCards() {
	return communityChestCards;
}

/**
 * Gets the current turn of the game.
 *
 * @return the current turn of the game as an integer
 * @effects Returns turn of the game
 */
public int getTurn() {
	return turn;
}
public String toString() {
	String result = "Game state holder holds game state for save and load operations.";
	return result;
}
public boolean repOK() {
	if(this == null){
		return false;
	} else {
		return true;
	}
}

public void setTurn(int turn) {
	this.turn = turn;
}

}
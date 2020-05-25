	package domain.gamecontroller;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.json.JSONException;

import java.io.FileReader;
import domain.player.*;
import domain.square.*;
import domain.cards.*;
import domain.*;
import technical.DataPackage.*;
import technical.SaveLoad.saveLoad;

// TODO: Auto-generated Javadoc
/*
 * ouz notlar 
 * 1. lotSquare classina housePrice hotelPrice skyscraperPrice ekledim
 * 2. propertyde kac tane ev otel gokdelen oldugunu gormek icin numberOfHouse hasHotel hasSkyscraper gibi
 * fieldlar ekledim
 * 3. lotSquare a 2 field daha, majorityOwnership(o renkten en az 2 tapu) ve monopoly(o rengin butun
 * tapulari) 
 * 4. triplet number diye bi sey ekledim lotSquare a. Ayni renkten tapularin triplet numberlari
 * ayni. Bu sey icin oyuncu o rengin butun tapularina sahip mi falan diye bakmak icin
 * 5. number of same color squares diye bi sey ekledim ayni renkten kac tane var diye bakmak icin
 * 6. abi bu arada senin o property seyi farkli pakette oldugu icin midir nedir onun fieldlarini
 * bi turlu degistiremedim baska classlardan o yuzden her seyimi lotSquare a gore yaptim
 * 7. abi 3. notta bahsettigim 2 field var ya, onlar buy property kisminda hallediliyor
 * 8. buy property ve build skyscraper bitti
 * 9. abi color group ve number of siblingsi sildim iste yukarida dedidigm benzer seyleri ekledikten sonra
 * 10. abi playerda ownedProperties diye bi liste vardi ben onu owned lots diye degistridim 
 * sadece tapular var burda ev kurulabilen falan. Diger propertyler icin baska liste mi tutsak diye
 * dusundum ne biliyim ownedutilities ownedrailroads falan yoksa sonra typecasting falan ugrasmak 
 * zorunda kaliriz daha cok vaktim olsa yapardim ama vaktim baya az su an
 * 11. roll dice sadece dice i rolluyor o kadar
 * 12. addPlayer i yaptim biraz
 * 13. squeezeplaye 0 dedim , go 24 oldu, subway de 64 oldu 
 * 14. player a constructor koydum
 * 15. initializeBoard kismini hazirlaim
 * 16. start game metodunu yazdim gamecontrollerda
 * 17. piecein tam olarak ne oldugunu bilmedigim icin simdilik playerin constructorindan cikardim bu phasede
 * gerekemez muhtemelen
 * 18. abi burdaki 152. satirdaki not cok onemli ***********************************
 * 19. kanka olay mainde donuyo
 * 20. save gameide yaptim loadi yaparsin abi
 */

/**
 * The Class gameController. Which is the first element of the domain structure beyond GUI. It is a Controller. It connects the every piece of the game to each other. GUI communicates directly and only to this class to 
 * make appropriate changes in the game state. This class communicates to all other domain classes according to the message that it takes from the GUI.
 */
/* NOTLAR
*/
public class gameController {
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * The board field which is a Board object.
	 */
	Board board;
	
	/** Field that holds the current state of the game. This class is updated after each turn to keep the track of the game.
	 * Also this class is used to save & load the game correctly */
	GameStateHolder stateHolder;
	
	/** saveLoad object that saves & loads the game. */
	saveLoad saveLoader;
	int total = 0;
	
	
/**
 * Instantiates a new game controller object. It is an empty constructor
 */
public gameController() {
	stateHolder = new GameStateHolder(); 	
	saveLoader = new saveLoad();
}

/**
 * Roll the dices. Starts a new turn by moving the player to the new location based on the dices.
 * 
 * @effects Calls the rollDice() method of the Board object. Then moves the player to the new location. Maintains the flow of the game. Checks if there is a rent that should be paid.
 */
// BURASI SÝLÝNECEK. ROLL ÖZELLÝÐÝ ZARLARIN ÝÇÝNE KONACAK
public void rollDice() {
	board.rollDice();
	move();
	
}

public boolean checkRent() {
	if (getCurrentSquare() instanceof PropertySquare) {

		PropertySquare tempPropertySquare = (PropertySquare) getCurrentSquare();
		if (tempPropertySquare.isOwned() && !( tempPropertySquare.getOwner().equals(getCurrentPlayer()) ) ) {
			return true;
		}
	}
	return false;
}

public String returnPropertyType() {
	String classname = "";
	if(this.getCurrentPlayer().getTrack()==0) {
		classname = this.getSquares().get(this.getCurrentPlayer().getPosition()).getClass().getName();
		
	}else if(this.getCurrentPlayer().getTrack()==1) {
		classname = this.getSquares().get(this.getCurrentPlayer().getPosition()+24).getClass().getName();
		
	}else {
		classname = this.getSquares().get(this.getCurrentPlayer().getPosition()+64).getClass().getName();
		
	}
	return classname;
}

public void doPropertySquareAction() {
	String classname = this.returnPropertyType();
	
	if(classname.equals("domain.square.LotSquare")|| classname.equals("domain.square.RailroadSquare") || classname.equals("domain.square.UtilitySquare")){
	
		
		
		if(((PropertySquare)getCurrentPlayersCurrentSquare()).isOwned()) {
		    JOptionPane.showMessageDialog(null, "This property is already owned. You need to pay " + ((PropertySquare)getCurrentPlayersCurrentSquare()).getRent(((PropertySquare)getCurrentPlayersCurrentSquare()).getOwner(), this.total));
			if(this.getCurrentPlayer().getMoney() <((PropertySquare)getCurrentPlayersCurrentSquare()).getRent(((PropertySquare)getCurrentPlayersCurrentSquare()).getOwner(), this.total) ) {
				//bankrupt
			}else {
				((PropertySquare)getCurrentPlayersCurrentSquare()).getOwner().setMoney(
						((PropertySquare)getCurrentPlayersCurrentSquare()).getOwner().getMoney() +
						((PropertySquare)getCurrentPlayersCurrentSquare()).getRent(((PropertySquare)getCurrentPlayersCurrentSquare()).getOwner(), this.total));
				this.getCurrentPlayer().setMoney(this.getCurrentPlayer().getMoney() - ((PropertySquare)getCurrentPlayersCurrentSquare()).getRent(((PropertySquare)getCurrentPlayersCurrentSquare()).getOwner(), this.total));
			}
		}else {
			boolean bool = true;
			
			while(bool) {
			String decision = JOptionPane.showInputDialog(((PropertySquare)getCurrentPlayersCurrentSquare()).getName() + " " + "Price: "+
					((PropertySquare)getCurrentPlayersCurrentSquare()).getPrice() + " Would you like to buy this square?(y/n) : ");
			if(decision.equals("y")||decision.equals("Y")) {
				bool = false;
				if(this.getCurrentPlayer().getMoney()<((PropertySquare)getCurrentPlayersCurrentSquare()).getPrice()) {
					JOptionPane.showMessageDialog(null, "You can not afford this square. Its now put on auction.");
					doAuction(((PropertySquare)getCurrentPlayersCurrentSquare()));
				
				}else {
					this.getCurrentPlayer().setMoney(this.getCurrentPlayer().getMoney()-((PropertySquare)getCurrentPlayersCurrentSquare()).getPrice());
					this.getCurrentPlayer().getOwnedProperties().add(((PropertySquare)getCurrentPlayersCurrentSquare()));
					if(this.returnPropertyType().equals("domain.square.RailroadSquare")) {
						this.getCurrentPlayer().setNumRailroads(this.getCurrentPlayer().getNumRailroads() + 1);
					}
					if(this.returnPropertyType().equals("domain.square.UtilitySquare")) {
						this.getCurrentPlayer().setNumUtilities(this.getCurrentPlayer().getNumUtilities() + 1);
					}
					((PropertySquare)getCurrentPlayersCurrentSquare()).setOwned(true);
					((PropertySquare)getCurrentPlayersCurrentSquare()).setOwner(this.getCurrentPlayer());
							}
			}else if(decision.equals("n")||decision.equals("N")) {
				//auction
				JOptionPane.showMessageDialog(null, "Property now put on auction.");
				if(this.getPlayers().size()==2) {
					this.getPlayers().get((this.getCurrentPlayer().getPlayerID() +1)%2).getOwnedProperties().add(((PropertySquare)getCurrentPlayersCurrentSquare()));
					((PropertySquare)getCurrentPlayersCurrentSquare()).setOwner(this.getPlayers().get((this.getCurrentPlayer().getPlayerID() +1)%2));
					((PropertySquare)getCurrentPlayersCurrentSquare()).setOwned(true);
					if(this.returnPropertyType().equals("domain.square.RailroadSquare")) {
						this.getPlayers().get((this.getCurrentPlayer().getPlayerID() +1)%2).setNumRailroads(this.getCurrentPlayer().getNumRailroads() + 1);
					}
					if(this.returnPropertyType().equals("domain.square.UtilitySquare")) {
						this.getPlayers().get((this.getCurrentPlayer().getPlayerID() +1)%2).setNumUtilities(this.getCurrentPlayer().getNumUtilities() + 1);
					}
					
				}else {
				doAuction(((PropertySquare)getCurrentPlayersCurrentSquare()));
				}
				JOptionPane.showMessageDialog(null, "Bid Sucessful");
				bool = false;
			} else {
				bool = true;
			}
		}
			}
		
	}
}
public void doGoToJailSquare() {
	if(returnPropertyType().equals("domain.square.GoToJailSquare")) {
	getCurrentPlayer().setInJail(true);
	getCurrentPlayer().directRouteMove(1, 10);
	}
	
}
public void rollInJail() {
	board.getDie1().roll();
	board.getDie2().roll();
	if(board.getDie1().getFaceValue()==board.getDie2().getFaceValue()) {
		getCurrentPlayer().setInJail(false);
		move();
		
}
	
}
public void doAuction(PropertySquare sq) {
	boolean bool = true;
	boolean bool2 = true; 
	int bid = 0; 
	int tempBid = 0;
	boolean turnFlag = true; 
	boolean giveup = false;
	int whoseTurn = (this.getCurrentPlayer().getPlayerID() +1) % this.getPlayers().size();
	
	List<Player> gaveup = new ArrayList<Player>();
	gaveup.add(this.getCurrentPlayer());
	while(bool) {
		turnFlag = true;
		
		for(int i =0; i< gaveup.size(); i++) {		
			if(this.getPlayers().get(whoseTurn).getPlayerID()==gaveup.get(i).getPlayerID()) {
				turnFlag = false;
			}
			}
		
		if(gaveup.size()==this.getPlayers().size() -1 && turnFlag) {
			this.getPlayers().get(whoseTurn).setMoney(this.getPlayers().get(whoseTurn).getMoney() - sq.getPrice());
			this.getPlayers().get(whoseTurn).getOwnedProperties().add(sq);
			if(this.returnPropertyType().equals("domain.square.RailroadSquare")) {
				this.getPlayers().get(whoseTurn).setNumRailroads(this.getPlayers().get(whoseTurn).getNumRailroads() + 1);
			}
			if(this.returnPropertyType().equals("domain.square.UtilitySquare")) {
				this.getPlayers().get(whoseTurn).setNumUtilities(this.getPlayers().get(whoseTurn).getNumUtilities()+ 1);
			}
			sq.setOwned(true);
			sq.setOwner(this.getPlayers().get(whoseTurn));
			bool=false;
			bool2=false;
			break;
		}
		
		
		
		if(turnFlag) {
		bool2 = true;
		while(bool2) {
		String bidMessage = JOptionPane.showInputDialog("P "
				 + whoseTurn +  "'s turn. " + "Current bid for " + sq.getName() + " is " + bid + "." + " Write"
				+ " your bid or write N to give up.");
		
		if(bidMessage.equals("N") || bidMessage.equals("n")) {
			bool2 = false;
			giveup = true;
			
		}else {
		try {
		
		tempBid = Integer.parseInt(bidMessage);
		if(tempBid>this.getPlayers().get(whoseTurn).getMoney()) {
			JOptionPane.showMessageDialog(null, "Not enough money. Enter another bid or write N to give up.");
			bool = true;
			
		}else {
		bid = tempBid;
		bool2 = false;
		}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Please enter a number or write N!");
			bool2 = true;
		}
		}
		JOptionPane.showMessageDialog(null, "Bid Sucessful");
		}
		
		if(giveup) {
			gaveup.add(this.getPlayers().get(whoseTurn));
		}
		
		whoseTurn = (whoseTurn + 1) % this.getPlayers().size();
		
		
	} else{
		whoseTurn = (whoseTurn + 1) % this.getPlayers().size();
		
	}
		}
} 
/**
 * Adds a player Player to the game.
 *
 * @param playerName the name of the player
 * @param playerColor the color of the player
 * @effects When addPlayer message is received from the GUI by the controller, controller calls the addPlayer 
 * method of the Board. 
 */
public void addPlayer(String playerName,int playerMoney, Color playerColor) {

	board.addPlayer(playerName,playerMoney, playerColor);
}

/**
 * Gets the current player.
 *
 * @effects Returns the current player by calling the getPlayers() and getTurn() methods of the Board object.
 * @return the current player
 */
public Player getCurrentPlayer() {
	return board.getPlayers().get(board.getTurn());
}
/**
 * Gets the square that the current players is on.
 * 
 * @effects Returns the current square by calling the getSquares() of the Board object. Also uses getCurrentPlayer() method of the gameController object.
 * @return the square that the current player is on
 */
public Square getCurrentSquare() {
	return board.getSquares().get(getCurrentPlayer().getPosition());
}
/**
 * Set the game's state to next turn.
 * 
 * @effects Calls the nextTurn() method of the Board class. Then updates GameStateHolder to
 * temporarily save game state.
 */
public void nextTurn() {
	board.nextTurn();
	stateHolder.update();
}

/**
 * Shuffle the cards that are on the board.
 *
 * @param cards the cards list that will be shuffled
 * @modifies cards: List Cards
 * @effects Shuffles the given input cards list. 
 */

public void shuffle1(List<ChanceCard> cards) {
	Collections.shuffle(cards);
}
public void shuffle2(List<CommunityChestCard> cards) {
	Collections.shuffle(cards);
}

/**
 * Gets the action response.
 *
 * @param player the player
 * 
 */
public void getActionResponse(Player player) {
	
}

/**
 * Moves the current player to the new location.
 *
 *@effects Moves the current player to the new location by using the move() method of the Player class.
 */
public void move() {
	int total =  board.getDie1().getFaceValue() + board.getDie2().getFaceValue();
	this.total = total;
	for(int i=0; i<total; i++) {
	moveNext(total);
	}
	//getCurrentPlayer().move(total);
	}

public void moveNext(int total) {
	// To check if you are on railroad square or not.
	if (getCurrentPlayersCurrentSquare() instanceof RailroadSquare) {
		if( total%2 == 0) {
			RailroadSquare railroad = (RailroadSquare) getCurrentPlayersCurrentSquare();
			int new_position = railroad.getNew_position();
			int new_track = railroad.getNew_track();
			getCurrentPlayer().setPosition(new_position);
			getCurrentPlayer().setTrack(new_track);
		}
	}
	// If player PASSES Bonus Square they receive $250
	else if (getCurrentPlayersCurrentSquare() instanceof BonusSquare) {
		int money = getCurrentPlayer().getMoney();
		getCurrentPlayer().setMoney(money+250);
	} 
	// If player PASSES Go Square they receive $200
	else if (getCurrentPlayersCurrentSquare() instanceof GoSquare) {
		int money = getCurrentPlayer().getMoney();
		getCurrentPlayer().setMoney(money + 200);
	}
	// If player PASSES PayDay Square they will receive money.
	else if (getCurrentPlayersCurrentSquare() instanceof PayDaySquare) {
		if(total%2==0) {
			int money = getCurrentPlayer().getMoney();
			getCurrentPlayer().setMoney(money+400);
		}else {
			int money = getCurrentPlayer().getMoney();
			getCurrentPlayer().setMoney(money+300);
		}
	}
	// Player moves to the next square.
	getCurrentPlayer().moveNext();
}

public void directRouteMove(int track, int position) {
	getCurrentPlayer().directRouteMove(track, position);
}

public void doBonusSquareAction() {
	int money = getCurrentPlayer().getMoney();
	getCurrentPlayer().setMoney(money+300);
}

public void doIncomeTax() {
	if(returnPropertyType().equals("domain.square.IncomeTaxSquare")) {
	getCurrentPlayer().setMoney(getCurrentPlayer().getMoney() - 200);
	if(getSquares().get(44) instanceof FreeParkingSquare) {
	((FreeParkingSquare)board.getSquares().get(44)).addPool(200);
	}}
}

public void doFreeParkingSquareAction() {
	if(returnPropertyType().equals("domain.square.FreeParkingSquare")) {
	((FreeParkingSquare)getCurrentPlayersCurrentSquare()).givePool(getCurrentPlayer());
	}
}

public void doGoSquareAction() {
	int money = getCurrentPlayer().getMoney();
	getCurrentPlayer().setMoney(money+200);
}

public void doPayDaySquareAction() {
	int total =  board.getDie1().getFaceValue() + board.getDie2().getFaceValue();
	if(total%2==0) {
		int money = getCurrentPlayer().getMoney();
		getCurrentPlayer().setMoney(money+400);
	}else {
		int money = getCurrentPlayer().getMoney();
		getCurrentPlayer().setMoney(money+300);
	}
}

public void doHollandTunnelSquare() {
	if(returnPropertyType().equals("domain.square.HollandTunnelSquare")) {
		if(getCurrentSquare().getLocation()==18) {
			directRouteMove(2, 14);
		}else {
			directRouteMove(0,18);
		}
	}
}

public void doChanceSquareAction() {
	if(returnPropertyType().equals("domain.square.ChanceCardSquare")) {
		this.shuffle1(this.board.getChanceCards());
		this.getCurrentPlayer().getOwnedCards().add((Card)this.board.getChanceCards().get(0));
		JOptionPane.showMessageDialog(null, "You got the card : " + this.board.getChanceCards().get(0).getName());
		this.board.getChanceCards().get(0).use(this.getCurrentPlayer(), this.getBoard().getFree_parking());
		this.getCurrentPlayer().getOwnedCards().remove(0);
	}
}

public void doCommunitySquareAction() {
	if(returnPropertyType().equals("domain.square.CommunityCardSquare")) {
	this.shuffle2(this.board.getCommunityChestCards());
	this.getCurrentPlayer().getOwnedCards().add((Card)this.board.getCommunityChestCards().get(0));
	JOptionPane.showMessageDialog(null, "You got the card : " + this.board.getChanceCards().get(0).getName());
	this.board.getCommunityChestCards().get(0).use(this.getCurrentPlayer(), this.getBoard().getFree_parking());
	this.getCurrentPlayer().getOwnedCards().remove(0);
	}
}



/**
 * Current player buys the property that the player is currently on.
 * 
 * @effects Calls the buyProperty method of the Player class for the currentPlayer object.
 * 
 */
public void buyProperty() {
	if(getCurrentSquare().isBuyable()) {
		getCurrentPlayer().buyProperty((PropertySquare) getCurrentSquare());
	}
}

/**
 * Removes the dead player from the game.
 *
 * @param player the player that will be removed from the dead since the player is so-called dead.
 * @modifies Players list of the Board class.
 * @effects Removes the Player object from the players List Player of the Board object.
 */
public void removeDeadPlayer(Player player) {

	board.getPlayers().remove(player);
}

/**
 * Start the game by initializing board by calling getInstance() method of the board Board object.
 * 
 * @modifies board field of gameController object
 * @effects Calls a singleton board Board object at the start of the game, to initialize once and for all.
 */
public void startGame() { 
	board = Board.getInstance();
}

/**
 * Starts saved game.
 *
 * @param data the GameStateHolder  object that holds the necessary information to start the game.
 * @effects Start the game from the information of the saved game.
 */
public void startSavedGame(org.json.JSONObject data) {
	board = Board.getInstance();
	
	try {
		//org.json.JSONArray players = new org.json.JSONArray(data.getJSONArray("Players"));
		org.json.JSONArray players = data.getJSONArray("Players");
		//org.json.JSONArray squares = new org.json.JSONArray(data.getJSONArray("Squares"));
		org.json.JSONArray squares = data.getJSONArray("Squares");
		for(int i=0; i < players.length();i++) {
			org.json.JSONObject player = (org.json.JSONObject) players.get(i);
			Player playerObj = new Player(player.getString("Name"), player.getInt("Money"), Color.red);
			board.getPlayers().add(playerObj);

			org.json.JSONArray properties = player.getJSONArray("Owned Properties"); 
			if(properties.length()> 0) {
			for(int j=0; j < properties.length(); j++) {
				org.json.JSONObject property = (org.json.JSONObject) properties.get(j);
				PropertySquare propertySquareObj = (PropertySquare) board.getSquares().get(property.getInt("ID"));
				propertySquareObj.setOwner(board.getPlayers().get(i));
				propertySquareObj.setOwned(true);
				playerObj.getOwnedProperties().add(propertySquareObj);
				board.getSquares().remove(property.getInt("ID"));
				board.getSquares().add(property.getInt("ID"), propertySquareObj);
			}}
		}
		
		board.setTurn(data.getInt("Turn"));
		
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}




/**
 * Load the game.
=======
/** 
 * Load game.
>>>>>>> branch 'master' of ssh://git@git.ku.edu.tr/pmoc_203/UltimateMonopoly.git
 *
 * @param fileName the name of the save file
 * @effects Calls the loadGame method of saveLoad class.
 */
public org.json.JSONObject LoadGame(String fileName) {
	org.json.JSONObject data = saveLoader.loadGame(fileName);
	return data;
}

/**
 * Save game.
<<<<<<< HEAD
 * @modifies stateHolder object.
 * @effects Updates the stateHolder  object then saves the game by using saveGame() method of the saveLoad object.
 */
public void saveGame() {
	stateHolder.update();
	saveLoader.saveGame(stateHolder);
}


/**
 * Gets the number of alive players.
 * 
 * @effects Calls the getNumPlayers() method of the board  object.
 * @return number of players as an integer.
 */
public int numberOfPlayers() {
	return board.getNumPlayers();
}

public Square getCurrentPlayersCurrentSquare() {
	if(this.getCurrentPlayer().getTrack()==0) {
		return this.getBoard().getSquares().get(this.getCurrentPlayer().getPosition());
	}else if (this.getCurrentPlayer().getTrack()==1) {
		return this.getBoard().getSquares().get(this.getCurrentPlayer().getPosition()+ 24);
	}else {
		return this.getBoard().getSquares().get(this.getCurrentPlayer().getPosition() + 64);
	}
}

/**
 * Gets the players list.
 * 
 * @effects Calls the getPlayers() method of the board object.
 * @return players list 
 */
public Board getBoard() {
	return board;
} 

public void setBoard(Board b) {
	board = b;
}

public List<Player> getPlayers() {
	return board.getPlayers();
}

public List<Square> getSquares() {
	return board.getSquares();
}
public String toString() {
	String result = "This is the game controller. ";
	return result;
} 
public boolean repOk() {
	if(this == null || board== null || this.stateHolder == null || this.saveLoader == null) {
		return false; 
	} else { 
		return true;
	}
}


}
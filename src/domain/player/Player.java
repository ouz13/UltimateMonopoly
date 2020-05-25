package domain.player;

import java.awt.Color;
import java.util.*;
import domain.cards.*;
import domain.square.LotSquare;
import domain.square.PropertySquare;
import domain.square.Square;
import domain.buildings.*;
import domain.*;

// TODO: Auto-generated Javadoc
/**

 * The Player class.
 * This class keeps the information related to the Players in the game.
 */

public class Player {

/** OVERVIEW : This class keeps the information related to the Players in the game. */
	
	/** The name of the player */
	String name;
	
	/** The position of the player. */
	int position;
	
	/** The track that the player is on. */
	int track; 
	
	/** The money of the player. */
	int money;
	
	/** The owned properties. */
	List<PropertySquare> ownedProperties;
	List<PropertySquare> ownedMortgages;
	/** The owned cards by the player. */
	List<Card> ownedCards;
	
	/** The owned buildings by the player. */
	List<Building> ownedBuilding;

	public List<PropertySquare> getOwnedMortgages() {
		return ownedMortgages;
	}
	public void setOwnedMortgages(List<PropertySquare> ownedMortgages) {
		this.ownedMortgages = ownedMortgages;
	}


	/** State of the player. True if alive. False if dead. */
	Piece piece;
	
	/** State of the player. */
	boolean isAlive;

	/** The color of the player. */
	Color color;
	
	static int id = 0;
	int playerID;
	
	boolean inJail;
	int numRailroads = 0;
	int numUtilities = 0;

public int getNumUtilities() {
		return numUtilities;
	}
	public void setNumUtilities(int numUtilities) {
		this.numUtilities = numUtilities;
	}
public int getNumRailroads() {
		return numRailroads;
	}
public Player() {
	
}

	public void setNumRailroads(int numRailroads) {
		this.numRailroads = numRailroads;
	}

public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
/**
 * Instantiates a new player.
 *
 * @param name  :The name of the player
 * @param color :the color of the player
 * @effects Creates a new player. 
 * Sets the name of the player to the name input.
 * Sets the color of the player to the color input.
 * Position is set to 0 initially.
 * Track is set to 1 initially.
 * Money is set to 3200 initially.
 * ownedProperties, ownedCards, ownedBuilding lists are created as empty lists.
 * isAlive is set to true initially.
 */

public Player(String name, int money,  Color color) {
	this.money = money;
	this.name = name;
	this.color = color;
	position = 0; // go 
	track = 1;
	ownedProperties = new ArrayList<PropertySquare>();
	ownedMortgages = new ArrayList<PropertySquare>();
	ownedCards = new ArrayList<Card>();
	ownedBuilding = new ArrayList<Building>();
	isAlive = true;
	playerID = id++;
	inJail = false;
	}

public boolean isInJail() {
	return inJail;
}

public void setInJail(boolean inJail) {
	this.inJail = inJail;
}

public static int getId() {
	return id;
}

public static void setId(int id) {
	Player.id = id;
}

/**
 * Moves the player to the new position
 * 
 */
public void buildHouse(int trck, int pos, int numHouse) {
	for(int i=0; i < ownedProperties.size(); i++) {
		if(ownedProperties.get(i).getTrack() == trck && ownedProperties.get(i).getLocation() ==pos) {
			((LotSquare)ownedProperties.get(i)).setNumOfHousesBuilt(((LotSquare)ownedProperties.get(i)).getNumOfHousesBuilt());
			this.setMoney(this.getMoney()- numHouse* ((LotSquare)ownedProperties.get(i)).getHousePrice());
		}
	}
}

public void buildHotel(int trck, int pos) {
	for(int i=0; i < ownedProperties.size(); i++) {
		if(ownedProperties.get(i).getTrack() == trck && ownedProperties.get(i).getLocation() ==pos) {
			((LotSquare)ownedProperties.get(i)).setNumOfHousesBuilt(0);
			((LotSquare)ownedProperties.get(i)).setHasHotel(true);
			this.setMoney(this.getMoney()-((LotSquare)ownedProperties.get(i)).getHousePrice());
			
		}
	}
}

public void buildSkyscraper(int trck, int pos) {
	for(int i=0; i < ownedProperties.size(); i++) {
		if(ownedProperties.get(i).getTrack() == trck && ownedProperties.get(i).getLocation() ==pos) {
			((LotSquare)ownedProperties.get(i)).setNumOfHousesBuilt(0);
			((LotSquare)ownedProperties.get(i)).setHasHotel(false);
			((LotSquare)ownedProperties.get(i)).setHasSkyscraper(true);
			this.setMoney(this.getMoney()-((LotSquare)ownedProperties.get(i)).getHousePrice());
			
		}
	}
}
public void moveNext() {
	
	if(this.getTrack()==0) {
		this.position = (this.position + 1)%24;
	}else if(this.getTrack()==1) {
		this.position = (this.position + 1)%40;
	}else {
		this.position = (this.position + 1)%56;
	}
}
public void sellDeed(int deed) {
	this.setMoney(this.getMoney() + this.getOwnedProperties().get(deed).getPrice()/2);
	this.getOwnedProperties().remove(deed);
	
}


public void directRouteMove(int track, int position) {
	this.setPosition(position);
	this.setTrack(track);
}

/**
 * Gets the name of the player.
 *
 * @return the name of the player
 */
public String getName() {
	return name;
}

/**
 * Sets the name.
 *
 * @param name the new name of the player
 * @modifies name field of the player.
 * @effects Modifies the name of the player.
 */
public void setName(String name) {
	/**
	 * @modifies name 
	 * @effects Changes the name of the player
	 */
	this.name = name;
}

/**
 * Gets the position of the player.
 *
 * @return the position of the player
 * @effects Gives the position of the player in a track
 */
public Color getColor() {
	return color;
}

public int getPosition() {
	return position;
}

/**
 * Sets the position.
 *
 * @param position the new position
 * @modifies position field of the player
 * @effects Changes the position of the player in a track
 */
public void setPosition(int position) {
	this.position = position;
}

/**
 * Gets the track that the player is on.
 *
 * @return the track
 * @effects Gives the track that the player is on
 */
public int getTrack() {
	return track;
}

/**
 * Sets the track.
 *
 * @param track the new track
 * @modifies track
 * @effects Changes the track that the player is on
 */
public void setTrack(int track) {
	this.track = track;
}

/**
 * Gets the money of the player.
 *
 * @return the money of the player
 * @effects Returns the player's money
 */
public int getID() {
	return playerID;
}

public int getMoney() {
	return money;
}

/**
 * Sets the money.
 *
 * @param money the new money of the player
 * @modifies money
 * @effects Changes the player's money
 */
public void setMoney(int money) {
	this.money = money;
}

/**
 * Gets the owned properties.
 *
 * @return the owned properties
 * @effects Gives the list of the properties that the player owns.
 */
public List<PropertySquare> getOwnedProperties() {
	return ownedProperties;
}

/**
 * Sets the owned properties.
 *
 * @param ownedProperties the new owned properties
 * @modifies ownedProperties
 * @effects Changes the list of the properties that the player owns.
 */
public void setOwnedProperties(List<PropertySquare> ownedProperties) {
	this.ownedProperties = ownedProperties;
}

/**
 * Gets the owned cards.
 *
 * @return the owned cards
 * @effects Gives the list of the cards that the player owns.
 */
public List<Card> getOwnedCards() {
	return ownedCards;
}

/**

 * Sets the owned cards.
 *
 * @param ownedCards the new owned cards
 * @modifies ownedCards
 * @effects Changes the list of the cards that the player owns. 
 */

public void setOwnedCards(List<Card> ownedCards) {
	this.ownedCards = ownedCards;
}

/**
 * Gets the owned building.
 *
 * @return the owned building
 * @effects Gives the list of owned buildings
 */

public List<Building> getOwnedBuilding() {
	/**
	 */
	return ownedBuilding;
}

/**
 * Sets the owned building.
 *
 * @param ownedBuilding the new owned building
 * @modifies ownedBuilding 
 * @effects Changes the list of owned buildings 
 */

public void setOwnedBuilding(List<Building> ownedBuilding) {
	this.ownedBuilding = ownedBuilding;
}

/**
 * Buy property.
 *
 * @param property the property
 * @modifies ownedProperties, money, PropertySquare.owner
 * @effects Checks if the player can buy the property that he is on. If player can buy it, player buys the
 * property.Decreases the money of the player by the price. Adds it to player's inventory. Also sets the property's owner to the player. 
 */

public void buyProperty(PropertySquare property) {
	
	if(canAfford(property.getPrice())) {
		if(property.getOwner() == null) {
			this.setMoney(getMoney() - property.getPrice());
			this.ownedProperties.add(property);
			property.setOwner(this);
			property.setOwned(true);
		}
	}
}


/**
 * Sell property.
 *
 * @param property the property
 * @effects Player sells the property
 */

public void sellProperty(Property property) {
	
}

/**
 * Pay rent.
 *
 * @param owner the owner
 * @param rent the rent
 * @modifies this.money, owner.money
 * @effects If player can afford, player pays the rent to the owner of the property. Decreases the 
 * money of the payer, increases the money of the owner by the rent price. 
 */

public void payRent(Player owner, int rent) {
	
	if (canAfford(rent)) {
		this.setMoney(this.getMoney() - rent);
		owner.setMoney(owner.getMoney() + rent);
	}
	
}

/**
 * Can afford.
 *
 * @param upcomingExpanditure the amount that is provisioned to be deducted from player's money
 * @effects Decides if the player can afford the next move(buyProperty, payRent, buildHouse etc.) 
 * @return true, if successful
 */

public boolean canAfford(int upcomingExpanditure) {
	if(upcomingExpanditure < this.getMoney()) {
		return true;
	}
	return false;
}

/**
 * Draw a card from the deck.
 * 
 * @effects The player draws a card from the deck.
 */

public void	drawCard() {
	
}

/**
 * Use card from player's card lists.
 *
 * @param card the card
 * @effects Player uses the selected card.
 */
public void useCard(Card card) {
	
}


/**
 * Builds the skyscraper.
 *
 * @param p the price
 * @modifies this.money, this.ownedBuildings
 * @effects Checks if player can afford to build a skyscraper. If yes, player's money is decreased.
 * Skyscraper is added to the player's ownedBuildings list.
 */

/**

 * Builds the hotel.
 *
 * @param p the p
 * @modifies this.money, this.ownedBuildings
 * @effects Checks if player can afford to build a hotel. If yes, player's money is decreased.
 * Hotel is added to the player's ownedBuildings list.
 */


/**
 * Builds the house.
 *
 * @param p the p
 * @modifies this.money, this.ownedBuildings
 * @effects Checks if player can afford to build a house. If yes, player's money is decreased.
 * House is added to the player's ownedBuildings list.  
 */


//public String toString() {
//	String result = "This is player with ";
//	result = result + this.getName();
//	result+= " with cash ";
//	result+= this.getMoney();
//	return result;
//}
public boolean repOK() {
	if(this==null||this.getMoney()<0||this.getName() == "" || this.getOwnedBuilding() == null) {
		return false;
	} else{
		return true;
	}
}
	

}
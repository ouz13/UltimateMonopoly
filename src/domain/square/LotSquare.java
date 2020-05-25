package domain.square;

import java.awt.Color;

import domain.player.Player;

public class LotSquare extends PropertySquare {
	
	
	int hotelPrice;
	int housePrice;
	int skyscraperPrice;
	int numOfHousesBuilt;
	boolean hasHotel;
	boolean hasSkyscraper;
	boolean majorityOwnership;
	boolean monopoly;
	int tripletNumber;
	int numberOfSameColorSquares;
	
	
public LotSquare(String name, int location, int track, int price, int rent, int house_price) {

	super.name = name;
	super.location = location;
	super.track = track;
	super.price = price;
	super.rent = rent;
	super.isBuyable = true;
	super.owner = null;
	super.isOwned = false;
	super.locationID = id++;
	housePrice = house_price;
	hotelPrice = house_price;
	numOfHousesBuilt = 0;
	hasHotel = false;
	hasSkyscraper = false;
	majorityOwnership = false;
	monopoly = false;
	}
public LotSquare() {
	
}

public int getRent(Player p, int tot) {
	return rent;
}

public int getHotelPrice() {
	return hotelPrice;
}


public void setHotelPrice(int hotelPrice) {
	this.hotelPrice = hotelPrice;
}


public int getHousePrice() {
	return housePrice;
}


public void setHousePrice(int housePrice) {
	this.housePrice = housePrice;
}


public int getSkyscraperPrice() {
	return skyscraperPrice;
}


public void setSkyscraperPrice(int skyscraperPrice) {
	this.skyscraperPrice = skyscraperPrice;
}


public int getNumOfHousesBuilt() {
	return numOfHousesBuilt;
}


public void setNumOfHousesBuilt(int numOfHousesBuilt) {
	this.numOfHousesBuilt = numOfHousesBuilt;
}


public boolean isHasHotel() {
	return hasHotel;
}


public void setHasHotel(boolean hasHotel) {
	this.hasHotel = hasHotel;
}


public boolean isHasSkyscraper() {
	return hasSkyscraper;
}


public void setHasSkyscraper(boolean hasSkyscraper) {
	this.hasSkyscraper = hasSkyscraper;
}


public boolean isMajorityOwnership() {
	return majorityOwnership;
}


public void setMajorityOwnership(boolean majorityOwnership) {
	this.majorityOwnership = majorityOwnership;
}


public boolean isMonopoly() {
	return monopoly;
}


public void setMonopoly(boolean monopoly) {
	this.monopoly = monopoly;
}


public int getTripletNumber() {
	return tripletNumber;
}


public void setTripletNumber(int tripletNumber) {
	this.tripletNumber = tripletNumber;
}


public int getNumberOfSameColorSquares() {
	return numberOfSameColorSquares;
}


public void setNumberOfSameColorSquares(int numberOfSameColorSquares) {
	this.numberOfSameColorSquares = numberOfSameColorSquares;
}

public String toString(){
	String result = "Square.toString :\n";
	result += "Name: " + name + "\n";
	result += "Position: " + location + "\n";
	result += "Track: " + track + "\n";
	result += "Price: " + super.price + "\n";
	result += "Rent: " + super.rent + "\n";
	result += "Owner: " + owner + "\n";
	result += "isOwned: " + isOwned + "\n";
	return result;
}
	
} 

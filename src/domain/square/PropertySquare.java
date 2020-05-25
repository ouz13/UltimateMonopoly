package domain.square;

import domain.*;
import domain.player.*;

public abstract class PropertySquare extends Square{

	int rent;
	int price;
	Player owner;
	boolean isOwned;
	
public abstract int getRent(Player owner, int tot);
	


public void setRent(int rent) {
	this.rent = rent;
}

public int getPrice() {
	return price;
}

public void setPrice(int price) {
	this.price = price;
}

public Player getOwner() {
	return owner;
}

public void setOwner(Player owner) {
	this.owner = owner;
}

public boolean isOwned() {
	return isOwned;
}

public void setOwned(boolean isOwned) {
	this.isOwned = isOwned;
}

	
}

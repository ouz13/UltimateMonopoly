package domain.square;

import domain.player.Player;

public class FreeParkingSquare extends Square {
	int pool=0;
	public FreeParkingSquare(int location, int track) {
		super.name = "Free Parking";
		super.location = location;
		super.track = track;
		super.isBuyable = false;
		super.locationID = id++;
	}
	
	public void addPool(int amount) {
		pool = pool+amount;
	}
	
	public void givePool(Player p) {
		p.setMoney(p.getMoney()+pool);
		pool = 0;
	}
}

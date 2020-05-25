package domain.square;

public class BonusSquare extends Square {
	
	public BonusSquare(String name, int location, int track) {
		super.name = name;
		super.location = location;
		super.track = track;
		super.isBuyable = false;
		super.locationID = id++;
	}
}
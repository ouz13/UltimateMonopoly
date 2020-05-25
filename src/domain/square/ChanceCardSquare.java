package domain.square;

public class ChanceCardSquare extends Square {
	public ChanceCardSquare(int location, int track) {
		super.name = "Chance Card";
		super.location = location;
		super.track = track;
		super.isBuyable = false;
		super.locationID = id++;
	}
	
}

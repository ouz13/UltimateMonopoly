package domain.square;

public class PayDaySquare extends Square {
	
	public PayDaySquare(String name, int location, int track) {
		super.name = name;
		super.location = location;
		super.track = track;
		super.locationID = id++;
		super.isBuyable = false;
	}
}


package domain.square;

public class IncomeTaxSquare extends Square {
	
	public IncomeTaxSquare(String name, int location, int track) {
		super.name = name;
		super.location = location;
		super.track = track;
		super.isBuyable = false;
		super.locationID = id++;
	}
	
}

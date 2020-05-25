package domain.square;

public class SubwaySquare extends Square {

	public SubwaySquare(String name, int location, int track) {
		super.name = name;
		super.location = location;
		super.track = track;
		super.locationID = id++;
		super.isBuyable = false;
	}
}
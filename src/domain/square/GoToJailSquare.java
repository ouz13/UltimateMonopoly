package domain.square;

public class GoToJailSquare extends Square {

	public GoToJailSquare(String name, int location, int track) {
		super.name = name;
		super.location = location;
		super.track = track;
		super.locationID = id++;
		super.isBuyable = false;
	}
}
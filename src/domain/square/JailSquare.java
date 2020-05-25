package domain.square;

public class JailSquare extends Square {
	public JailSquare(String name, int location, int track) {
		super.name = name;
		super.location = location;
		super.track = track;
		super.locationID = id++;
		super.isBuyable = false;
	}
}

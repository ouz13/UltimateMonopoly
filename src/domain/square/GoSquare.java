package domain.square;

public class GoSquare extends Square{
	public GoSquare(String name, int location, int track, boolean isBuyable) {
		super.name = name;
		super.location = location;
		super.track = track;
		super.isBuyable = isBuyable;
		super.locationID = id++;
	}
}

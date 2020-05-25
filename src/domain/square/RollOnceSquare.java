package domain.square;

public class RollOnceSquare extends Square{
	public RollOnceSquare(int location) {
		super.name = "Roll Once Square";
		super.location = location;
		super.track = 0;
		super.isBuyable = false;
		super.locationID = id++;
	}
}

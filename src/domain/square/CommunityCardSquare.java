package domain.square;

public class CommunityCardSquare extends Square {
	public CommunityCardSquare(int location, int track) {
		super.name = "Community Card";
		super.location = location;
		super.track = track;
		super.isBuyable = false;
		super.locationID = id++;
	}
}

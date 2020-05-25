package domain.square;

public class SqueezePlay extends Square {
	public SqueezePlay(int location, int track) {
		super.name = "Squeeze Play";
		super.location = location;
		super.track = track;
		super.isBuyable = false;
		super.locationID = id++;
	}
}

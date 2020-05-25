package domain.square;

public class RegularSquare extends Square {
	
	public RegularSquare(String name, int location, int track) {
		super.name = name;
		super.location = location;
		super.track = track;
		super.isBuyable = false;
		super.locationID = id++;
	}}

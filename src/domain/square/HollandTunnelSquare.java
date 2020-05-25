package domain.square;

public class HollandTunnelSquare extends Square {
	public HollandTunnelSquare(String name, int location, int track) {
		super.name = name;
		super.location = location;
		super.track = track;
		super.isBuyable = false;
		super.locationID = id++;
	}
}

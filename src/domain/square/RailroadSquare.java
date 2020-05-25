package domain.square;

import domain.gamecontroller.gameController;
import domain.player.Player;

public class RailroadSquare extends PropertySquare{
	int new_track ;
	int new_position;
	
	public RailroadSquare(String name, int location, int track, int new_position, int new_track) {
		super.name = name;
		super.location = location;
		super.track = track;
		super.locationID = id++;
		this.new_position = new_position;
		this.new_track = new_track;
		super.price =200;

	}
	
	public int getRent(Player owner, int tot) {
		return 25*(2^owner.getNumRailroads());
	}
	public int getNew_track() {
		return new_track;
	}
	public void setNew_track(int new_track) {
		this.new_track = new_track;
	}
	public int getNew_position() {
		return new_position;
	}
	public void setNew_position(int new_position) {
		this.new_position = new_position;
	}
	
	
}
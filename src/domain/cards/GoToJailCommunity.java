package domain.cards;

import domain.player.Player;
import domain.square.FreeParkingSquare;

public class GoToJailCommunity extends CommunityChestCard{
	public GoToJailCommunity() {
		super.name = "Go To Jail Card";
		super.definition = "Go directly to jail."; 
		super.immediate = true;
	}
	
	public void use(Player p, FreeParkingSquare fps) {
		p.setTrack(1);
		p.setPosition(10);
		p.setInJail(true);
	}
	public String getName() {
		return super.name;
	}
}

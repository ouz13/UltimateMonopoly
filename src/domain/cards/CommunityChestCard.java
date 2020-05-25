package domain.cards;

import domain.player.Player;
import domain.square.FreeParkingSquare;

public abstract class CommunityChestCard extends Card {
	String name;
	String definition; 
	boolean immediate;
	public abstract void use(Player p, FreeParkingSquare fps);
	public abstract String getName();
}

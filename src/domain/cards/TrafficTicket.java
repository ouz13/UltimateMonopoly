package domain.cards;

import domain.player.Player;
import domain.square.FreeParkingSquare;

public class TrafficTicket extends ChanceCard {
	
	public TrafficTicket() {
		super.name = "Traffic Ticket";
		super.definition = "Pay the pool 15$"; 
		super.immediate = true;
	}
	
	public void use(Player p, FreeParkingSquare fps) {
		if(p.getMoney()< 15) {
			//bankrupt
		} else {
			p.setMoney(p.getMoney()-15);
			fps.addPool(15);
		}
	}
	public String getName() {
		return super.name;
	}
}


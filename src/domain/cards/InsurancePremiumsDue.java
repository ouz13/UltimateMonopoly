package domain.cards;

import domain.player.Player;
import domain.square.FreeParkingSquare;

public class InsurancePremiumsDue extends CommunityChestCard{
	
	public InsurancePremiumsDue() {
		super.name = "Insurance Premiums Due";
		super.definition = "Pay $50 to pool";
		super.immediate = true;
	}
	
	public void use(Player p, FreeParkingSquare sq) {
		if(p.getMoney()< 15) {
			//bankrupt
		} else {
		p.setMoney(p.getMoney()-50);
		sq.addPool(50);
		}
	}
	public String getName() {
		return super.name;
	}
}

package domain.cards;

import domain.player.Player;
import domain.square.FreeParkingSquare;

public class AdvanceToPayCorner extends ChanceCard {
	public AdvanceToPayCorner() {
		super.name = "Advance To The Pay Corner";
		super.definition = "On outer track, move to 'Payday', On center track, move to 'Go', On inner track, move to 'Bonus'"; 
		super.immediate = true;
	}
	
	public void use(Player p, FreeParkingSquare fps) {
		if(p.getTrack() ==0) {
			p.setPosition(6);
			p.setMoney(p.getMoney()+300);
		}else if(p.getTrack()==1) {
			p.setPosition(0);
			p.setMoney(p.getMoney()+200);
		}else {
			p.setPosition(28);
			p.setMoney(p.getMoney()+400);
		}
	}
	
	public String getName() {
		return super.name;
	}
}

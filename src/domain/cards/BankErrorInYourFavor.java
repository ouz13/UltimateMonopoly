package domain.cards;

import domain.player.Player;
import domain.square.FreeParkingSquare;

public class BankErrorInYourFavor  extends CommunityChestCard{
	public BankErrorInYourFavor() {
		super.name = "Bank Error In Your Favor";
		super.definition = "Collect $200 from bank";
		super.immediate = true;
	}
	
	public void use(Player p, FreeParkingSquare fps) {
		p.setMoney(p.getMoney()+200);
	}
	public String getName() {
		return super.name;
	}
}

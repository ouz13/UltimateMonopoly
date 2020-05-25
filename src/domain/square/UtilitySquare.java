package domain.square;

import domain.player.Player;

public class UtilitySquare extends PropertySquare{
	public UtilitySquare(String name, int location, int track) {
		super.name = name; 
		super.location = location;
		super.track = track;
		super.locationID = id++;
		super.price = 150;
	}	
	
	public int getRent(Player owner, int tot) {
		if(owner.getNumUtilities()==1) {
			return tot*4;
		} else if(owner.getNumUtilities()==2) {
			return tot*10;
		} else if(owner.getNumUtilities()==3) {
			return tot*20;
		} else if(owner.getNumUtilities()==4) {
			return tot*40;
		} else if(owner.getNumUtilities()==5) {
			return tot*80;
		} else if(owner.getNumUtilities()==6) {
			return tot*100;
		} else if(owner.getNumUtilities()==7) {
			return tot*120;
		} else if(owner.getNumUtilities()==8) {
			return tot*150;
		} else {
			return 0;
		}
	}

}

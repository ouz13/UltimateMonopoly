package domain.cards;

import domain.player.Player;
import domain.square.FreeParkingSquare;
import domain.square.LotSquare;

public class HurricaneCard extends ChanceCard {
	
		
		public HurricaneCard() {
			super.name = "Hurricane Card";
			super.definition = "Removes houses"; 
			super.immediate = true;
		}
		
		public void use(Player p, FreeParkingSquare fps) {
			for(int i=0; i<p.getOwnedProperties().size(); i++) {
				if(((LotSquare)p.getOwnedProperties().get(i)).getClass().getName().equals("domain.square.LotSquare") && 
						((LotSquare)p.getOwnedProperties().get(i)).getNumOfHousesBuilt() > 0) {
					((LotSquare)p.getOwnedProperties().get(i)).setNumOfHousesBuilt(((LotSquare)p.getOwnedProperties().get(i)).getNumOfHousesBuilt()-1);
					i = p.getOwnedProperties().size();
				}
			else if(((LotSquare)p.getOwnedProperties().get(i)).getClass().getName().equals("domain.square.LotSquare") && 
						((LotSquare)p.getOwnedProperties().get(i)).isHasHotel() ==true) {
				i = p.getOwnedProperties().size();
				((LotSquare)p.getOwnedProperties().get(i)).setHasHotel(false);
			}else if(((LotSquare)p.getOwnedProperties().get(i)).getClass().getName().equals("domain.square.LotSquare") && 
					((LotSquare)p.getOwnedProperties().get(i)).isHasSkyscraper() ==true){
				i = p.getOwnedProperties().size();
				((LotSquare)p.getOwnedProperties().get(i)).setHasSkyscraper(false);
			}else {
				
			}
			
		}
		}
		public String getName() {
			return super.name;
		}
	

}

package UI.Buttons;

import java.io.IOException;

import javax.swing.JOptionPane;

import UI.GameBoard.GameBoard;
import domain.gamecontroller.gameController;

public class numberOfPlayersGetter {
	public int numPlayers = 0;
	public numberOfPlayersGetter() {
		boolean bool = true;
		String numPlayersInput = null;
		while(bool) {
		numPlayersInput = JOptionPane.showInputDialog("Would you like to start a new game or load a game?(Type N for New Game"
				+ ", L for Loading a Game.");
		if(numPlayersInput.equals("L")|| numPlayersInput.equals("N")) {
			bool = false;
		}
		}
		if(numPlayersInput.equals("N")) {
			boolean b = true;
			while(b) {
			numPlayersInput = JOptionPane.showInputDialog("Enter number of players: ");
			try{
 			numPlayers = Integer.parseInt(numPlayersInput);
 			b = false;
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Please enter a number!");
				b=true;
			}
			}
		}
		
	
		
	}
}

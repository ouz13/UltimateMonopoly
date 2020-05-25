import java.awt.Color;
import java.io.IOException;
import java.util.Scanner;

import UI.GameBoard.GameBoard;
import domain.gamecontroller.gameController;
import domain.player.Player;
import domain.square.LotSquare;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		GameBoard UI = new GameBoard();
		gameController controller = new gameController();
		controller.startGame();
		controller.addPlayer("Mustafa", 3200, Color.RED);
		LotSquare s = (LotSquare) controller.getSquares().get(1);
		s.setOwner(controller.getPlayers().get(0));
		controller.saveGame();
		controller.LoadGame("SavedGameData.json");
	}

}
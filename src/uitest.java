import javax.swing.JLabel;
import javax.swing.JPanel;

import UI.Buttons.numberOfPlayersGetter;
import UI.GameBoard.GameBoard;
import domain.Board;
import domain.gamecontroller.gameController;
import domain.square.LotSquare;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;     
public class uitest {

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		numberOfPlayersGetter g = new numberOfPlayersGetter();
		
		GameBoard board = new GameBoard(g);
		
		board.controller.startGame();
		//SIKINTI SU, BUNU KULLANINCA KENDISI VERIYOR NEW GAMEI AMA NEW GAME SOL USTTE KALIYOR
		if(g.numPlayers>0) {
		////	board.newGame.doClick();
		
			
		}
		
		
	
	} 
	}



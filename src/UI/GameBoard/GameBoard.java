package UI.GameBoard;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import domain.Board;
import domain.gamecontroller.gameController;
import UI.Buttons.numberOfPlayersGetter;
import domain.square.LotSquare;
public class GameBoard extends JPanel implements ActionListener {
	
	public Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public BufferedImage image;
	public JFrame frame;
	public int boardSize = (int)(screenSize.height*0.90); // 0.90 to fit the screen
	public JButton exit;
	public JButton roll;
	public JButton manage;
	public JButton endTurn;
	public JButton load ;
	public JButton newGame;
	public JButton save;
	public gameController controller = new gameController();
	public int numPlayers;
	public List<JLabel> cash = new ArrayList<JLabel>();
	public List<JLabel> location = new ArrayList<JLabel>();
	public List<JPanel> cashpanels = new ArrayList<JPanel>();
	public List<JPanel> locpanels = new ArrayList<JPanel>();
	
	public GameBoard(numberOfPlayersGetter g) {
		numPlayers = g.numPlayers;
		frame = new JFrame();
		addMonopolyBoardImage(frame);
		setInitialFrame(frame);
		//addButtonsToFrame(frame);
		addButtonsAA();
		

		
	}
	
	public void actionPerformed(ActionEvent e) {
		int right_gap_width = screenSize.width - boardSize;
		int player_score_height = screenSize.height /2;
		
		if(e.getSource()== newGame) {
			newGamePressed();
			JLabel label[] = new JLabel[controller.getPlayers().size()];
			System.out.println(label.length);
			
		/*	for(int i=0; i<controller.getPlayers().size(); i++) {
				label[i] = new JLabel("");
				frame.add(label[i]);
				label[i].setLocation((int)(boardSize + (0.5 +i)*right_gap_width/numPlayers) - 50/numPlayers, 60);
				label[i].setVisible(true);
				label[i].setText("Cash : " + controller.getPlayers().get(i));
				
			} */
			
		}
		if(e.getSource()== exit) {
			exitPressed();
		}
		if(e.getSource()== roll) {
			rollPressed();
		}
		if(e.getSource() == endTurn) {
			endTurnPressed();
		}
		if(e.getSource() == save) {
			saveGamePressed();
		}
		if(e.getSource()==load) {
			loadPressed();
		}
		if(e.getSource()==manage) {
			managePressed();
		}
	}
	
	//1)add colors of pieces to colors of strings of the players
	//2)use player number to open as many slots as needed, write closed for non playing slots
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, boardSize, boardSize, null);
		//variables needed for score table
		
	}
	public void setInitialFrame(JFrame frame) {
		frame.setSize(this.screenSize.width, this.screenSize.height);
		frame.setContentPane(this);
		frame.setVisible(true);
		frame.setLayout(null);
	}
	
	
	
	public void addButtonsAA() {
		newGame = new JButton("NEW GAME");
		frame.add(newGame);
		newGame.setLocation((int)(this.boardSize*1.05+300),(int)(this.boardSize*0.90));
		newGame.setSize(100, 100);
		newGame.addActionListener(this);
		
		roll = new JButton("START TURN");
		frame.add(roll);
		roll.setLocation((int)(this.boardSize*1.05+0),(int)(this.boardSize*0.90));
		roll.setSize(100, 100);
		roll.addActionListener(this);
		
		exit = new JButton("EXIT");
		frame.add(exit);
		exit.setLocation((int)(this.boardSize*1.05+600),(int)(this.boardSize*0.90));
		exit.setSize(100, 100);
		exit.addActionListener(this);
		
		endTurn = new JButton("END TURN");
		frame.add(endTurn);
		endTurn.setLocation((int)(this.boardSize*1.05+700),(int)(this.boardSize*0.90));
		endTurn.setSize(100, 100);
		endTurn.addActionListener(this);
		
		load = new JButton("LOAD");
		frame.add(load);
		load.setLocation((int)(this.boardSize*1.05+400),(int)(this.boardSize*0.90));
		load.setSize(100, 100);
		load.addActionListener(this);
		
		save = new JButton("SAVE");
		frame.add(save);
		save.setLocation((int)(this.boardSize*1.05+500),(int)(this.boardSize*0.90));
		save.setSize(100, 100);
		save.addActionListener(this);
		
		manage = new JButton("MANAGE");
		frame.add(manage);
		manage.setLocation((int)(this.boardSize*1.05+100),(int)(this.boardSize*0.90));
		manage.setSize(100, 100);
		manage.addActionListener(this);
	}
	
	public void addMonopolyBoardImage(JFrame frame) {
		try {
			image = ImageIO.read(new File("board.png"));
		} catch (IOException e) {
			System.out.println("File cant be found.");
		}
	}
	
	public void exitPressed() {
		System.exit(0);
	}
	public void loadPressed() {
		controller.startSavedGame(controller.LoadGame("SavedGameData.json"));
	}
	public void rollPressed() {
		if(controller.getCurrentPlayersCurrentSquare().getName().equals("Subway")) {
			int trck = 0;
			int pos = 0;
			boolean b = true;
			while(b) {
			String track = JOptionPane.showInputDialog("Enter the track you would like to go:  ");
			try{
 			trck = Integer.parseInt(track);
 			
 			b = false;
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Please enter a number!");
				b=true;
			}
			if(trck<0 || trck > 2) {
				b = true;
				JOptionPane.showMessageDialog(null, "Please enter a valid number!");
			}
			b =true;
			}
			while(b) {
				String position = JOptionPane.showInputDialog("Enter the position you would like to go:  ");
				try{
	 			pos = Integer.parseInt(position);
	 			
	 			b = false;
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Please enter a number!");
					b=true;
				}
				if((trck==0 && pos>23) || (trck==1 && pos>40) || (trck==2 && pos >56) || pos<0) {
					b = true;
					JOptionPane.showMessageDialog(null, "Please enter a valid number!");
				}
				}
			controller.directRouteMove(trck, pos);
			controller.doPropertySquareAction();
			controller.doGoToJailSquare();
			controller.doChanceSquareAction();
			controller.doCommunitySquareAction();
			controller.doFreeParkingSquareAction();
			controller.doIncomeTax();
			controller.doHollandTunnelSquare();
	
			
		} else {
			if(controller.getCurrentPlayer().isInJail()==false) {
		controller.rollDice();
		JOptionPane.showMessageDialog(null, controller.getCurrentPlayer().getName()+ " rolled " + controller.getTotal());
		controller.doPropertySquareAction();
		controller.doGoToJailSquare();
		controller.doChanceSquareAction();
		controller.doCommunitySquareAction();
		controller.doFreeParkingSquareAction();
		controller.doIncomeTax();
		controller.doHollandTunnelSquare();

		}
			else {
			controller.rollInJail();
			controller.doPropertySquareAction();
			controller.doGoToJailSquare();
			controller.doChanceSquareAction();
			controller.doCommunitySquareAction();
			controller.doFreeParkingSquareAction();
			controller.doIncomeTax();
			controller.doHollandTunnelSquare();

		}
		
		}
		
			location.get(controller.getCurrentPlayer().getPlayerID()).setText("L: " + controller.getCurrentPlayersCurrentSquare().getName());
		
		
		//String rolled = JOptionPane.showMessageDialog(frame, "You rolled " + this.controller.getBoard().getDie1().getFaceValue() +
	//			 " ");
		
		
		
	}
	public void endTurnPressed() {
		for(int i=0; i<numPlayers; i++) {
			cash.get(i).setText("Cash : $" +controller.getPlayers().get(i).getMoney());
			
		}
		
		
		controller.nextTurn();
		for(int i=0; i< controller.getPlayers().size(); i++) {
			int last_deed_loc = controller.getPlayers().get(i).getOwnedProperties().size();
			if(last_deed_loc>0) {
				JLabel last_added = new JLabel(controller.getPlayers().get(i).getOwnedProperties().get(last_deed_loc -1 ).getName());
				frame.add(last_added);
				last_added.setSize(100,30);
				last_added.setLocation(1000+100*i, 160 + 30*last_deed_loc);
			}
		}
	}
	
	public void saveGamePressed() {
		controller.saveGame();
	}
	
	public void managePressed() {
		int h = 0;
		String choose = JOptionPane.showInputDialog(null, "Would you like to sell or improve property or rebuy mortgage?(sell/improve/rebuy)");
		
		if(choose.equals("improve")) {
		boolean b = true;
		boolean c = true;
		
			int trck = 0;
			int pos = 0;
			while(c) {
			while(b) {
			String track = JOptionPane.showInputDialog(null, "Enter the track you would like to build:  ");
			try{
 			trck = Integer.parseInt(track);
 			
 			b = false;
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Please enter a number!");
				b=true;
			}
			if(trck<0 || trck > 2) {
				b = true;
				JOptionPane.showMessageDialog(null, "Please enter a valid number!");
			}
			}
			b = true;
			while(b) {
				String position = JOptionPane.showInputDialog("Enter the position you would like to build:  ");
				try{
	 			pos = Integer.parseInt(position);
	 			
	 			b = false;
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Please enter a number!");
					b=true;
				}
				if((trck==0 && pos>23) || (trck==1 && pos>40) || (trck==2 && pos >56) || pos<0) {
					b = true;
					JOptionPane.showMessageDialog(null, "Please enter a valid number!");
				}
				}
			for(int i=0; i<controller.getCurrentPlayer().getOwnedProperties().size(); i++) {
				if(controller.getCurrentPlayer().getOwnedProperties().get(i).getTrack()==trck && 
						controller.getCurrentPlayer().getOwnedProperties().get(i).getLocation()==pos) {
					c = false;
				}
			}
			}
			b = true;
			
		
		boolean d = true;
		String hhs = JOptionPane.showInputDialog( "num houses " + ((LotSquare)controller.getBoard().getSqByTP(trck, pos)).getNumOfHousesBuilt() 
				+ " there is hotel " + ((LotSquare)controller.getBoard().getSqByTP(trck, pos)).isHasHotel() +
				" there is skyscraper " + ((LotSquare)controller.getBoard().getSqByTP(trck, pos)).isHasSkyscraper()
			+	"Do you want to build house, hotel or skyscraper?(Type house, hotel or skyscraper)");
		while(d) {
		if(hhs.equals("house")) {
			int n=0;
			while(b) {
			String num = JOptionPane.showInputDialog("How many houses would you like to build?");
			try{
	 		    n = Integer.parseInt(num);
	 			b = false;
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Please enter a valid number!");
				b=true;
			}
			}
			
			JOptionPane.showMessageDialog(null, n + " houses built.");
			controller.getCurrentPlayer().buildHouse(trck, pos, n);
			d = false;
			
		}else if(hhs.equals("hotel")) {
			JOptionPane.showMessageDialog(null, "Hotel built.");
			controller.getCurrentPlayer().buildHotel(trck, pos);
			d = false; 
			
		}else if(hhs.equals("skyscraper")) {
			JOptionPane.showMessageDialog(null, "Skyscraper built.");
			controller.getCurrentPlayer().buildSkyscraper(trck, pos);
			d = false;
		}else {
			d =true;
		}}}else if(choose.equals("sell")) {
			String d = JOptionPane.showInputDialog("Which deed would you like to sell?(0-" + (controller.getCurrentPlayer().getOwnedProperties().size()-1) + ")");
			try {
				h = Integer.parseInt(d);
			}catch(Exception e){
				h = 0;
			}
			
			if(((LotSquare)controller.getCurrentPlayer().getOwnedProperties().get(h)).getClass().getName().equals("domain.square.RailroadSquare")) {
				controller.getCurrentPlayer().setNumRailroads(controller.getCurrentPlayer().getNumRailroads()-1);
			}else if(((LotSquare)controller.getCurrentPlayer().getOwnedProperties().get(h)).getClass().getName().equals("domain.square.UtilitySquare")) {
				controller.getCurrentPlayer().setNumRailroads(controller.getCurrentPlayer().getNumUtilities()-1);
			}
			controller.getCurrentPlayer().getOwnedMortgages().add(((LotSquare)controller.getCurrentPlayer().getOwnedProperties().get(h)));
			controller.getCurrentPlayer().sellDeed(h);
			
		} else {
			String r = JOptionPane.showInputDialog("Which deed would you like to rebuy?(0-" + (controller.getCurrentPlayer().getOwnedMortgages().size()-1) + ")");
			try {
				h = Integer.parseInt(r);
			}catch(Exception e){
				h = 0;
			}
			controller.getCurrentPlayer().setMoney(controller.getCurrentPlayer().getMoney() - controller.getCurrentPlayer().getOwnedMortgages().get(h).getPrice()/2);
			controller.getCurrentPlayer().getOwnedProperties().add(controller.getCurrentPlayer().getOwnedMortgages().get(h));
			controller.getCurrentPlayer().getOwnedMortgages().remove(h);
			
		}
	}
	
	public void newGamePressed() {
	//	numPlayers = 0;
		controller.shuffle1(controller.getBoard().getChanceCards());
		controller.shuffle2(controller.getBoard().getCommunityChestCards());
		String[] colors = {"BLUE", "GREEN", "RED", "YELLOW", "ORANGE", "MAGENTA", "BLACK", "WHITE"};
		Color c = null;
		boolean colorName = true;
		
	//	while(numPlayers < 2 || numPlayers > 8) {
	//	String numPlayersInput = JOptionPane.showInputDialog("Enter number of players: ");
	//	try {
	//	 numPlayers = Integer.parseInt(numPlayersInput);
	//	}catch(Exception e2) {
	//		System.out.println("Not number");
	//	}
	//	}
		for(int i=0; i < numPlayers ; i++) {
			String playerName = JOptionPane.showInputDialog("Enter name for player " + (i+1) + ": ");
			String playerColor = "";
			while(colorName) {
			playerColor = JOptionPane.showInputDialog("Enter color for player "
					+ (i+1) + " : (Colors available: " + colors[0] + " "+ colors[1]+ " " +colors[2]+ " " +
					colors[3]+ " " +colors[4]+ " " +colors[5]+ " " +
					colors[6]+ " " +colors[7] + ")" );
			for(int k=0; k<8; k++) {
				if(colors[k].equals(playerColor)) {
					colorName = false;
				}
			}
			}
			for(int j=0; j<8; j++) {
				if(colors[j].equals(playerColor)) {
					colors[j] = "";
				}
			}
			//null senaryosunu degistir
			colorName = true;
			switch(playerColor) { 
			case "BLUE": c=Color.BLACK;
			case "GREEN": c=Color.GREEN;
			case "RED": c=Color.RED;
			case "YELLOW": c=Color.YELLOW;
			case "ORANGE": c=Color.ORANGE;
			case "MAGENTA": c=Color.MAGENTA;
			case "BLACK": c=Color.BLACK;
			case "WHITE": c=Color.WHITE;
			}
			this.controller.addPlayer(playerName,3000, c);
			JLabel n = new JLabel(playerName);
			JLabel l = new JLabel("Player "+ i);
			frame.add(l);
			frame.add(n);
			n.setSize(100,30);
			l.setSize(100,30);
			l.setLocation(1000+i*100, 10);
			n.setLocation(1000+i*100, 40);
			for(int j=0; j<controller.numberOfPlayers(); j++) {
				JLabel assets = new JLabel("Assets");
				frame.add(assets);
				assets.setLocation(1000 + 100*j, 130);
				assets.setSize(100,30);
				cash.add(new JLabel("Cash : $" +controller.getPlayers().get(j).getMoney() ));
				location.add(new JLabel("L: " + controller.getCurrentPlayersCurrentSquare().getName()));
				cash.get(j).setSize(100, 30);
				cash.get(j).setLocation(1000+100*j, 70);
				location.get(j).setSize(100, 30);
				location.get(j).setLocation(1000+100*j, 100);
				frame.add(cash.get(j));
				frame.add(location.get(j));
				}
			
		}
		
			
	
		
	
	
	
}
}

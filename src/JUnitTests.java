import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import domain.Board;
import domain.Die;
import domain.gamecontroller.gameController;
import domain.player.Player;
import domain.square.LotSquare;
import technical.DataPackage.GameStateHolder;

public class JUnitTests {
	/*private static Player p1;
	private static Player p2;
	private static LotSquare s1;
	private static gameController c1;
	private static Board b1;
	private static GameStateHolder gsh1;
	private static Die d1;
	//Player Tests
	@Test
	public void testPlayer() {
		p1 = new Player("Player",1500, Color.BLACK);
		assertTrue("repOk is true", p1.repOK());
	}
	@Test
	public void testNegativeMoney() {
		p1 = new Player("Player",-10, Color.BLACK);
		assertTrue("repOk is false", p1.repOK());
	}
	@Test
	public void testZeroMoney() {
		p1 = new Player("Player",0, Color.BLACK);
		assertTrue("repOk is true", p1.repOK());
	}
	@Test
	public void testNegativePosition() {
		p1 = new Player("Player",1500, Color.BLACK);
		p1.setPosition(-10);
		assertTrue("repOk is false", p1.repOK());
	}
	@Test
	public void testHighPosition() {
		p1 = new Player("Player",1500, Color.BLACK);
		p1.setPosition(150);
		assertTrue("repOk is false", p1.repOK());
	}
	@Test
	public void testNegativeTrack() {
		p1 = new Player("Player",1500, Color.BLACK);
		p1.setTrack(-1);
		assertTrue("repOk is false", p1.repOK());
	}
	@Test
	public void testHighTrack() {
		p1 = new Player("Player",1500, Color.BLACK);
		p1.setTrack(5);
		assertTrue("repOk is false", p1.repOK());
	}
	@Test
	public void testAfford() {
		p1 = new Player("Player",1500, Color.BLACK);
		p1.canAfford(1000);
		assertTrue("repOk is true", p1.repOK());
	}
	@Test
	public void testPayPayableRent() {
		p1 = new Player("Player", 1500, Color.BLACK);
		p1.payRent(p2, 1000);
		assertTrue("repOk is true", p1.getMoney()==500);
	}
	@Test
	public void testPayunPayableRent() {
		p1 = new Player("Player", 1500, Color.BLACK);
		p1.payRent(p2, 2000);
		assertTrue("repOk is false", p1.repOK());
	}
	
	
	//Property Tests
	@Test
	public void testProperty() {
		s1 = new LotSquare("Square", 10, 1, 180, 26, 50, 50, 50, 2, 2);
		assertTrue("Property Contructor repok is true",s1.repOk());
	}
	@Test
	public void testGetRentProperty() {
		s1 = new LotSquare("Square", 10, 1, 180, 26, 50, 50, 50, 2, 2);
		s1.getRent();
		assertTrue("Property Contructor repok is true",s1.repOk());
		
	}
	@Test
	public void testGetRentNoHouse() {
		s1 = new LotSquare("Square", 10, 1, 180, 26, 50, 50, 50, 2, 2);
		s1.setNumOfHousesBuilt(0); 
		assertTrue("Property Contructor repok is true",s1.getRent()==26);
	}
	@Test
	public void testGetRentNoHotel() {
		s1 = new LotSquare("Square", 10, 1, 180, 26, 50, 50, 50, 2, 2);
		s1.setHasHotel(false); 
		assertTrue("Property Contructor repok is true",s1.getRent()==26);
	}
	@Test
	public void testGetRentNoSkyscraper() {
		s1 = new LotSquare("Square", 10, 1, 180, 26, 50, 50, 50, 2, 2);
		s1.setHasSkyscraper(false);
		assertTrue("Property Contructor repok is true",s1.getRent()==26);
	}
	@Test
	public void testGetRentOneHouse() {
		s1 = new LotSquare("Square", 10, 1, 180, 26, 50, 50, 50, 2, 2);
		s1.setNumOfHousesBuilt(1); 
		assertTrue("Property Contructor repok is true",s1.getRent()==50);
	}
	@Test
	public void testGetRentMajority() {
		s1 = new LotSquare("Square", 10, 1, 180, 26, 50, 50, 50, 2, 2);
		s1.setMajorityOwnership(true);
		assertTrue("Property Contructor repok is true",s1.getRent()==52);
	}
	@Test
	public void testGetRentMonopoly() {
		s1 = new LotSquare("Square", 10, 1, 180, 26, 50, 50, 50, 2, 2);
		s1.setMonopoly(true); 
		assertTrue("Property Contructor repok is true",s1.getRent()==78);
	}
	@Test
	public void testBuildHouse() {
		s1 = new LotSquare("Square", 10, 1, 180, 26, 50, 50, 50, 2, 2);
		s1.setNumOfHousesBuilt(1); 
		assertTrue("Property Contructor repok is true",s1.repOk());
	}
	@Test
	public void testSetOwner() {
		s1 = new LotSquare("Square", 10, 1, 180, 26, 50, 50, 50, 2, 2);
		s1.setOwner(p1);
		assertTrue("Property Contructor repok is true",s1.getOwner()==p1);
	}
	@Test
	public void testGameController() {
		c1 = new gameController();
		assertTrue("repOk is true", p1.repOK());
	}
	@Test
	public void testsetBoard() {
		c1 = new gameController();
		b1 = b1.getInstance();
		c1.setBoard(b1);
		assertTrue("repOk is true", c1.getBoard() == b1);
	}
	@Test
	public void testGameStateHolder() {
		gsh1 = new GameStateHolder();
		assertTrue("repOk is true", gsh1.repOK());
	}
	@Test
	public void testGetTurn() {
		gsh1 = new GameStateHolder();
		gsh1.setTurn(1);
		assertTrue("repOk is true", gsh1.getTurn()==1);
	}
	@Test
	public void testDie() {
		d1 = new Die();
		assertTrue("repOk is true", d1.repOK());
	}
	@Test 
	public void testSetFaceValue() {
		d1 = new Die();
		d1.setFaceValue(2);
		assertTrue("repOk is true", d1.getFaceValue()==2);
	}
	@Test 
	public void testGetFaceValue() {
		d1 = new Die();
		d1.setFaceValue(2);
		assertTrue("repOk is true", d1.getFaceValue()==2);
	}
	@Test
	public void testBoard() {
		b1 = b1.getInstance();
		assertTrue("repOk is true", b1.repOK());
	}
	@Test
	public void testsetTurnBoard() {
		b1 = b1.getInstance();
		b1.setTurn(1);
		assertTrue("repOk is true", b1.getTurn()==1);
	}
	@Test
	public void testsetNumPlayerBoard() {
		b1 = b1.getInstance();
		b1.setNumPlayers(1);
		assertTrue("repOk is true", b1.getNumPlayers()==1);
	}
	*/
	
}
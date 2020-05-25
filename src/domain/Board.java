package domain;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import domain.cards.AdvanceToPayCorner;
import domain.cards.BankErrorInYourFavor;
import domain.cards.Card;
import domain.cards.ChanceCard;
import domain.cards.CommunityChestCard;
import domain.cards.GoToJailCard;
import domain.cards.GoToJailCommunity;
import domain.cards.HurricaneCard;
import domain.cards.InsurancePremiumsDue;
import domain.cards.TrafficTicket;
import domain.player.Player;
import domain.square.BonusSquare;
import domain.square.ChanceCardSquare;
import domain.square.CommunityCardSquare;
import domain.square.FreeParkingSquare;
import domain.square.GoSquare;
import domain.square.GoToJailSquare;
import domain.square.HollandTunnelSquare;
import domain.square.IncomeTaxSquare;
import domain.square.JailSquare;
import domain.square.LotSquare;
import domain.square.PayDaySquare;
import domain.square.RailroadSquare;
import domain.square.RegularSquare;
import domain.square.RollOnceSquare;
import domain.square.Square;
import domain.square.SqueezePlay;
import domain.square.SubwaySquare;
import domain.square.UtilitySquare;
// TODO: Auto-generated Javadoc
/**
 * The Class Board.
 * A singleton class that hold the objects that are relevant to the board of the game. Related objects
 * are Squares, Players and Cards. These objects will be initialized and be manipulated here.
 * 
 * @author 203_pmoc
 */
public final class Board {
 
 /** The instance field that holds the singleton instance. */
 private static  Board instance = null;
 
 /** The players list that holds the Player objects. */
 private List<Player> players;
 
 /** The squares list that holds the Square objects. */
 private List<Square> squares;
 
 /** The chance cards list that holds the chanceCards objects that are on the board. */
 private List<ChanceCard> chanceCards;
 
 /** The community chest cards list that holds the communityChestCards objects that are on the board. */
 private List<CommunityChestCard> communityChestCards;
 
 /** A Die object that corresponds to the first die. */
 private Die die1;
 
 /** A Die object that corresponds to the second die. */
 private Die die2;
 
 /** A SpeedDie object that corresponds to the speed die. */
 private SpeedDie speedDie;
 
 /** Field that holds the information of which player's turn it is . */
 private int turn;
 
 /** Field that holds the number of alive players in the game. */
 private int numPlayers;
 
 private int jailCounter = 0;
 
 private FreeParkingSquare free_parking;
 
 public FreeParkingSquare getFree_parking() {
	return free_parking;
}

public void setFree_parking(FreeParkingSquare free_parking) {
	this.free_parking = free_parking;
}

/**
  * Instantiates a new board by creating empty lists for squares and players.
  * Initializes the board by populating squares and players lists. Initial turn is set to 0 
  */
 private Board() {
  squares = new ArrayList<Square>();
  players = new ArrayList<Player>();
  chanceCards = new ArrayList<ChanceCard>();
  communityChestCards = new ArrayList<CommunityChestCard>();
  initializeBoard();
  initializeCards();
  turn = 0;
 }
 
 /**
  * Gets the single instance of Board.
  * 
  * @effects: Creates a singleton object if there is no instance and returns that newly created instance.
  * If instance was already created, then already created instance is returned.
  * @modifies: instance field of Board object
  *
  * @return single instance of Board
  */
 public static Board getInstance() {
  if (instance == null) {
   synchronized(Board.class) {
    if (instance == null) {
     instance = new Board();
    }
   }
  }
  return instance;
 }
 
/**
 * Initialize board.
 * @requires: Squares and Cards Lists should be created
 * @modifies: squares and cards lists.
 * @effects: Square and Cards objects are created according to the Ultimate Monopoly game rules. These objects are put into the corresponding lists.
 */
// Buraya Card Initialization eklenmeli.
 public void initializeBoard() {
  /**
   * @requires: Squares and Cards Lists should be created
   * @modifies: Squares and Cards Lists are populated
   * @effects: Square and Cards objects are created according to the Ultimate Monopoly game rules. These objects are put into the corresponding lists.
   */
	 
	 //////////////////////////////  START OF TRACK 0 ///////////////////////////////////
	 
	 //////////////////// KURALLARDA SQUEEZE PLAY DE VAR. RANDOM GELCEK FEATURE OLABİLİR!
	 SqueezePlay squeezePlay = new SqueezePlay(0, 0);
	 LotSquare the_embarcadero = new LotSquare("The Embarcadero", 1, 0, 210, 17, 100);
	 LotSquare fishermans_wharf = new LotSquare("Fisherman's Wharf", 2, 0, 250, 21, 100);
	 UtilitySquare telephone_company = new UtilitySquare("Telephone Company", 3, 0);
	 CommunityCardSquare chestCard_01 = new CommunityCardSquare( 4, 0);
	 LotSquare beacon_street = new LotSquare("Beacon St.", 5, 0, 330, 30, 200);
	 BonusSquare bonus_square = new BonusSquare("BONUS Square", 6, 0);
	 LotSquare boylston_street = new LotSquare("Boylston St.", 7, 0, 330, 30, 200);
	 LotSquare newbury_street = new LotSquare("Newbury St.", 8, 0, 380, 40, 200);
	 RailroadSquare transit_station_1 = new RailroadSquare("TRANSIT STATION", 9, 0, 15,1);
	 LotSquare fifth_ave = new LotSquare("Fifth Ave.", 10, 0, 430, 60, 300);
	 LotSquare madison_ave = new LotSquare("Madison Ave.", 11, 0, 430, 60, 300);
	 RegularSquare roll_three = new RegularSquare("Roll Three", 12, 0);
	 LotSquare wall_street = new LotSquare("Wall St.", 13, 0, 500, 80, 300);
  
  ///////////// TAX REFUND OZELLIĞI EKLENEBİLİR. KURALLARA BAKILIP RANDOM GELECEK ŞEYLER ARASINDA BU VAR MI KARARLAŞTIRILMALI!!!!!
  IncomeTaxSquare tax_refund = new IncomeTaxSquare("Tax Refund", 14, 0);
  UtilitySquare gas_company = new UtilitySquare("Gas Company", 15, 0);
  ChanceCardSquare chanceCard_01 = new ChanceCardSquare(16,0);
  LotSquare florida_ave = new LotSquare("Floride Ave.", 17, 0, 130, 9, 50);
  
  ////////////// TUNNEL ÖZELLİĞİ İÇİN KURALLARA VE KRİTERLERE BAKILMALI!!!!!
  HollandTunnelSquare holland_tunnel1 = new HollandTunnelSquare("Holland Tunnel", 18, 0); 
  LotSquare miami_ave = new LotSquare("Miami Ave.", 19, 0, 130, 9, 50);
  LotSquare biscayne_ave = new LotSquare("Biscayne Ave.", 20, 0, 150, 11, 50);
  RailroadSquare transit_station_2 = new RailroadSquare("TRANSIT STATION", 21, 0, 35, 1);
  RegularSquare reverse_direction = new RegularSquare("Reverse Direction", 22, 0);
  LotSquare lombard_st = new LotSquare("Lombard St.", 23, 0, 210, 17, 100);
  
  //////////////////////////////////////// END OF TRACK 0 ///////////////////////////////////
  
  
  
  
  //////////////////////////////////// START OF TRACK 1 //////////////////////////////////////
  GoSquare go = new GoSquare("Go", 0, 1, false);
  LotSquare mediterranean_ave = new LotSquare("Mediterranean Ave", 1, 1, 60, 2, 50);
  CommunityCardSquare community_chest_11 = new CommunityCardSquare(2,1);
  LotSquare baltic_ave = new LotSquare("Baltic Avenue", 3, 1, 60, 4, 50);
  
  /////////// INCOME TEX ÖZELLİĞİ İÇİN KURALLARA VE KRİTERLERE BAKILMALI!!!!
  IncomeTaxSquare income_tax = new IncomeTaxSquare("Income Tax", 4, 1); 
  RailroadSquare transit_station_11 = new RailroadSquare("Transit Station", 5, 1, 7, 2);
  LotSquare oriental_ave = new LotSquare("oriental_ave", 6, 1, 100, 6, 50);
  ChanceCardSquare chance_11 = new ChanceCardSquare(7,1);
  LotSquare vermont_ave = new LotSquare("vermont_ave", 8, 1, 100, 6, 50);
  LotSquare connecticut_ave = new LotSquare("connecticut_ave", 9, 1, 120, 8, 50);
  JailSquare jail = new JailSquare("Jail",10,1);
  LotSquare st_charles_place = new LotSquare("St. Charles Place", 11, 1, 140, 10, 100);
  UtilitySquare electric_company = new UtilitySquare("Electric Company", 12, 1); 
  LotSquare states_ave = new LotSquare("States Ave", 13,1, 140, 10, 100);
  LotSquare virginia_ave = new LotSquare("Virginia Ave", 14, 1, 160, 12, 100);
  RailroadSquare pennsylvania = new RailroadSquare("Pennysylvania",15,1,9,0);
  LotSquare st_james_place = new LotSquare("St. James Place", 16, 1, 180, 14, 100);
  CommunityCardSquare community_chest_12 = new CommunityCardSquare(17,1);
  LotSquare tennessee_ave = new LotSquare("Tennessee Avenue", 18, 1, 180, 14, 100);
  LotSquare new_york_ave = new LotSquare("New York Avenue", 19, 1, 200, 16, 100);
  free_parking = new FreeParkingSquare(20,1);
  LotSquare kentucky_ave = new LotSquare("kentucky_ave", 21, 1, 200, 18, 150);
  ChanceCardSquare chance_12 = new ChanceCardSquare(22,1);
  LotSquare indiana_ave = new LotSquare("indiana_ave", 23, 1, 200, 18, 150);
  LotSquare illinois_ave = new LotSquare("illinois_ave", 24, 1, 240, 20, 150);
  RailroadSquare transit_station_12 = new RailroadSquare("Transit Station", 25, 1, 35, 2);
  LotSquare atlantic_ave = new LotSquare("St. Charles Place", 26, 1, 260, 22, 150);
  UtilitySquare water_company = new UtilitySquare("Electric Company", 27, 1); 
  LotSquare ventnor_ave = new LotSquare("States Ave", 28,1, 260, 22, 150);
  LotSquare marvin_ave = new LotSquare("Virginia Ave", 29, 1, 280, 24, 150);
  GoToJailSquare go_to_jail = new GoToJailSquare("Go To Jail", 30, 1);
  LotSquare pacific_ave = new LotSquare("pacific_ave", 31, 1, 300, 26, 200);
  CommunityCardSquare community_chest_13 = new CommunityCardSquare(32,1);
  LotSquare north_carolina_ave = new LotSquare("north_carolina_ave", 33, 1, 300, 26, 200);
  LotSquare pennslyvania_ave = new LotSquare("pennslyvania_ave", 34, 1, 320, 28, 200);
  RailroadSquare short_line = new RailroadSquare("Short Line", 35, 1, 21, 0);
  ChanceCardSquare chance_13 = new ChanceCardSquare(36,1); 
  LotSquare park_place = new LotSquare("park_place", 37, 1, 400, 35, 200);  
  
/////////// LUXURY TEX ÖZELLİĞİ İÇİN KURALLARA VE KRİTERLERE BAKILMALI!!!!
  IncomeTaxSquare luxury_tax = new IncomeTaxSquare("Luxury Tax", 38, 1); 
  LotSquare boardwalk = new LotSquare("boardwalk", 39, 1, 400, 50, 200);

 //////////////////////// END OF TRACK 1  ////////////////////////////
  

  ////////////////////////////////// START OF TRACK 2 ///////////////////////////
  RegularSquare stock_exchange = new RegularSquare("Stock Exchange", 0, 2);
  LotSquare lake_st = new LotSquare("Lake St.", 1, 2, 30, 1, 50);
  CommunityCardSquare chestCard_21 = new CommunityCardSquare( 2, 2);
  LotSquare nicollet_ave = new LotSquare("Nicollet Ave.", 3, 2, 30, 1, 50);
  LotSquare hennepin_ave = new LotSquare("Hennepin Ave.", 4, 2, 60, 3, 50);
  
  ///////// BUS TICKET KURALLARDA VAR. RANDOM FEATURE BU OLABİLİR. IMPLEMENT EDEBİLİRİZ.
  RegularSquare bus_ticket = new RegularSquare("Bus Ticket", 5, 2);
  
  ///////// CAB COMPANY KURALLARDA VAR. RANDOM FEATURE BU OLABİLİR. IMPLEMENT EDEBİLİRİZ. NORMALDE BUYABLE BIR PROPERTY BURASI. O YUZDEN PROPERTY SQUARE'I
  /////// EXTEND EDEN BIR CLASS OLUSTURULMALI.
  RegularSquare checker_cab_co = new RegularSquare("Checker Cab Co.", 6, 2);
  RailroadSquare reading_railroad = new RailroadSquare("Reading Railroad", 7, 2, 5,1); 
  LotSquare the_esplanade = new LotSquare("The Esplanade", 8, 2, 100, 5, 50);
  LotSquare canal_street = new LotSquare("Canal St.", 9, 2, 100, 5, 50);
  ChanceCardSquare chanceCard_21 = new ChanceCardSquare(10,2);
  UtilitySquare cable_company = new UtilitySquare("Cable Company", 11, 2);
  LotSquare magazine_street = new LotSquare("Magazine Street", 12, 2, 120, 8, 50);
  LotSquare bourbon_street = new LotSquare("Bourbon St.", 13, 2, 60, 3, 50);
  
  //////////////////// YUKARDAKI TUNNEL ILE AYNI DAVRANIŞI VAR. RAILROAD'A ÇOK BENZIYO ASLINDA O YÜZDEN YAPILMASI BAYA KOLAY OLUR.
  HollandTunnelSquare holland_tunnel2 = new HollandTunnelSquare("Holland Tunnel", 14, 2); 
  
  /////////////////////// BU DA IMPLEMENT EDİLEBİLCEK ŞEYLER ARASINDA SANIRIM. AUCTIONU DAHA ÖNCE YAPTĞIMIZ İÇİN BUNU YAPMASI DA PEK ZOR OLMAZ DİYE DÜŞÜNÜYORUM.
  ////////////////// YAPMAYI DENEYEBİLİRİZ BELKİ.
  RegularSquare auction = new RegularSquare("Auction", 15, 2); 
  LotSquare katy_freeway = new LotSquare("Katy Freeway", 16, 2, 140, 11, 100);
  LotSquare westheimer_road = new LotSquare("Westheimer Road", 17, 2, 140, 11, 100);
  UtilitySquare internet_service_provider = new UtilitySquare("Internet Service Provider", 18, 2); 
  LotSquare kirby_drive = new LotSquare("Kirby Drive", 19, 2, 160, 14, 100);
  LotSquare cullen_blvd = new LotSquare("Cullen Blvd.", 20, 2, 160, 14, 100);
  ChanceCardSquare chanceCard_22 = new ChanceCardSquare(21,2);
  
  //////////////////// YUKARDAKİ GİBİ BİR CAB COMPANY
  RegularSquare black_and_white_cab_co = new RegularSquare("Black&White Cab Co.", 22, 2); 
  LotSquare dekalb_ave = new LotSquare("Dekalb Ave.", 23, 2, 180, 17, 100);
  CommunityCardSquare chestCard_22 = new CommunityCardSquare( 24, 2);
  LotSquare young_intl_blvd = new LotSquare("Young Int'l Blvd.", 25, 2, 180, 17, 100);
  LotSquare decatur_street = new LotSquare("Decatur St.", 26, 2, 200, 20, 100);
  LotSquare peachtree_street = new LotSquare("Peachtree St.", 27, 2, 200, 20, 100);
  PayDaySquare pay_day = new PayDaySquare("Pay Day", 28, 2);
  LotSquare randolph_street = new LotSquare("Randolph St.", 29, 2, 220, 23, 150);
  ChanceCardSquare chanceCard_23 = new ChanceCardSquare(30,2);
  LotSquare lake_shore_dr = new LotSquare("Lake Shore Dr.", 31, 2, 220, 23, 150);
  LotSquare wacker_dr = new LotSquare("Wacker Dr.", 32, 2, 240, 26, 150);
  LotSquare michigan_ave = new LotSquare("Michigan Ave.", 33, 2, 240, 26, 150);
  
  ////////////////////YUKARDAKİ GİBİ BİR CAB COMPANY 
  RegularSquare yellow_cab_co = new RegularSquare("Yellow Cab Co.", 34, 2);
  RailroadSquare b_and_o_railroad = new RailroadSquare("B&O Railroad", 35, 2, 25, 1);
  CommunityCardSquare community_chest_23 = new CommunityCardSquare(36,2);
  LotSquare south_temple = new LotSquare("south_temple", 37, 2, 260, 32, 200);
  LotSquare west_temple = new LotSquare("west_temple", 38, 2, 260, 32, 200);
  UtilitySquare trash_collection = new UtilitySquare("trash_collection", 39, 2); 
  LotSquare north_temple = new LotSquare("north_temple", 40, 2, 280, 38, 200);
  LotSquare temple_square = new LotSquare("temple_square", 41, 2, 280, 38, 200);
  SubwaySquare subway = new SubwaySquare("Subway", 42, 2);
  LotSquare south_street = new LotSquare("south_street", 43, 2, 300, 45, 250);
  LotSquare broad_street = new LotSquare("broad_street", 44, 2, 300, 45, 250);
  LotSquare walnut_street = new LotSquare("walnut_street", 45, 2, 320, 55, 250);
  CommunityCardSquare community_chest_24 = new CommunityCardSquare(46,2);
  LotSquare market_street = new LotSquare("market_street", 47, 2, 320, 55, 250);
  
  ////////////////////// IMPLEMENT EDİLEBİLİR!
  RegularSquare bus_ticket1 = new RegularSquare("bus_ticket", 48, 2); 
  UtilitySquare sewage_system = new UtilitySquare("sewage_system", 49, 2); 
  
  /////////////////// IMPLEMENT EDILEBILIR!
  RegularSquare cab_company = new RegularSquare("cab_company", 50, 2); 
  
  //////////// IMPLEMENT EDILEBILIR
  RegularSquare birthday_gift = new RegularSquare("birthday_gift", 51, 2); 
  LotSquare mulholland_drive = new LotSquare("mulholland_drive", 52, 2, 350, 70, 300);
  LotSquare ventura_boulevard = new LotSquare("ventura_boulevard", 53, 2, 400, 80, 300);
  ChanceCardSquare chance_23 = new ChanceCardSquare(54,2);
  LotSquare rodeo_drive = new LotSquare("rodeo_drive", 55, 2, 500, 90, 300);
    
  //track 0
  squares.add(squeezePlay);squares.add(the_embarcadero);squares.add(fishermans_wharf);
  squares.add(telephone_company);squares.add(chestCard_01);squares.add(beacon_street);
  squares.add(bonus_square);squares.add(boylston_street);squares.add(newbury_street);
  squares.add(transit_station_1);squares.add(fifth_ave);squares.add(madison_ave);
  squares.add(roll_three);squares.add(wall_street);
  squares.add(tax_refund);
  squares.add(gas_company);
  squares.add(chanceCard_01);squares.add(florida_ave);
  squares.add(holland_tunnel1);squares.add(miami_ave);squares.add(biscayne_ave);
  squares.add(transit_station_2);
  squares.add(reverse_direction);squares.add(lombard_st);
  squares.add(go);
  //track 0
  //track 1
    squares.add(mediterranean_ave);
    squares.add(community_chest_11);squares.add(baltic_ave); squares.add(income_tax);
    squares.add(transit_station_11); squares.add(oriental_ave);squares.add(chance_11);
    squares.add(vermont_ave); squares.add(connecticut_ave);squares.add(jail);
    squares.add(st_charles_place);squares.add(electric_company);squares.add(states_ave);
    squares.add(virginia_ave);squares.add(pennsylvania);squares.add(st_james_place);
    squares.add(community_chest_12);squares.add(tennessee_ave);squares.add(new_york_ave);
    squares.add(free_parking);squares.add(kentucky_ave);squares.add(chance_12);
    squares.add(indiana_ave);squares.add(illinois_ave);squares.add(transit_station_12);
    squares.add(atlantic_ave);squares.add(water_company);squares.add(ventnor_ave);
    squares.add(marvin_ave);squares.add(go_to_jail);squares.add(pacific_ave);
    squares.add(community_chest_13);squares.add(north_carolina_ave);squares.add(pennslyvania_ave);
    squares.add(short_line);squares.add(chance_13);squares.add(park_place);
    squares.add(luxury_tax);squares.add(boardwalk);
  //track 1
  //track 2
    squares.add(stock_exchange);squares.add(lake_st);squares.add(chestCard_21);
    squares.add(nicollet_ave);squares.add(hennepin_ave);
    squares.add(bus_ticket);
    squares.add(checker_cab_co);
    squares.add(reading_railroad);squares.add(the_esplanade);squares.add(canal_street);
    squares.add(chanceCard_21);
    squares.add(cable_company);squares.add(magazine_street);squares.add(bourbon_street);
    squares.add(holland_tunnel2);
    squares.add(auction);squares.add(katy_freeway);squares.add(westheimer_road);
    squares.add(internet_service_provider);squares.add(kirby_drive);squares.add(cullen_blvd);
    squares.add(chanceCard_22);
    squares.add(black_and_white_cab_co);squares.add(dekalb_ave);
    squares.add(chestCard_22);squares.add(young_intl_blvd);
    squares.add(decatur_street);squares.add(peachtree_street);
    squares.add(pay_day);squares.add(randolph_street);
    squares.add(chanceCard_23);squares.add(lake_shore_dr);
    squares.add(wacker_dr);squares.add(michigan_ave);
    squares.add(yellow_cab_co);
    squares.add(b_and_o_railroad);
    squares.add(community_chest_23);squares.add(south_temple);squares.add(west_temple);
    squares.add(trash_collection);squares.add(north_temple);squares.add(temple_square);
    squares.add(subway);squares.add(south_street);squares.add(broad_street);
    squares.add(walnut_street);squares.add(community_chest_24);
    squares.add(market_street);squares.add(bus_ticket1);squares.add(sewage_system);
    squares.add(cab_company);squares.add(birthday_gift);
    squares.add(mulholland_drive);squares.add(ventura_boulevard);squares.add(chance_23);
    squares.add(rodeo_drive);
    //track 2
  die1 = new Die();
  die2 = new Die();
 }
 
 public Square getSqByTP(int track, int pos) {
		if(track == 0) {
			return squares.get(pos);
		}else if(track==1) {
			return squares.get(24+pos);
		}else {
			return squares.get(40+pos);
		}
 }
 public void initializeCards() {
	 AdvanceToPayCorner cha1 = new AdvanceToPayCorner();
	 BankErrorInYourFavor com1 = new BankErrorInYourFavor();
	 GoToJailCard cha2 = new GoToJailCard();
	 GoToJailCommunity com2 = new GoToJailCommunity();
	 InsurancePremiumsDue com3 = new InsurancePremiumsDue();
	 TrafficTicket cha3 = new TrafficTicket();
	 HurricaneCard cha4 = new HurricaneCard();
	 chanceCards.add(cha1); chanceCards.add(cha2); chanceCards.add(cha3);
	 communityChestCards.add(com1); communityChestCards.add(com2); communityChestCards.add(com3);
	 chanceCards.add(cha4);
 }
 
 /**
  * Rolls three dice by calling roll() methods of dices.
  * 
  * @requires: die1,die2 and speedDie should exists.
  * @effects: Rolls three dices.
  */
 public void rollDice() {
  if(getDie1().getFaceValue() == getDie2().getFaceValue()) {
   jailCounter++;
  }else {
   jailCounter = 0;
  }
  if(jailCounter==3) {
   //go to jail
  }
  die1.roll();
  die2.roll();
  
  System.out.println("Roll is " + die1.getFaceValue() + " " + die2.getFaceValue());
 }
 /**
  * Adds a player to the game by initializing a Player object using given "name" and "color" parameters. Then adds this player object to the players list.
  * 
  * @param name The name of the player
  * @param color The color of the player
  * @requires: There exists a players Player List.
  * @modifies: Modifies players ListPlayer.
  * @effects: Creates a new Player object and adds it into the List.
  */
 public void addPlayer(String name,int money, Color color) {
  players.add(new Player(name,money,color));
  /**
   * @requires: There exists a <Player> List.
   * @modifies: Modifies List<Player>.
   * @effects: Creates a new <Player> object and adds it into the List.
   */
 }
 
 /** 
  * Gets the players list of the Board object.
  * @effects Returns the players list.
  * @return the players list List Player
  */
 public List<Player> getPlayers() {
  return players;
 }
 /**
  * Gets the squares list of the Board object.
  * 
  * @effects Returns the squares list.
  * @return the squares list List Square
  */
 public List<Square> getSquares() {
  return squares;
 }
 /**
  * Gets the chance cards list of the Board object.
  *
  * @effects Returns the chanceCards list
  * @return the chance cards list List chanceCard
  */
 public List<ChanceCard> getChanceCards() {
  return chanceCards;
 }
 /**
  * Gets the community chest cards list of the Board object.
  *
  * @effects Returns the communityChestCards list
  * @return the community chest cards list List communityChanceCard
  */
 public List<CommunityChestCard> getCommunityChestCards() {
  return communityChestCards;
 }
 /**
  * Gets the first die object.
  *
  * @effects Returns a Die object that corresponds to the first die.
  * @return the die1 Die object.
  */
 public Die getDie1() {
  return die1;
 }
 /**
  * Gets the seconds die object
  *
  * @effects Returns a Die object that corresponds to the second die.
  * @return the die 2 Die object.
  */
 public Die getDie2() {
  return die2;
 }
 /**
  * Gets speedDie object.
  *
  * @effects Returns a SpeedDie object that corresponds to the speed die.
  * @return the speed die SpeedDie object.
  */
 public SpeedDie getSpeedDie() {
  return speedDie;
 }
 /**
  * Gets the current turn which is an integer that corresponds to current player's index in the players list.
  *
  * @effects Returns the turn field of Board object.
  * @return the turn as an integer.
  */
 public int getTurn() {
  return turn;
 }
 /**
  * Gets the number of alive players in the game.
  *
  * @effects Returns the number of alive players.
  * @return the numPlayers field of the Board object.
  */
 public int getNumPlayers() {
  return players.size();
 }
 /**
  * Changes game to next turn by setting the "turn" field of the Board object to a new value.
  * @modifies "turn" field of the Board object.
  * @effects Increases turn by 1 which corresponds to the next player's turn if dices are not double. If current player rolls a double, turn remains unchanged.   
  */
 public void nextTurn() {
  if(getDie1().getFaceValue() == getDie2().getFaceValue()) {
 
  }else {
  turn = (turn + 1) % this.getPlayers().size();
  }
 }
 public String toString() {
  String result = "This is the game board.";
  return result;
 }
 public boolean repOK() {
  if(this==null||this.players==null||this.squares==null) {
   return false;
  }else {
   return true;
  }
 }
 public void setTurn(int turn) {
  this.turn = turn;
 }
 public void setNumPlayers(int numPlayers) {
  this.numPlayers = numPlayers;
 }
}



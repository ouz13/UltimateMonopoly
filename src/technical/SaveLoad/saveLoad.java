package technical.SaveLoad;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.*;

import domain.player.Player;
import domain.square.LotSquare;
import domain.square.PropertySquare;
import domain.square.Square;
import technical.DataPackage.GameStateHolder;

public class saveLoad {
	JSONObject jsonFile;
	JSONArray playersList;
	JSONArray squaresList;
	JSONArray chanceList;
	JSONArray chestList;
	int turn;
	
	@SuppressWarnings("unchecked")
	public saveLoad() {
		jsonFile = new JSONObject();
		
		playersList = new JSONArray();
		jsonFile.put("Players", playersList);
		
		squaresList = new JSONArray();
		jsonFile.put("Squares", squaresList);
		
		chanceList = new JSONArray();
		jsonFile.put("Chance Cards", chanceList);
		
		chestList = new JSONArray();
		jsonFile.put("Community Chance Cards", chestList);
		
		jsonFile.put("Turn", null);
	}
	
	public void saveGame(GameStateHolder stateHolder) {
		
		for (int i=0; i < stateHolder.getPlayers().size() ; i++) {
			JSONObject playersListElement = new JSONObject();
			Player player = stateHolder.getPlayers().get(i);
			List<PropertySquare> properties = player.getOwnedProperties();
			JSONArray propertiesList = new JSONArray();
			for(int j=0; j < properties.size();j++) {
				JSONObject propertyJSON = new JSONObject();
				propertyJSON.put("Name", properties.get(j).getName());
				propertyJSON.put("ID", properties.get(j).getID());
				propertiesList.add(propertyJSON);
				//playersListElement.put("Owned Properties",propertyJSON);
			}
			playersListElement.put("Owned Properties", propertiesList);
			//playersListElement.put("Color", player.getColor().);
			playersListElement.put("Position", player.getPosition());
			playersListElement.put("Track", player.getTrack());
			playersListElement.put("Money", player.getMoney());
			playersListElement.put("Name",player.getName());
			playersListElement.put("ID", player.getPlayerID());
			playersList.add(playersListElement);
		}
		
		for (int i=0; i < stateHolder.getSquares().size(); i++) {
			if(stateHolder.getSquares().get(i) instanceof LotSquare) {
				LotSquare square = (LotSquare) stateHolder.getSquares().get(i);
				JSONObject squaresListElement = new JSONObject();
				squaresListElement.put("Square Type", square.getClass());
				squaresListElement.put("Name", square.getName());
				squaresListElement.put("Location", square.getLocation());
				squaresListElement.put("Track", square.getTrack());
				squaresListElement.put("Price", square.getPrice());
				if(square.getOwner() != null) {
					squaresListElement.put("Owner", square.getOwner().getPlayerID());
				}else {
					squaresListElement.put("Owner", square.getOwner());
				}
			//	squaresListElement.put("Owner", square.getOwner());     
				squaresListElement.put("isOwned?", square.isOwned());
				squaresList.add(squaresListElement);
			}else {
			//squaresList.add(stateHolder.getSquares().get(i));
			}
			
		}
		
		//for(int i=0; i < stateHolder.getChanceCards().size(); i++) {
		//	chanceList.add(stateHolder.getChanceCards().get(i));
		//}
		
		//for(int i=0; i < stateHolder.getCommunityChestCards().size(); i++) {
		//	chestList.add(stateHolder.getCommunityChestCards().get(i));
		//}
		
		turn = stateHolder.getTurn();
		
		jsonFile.remove("Players");
		jsonFile.put("Players", playersList);
		
		jsonFile.remove("Squares");
		jsonFile.put("Squares", squaresList);
		
		jsonFile.remove("Chance Cards");
		jsonFile.put("Chance Cards", chanceList);
		
		jsonFile.remove("Community Chance Cards");
		jsonFile.put("Community Chance Cards", chestList);
		
		jsonFile.remove("Turn");
		jsonFile.put("Turn", turn);
		
		try(FileWriter file = new FileWriter("SavedGameData.json"))
		{
			file.write(jsonFile.toString());
			file.flush();
		} 
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}

	public org.json.JSONObject loadGame(String fileName) {
	
		BufferedReader br = null;
		FileReader fr = null;
		
		
		try {
			
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			//StringBuilder sb = new StringBuilder();
			
			String jsondata = br.readLine();
			System.out.println(jsondata);
			
			org.json.JSONObject jsonobj = new org.json.JSONObject(jsondata);
			return jsonobj;
			//org.json.JSONArray players = new org.json.JSONArray(jsonobj.getJSONArray("Players").toString());
			//System.out.println(players);
			
			//System.out.println(jsonobj);
			//org.json.JSONArray squares = new org.json.JSONArray(jsonobj.getJSONArray("Squares").toString());
			//System.out.println(squares);
			

			//org.json.JSONObject sqr = (org.json.JSONObject) squares.get(0);
			//System.out.println(sqr.get("Owner"));
			//Player player = (Player) sqr.get("Owner");
			//System.out.println(sqr.get("Owner").getClass());
			
			//JSONObject obj = (JSONObject) parser.parse(new FileReader("C:\\Users\\Mustafa\\git\\UltimateMonopoly\\UltimateMonopoly\\monopoly\\SavedGameData.json"));
			//JSONArray a = (JSONArray) parser.parse(new FileReader("C:\\Users\\Mustafa\\git\\UltimateMonopoly\\UltimateMonopoly\\monopoly\\SavedGameData.json"));
			
		
			//List<Square> squares = (List<Square>) jsonObject.get("Squares");
			//for(int i=0; i < squares.size(); i++ ){
			//	Square square = squares.get(i);
				//if ()
			//}
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}
		/*
		BufferedReader br = null;
		FileReader fr = null;
		
		try {

			//br = new BufferedReader(new FileReader(FILENAME));
			String currentpath = new java.io.File( "." ).getCanonicalPath();
			String filepath = currentpath + "\\" + fileName;
			fr = new FileReader(filepath);
			br = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				if(sCurrentLine == "Player") {
					Player p = new Player(br.readLine());
					p.setMoney(Integer.parseInt(br.readLine()));
					p.setPosition(Integer.parseInt(br.readLine()));
				}
			}

		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();
			}
		}*/
	}
}

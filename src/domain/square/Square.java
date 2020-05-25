package domain.square;

public abstract class Square {

	String name;
	int location;
	int track;
	boolean isBuyable;
	static int id=0;
	int locationID;
	
	
public int getID() {
	return locationID;
}
public String getName() {
	return name;
}

public int getLocation() {
	return location;
}

public int getTrack() {
	return track;
}

public boolean isBuyable() {
	return isBuyable;
}
public boolean repOk() {
	if(this==null||name==null) {
		return false;
	}else if(location<0 || location > 56 || track < 0 || track> 2) {
		return false;
	}else
		return true;
}
public String toString(){
	String result = "Square.toString :\n";
	result += "Name: " + name + "\n";
	result += "Position: " + location + "\n";
	result += "Track: " + track + "\n";
	return result;
}
	

}

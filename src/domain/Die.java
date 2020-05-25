package domain;

import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Die class.
 * Die related informations are held here.
 */
public class Die {
	
	/** The face value of the die. */
	int faceValue;

/**
 * Instantiates a new die. 
 * Initial faceValue is zero.
 */
public Die() {
	faceValue = 0;
}

/**
 * Roll the dice.
 * 
 * @effects Randoms a number between 1 and 6. Changes the face value of the die to the randommed value by using setFaceValue() method.
 */
public void roll() {
	Random rand = new Random();
	setFaceValue(rand.nextInt(6) + 1);
}

/**
 * Gets the face value.
 *
 * @return the face value
 */
public int getFaceValue() {
	return faceValue;
}

/**
 * Sets the face value.
 *
 * @param faceValue the new face value
 * @modifies faceValue
 * @effects Changes faceValue
 */
public void setFaceValue(int faceValue) {
	this.faceValue = faceValue;
}
public String toString() {
	String result = "This is a die with face value ";
	result = result + this.getFaceValue();
	return result;
}
public boolean repOK() {
	if(this == null || this.getFaceValue() == 0) {
		return false;
	} else { 
		return true;
	}
}



}

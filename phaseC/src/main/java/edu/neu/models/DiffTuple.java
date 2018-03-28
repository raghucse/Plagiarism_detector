package edu.neu.models;

/**
 * This class is used to maintain information of the code
 */
public class DiffTuple {

	private String data;
	private boolean match;

	/**
	 * This constructor sets the data and the boolean value
	 * which determines if any match is found
	 * @param data is the string which is to be checked
	 * @param match is boolean value which signifies whether
	 *              or not any match in the given data is found
	 */
	public DiffTuple(String data, boolean match) {
		this.data = data;
		this.match = match;
	}

	/**
	 * @return the data which is a string value
	 */
	public String getData() {
		return data;
	}

	/**
	 * Sets the data variable with some string value
	 * @param data is the value which is to be assigned
	 */
	public void setData(String data) {
		this.data = data;
	}


	/**
	 * Determines whether any match is found
	 * @return true iff, match found
	 * 		   else returns false
	 */
	public boolean isMatch() {
		return match;
	}

	/**
	 * Used to set the boolean parameter
	 * @param match determines the value to be set
	 */
	public void setMatch(boolean match) {
		this.match = match;
	}

	/**
	 * @return  Returns the data and the match result which is boolean
	 * 			in a string format
	 */
	public String toString() {
		return this.data + "," + this.match;
	}
	
}

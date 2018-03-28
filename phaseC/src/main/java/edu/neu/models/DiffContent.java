package edu.neu.models;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DiffContent implements Serializable{

	List<DiffTuple> matchList1;
	List<DiffTuple> matchList2;

	/**
	 * This constructor maintains two match lists
	 */
	public DiffContent() {
		this.matchList1 = new ArrayList<>();
		this.matchList2 = new ArrayList<>();
	}

	/**
	 * This function is used to determine the differences between the codes
	 * @param str is string which is different in the file
	 * @param match is the boolean value which determines whether or not the
	 *              string is different in the two codes
	 * @param isFirst is the boolean value which determines whether it is from the
	 *                first list or not
	 */
	public DiffContent(String str, boolean match, boolean isFirst) {
		this();
		if(isFirst)
			this.matchList1.add(new DiffTuple(str, match));
		else
			this.matchList2.add(new DiffTuple(str, match));
	}

	/**
	 * @return Returns the matched contents
	 */
	public String getResult() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("First File : \n");
		for(DiffTuple rt : matchList1) {
			sb.append(rt);
			sb.append("\n");
		}
		sb.append("Second File : \n");
		for(DiffTuple rt : matchList2) {
			sb.append(rt);
			sb.append("\n");
		}
		
		return sb.toString();
	}

	/**
	 * Adds the string to the first result
	 * @param str is the string to be added to the list
	 * @param match is the boolean value which determines whether or not the string is matched
	 */
	public void appendToResult1(String str, boolean match) {
		this.matchList1.add(new DiffTuple(str, match));
	}

	/**
	 * Adds the string to the second result
	 * @param str is the string to be added to the list
	 * @param match is the boolean value which determines whether or not the string is matched
	 */
	public void appendToResult2(String str, boolean match) {
		this.matchList2.add(new DiffTuple(str, match));
	}

	/**
	 * Adds the object of the DiffTuple class to the first match list
	 * @param rt is the object of the DiffTuple class
	 */
	public void appendReportTupleTo1(DiffTuple rt) {
		this.matchList1.add(rt);
	}

	/**
	 * Adds the object of the DiffTuple class to the second match list
	 * @param rt is the object of the DiffTuple class
	 */
	public void appendReportTupleTo2(DiffTuple rt) {
		this.matchList2.add(rt);
	}

	/**
	 * This function is used to maintain both the match lists
	 * @param rc is the object of the DiffContent class
	 */
	public void addAll(DiffContent rc) {
		this.matchList1.addAll(rc.matchList1);
		this.matchList2.addAll(rc.matchList2);
	}

	/**
	 * This function is used to write the data
	 * @param out is the object of ObjectOutputStream class
	 * @throws IOException
	 */
	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		int listSize1 = matchList1.size();
		out.writeInt(listSize1);
		for(DiffTuple rt : matchList1) {
			out.writeUTF(rt.getData());
			out.writeBoolean(rt.isMatch());
		}
		
		int listSize2 = matchList2.size();
		out.writeInt(listSize2);
		for(DiffTuple rt : matchList2) {
			out.writeUTF(rt.getData());
			out.writeBoolean(rt.isMatch());
		}
		
	}

	/**
	 * This function is used to read the data
	 * @param in is the object of ObjectInputStream class
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		int size1 = in.readInt();
		matchList1 = new ArrayList<>();
		for(int i=0; i<size1; i++) {
			matchList1.add(new DiffTuple(in.readUTF(), in.readBoolean()));
		}
		
		int size2 = in.readInt();
		matchList2 = new ArrayList<>();
		for(int i=0; i<size2; i++) {
			matchList2.add(new DiffTuple(in.readUTF(), in.readBoolean()));
		}
	}
	
}

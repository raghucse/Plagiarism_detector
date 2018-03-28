package edu.neu.models;

import edu.neu.comparison.ComparisonReport;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class specifies the content included in the
 * Plagiarism report
 */
public class ReportContent implements Serializable{
	
	String reportMessage;
	List<ComparisonReport> comparisonList;

	/**
	 * This constructor sets the report message and the comparison list
	 */
	public ReportContent() {
		reportMessage = "Report Content";
		comparisonList = new ArrayList<>();
	}

/**
 * Used for writing the data which is the list of files to be compared
 * @param out is the object of ObjectOutputStream
 * @throws IOException
 **/
	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.writeUTF(reportMessage);
		int size = comparisonList.size();
		out.writeInt(size);
		for(int i=0; i<size; i++) {
			out.writeObject(comparisonList.get(i));
		}
	}

	/**
	 * Used for reading the data
	 * @param in is the object of the ObjectInputStream
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		reportMessage = in.readUTF();
		int size = in.readInt();
		comparisonList = new ArrayList<>();
		for(int i=0; i<size; i++) {
			comparisonList.add((ComparisonReport) in.readObject());
		}
	}

	/**
	 * Adds the report to the comparison list
	 * @param cr is the Comparison report to be added to the list
	 */
	public void addTOComparisonList(ComparisonReport cr) {
		comparisonList.add(cr);
	}

	/**
	 * @return returns the comparison list
	 */
	public List<ComparisonReport> getComparisonList(){
		return comparisonList;
	}

	/**
	 * @return returns the report message
	 */
	public String getReportMessage() {
		return reportMessage;
	}

	/**
	 * Sets the report message
	 * @param reportMessage specifies the value to be set as the report message
	 */
	public void setReportMessage(String reportMessage) {
		this.reportMessage = reportMessage;
	}

	/**
	 * Sets the comparison list
	 * @param comparisonList specifies the list of values to be added to
	 *                       the comparison list
	 */
	public void setComparisonList(List<ComparisonReport> comparisonList) {
		this.comparisonList = comparisonList;
	}
	
}

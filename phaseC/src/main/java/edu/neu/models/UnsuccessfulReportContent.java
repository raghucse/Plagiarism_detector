package edu.neu.models;

import edu.neu.Log;
import edu.neu.utils.Constants;

import java.io.IOException;

/**
 * This class handles the all the unsuccessful reports, i.e. the
 * reports of a failed Plagiarism run
 */
public class UnsuccessfulReportContent extends ReportContent{

	public UnsuccessfulReportContent() {
		super();
		this.reportMessage = Constants.P_CHECK_ERROR_STRING;
	}

	/**
	 * Used for writing the data
	 * @param out is the object of ObjectOutputStream
	 * @throws IOException
	 */
	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.writeInt(-404);
	}

	/**
	 * Used for reading the data
	 * @param in is the object of the ObjectInputStream
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		if(in.readInt() != -404) {
			Log.error("Error when deserializing an unsuccessful report");
		}
		else {
			this.reportMessage = Constants.P_CHECK_ERROR_STRING;
		}
	}
	
}

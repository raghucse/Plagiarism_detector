package edu.neu.models;

import java.io.IOException;

import edu.neu.Log;
import edu.neu.utils.Constants;

public class UnsuccessfulReportContent extends ReportContent{

	public UnsuccessfulReportContent() {
		super();
		this.reportMessage = Constants.P_CHECK_ERROR_STRING;
	}
	
	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.writeInt(-404);
	}
	
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		if(in.readInt() != -404) {
			Log.error("Error when deserializing an unsuccessful report");
		}
		else {
			this.reportMessage = Constants.P_CHECK_ERROR_STRING;
		}
	}
	
}

package edu.neu.models;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.neu.comparison.ComparisonReport;

public class ReportContent implements Serializable{
	
	String reportMessage;
	List<ComparisonReport> comparisonList;

	public ReportContent() {
		reportMessage = "Report Content";
		comparisonList = new ArrayList<>();
	}
	
	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.writeUTF(reportMessage);
		int size = comparisonList.size();
		out.writeInt(size);
		for(int i=0; i<size; i++) {
			out.writeObject(comparisonList.get(i));
		}
	}
	
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		reportMessage = in.readUTF();
		int size = in.readInt();
		comparisonList = new ArrayList<>();
		for(int i=0; i<size; i++) {
			comparisonList.add((ComparisonReport) in.readObject());
		}
	}
	
	public void addTOComparisonList(ComparisonReport cr) {
		comparisonList.add(cr);
	} 
	
	public List<ComparisonReport> getComparisonList(){
		return comparisonList;
	}
	
}

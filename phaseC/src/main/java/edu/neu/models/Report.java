package edu.neu.models;

import java.util.ArrayList;
import java.util.List;

public class Report {

	List<String> result;
	
	public Report() {
		this.result = new ArrayList<>();
	}
	
	public Report(String res) {
		this();
		this.result.add(res);
	}
	
	public String getResult() {
		return String.join(",", result);
	}
	
	public void appendToResult(String str) {
		this.result.add(str);
	}
	
}

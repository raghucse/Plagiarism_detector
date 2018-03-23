package edu.neu.models;

public class ReportTuple {

	private String data;
	private boolean match;
	
	public ReportTuple(String data, boolean match) {
		this.data = data;
		this.match = match;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public boolean isMatch() {
		return match;
	}
	
	public void setMatch(boolean match) {
		this.match = match;
	}
	
	@Override
	public String toString() {
		return this.data + "," + this.match;
	}
	
}

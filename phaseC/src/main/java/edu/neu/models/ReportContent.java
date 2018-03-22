package edu.neu.models;

import java.util.ArrayList;
import java.util.List;

public class ReportContent {

	List<ReportTuple> matchList;
	
	public ReportContent() {
		this.matchList = new ArrayList<>();
	}
	
	public ReportContent(String str, boolean match) {
		this();
		this.matchList.add(new ReportTuple(str, match));
	}
	
	public String getResult() {
		StringBuilder sb = new StringBuilder();
		for(ReportTuple rt : matchList) {
			sb.append(rt);
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public void appendToResult(String str, boolean match) {
		this.matchList.add(new ReportTuple(str, match));
	}
	
	public void addAll(ReportContent rc) {
		this.matchList.addAll(rc.matchList);
	}
	
}

package edu.neu.models;

import java.util.List;
import java.util.Map;

public class Assignment {

	private String id;
	private String name;
	private String dueDate;
	private List<String> requiredFiles;
	private Map<String, Submission> studentSubmissions;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public List<String> getRequiredFiles() {
		return requiredFiles;
	}
	public void setRequiredFiles(List<String> requiredFiles) {
		this.requiredFiles = requiredFiles;
	}
	public Map<String, Submission> getStudentSubmissions() {
		return studentSubmissions;
	}
	public void setStudentSubmissions(Map<String, Submission> studentSubmissions) {
		this.studentSubmissions = studentSubmissions;
	}
	
}


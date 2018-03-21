package edu.neu.models;

/**
 * The student model to represent the student object inside a course
 * @author bharat vaidhyanathan
 */
public class Student {

	String name;
	String id;
	String email;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}

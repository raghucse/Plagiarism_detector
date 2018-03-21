package edu.neu.models;

import java.util.List;

public class Course {

	private String id;
	private Instructor primaryInstructor;
	private List<Instructor> secondaryInstructors;
	private List<Grader> graders;
	private List<Assignment> assignments;
	private List<Student> students;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Instructor getPrimaryInstructor() {
		return primaryInstructor;
	}
	public void setPrimaryInstructor(Instructor primaryInstructor) {
		this.primaryInstructor = primaryInstructor;
	}
	public List<Instructor> getSecondaryInstructors() {
		return secondaryInstructors;
	}
	public void setSecondaryInstructors(List<Instructor> secondaryInstructors) {
		this.secondaryInstructors = secondaryInstructors;
	}
	public List<Grader> getGraders() {
		return graders;
	}
	public void setGraders(List<Grader> graders) {
		this.graders = graders;
	}
	public List<Assignment> getAssignments() {
		return assignments;
	}
	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
}

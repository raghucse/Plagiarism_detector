package edu.neu.models;

public class Submission {

	private String id;
	private Student student;
	private Course course;
	private Assignment assignment;
	private Source source;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Assignment getAssignment() {
		return assignment;
	}
	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}
	public Source getSource() {
		return source;
	}
	public void setSource(Source source) {
		this.source = source;
	}
	
}

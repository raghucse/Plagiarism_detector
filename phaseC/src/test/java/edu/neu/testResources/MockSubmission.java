package edu.neu.testResources;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import edu.neu.models.Submission;

public class MockSubmission implements Submission{

	private String MOCK_STUDENT = "MockStudent";
	public List<File> files;
	
	public MockSubmission() {
		files  = new ArrayList<File>();
	}
	
	@Override
	public List<File> getFiles() {
		return this.files;
	}

	@Override
	public String getStudentName() {
		return MOCK_STUDENT;
	}

}

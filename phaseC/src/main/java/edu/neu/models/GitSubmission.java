package edu.neu.models;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GitSubmission implements Submission{
	
	private String gitURL;
	
	public GitSubmission(String gitURL) {
		this.gitURL = gitURL;
	}

	@Override
	public List<File> getFiles() {
		return new ArrayList<>();
	}

}

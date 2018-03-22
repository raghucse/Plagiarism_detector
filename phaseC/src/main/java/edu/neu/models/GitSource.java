package edu.neu.models;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GitSource implements Source{
	
	private String gitURL;
	
	public GitSource(String gitURL) {
		this.gitURL = gitURL;
	}

	@Override
	public List<File> getFiles() {
		return new ArrayList<>();
	}

}

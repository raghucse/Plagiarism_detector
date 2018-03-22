package edu.neu.testResources;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import edu.neu.models.Source;

public class MockSource implements Source{

	public List<File> files;
	
	public MockSource() {
		files  = new ArrayList<File>();
	}
	
	@Override
	public List<File> getFiles() {
		return this.files;
	}

}

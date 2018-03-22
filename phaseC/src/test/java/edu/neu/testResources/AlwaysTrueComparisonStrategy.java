package edu.neu.testResources;

import java.io.File;

import edu.neu.comparison.Strategy;

public class AlwaysTrueComparisonStrategy implements Strategy{

	@Override
	public String compare(File f1, File f2) {
		return "Match";
	}

}

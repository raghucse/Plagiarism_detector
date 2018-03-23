package edu.neu.comparison;

import java.io.File;

public interface Strategy {
	public ComparisonReport compare(File f1, File f2);
}

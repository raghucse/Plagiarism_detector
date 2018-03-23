package edu.neu.comparison;

import java.io.File;

public interface Strategy {
	public STRATEGIES getName();
	public double compare(File f1, File f2);
}

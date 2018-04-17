package edu.neu.comparison;

import java.io.File;

/**
 * This interface is used to implement the specified
 * strategy for comparison
 */
public interface Strategy {
	/**
	 * @return the strategy name to be implemented for comparison
	 */
	public STRATEGIES getName();

	/**
	 * Compares the given two files using the selected strategy
	 * @param f1 is the first file
	 * @param f2 is the second file
	 * @return the comparison score
	 */
	public Scores compare(File f1, File f2);
}

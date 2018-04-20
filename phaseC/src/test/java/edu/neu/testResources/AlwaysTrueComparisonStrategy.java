package edu.neu.testResources;

import java.io.File;

import edu.neu.comparison.ComparisonReport;
import edu.neu.comparison.STRATEGIES;
import edu.neu.comparison.Scores;
import edu.neu.comparison.Strategy;
import edu.neu.models.DiffContent;

public class AlwaysTrueComparisonStrategy implements Strategy{

	@Override
	public Scores compare(File f1, File f2) {
		/*DiffContent diffContent = new DiffContent();
		diffContent.appendToResult1("Match", true);
		diffContent.appendToResult2("Match", true);
		return new ComparisonReport(1.0, 1, diffContent);*/
		return new Scores(1.0, "AlwaysTrueComparisonStrategy:1");
	}

	@Override
	public STRATEGIES getName() {
		return STRATEGIES.ALWAYS_TRUE;
	}
	
}

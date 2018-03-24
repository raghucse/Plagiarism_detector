package factories;

import edu.neu.astgeneration.ASTUtils;
import edu.neu.comparison.ComplexStrategy1;
import edu.neu.comparison.CosineSimilarity;
import edu.neu.comparison.LCS;
import edu.neu.comparison.LVDistance;
import edu.neu.comparison.STRATEGIES;
import edu.neu.comparison.Strategy;
import edu.neu.testResources.AlwaysTrueComparisonStrategy;

public class ComparisonStrategyFactory {
	
	private ComparisonStrategyFactory() {
		// hiding the implicit constructor
	}
	
	public static Strategy getComparisonStrategy(STRATEGIES strategy, ASTUtils astUtils) {
		switch(strategy)
		{
			case COMPLEX1: return new ComplexStrategy1(astUtils);
			case ALWAYS_TRUE: return new AlwaysTrueComparisonStrategy();
			case COSINE: return new CosineSimilarity(astUtils);
			case LEVENSHTEIN: return new LVDistance(astUtils);
			case LCS: return new LCS(astUtils);
			default: return new AlwaysTrueComparisonStrategy();
		}
	}
}

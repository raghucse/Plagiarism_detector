package factories;

import edu.neu.astgeneration.ASTUtils;
import edu.neu.comparison.ComplexStrategy1;
import edu.neu.comparison.CosineSimilarity;
import edu.neu.comparison.LCS;
import edu.neu.comparison.LVDistance;
import edu.neu.comparison.STRATEGIES;
import edu.neu.comparison.Strategy;

/**
 * This class handles the comparison by implementing the
 * specified comparison strategy
 */
public class ComparisonStrategyFactory {

	/**
	 * This constructor creates an object of ComparisonStrategyFactory class
	 */
	private ComparisonStrategyFactory() {}

	/**
	 * Returns the object of a particular strategy based on the specified parameters
	 * @param strategy determines the strategy to be implemented
	 * @param astUtils is an object of the ASTUtils class
	 * @return returns the object of a particular strategy depending on the specified strategy value
	 */
	public static Strategy getComparisonStrategy(STRATEGIES strategy, ASTUtils astUtils) {
		switch(strategy)
		{
			case COMPLEX1: return new ComplexStrategy1(astUtils);
			case COSINE: return new CosineSimilarity(astUtils);
			case LEVENSHTEIN: return new LVDistance(astUtils);
			case LCS: return new LCS(astUtils);
			default: return new LCS(astUtils);
		}
	}
}

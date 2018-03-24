package edu.neu.factories;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.neu.Log;
import edu.neu.astgeneration.ASTUtils;
import edu.neu.comparison.ComplexStrategy1;
import edu.neu.comparison.CosineSimilarity;
import edu.neu.comparison.LCS;
import edu.neu.comparison.LVDistance;
import edu.neu.comparison.STRATEGIES;
import edu.neu.comparison.Strategy;
import edu.neu.utils.Constants;
import factories.ComparisonStrategyFactory;

public class ComparisonStrategyFactoryTests {

	@Test
	public void testAlwaysTrue() {
		Strategy strategy = ComparisonStrategyFactory.getComparisonStrategy(STRATEGIES.ALWAYS_TRUE, new ASTUtils());
		assertTrue(strategy instanceof LCS);
	}
	
	@Test
	public void testLCS() {
		Strategy strategy = ComparisonStrategyFactory.getComparisonStrategy(STRATEGIES.LCS, new ASTUtils());
		assertTrue(strategy instanceof LCS);
	}
	
	@Test
	public void testCosine() {
		Strategy strategy = ComparisonStrategyFactory.getComparisonStrategy(STRATEGIES.COSINE, new ASTUtils());
		assertTrue(strategy instanceof CosineSimilarity);
	}
	
	@Test
	public void testLVDistance() {
		Strategy strategy = ComparisonStrategyFactory.getComparisonStrategy(STRATEGIES.LEVENSHTEIN, new ASTUtils());
		assertTrue(strategy instanceof LVDistance);
	}
	
	@Test
	public void testComplex1() {
		Strategy strategy = ComparisonStrategyFactory.getComparisonStrategy(STRATEGIES.COMPLEX1, new ASTUtils());
		assertTrue(strategy instanceof ComplexStrategy1);
		Log.info(Constants.P_CHECK_ERROR_STRING+"");
		Log.info(Constants.DEFAULT_PLAGIARISM_STRATEGY+"");
	}
	
}

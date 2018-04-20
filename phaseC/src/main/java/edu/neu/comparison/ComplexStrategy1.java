package edu.neu.comparison;

import edu.neu.astgeneration.ASTUtils;

import java.io.File;
import java.util.stream.DoubleStream;

/**
 * This class implements all the three strategies for the given
 * files and generates an average score
 */
public class ComplexStrategy1 implements ASTBasedStrategy{

	private ASTUtils astUtils;
	
	private Strategy[] simplerStrategies;
	private double[] weights;

	/**
	 * The constructor creates the objects of each of the comparison strategies
	 * @param astUtils is the object of the ASTUtils class
	 */
	public ComplexStrategy1(ASTUtils astUtils) {
		this.astUtils = astUtils;
		this.simplerStrategies = new Strategy[3];
		simplerStrategies[0] = new LCS(astUtils);
		simplerStrategies[1] = new LVDistance(astUtils);
		simplerStrategies[2] = new CosineSimilarity(astUtils);
		weights = new double[]{1.0, 1.0, 1.0};
	}

	/**
	 * @return Returns the strategy name as COMPLEX1
	 */
	public STRATEGIES getName() {
		return STRATEGIES.COMPLEX1;
	}

	/**
	 * Implements all the comparison strategies for the given
	 * files and returns an average score
	 * @param f1 is the first file
	 * @param f2 is the second file
	 * @return an average score based on the scores obtained
	 * 		   for each of the strategies
	 */
	public Scores compare(File f1, File f2) {
		Scores computedScores;
		double computedScore = 0.0;
		StringBuilder computedDescription = new StringBuilder();
		for(int i=0; i<simplerStrategies.length; i++) {
			computedScores = simplerStrategies[i].compare(f1, f2);
			computedScore += weights[i] * computedScores.getTotalScore();
			computedDescription.append(computedScores.getSubScores());
			computedDescription.append(" ");
		}
		computedScore /= DoubleStream.of(weights).sum();
		return new Scores(computedScore, computedDescription.toString());
	}

	/**
	 * Returns the AST Utils used by the strategy
	 */
	@Override
	public ASTUtils getASTUtils() {
		return astUtils;
	}
	
	/**
	 * Set the LCS weight to the one specified
	 * @param weight : The new weight of LCS
	 */
	public void setLCSWeight(double weight) {
		weights[0] = weight;
	}
	
	/**
	 * Set the LVD weight to the one specified
	 * @param weight : The new weight of LVD
	 */
	public void setLVDWeight(double weight) {
		weights[1] = weight;
	}
	
	/**
	 * Set the Cosine weight to the one specified
	 * @param weight : The new weight of Cosine
	 */
	public void setCosineWeight(double weight) {
		weights[2] = weight;
	}
	
	public double[] getWeights() {
		return weights;
	}

}

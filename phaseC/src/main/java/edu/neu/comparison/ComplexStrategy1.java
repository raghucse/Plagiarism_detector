package edu.neu.comparison;

import java.io.File;
import java.util.stream.DoubleStream;

import edu.neu.astgeneration.ASTUtils;

public class ComplexStrategy1 implements ASTBasedStrategy{

	private ASTUtils astUtils;
	
	private Strategy[] simplerStrategies;
	private double[] weights;
	
	public ComplexStrategy1(ASTUtils astUtils) {
		this.astUtils = astUtils;
		this.simplerStrategies = new Strategy[3];
		simplerStrategies[0] = new LCS(astUtils);
		simplerStrategies[1] = new LVDistance(astUtils);
		simplerStrategies[2] = new CosineSimilarity(astUtils);
		weights = new double[]{1.0, 1.0, 1.0};
	}
	
	@Override
	public STRATEGIES getName() {
		return STRATEGIES.COMPLEX1;
	}

	@Override
	public Scores compare(File f1, File f2) {
		double computedScore = 0.0;
		for(int i=0; i<simplerStrategies.length; i++) {
			computedScore += weights[i] * simplerStrategies[i].compare(f1, f2).getTotalScore();
		}
		computedScore /= DoubleStream.of(weights).sum();
		return new Scores(computedScore, "LCS:"+simplerStrategies[0]+ "; "+ "LVDistance:"+simplerStrategies[1]+ "; "+
				"CosineSimilarity:"+simplerStrategies[2]+ ";");
	}

	@Override
	public ASTUtils getASTUtils() {
		return astUtils;
	}

}

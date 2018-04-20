package edu.neu.comparison;

import edu.neu.Log;
import edu.neu.astgeneration.ASTUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Calculate the cosine similarity between two AST trees
 *
 * @author Junhao Qu
 */
public class CosineSimilarity implements ASTBasedStrategy{
	
	private ASTUtils astUtils;

    /**
     * The constructor is used to set the astUtils parameter
     * @param astUtils is an object of the ASTUtils class which
     *                 determines the value to be set for the local astUtils
     *                 variable
     */
	public CosineSimilarity(ASTUtils astUtils) {
		this.astUtils = astUtils;
	}


    /**
     * Returns the name of the strategy to be used for comparison
     * @return cosine as the value as cosine similarity will be used
     *         for comparison
     */
	public STRATEGIES getName() {
		return STRATEGIES.COSINE;
	}


    /**
     * This function is used to compare two files using cosine similarity
     * @param f1 is the first file
     * @param f2 is the second file
     * @return the score generated after implementing the cosine similarity
     *         measure strategy
     */
	public Scores compare(File f1, File f2) {
		try {
			double score = cosSim(
					astUtils.getAstPrinter().getASTStringeEq(astUtils.getParserFacade().parse(f1)),
					astUtils.getAstPrinter().getASTStringeEq(astUtils.getParserFacade().parse(f2))
					);
			return new Scores(score, "CosineSimilarity:"+score+ ";");
		} catch (IOException e) {
			Log.info("ERROR while reading files for comparison "+e.getStackTrace());
		}
		return new Scores(0, "CosineSimilarity:"+0+ ";");
	}

    /**
     * @return Returns the astUtils value
     */
	public ASTUtils getASTUtils() {
		return astUtils;
	}

    /**
     * This function is used to calculate the cosine similarity measure
     * @param tree1 is AST tree of the first file
     * @param tree2 is the AST tree of the second file
     * @return returns a similarity value
     */

    public double cosSim(ArrayList<String> tree1, ArrayList<String> tree2) {

        // Build a bow
        Set<String> bow = new HashSet<>();
        bow.addAll(tree1);
        bow.addAll(tree2);

        // Build two vectors
        List<String> newBow = new ArrayList<>();
        newBow.addAll(bow);
        int[] vec1 = new int[newBow.size()];
        int[] vec2 = new int[newBow.size()];
        for (int i = 0; i < newBow.size(); i++) {
            vec1[i] = tree1.contains(newBow.get(i)) ? 1 : 0;
            vec2[i] = tree2.contains(newBow.get(i)) ? 1 : 0;
        }

        // Calculate result
        int up = crossMul(vec1, vec2);
        double down = square(vec1) * square(vec2);
        double score =  up / down;
        double actualScore;
        actualScore = (score-0.51)/0.86;

        if(score == 1)
            return score;
        else
            return actualScore;
    }

    /**
     * This function is used to perform cross multiplication
     * @param arrayA integer array of the first file
     * @param arrayB integer array of the second file
     * @return cross multiplication value of the two integer arrays
     */
    private int crossMul(int[] arrayA, int[] arrayB) {
        int res = 0;
        for (int i = 0; i < arrayA.length; i++) {
            res += arrayA[i] * arrayB[i];
        }
        return res;
    }

    /**
     * Calculates the square of the values
     * @param arrayA is an integer array
     * @return the square of the values
     */
    private double square(int[] arrayA) {
        int res = 0;
        for (int i : arrayA) {
            res += i * i;
        }
        return Math.sqrt(res);
    }

}

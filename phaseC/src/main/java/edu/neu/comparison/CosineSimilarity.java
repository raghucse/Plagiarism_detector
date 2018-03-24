package edu.neu.comparison;

import java.io.File;
import java.io.IOException;
import java.util.*;

import edu.neu.Log;
import edu.neu.astgeneration.ASTUtils;

/**
 * Calculate the cosine similarity between two AST trees
 *
 * @author Junhao Qu
 * @see https://en.wikipedia.org/wiki/Cosine_similarity
 */
public class CosineSimilarity implements ASTBasedStrategy{
	
	private ASTUtils astUtils;
	
	public CosineSimilarity(ASTUtils astUtils) {
		this.astUtils = astUtils;
	}
	
	@Override
	public STRATEGIES getName() {
		return STRATEGIES.COSINE;
	}

	@Override
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
	
	@Override
	public ASTUtils getASTUtils() {
		return astUtils;
	}

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

    private int crossMul(int[] arrayA, int[] arrayB) {
        int res = 0;
        for (int i = 0; i < arrayA.length; i++) {
            res += arrayA[i] * arrayB[i];
        }
        return res;
    }

    private double square(int[] arrayA) {
        int res = 0;
        for (int i : arrayA) {
            res += i * i;
        }
        return Math.sqrt(res);
    }

}

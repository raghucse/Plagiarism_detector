package edu.neu.comparison;

import edu.neu.Log;
import edu.neu.astgeneration.ASTUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Implementing another comparison strategy by using the Levenshtein
 * distance technique.
 * @author Ananta Rajesh Arora
 * @version 1.0
 * @since 2018-03-20
 */


public class LVDistance implements ASTBasedStrategy{
	
	private ASTUtils astUtils;

	/**
	 * The constructor is used to set the astUtils parameter
	 * @param astUtils is an object of the ASTUtils class which
	 *                 determines the value to be set for the local astUtils
	 *                 variable
	 */
	public LVDistance(ASTUtils astUtils) {
		this.astUtils = astUtils;
	}

	/**
	 * Returns the name of the strategy to be used for comparison
	 * @return LEVENSHTEIN as the value as Levenshtein Distance technique
	 * 		   will be used for comparison
	 */
	public STRATEGIES getName() {
		return STRATEGIES.LEVENSHTEIN;
	}

	/**
	 * This function is used to compare two files using Levenshtein
	 * distance Strategy
	 * @param f1 is the first file
	 * @param f2 is the second file
	 * @return the score generated after implementing the Levenshtein
	 *         distance strategy
	 */
	public Scores compare(File f1, File f2) {
		try {
			double score = calculateLD(
					astUtils.getAstPrinter().getASTStringeEq(astUtils.getParserFacade().parse(f1)),
					astUtils.getAstPrinter().getASTStringeEq(astUtils.getParserFacade().parse(f2))
					);
			return new Scores(score, "LVDistance:"+score+ ";");
		} catch (IOException e) {
			Log.info("ERROR while reading files for comparison "+e.getStackTrace());
		}
		return new Scores(0, "LVDistance:"+0+ ";");
	}

	/**
	 * @return Returns the astUtils value
	 */
	public ASTUtils getASTUtils() {
		return astUtils;
	}


	/**
	 * This function is used to calculate the Levenshtein distance
	 * between the two given trees
	 * @param tree1 is the AST tree of the first file
	 * @param tree2 is the AST tree of the second file
	 * @return a levenshtein distance score
	 */
    public double calculateLD(ArrayList<String> tree1, ArrayList<String> tree2) {
    		int tree1Length = tree1.size();
    		int tree2Length = tree2.size();
        int cost[][] = new int[tree1Length + 1][tree2Length + 1];

        for (int i = 0; i <= tree1Length; i++) {
            for (int j = 0; j <= tree2Length; j++) {
                if (i == 0)
                    cost[i][j] = j;

                else if (j == 0)
                    cost[i][j] = i;

                else if (tree1.get(i - 1).equals(tree2.get(j - 1)))
                    cost[i][j] = cost[i - 1][j - 1];

                else
                    cost[i][j] = 1 + Math.min(cost[i - 1][j - 1], Math.min(cost[i][j - 1], cost[i - 1][j]));
            }
        }

        int finalCost =  cost[tree1Length][tree2Length];

        double max_nodes = (double)Math.max(tree1Length,tree2Length);
        double score = (1.0-(finalCost/max_nodes));
        return score;

    }


}
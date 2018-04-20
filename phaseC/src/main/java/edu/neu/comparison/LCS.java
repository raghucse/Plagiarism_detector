package edu.neu.comparison;

import edu.neu.Log;
import edu.neu.astgeneration.ASTUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Implementing another comparison strategy by using the Longest
 * Common Subsequence (LCS) technique.
 * @author Ananta Rajesh Arora, Bharat Vaidhyanathan
 * @version 1.0
 * @since 2018-03-20
 */

public class LCS implements ASTBasedStrategy{

	private ASTUtils astUtils;

	/**
	 * The constructor is used to set the astUtils parameter
	 * @param astUtils is an object of the ASTUtils class which
	 *                 determines the value to be set for the local astUtils
	 *                 variable
	 */
	public LCS(ASTUtils astUtils) {
		this.astUtils = astUtils;
	}


	/**
	 * Returns the name of the strategy to be used for comparison
	 * @return LCS as the value as Longest Common Subsequence technique
	 * 		   will be used for comparison
	 */
	public STRATEGIES getName() {
		return STRATEGIES.LCS;
	}

	/**
	 * This function is used to compare two files using LCS Strategy
	 * @param f1 is the first file
	 * @param f2 is the second file
	 * @return the score generated after implementing the longest common
	 *         subsequence strategy
	 */
	public Scores compare(File f1, File f2) {
		try {
			double score = calculateLCS(
					astUtils.getAstPrinter().getASTStringeEq(astUtils.getParserFacade().parse(f1)),
					astUtils.getAstPrinter().getASTStringeEq(astUtils.getParserFacade().parse(f2))
					);
			return new Scores(score, "LCS:"+score+ ";");
		} catch (IOException e) {
			Log.info("ERROR while reading files for comparison "+e.getStackTrace());
		}
		return new Scores(0, "LCS:"+0+ ";");
	}

	/**
	 * @return Returns the astUtils value
	 */
	public ASTUtils getASTUtils() {
		return astUtils;
	}
	
    /**
     * This function implements the Longest Common Subsequence technique
     * and returns a normalized score which determines whether or not the files are
     * plagiarized
     * @param tree1 is a list of strings equivalent of the AST generated for the first file
     * @param tree2 is a list of strings equivalent of the AST generated for the second file
     * @return returns a score for the given files which determines whether or not the
     *         given files are plagiarized
     */
    public static double calculateLCS(ArrayList<String>tree1, ArrayList<String> tree2){

        int tree1Size = tree1.size();
        int tree2Size = tree2.size();
        int cost;
        double score;
        double maxNodeCount;
        int[][] lCSScore = new int[tree1Size+1][tree2Size+1];

        for(int i=0; i<=tree1Size; i++)
        {
            for(int j = 0; j<=tree2Size; j++)
            {
                if(i == 0 || j == 0)
                	lCSScore[i][j] = 0;
                else if(tree1.get(i-1).equals(tree2.get(j-1)))
                	lCSScore[i][j] = lCSScore[i-1][j-1]+1;
                else
                	lCSScore[i][j] = Math.max(lCSScore[i-1][j], lCSScore[i][j-1]);
            }
        }
        cost =  lCSScore[tree1Size][tree2Size];
        maxNodeCount =Math.max(tree1Size,tree2Size);
        score = cost/maxNodeCount;
        return score;
    }

}


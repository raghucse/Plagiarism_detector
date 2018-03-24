package edu.neu.comparison;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import edu.neu.Log;
import edu.neu.astgeneration.ASTUtils;

public class LVDistance implements ASTBasedStrategy{
	
	private ASTUtils astUtils;
	
	public LVDistance(ASTUtils astUtils) {
		this.astUtils = astUtils;
	}
	
	@Override
	public STRATEGIES getName() {
		return STRATEGIES.LEVENSHTEIN;
	}

	@Override
	public double compare(File f1, File f2) {
		try {
			return calculateLD(
					astUtils.getAstPrinter().getASTStringeEq(astUtils.getParserFacade().parse(f1)),
					astUtils.getAstPrinter().getASTStringeEq(astUtils.getParserFacade().parse(f2))
					);
		} catch (IOException e) {
			Log.info("ERROR while reading files for comparison "+e.getStackTrace());
		}
		return 0;
				
	}
	
	@Override
	public ASTUtils getASTUtils() {
		return astUtils;
	}


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
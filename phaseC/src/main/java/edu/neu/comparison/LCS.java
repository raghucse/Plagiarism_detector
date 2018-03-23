package edu.neu.comparison;

import java.util.ArrayList;
/**
 * Implementing another comparison strategy by using the Longest
 * Common Subsequence (LCS) technique.
 */

public class LCS {

    public static double calculateLCS(ArrayList<String>tree1, ArrayList<String> tree2){

        int tree1Size = tree1.size();
        int tree2Size = tree2.size();
        int cost;
        double score, maxNodeCount;
        int LCSScore[][] = new int[tree1Size+1][tree2Size+1];

        for(int i=0; i<=tree1Size; i++)
        {
            for(int j = 0; j<=tree2Size; j++)
            {
                if(i == 0 || j == 0)
                    LCSScore[i][j] = 0;
                else if(tree1.get(i-1).equals(tree2.get(j-1)))
                    LCSScore[i][j] = LCSScore[i-1][j-1]+1;
                else
                    LCSScore[i][j] = Math.max(LCSScore[i-1][j], LCSScore[i][j-1]);
            }
        }
        cost =  LCSScore[tree1Size][tree2Size];
        maxNodeCount =Math.max(tree1Size,tree2Size);
        score = cost/maxNodeCount;
        return score;
    }

}


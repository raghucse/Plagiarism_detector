package edu.neu.comparison;

/**
 * Calculate the edit distance of two String
 *
 * @author  Junhao qu
 * @see     "https://en.wikipedia.org/wiki/Edit_distance"
 */
public class EditDistance {

    /**
     * Calculate the edit distance between two strings
     * @param word1  the first string
     * @param word2  the second string
     * @return      the edit distance
     */
    public int editDist(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];


        for(int i = 1; i <= m; i++){
            dp[i][0] = i;
        }
        for(int i = 1; i <= n; i++){
            dp[0][i] = i;
        }

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                int insertion = dp[i][j - 1] + 1;
                int deletion = dp[i - 1][j] + 1;
                int replace = dp[i - 1][j - 1] + (word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1);
                dp[i][j] = Math.min(replace, Math.min(insertion, deletion));
            }
        }
        return dp[m][n];
    }

    /**
     * Set a judge value
     * @param len   length of LCS
     * @param val   the valve
     * @return      if the student is cheating
     */
    public String getRes(int len, int val) {
        if (len > val) {
            return "The student is cheating!";
        }
        return "The student is good";
    }
}

package edu.neu.comparison;

import java.util.TreeSet;

/**
 * Longest common sub-sequence.
 */
public class LCS {

    private String str1;    // First String
    private String str2;    // Second String
    private int[][] table;  // DP result table
    private TreeSet<String> set = new TreeSet<>();

    /**
     * Constructor
     *
     * @param str1  First String
     * @param str2  Second String
     */
    public LCS(String str1, String str2) {
        this.str1 = str1;
        this.str2 = str2;
    }

    /**
     * Building the result table
     *
     * @param m     Length of the first String
     * @param n     Length of the second String
     * @return      Length of LCS
     */
    private int lcs(int m, int n) {
        table = new int[m + 1][n + 1];

        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i == 0 || j == 0) {
                    table[i][j] = 0;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    table[i][j] = table[i - 1][j - 1] + 1;
                } else {
                    table[i][j] = Math.max(table[i - 1][j], table[i][j - 1]);
                }
            }
        }
        return table[m][n];
    }

    /**
     * Backtracking
     *
     * @param i         index
     * @param j         index
     * @param res   Res set
     */
    private void traceBack(int i, int j, StringBuilder res) {
        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                res.append(str1.charAt(i - 1));
                --i;
                --j;
            } else {
                if (table[i - 1][j] > table[i][j - 1]) {
                    --i;
                } else if (table[i - 1][j] < table[i][j - 1]) {
                    --j;
                } else {
                    traceBack(i - 1, j, res);
                    traceBack(i, j - 1, res);
                    return;
                }
            }
        }
        set.add(res.reverse().toString());
    }

    /**
     * Getter of the length of the LCS
     */
    public int getLength() {
        return lcs(str1.length(), str2.length());
    }

    /**
     * Getter of LCS as a string
     */
    public String getLCS() {
        StringBuilder sb = new StringBuilder();
        StringBuilder res = new StringBuilder();
        traceBack(str1.length(), str2.length(), sb);
        for (String s : set) {
            res.append(s);
        }
        return res.toString();
    }
}

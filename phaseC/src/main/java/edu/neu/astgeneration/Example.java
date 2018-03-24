package edu.neu.astgeneration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import edu.neu.comparison.LVDistance;
import edu.neu.comparison.LCS;

/**
 * This class contains the main function which is used for generating
 * ASTs for the given files and applies comparison strategy on them
 * @author Ananta Rajesh Arora
 * @version 1.0
 * @since 2018-03-20
 */

public class Example {

    /**
     * This function is used to call printAST() function that generates AST
     * for the given files and applies the comparison strategy on them
     * @param args are the command line arguments if any
     * @throws IOException in case of a invalid input
     */

    public static void main(String[] args) throws IOException {
        new Example().printAST();
    }


    /**
     * This function is used for generating the ASTs and applying
     * comparison strategies on them
     * @throws IOException
     */
    public void printAST() {
    		File f1 = new File(getClass().getClassLoader().getResource("simplecode2.py").getFile());
        File f2 = new File(getClass().getClassLoader().getResource("simplecode.py").getFile());
        
        LVDistance lvd = new LVDistance(new ASTUtils());
        LCS lcs = new LCS(new ASTUtils());
        
        double lvdScore = lvd.compare(f1, f2).getTotalScore();
        double lcsScore = lcs.compare(f1, f2).getTotalScore();
        
        System.out.println("Levenshtein Score: "+lvdScore);
        System.out.println("LCS Score : "+lcsScore);
    }

}


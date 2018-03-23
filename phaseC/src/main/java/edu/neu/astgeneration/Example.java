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
    public void printAST() throws IOException {
    		/*
    		double EPSILON = 0.01;

        File f1 = new File(getClass().getClassLoader().getResource("simplecode2.py").getFile());
        File f2 = new File(getClass().getClassLoader().getResource("simplecode.py").getFile());
        
        LVDistance lvDistance = new LVDistance(new ASTUtils());
        
        double LVscore = lvDistance.compare(f1, f2);
        System.out.println("Levenshtein Score: "+LVscore);

        double LCSScore = LCS.calculateLCS(AST1String,AST2String);
        System.out.println("LCS Score : "+LCSScore);
		*/

    }

}


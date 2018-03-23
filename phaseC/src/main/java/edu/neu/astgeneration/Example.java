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

        ArrayList<String> AST1String = new ArrayList<>();
        ArrayList<String> AST2String = new ArrayList<>();
        ParserFacade parserFacade = new ParserFacade();
        AstPrinter astPrinter = new AstPrinter();
        AST1String = astPrinter.getASTStringeEq(parserFacade.parse(new File(getClass().getClassLoader().getResource("simplecode2.py").getFile())));
        AST2String = astPrinter.getASTStringeEq(parserFacade.parse(new File(getClass().getClassLoader().getResource("simplecode.py").getFile())));
        int tree1Length = AST1String.size();
        int tree2Length = AST2String.size();
        double LVscore = LVDistance.calculateLD(AST1String,AST2String,tree1Length,tree2Length);
        System.out.println("Levenshtein Score: "+LVscore);

        double LCSScore = LCS.calculateLCS(AST1String,AST2String);
        System.out.println("LCS Score : "+LCSScore);


    }

}


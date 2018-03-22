package edu.neu.astgeneration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import edu.neu.comparison.LVDistance;

public class Example {

    //private static Logger lg = Logger.getLogger(AstPrinter.class.getName());

    public static void main(String[] args) throws IOException {
        new Example().printAST();
    }



    public void printAST() throws IOException {

        ArrayList<String> AST1String = new ArrayList<String>();
        ArrayList<String> AST2String = new ArrayList<String>();
        ParserFacade parserFacade = new ParserFacade();
        AstPrinter astPrinter = new AstPrinter();
        AST1String = astPrinter.getASTStringeEq(parserFacade.parse(new File(getClass().getClassLoader().getResource("simplecode2.py").getFile())));
        AST2String = astPrinter.getASTStringeEq(parserFacade.parse(new File(getClass().getClassLoader().getResource("simplecode.py").getFile())));
        int tree1Length = AST1String.size();
        int tree2Length = AST2String.size();
        double score = LVDistance.calculateLD(AST1String,AST2String,tree1Length,tree2Length);
        System.out.println(score);


    }

}


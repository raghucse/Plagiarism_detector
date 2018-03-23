package edu.neu.testResources;

import edu.neu.astgeneration.AstPrinter;
import edu.neu.astgeneration.ParserFacade;
import edu.neu.comparison.LCS;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class LCSTest {

    /**
     * Testing when two python files with little similar content is given
     * as an input
     */
    @Test
    public void test01(){
        double EPSILON = 0.01;
        double roundOff = 10000.0;
        double actualValue = 0.547945205479452;
        ArrayList<String> AST1String = new ArrayList<String>();
        ArrayList<String> AST2String = new ArrayList<String>();
        ParserFacade parserFacade = new ParserFacade();
        AstPrinter astPrinter = new AstPrinter();

        try {
            AST1String = astPrinter.getASTStringeEq(parserFacade.parse(new File(getClass().getClassLoader().getResource("simplecode2.py").getFile())));
            AST2String = astPrinter.getASTStringeEq(parserFacade.parse(new File(getClass().getClassLoader().getResource("simplecode.py").getFile())));
        } catch (IOException e) {

        }
        int tree1Length = AST1String.size();
        int tree2Length = AST2String.size();
        double score = LCS.calculateLCS(AST1String,AST2String);
        assertEquals((Math.round(score*roundOff)/roundOff),actualValue,EPSILON);
    }

    /**
     * Testing when both the files are completely same
     */
    @Test
    public void test02(){
        double EPSILON = 0.01;
        double roundOff = 10000.0;
        double actualValue = 1.0;
        ArrayList<String> AST1String = new ArrayList<String>();
        ArrayList<String> AST2String = new ArrayList<String>();
        ParserFacade parserFacade = new ParserFacade();
        AstPrinter astPrinter = new AstPrinter();

        try {
            AST1String = astPrinter.getASTStringeEq(parserFacade.parse(new File(getClass().getClassLoader().getResource("simplecode2.py").getFile())));
        } catch (IOException e) {

        }
        int tree1Length = AST1String.size();
        double score = LCS.calculateLCS(AST1String,AST1String);
        assertEquals((Math.round(score*roundOff)/roundOff),actualValue,EPSILON);
    }


}
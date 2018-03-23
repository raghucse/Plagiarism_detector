package edu.neu.testResources;

import edu.neu.astgeneration.AstPrinter;
import edu.neu.astgeneration.ParserFacade;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import edu.neu.comparison.LVDistance;

import static org.junit.Assert.*;

public class LVDistanceTest {

    @Test
    public void test01() {
        double EPSILON = 0.01;
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
        double score = LVDistance.calculateLD(AST1String,AST2String,tree1Length,tree2Length);
        assertEquals((Math.round(score*10000.0)/10000.0), 0.547945205479452,EPSILON);
    }

    @Test
    public void test02() {
        double EPSILON = 0.01;
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
        double score = LVDistance.calculateLD(AST1String,AST1String,tree1Length,tree1Length);
        assertEquals((Math.round(score*10000.0)/10000.0), 1.0,EPSILON);
    }

}
package edu.neu.testResources;

import edu.neu.astgeneration.AstPrinter;
import edu.neu.astgeneration.ParserFacade;
import org.junit.Test;
import edu.neu.comparison.CosineSimilarity;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CosineSimilarityTest {

    @Test
    public void twoDifferentFiles() {
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

        CosineSimilarity cos = new CosineSimilarity();
        double score = cos.cosSim(AST1String, AST2String);
        assertEquals((Math.round(score * 10000.0) / 10000.0), 0.9701425001453319, EPSILON);
    }
    

}
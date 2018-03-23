package edu.neu.testResources;

import edu.neu.astgeneration.ASTUtils;
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
    public void testDifferent() {
        double EPSILON = 0.01;

        File f1 = new File(getClass().getClassLoader().getResource("simplecode2.py").getFile());
        File f2 = new File(getClass().getClassLoader().getResource("simplecode.py").getFile());
        
        LVDistance lvDistance = new LVDistance(new ASTUtils());
        
        double score = lvDistance.compare(f1, f2);
        assertEquals((Math.round(score*10000.0)/10000.0), 0.547945205479452,EPSILON);
    }

    @Test
    public void testSame() {
    		double EPSILON = 0.01;
        File f1 = new File(getClass().getClassLoader().getResource("simplecode2.py").getFile());
        LVDistance lvDistance = new LVDistance(new ASTUtils());
        double score = lvDistance.compare(f1, f1);
        assertEquals((Math.round(score*10000.0)/10000.0), 1.0,EPSILON);
    }

}
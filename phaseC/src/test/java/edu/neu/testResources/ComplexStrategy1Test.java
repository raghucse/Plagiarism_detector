package edu.neu.testResources;

import edu.neu.astgeneration.ASTUtils;
import org.junit.Test;

import edu.neu.comparison.ComplexStrategy1;
import java.io.File;

import static org.junit.Assert.*;

public class ComplexStrategy1Test {

    @Test
    public void twoDifferentFiles() {
        double EPSILON = 0.01;
        double roundOff = 10000.0;
        double expectedValue = 0.547945205479452;
        File f1 = new File(getClass().getClassLoader().getResource("simplecode2.py").getFile());
        File f2 = new File(getClass().getClassLoader().getResource("simplecode.py").getFile());

        ComplexStrategy1 complex = new ComplexStrategy1(new ASTUtils());
        double score = complex.compare(f1, f2);
        assertEquals(expectedValue, (Math.round(score * roundOff) / roundOff), EPSILON);
    }

    @Test
    public void twoSameFiles() {
        double EPSILON = 0.01;
        double roundOff = 10000.0;
        double expectedValue = 1.0;
        File f1 = new File(getClass().getClassLoader().getResource("simplecode2.py").getFile());
        ComplexStrategy1 complex = new ComplexStrategy1(new ASTUtils());
        double score = complex.compare(f1, f1);
        assertEquals(expectedValue, (Math.round(score * roundOff) / roundOff), EPSILON);
    }
    @Test
    public void testVeryDifferent() {
        double EPSILON = 0.01;
        double expectedScore = 0.197;
        double roundOff = 10000.0;
        File f1 = new File(getClass().getClassLoader().getResource("samplefile1.py").getFile());
        File f2 = new File(getClass().getClassLoader().getResource("samplefile2.py").getFile());
        ComplexStrategy1 complex = new ComplexStrategy1(new ASTUtils());
        double score = complex.compare(f1, f2);;
        assertEquals((Math.round(score*roundOff)/roundOff), expectedScore,EPSILON);
    }

}
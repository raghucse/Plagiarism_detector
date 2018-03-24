package edu.neu.testResources;

import edu.neu.astgeneration.ASTUtils;
import edu.neu.comparison.CosineSimilarity;
import edu.neu.comparison.LCS;
import edu.neu.comparison.STRATEGIES;

import org.junit.Test;

import java.io.File;

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

        File f1 = new File(getClass().getClassLoader().getResource("simplecode2.py").getFile());
        File f2 = new File(getClass().getClassLoader().getResource("simplecode.py").getFile());
        
        LCS lcs = new LCS(new ASTUtils());
        double score = lcs.compare(f1, f2);
        assertEquals((Math.round(score*roundOff)/roundOff),actualValue,EPSILON);
    }

    /**
     * Testing when both the files are completely same
     */
    @Test
    public void test02(){
    		double EPSILON = 0.01;
        double roundOff = 10000.0;
        double actualValue = 1;

        File f1 = new File(getClass().getClassLoader().getResource("simplecode2.py").getFile());
        
        LCS lcs = new LCS(new ASTUtils());
        double score = lcs.compare(f1, f1);
        assertEquals((Math.round(score*roundOff)/roundOff),actualValue,EPSILON);
    }

    @Test
    public void testVeryDifferent() {
        double EPSILON = 0.01;
        double expectedScore = 0.197;
        double roundOff = 10000.0;
        File f1 = new File(getClass().getClassLoader().getResource("samplefile1.py").getFile());
        File f2 = new File(getClass().getClassLoader().getResource("samplefile2.py").getFile());
        LCS lcs = new LCS(new ASTUtils());
        double score = lcs.compare(f1, f2);
        assertEquals((Math.round(score*roundOff)/roundOff), expectedScore,EPSILON);
    }
    
    @Test
    public void testBasicConstruction() {
    		LCS lcs = new LCS(new ASTUtils());
        assertEquals(STRATEGIES.LCS, lcs.getName());
    }
    
    @Test(expected = NullPointerException.class)
    public void testThrowsNullASTUtils() {
    		LCS lcs = new LCS(null);
        assertNull(lcs.getASTUtils());
        lcs.compare(null, null);
    }


}
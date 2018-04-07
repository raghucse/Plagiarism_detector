package edu.neu.testResources;

import edu.neu.astgeneration.ASTUtils;
import edu.neu.comparison.LCS;

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

        File f1 = new File(getClass().getClassLoader().getResource("testfiles/student02/simplecode2.py").getFile());
        File f2 = new File(getClass().getClassLoader().getResource("testfiles/student01/simplecode.py").getFile());
        
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

        File f1 = new File(getClass().getClassLoader().getResource("testfiles/student02/simplecode2.py").getFile());
        
        LCS lcs = new LCS(new ASTUtils());
        double score = lcs.compare(f1, f1);
        assertEquals((Math.round(score*roundOff)/roundOff),actualValue,EPSILON);
    }


}
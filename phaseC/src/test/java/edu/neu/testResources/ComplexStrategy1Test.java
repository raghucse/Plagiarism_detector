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

        File f1 = new File(getClass().getClassLoader().getResource("testfiles/student02/simplecode2.py").getFile());
        File f2 = new File(getClass().getClassLoader().getResource("testfiles/student01/simplecode.py").getFile());

        ComplexStrategy1 complex = new ComplexStrategy1(new ASTUtils());
        double score = complex.compare(f1, f2);
        assertEquals(0.6887, (Math.round(score * 10000.0) / 10000.0), EPSILON);
    }

    @Test
    public void twoSameFiles() {
        double EPSILON = 0.01;
        
        File f1 = new File(getClass().getClassLoader().getResource("testfiles/student02/simplecode2.py").getFile());
        
        ComplexStrategy1 complex = new ComplexStrategy1(new ASTUtils());
        double score = complex.compare(f1, f1);
        assertEquals(1.0, (Math.round(score * 10000.0) / 10000.0), EPSILON);
    }

}
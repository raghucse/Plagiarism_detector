package edu.neu.testResources;

import edu.neu.astgeneration.ASTUtils;
import org.junit.Test;

import edu.neu.comparison.ComplexStrategy1;
import edu.neu.comparison.LCS;

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
        double score = complex.compare(f1, f2).getTotalScore();
        assertEquals(expectedValue, (Math.round(score * roundOff) / roundOff), EPSILON);
    }

    @Test
    public void twoSameFiles() {
        double EPSILON = 0.01;
        double roundOff = 10000.0;
        double expectedValue = 1.0;
        File f1 = new File(getClass().getClassLoader().getResource("simplecode2.py").getFile());
        ComplexStrategy1 complex = new ComplexStrategy1(new ASTUtils());
        double score = complex.compare(f1, f1).getTotalScore();
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
        double score = complex.compare(f1, f2).getTotalScore();
        assertEquals((Math.round(score*roundOff)/roundOff), expectedScore,EPSILON);
    }
    
    
    /**
     * Example test : for exactly same files
     */
    @Test
    public void testExactlySame(){
        double EPSILON = 0.01;
        double roundOff = 10000.0;
        double actualValue = 0.8566;

        File f1 = new File(getClass().getClassLoader().getResource("example/exactlySame/student1/student1-file1.py").getFile());
        File f2 = new File(getClass().getClassLoader().getResource("example/exactlySame/student2/student2-file1.py").getFile());
        
        ComplexStrategy1 complex = new ComplexStrategy1(new ASTUtils());
        double score = complex.compare(f1, f2).getTotalScore();
        assertEquals((Math.round(score*roundOff)/roundOff),actualValue,EPSILON);
    }
    
    
    /**
     * Example test : for files replaced names
     */
    @Test
    public void testSimplyChangeNames(){
        double EPSILON = 0.01;
        double roundOff = 10000.0;
        double actualValue = 0.8566;

        File f1 = new File(getClass().getClassLoader().getResource("example/simplyChangeNames/student1/student1-file1.py").getFile());
        File f2 = new File(getClass().getClassLoader().getResource("example/simplyChangeNames/student2/student2-file1.py").getFile());
        
        ComplexStrategy1 complex = new ComplexStrategy1(new ASTUtils());
        double score = complex.compare(f1, f2).getTotalScore();
        assertEquals((Math.round(score*roundOff)/roundOff),actualValue,EPSILON);
    }
    
    /**
     * Example test : for files changed sequence
     */
    @Test
    public void testSimplyChangeSequence(){
        double EPSILON = 0.01;
        double roundOff = 10000.0;
        double actualValue = 0.5867;

        File f1 = new File(getClass().getClassLoader().getResource("example/simplyChangingSequence/student1/student1-file1.py").getFile());
        File f2 = new File(getClass().getClassLoader().getResource("example/simplyChangingSequence/student2/student2-file1.py").getFile());
        
        ComplexStrategy1 complex = new ComplexStrategy1(new ASTUtils());
        double score = complex.compare(f1, f2).getTotalScore();
        assertEquals((Math.round(score*roundOff)/roundOff),actualValue,EPSILON);
    }
    
    /**
     * Example test : for files replaced names
     */
    @Test
    public void testChangeNameAndOrder(){
        double EPSILON = 0.01;
        double roundOff = 10000.0;
        double actualValue = 0.6322;

        File f1 = new File(getClass().getClassLoader().getResource("example/changeNameAndOrder/student1/student1-file1.py").getFile());
        File f2 = new File(getClass().getClassLoader().getResource("example/changeNameAndOrder/student2/student2-file1.py").getFile());
        
        ComplexStrategy1 complex = new ComplexStrategy1(new ASTUtils());
        double score = complex.compare(f1, f2).getTotalScore();
        assertEquals((Math.round(score*roundOff)/roundOff),actualValue,EPSILON);
    }
    
    /**
     * Example test : for totally different files
     */
    @Test
    public void testNotSameAtAll(){
        double EPSILON = 0.01;
        double roundOff = 10000.0;
        double actualValue = 0.1341;

        File f1 = new File(getClass().getClassLoader().getResource("example/notSameAtAll/student1/student1-file1.py").getFile());
        File f2 = new File(getClass().getClassLoader().getResource("example/notSameAtAll/student2/student2-file1.py").getFile());
        
        ComplexStrategy1 complex = new ComplexStrategy1(new ASTUtils());
        double score = complex.compare(f1, f2).getTotalScore();
        assertEquals((Math.round(score*roundOff)/roundOff),actualValue,EPSILON);
    }
    
    
    /**
     * Example test : for exactly same author
     */
    @Test
    public void testFromSameAuthor(){
        // Not implemented yet
    }

}
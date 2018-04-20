package edu.neu.testResources;

import edu.neu.astgeneration.ASTUtils;

import org.junit.Test;
import edu.neu.comparison.CosineSimilarity;
import edu.neu.comparison.LVDistance;
import edu.neu.comparison.STRATEGIES;

import java.io.File;

import static org.junit.Assert.*;

public class CosineSimilarityTest {

    @Test
    public void twoDifferentFiles() {
        double EPSILON = 0.01;
        double roundOff = 10000.0;
        double actualValue = 0.535;
        File f1 = new File(getClass().getClassLoader().getResource("simplecode2.py").getFile());
        File f2 = new File(getClass().getClassLoader().getResource("simplecode.py").getFile());
        CosineSimilarity cos = new CosineSimilarity(new ASTUtils());
        double score = cos.compare(f1, f2).getTotalScore();
        assertEquals((Math.round(score * roundOff) / roundOff), actualValue, EPSILON);
    }

    @Test
    public void twoSameFiles() {
        double EPSILON = 0.01;
        double roundOff = 10000.0;
        double actualValue = 1.0;
        File f1 = new File(getClass().getClassLoader().getResource("simplecode2.py").getFile());
        CosineSimilarity cos = new CosineSimilarity(new ASTUtils());
        double score = cos.compare(f1, f1).getTotalScore();
        assertEquals((Math.round(score * roundOff) / roundOff), actualValue, EPSILON);
    }

    @Test
    public void testVeryDifferent() {
        double EPSILON = 0.01;
        double expectedScore = 0.187;
        double roundOff = 10000.0;
        File f1 = new File(getClass().getClassLoader().getResource("samplefile1.py").getFile());
        File f2 = new File(getClass().getClassLoader().getResource("samplefile2.py").getFile());
        CosineSimilarity cos = new CosineSimilarity(new ASTUtils());
        double score = cos.compare(f1, f2).getTotalScore();
        assertEquals((Math.round(score*roundOff)/roundOff), expectedScore,EPSILON);
    }
    
    
    @Test
    public void testBasicConstruction() {
        CosineSimilarity cos = new CosineSimilarity(new ASTUtils());
        assertEquals(STRATEGIES.COSINE, cos.getName());
    }
    
    @Test(expected = NullPointerException.class)
    public void testThrowsNullASTUtils() {
        CosineSimilarity cos = new CosineSimilarity(null);
        assertNull(cos.getASTUtils());
        cos.compare(null, null);
    }

}
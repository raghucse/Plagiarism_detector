package edu.neu.testResources;

import edu.neu.astgeneration.ASTUtils;
import edu.neu.comparison.LCS;
import edu.neu.comparison.LVDistance;
import edu.neu.comparison.STRATEGIES;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LVDistanceTest {

    @Test
    public void testDifferent() {
        double EPSILON = 0.01;
        double expectedScore = 0.547945205479452;
        double roundOff = 10000.0;
        File f1 = new File(getClass().getClassLoader().getResource("simplecode2.py").getFile());
        File f2 = new File(getClass().getClassLoader().getResource("simplecode.py").getFile());
        LVDistance lvDistance = new LVDistance(new ASTUtils());
        double score = lvDistance.compare(f1, f2).getTotalScore();
        assertEquals((Math.round(score*roundOff)/roundOff), expectedScore,EPSILON);
    }

    @Test
    public void testSame() {
        double EPSILON = 0.01;
        double expectedScore = 1.0;
        double roundOff = 10000.0;
        File f1 = new File(getClass().getClassLoader().getResource("simplecode2.py").getFile());
        LVDistance lvDistance = new LVDistance(new ASTUtils());
        double score = lvDistance.compare(f1, f1).getTotalScore();
        assertEquals((Math.round(score*roundOff)/roundOff), expectedScore,EPSILON);
    }

    @Test
    public void testVeryDifferent() {
        double EPSILON = 0.01;
        double expectedScore = 0.197;
        double roundOff = 10000.0;
        File f1 = new File(getClass().getClassLoader().getResource("samplefile1.py").getFile());
        File f2 = new File(getClass().getClassLoader().getResource("samplefile2.py").getFile());
        LVDistance lvDistance = new LVDistance(new ASTUtils());
        double score = lvDistance.compare(f1, f2).getTotalScore();
        assertEquals((Math.round(score*roundOff)/roundOff), expectedScore,EPSILON);
    }

    @Test
    public void testBasicConstruction() {
    		LVDistance lvd = new LVDistance(new ASTUtils());
        assertEquals(STRATEGIES.LEVENSHTEIN, lvd.getName());
    }
    
    @Test(expected = NullPointerException.class)
    public void testThrowsNullASTUtils() {
    		LVDistance lvd = new LVDistance(null);
        assertNull(lvd.getASTUtils());
        lvd.compare(null, null);
    }
    
    /**
     * Example test : for exactly same files
     */
    @Test
    public void testExactlySame(){
        double EPSILON = 0.01;
        double roundOff = 10000.0;
        double actualValue = 1;

        File f1 = new File(getClass().getClassLoader().getResource("example/exactlySame/student1/student1-file1.py").getFile());
        File f2 = new File(getClass().getClassLoader().getResource("example/exactlySame/student2/student2-file1.py").getFile());
        
        LVDistance lvDistance = new LVDistance(new ASTUtils());
        double score = lvDistance.compare(f1, f2).getTotalScore();
        assertEquals((Math.round(score*roundOff)/roundOff),actualValue,EPSILON);
    }
    
    
    /**
     * Example test : for files replaced names
     */
    @Test
    public void testSimplyChangeNames(){
        double EPSILON = 0.01;
        double roundOff = 10000.0;
        double actualValue = 1;

        File f1 = new File(getClass().getClassLoader().getResource("example/simplyChangeNames/student1/student1-file1.py").getFile());
        File f2 = new File(getClass().getClassLoader().getResource("example/simplyChangeNames/student2/student2-file1.py").getFile());
        
        LVDistance lvDistance = new LVDistance(new ASTUtils());
        double score = lvDistance.compare(f1, f2).getTotalScore();
        assertEquals((Math.round(score*roundOff)/roundOff),actualValue,EPSILON);
    }
    
    /**
     * Example test : for exactly same files
     */
    @Test
    public void testNotSameAtAll(){
        double EPSILON = 0.01;
        double roundOff = 10000.0;
        double actualValue = 0.1171;

        File f1 = new File(getClass().getClassLoader().getResource("example/notSameAtAll/student1/student1-file1.py").getFile());
        File f2 = new File(getClass().getClassLoader().getResource("example/notSameAtAll/student2/student2-file1.py").getFile());
        
        LVDistance lvDistance = new LVDistance(new ASTUtils());
        double score = lvDistance.compare(f1, f2).getTotalScore();
        assertEquals((Math.round(score*roundOff)/roundOff),actualValue,EPSILON);
    }
    
}
package edu.neu.testResources;

import edu.neu.astgeneration.ASTUtils;
import edu.neu.comparison.LVDistance;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class LVDistanceTest {

    @Test
    public void testDifferent() {
        double EPSILON = 0.01;
        double expectedScore = 0.547945205479452;
        double roundOff = 10000.0;
        File f1 = new File(getClass().getClassLoader().getResource("simplecode2.py").getFile());
        File f2 = new File(getClass().getClassLoader().getResource("simplecode.py").getFile());
        LVDistance lvDistance = new LVDistance(new ASTUtils());
        double score = lvDistance.compare(f1, f2);
        assertEquals((Math.round(score*roundOff)/roundOff), expectedScore,EPSILON);
    }

    @Test
    public void testSame() {
        double EPSILON = 0.01;
        double expectedScore = 1.0;
        double roundOff = 10000.0;
        File f1 = new File(getClass().getClassLoader().getResource("simplecode2.py").getFile());
        LVDistance lvDistance = new LVDistance(new ASTUtils());
        double score = lvDistance.compare(f1, f1);
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
        double score = lvDistance.compare(f1, f2);
        assertEquals((Math.round(score*roundOff)/roundOff), expectedScore,EPSILON);
    }

}
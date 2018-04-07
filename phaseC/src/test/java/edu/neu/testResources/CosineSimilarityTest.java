package edu.neu.testResources;

import edu.neu.astgeneration.ASTUtils;
import org.junit.Test;
import edu.neu.comparison.CosineSimilarity;
import java.io.File;

import static org.junit.Assert.*;

public class CosineSimilarityTest {

    @Test
    public void twoDifferentFiles() {
        double EPSILON = 0.01;

        File f1 = new File(getClass().getClassLoader().getResource("testfiles/student02/simplecode2.py").getFile());
        File f2 = new File(getClass().getClassLoader().getResource("testfiles/student01/simplecode.py").getFile());

        CosineSimilarity cos = new CosineSimilarity(new ASTUtils());
        double score = cos.compare(f1, f2);
        assertEquals((Math.round(score * 10000.0) / 10000.0), 0.9701425001453319, EPSILON);
    }

    @Test
    public void twoSameFiles() {
        double EPSILON = 0.01;
        
        File f1 = new File(getClass().getClassLoader().getResource("testfiles/student02/simplecode2.py").getFile());
        
        CosineSimilarity cos = new CosineSimilarity(new ASTUtils());
        double score = cos.compare(f1, f1);
        assertEquals((Math.round(score * 10000.0) / 10000.0), 1.0, EPSILON);
    }

}
package edu.neu;

import edu.neu.comparison.EditDistance;
import edu.neu.comparison.LCS;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class ComparisonTest {

    private String getSingleFile(String path) {
        StringBuilder sb = new StringBuilder();
        try (
                FileInputStream fileInputStream = new FileInputStream(new File(path));
                Reader reader = new InputStreamReader(fileInputStream)
        ) {
            int temp;
            while ((temp = reader.read()) != -1) {
                if (((char) temp) != '\r') {
                    sb.append((char) temp);
                }
            }
        } catch (Exception e) {
            Logger logger = Logger.getLogger("Warning");
            logger.warning(e.getMessage());
        }
        return sb.toString();
    }

    @Test
    public void testLCS() {
        String file1 = "src/main/resources/simplecodetree.txt";
        String file2 = "src/main/resources/simplecode2tree.txt";
        LCS lcs = new LCS(getSingleFile(file1), getSingleFile(file2));
        assertEquals("The student is cheating!", lcs.getRes(lcs.getLength(), 500));
    }

    @Test
    public void testEDist() {
        String file1 = "src/main/resources/simplecodetree.txt";
        String file2 = "src/main/resources/simplecode2tree.txt";
        EditDistance distance = new EditDistance();
        assertEquals("The student is cheating!", distance.getRes(distance.editDist(getSingleFile(file1), getSingleFile(file2)), 500));
    }
}

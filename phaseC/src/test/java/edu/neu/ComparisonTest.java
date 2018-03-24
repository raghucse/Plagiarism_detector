package edu.neu;

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
    public void test01(){

    }
}

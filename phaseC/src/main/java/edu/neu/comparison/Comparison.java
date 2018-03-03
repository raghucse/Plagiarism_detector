package edu.neu.comparison;

import java.io.*;
import java.util.logging.Logger;

/**
 * Class to compare two AST
 *
 * @author Junhao Qu
 */
public class Comparison {

    private String path1;   // Path to the first file
    private String path2;   // Path to the second file
    private String file1;   // Content of the first file
    private String file2;   // Content of the second file

    /**
     * Constructor
     */
    public Comparison(String path1, String path2) {
        this.path1 = path1;
        this.path2 = path2;
    }

    /**
     * Get content of two files
     */
    public void getFiles() {
        file1 = getSingleFile(path1);
        file2 = getSingleFile(path2);
    }

    /**
     * Convert a file to String
     *
     * @param path  path to the file
     * @return      content of the file in String
     */
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

    /**
     * Get the result of LCS
     */
    public void longestCommonSubstring() {
        LCS myClass = new LCS(file1, file2);

        System.out.println("\n***** The first comparison strategy: longest common subsequence *****");
        System.out.println("The length of LCS is: " + myClass.getLength());
        // System.out.println(myClass.getLCS());
    }

    /**
     * Get the edit distance
     */
    public void eDist() {
        EditDistance distance = new EditDistance();
        System.out.println("\n***** The second comparison strategy: edit distance *****");
        System.out.println(distance.editDist(file1, file2));
    }
}

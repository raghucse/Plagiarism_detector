package edu.neu.comparison;

public class Main {

    public static void main(String[] args) {

        Comparison comparison = new Comparison("src/main/resources/simplecodetree.txt", "src/main/resources/simplecode2tree.txt");
        comparison.getFiles();
        comparison.longestCommonSubstring();
        comparison.eDist();

    }
}

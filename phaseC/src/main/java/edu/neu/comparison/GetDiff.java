package edu.neu.comparison;

import java.util.ArrayList;

/**
 * This class is used to fetch the line numbers of the content that is similar in both the files
 * @author Ananta Rajesh Arora
 * @version 1.0
 * @since 2018-04-19
 */
public class GetDiff {

    /**
     * This method is used to get the line numbers of the content similar in both tht files
     * @param tree1 is a string representation of the AST of file1
     * @param tree2 is a string representation of the AST of file2
     * @param tree1Length is the size of the AST of the first file
     * @param tree2Length is the size of the AST of the second file
     * @param lineNum1 is a list that keeps track of the line numbers of the similar content
     *                 in file1
     * @param lineNum2 is a list that keeps track of the line numbers of the similar content
     *                 in file2
     * @return
     */
    public static GetContent getMatchingLines(ArrayList<String> tree1, ArrayList<String> tree2, int tree1Length, int tree2Length,
                                         ArrayList<Integer>lineNum1, ArrayList<Integer>lineNum2) {

        int cost[][] = new int[tree1Length + 1][tree2Length + 1];
        ArrayList<Integer> file1MatCont = new ArrayList<>();
        ArrayList<Integer> file2MatCont = new ArrayList<>();

        for (int i = 1; i <= tree1Length; i++) {
            for (int j = 1; j <= tree2Length; j++) {

                if (tree1.get(i - 1).equals(tree2.get(j - 1)))
                {
                    file1MatCont.add(lineNum1.get(i-1));
                    file2MatCont.add(lineNum2.get(j-1));
                }
            }
        }



        //return score;
        GetContent gc = getInfo(file1MatCont,file2MatCont);
        return gc;
    }

    /**
     * This method wraps the line numbers of both the files in an object and returns the object
     * @param matchContF1 is a list of the line numbers of the similar content in file1
     * @param matchContF2 is a list of the line numbers of the similar content in file2
     * @return returns the list of line numbers wrapped in an object of GetContent class
     */
    public static GetContent getInfo(ArrayList<Integer> matchContF1, ArrayList<Integer> matchContF2){
        GetContent gc = new GetContent();
        gc.setMatchedContentF1(matchContF1);
        gc.setMatchedContentF2(matchContF2);
        return gc;
    }
}

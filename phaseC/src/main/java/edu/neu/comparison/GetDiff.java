package edu.neu.comparison;

import java.util.ArrayList;

/**
 *
 */
public class GetDiff {

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

    public static GetContent getInfo(ArrayList<Integer> matchContF1, ArrayList<Integer> matchContF2){
        GetContent gc = new GetContent();
        gc.setMatchedContentF1(matchContF1);
        gc.setMatchedContentF2(matchContF2);
        return gc;
    }
}

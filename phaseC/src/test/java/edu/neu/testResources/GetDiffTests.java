package edu.neu.testResources;

import edu.neu.comparison.DiffContent;
import edu.neu.comparison.GetContent;
import edu.neu.comparison.GetDiff;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GetDiffTests {


    @Test
    public void test01(){
        GetContent gc1 = new GetContent();
        GetContent gc2 = new GetContent();
        ArrayList<String> ast1 = new ArrayList<>();
        ast1.add("funcdef");
        ast1.add("stmt");
        ast1.add("stmt");

        ArrayList<String> ast2 = new ArrayList<>();
        ast2.add("funcdef");
        ast2.add("stmt");
        ast2.add("stmt");

        int tree1Length = ast1.size();
        int tree2Length = ast2.size();
        ArrayList<Integer> lineNum1 = new ArrayList<>();
        ArrayList<Integer> lineNum2 = new ArrayList<>();
        lineNum1.add(1);
        lineNum1.add(2);
        lineNum1.add(1);
        lineNum2.add(1);
        lineNum2.add(2);
        lineNum2.add(1);
        gc1 = GetDiff.getMatchingLines(ast1,ast2,tree1Length,tree2Length,lineNum1,lineNum2);
        ArrayList<Integer> matCont1 = new ArrayList<>();
        matCont1.add(1);
        matCont1.add(2);
        matCont1.add(2);
        matCont1.add(1);
        matCont1.add(1);
        gc2.setMatchedContentF1(matCont1);
        assertEquals(gc1.getMatchedContentF1(),gc2.getMatchedContentF1());
    }

    @Test
    public void test02() throws IOException {
        File f1 = new File("src/main/resources/simplecode2.py");
        File f2 = new File("src/main/resources/simplecode.py");
        DiffContent dc = new DiffContent();
        ArrayList<ArrayList<String>>finalArr = dc.getFinalDiff(f1,f2);
        ArrayList<ArrayList<String>> finalArr2 = finalArr;
        assertEquals(finalArr.size(),finalArr2.size());
    }
}

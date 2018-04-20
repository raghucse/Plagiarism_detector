package edu.neu.comparison;

import edu.neu.Log;
import edu.neu.astgeneration.ASTUtils;
import org.antlr.v4.runtime.RuleContext;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

/** This class is used to find the similarities between the two files
 * so as to display the same in the git diff tab of UI
 * @author Ananta Rajesh Arora
 * @version 1.0
 * @since 2018-04-19
 */
public class DiffContent {

    ArrayList<String> AST1String = new ArrayList<String>();
    ArrayList<String> AST2String = new ArrayList<String>();
    ArrayList<Integer> lineNum1;
    ArrayList<Integer> lineNum2;
    ASTUtils astUtils1 =new ASTUtils();
    ASTUtils astUtils2 = new ASTUtils();
    ArrayList<String> matContString = new ArrayList<>();

    /**
     * This method finds the similarities between the two files
     * @param f1 is the first file
     * @param f2 is the second file
     * @return returns the files and the similar content between them
     */

    public  List<List<String>> getFinalDiff(File f1, File f2) {
        RuleContext ctx1 = null;
        RuleContext ctx2 = null;
        try {
            ctx1 = astUtils1.getParserFacade().parse(f1);
            ctx2 = astUtils2.getParserFacade().parse(f2);

        AST1String = astUtils1.getAstPrinter().getASTStringeEq(ctx1);
        lineNum1 = astUtils1.getAstPrinter().lineNum;
        AST2String = astUtils2.getAstPrinter().getASTStringeEq(ctx2);
        lineNum2 = astUtils2.getAstPrinter().lineNum;
        int tree1Length = AST1String.size();
        int tree2Length = AST2String.size();
        GetContent gc = GetDiff.getMatchingLines(AST1String,AST2String,tree1Length,tree2Length,lineNum1,lineNum2);
        ArrayList<Integer> matchedContentF1 = gc.getMatchedContentF1();
        ArrayList<Integer> matchedContentF2 = gc.getMatchedContentF2();
        List<List<String>> finalArr = new ArrayList<List<String>>( 4);

        LineNumberReader lr1 = null;
        try(LineNumberReader lr2 = new LineNumberReader(new FileReader(f2))) {
            lr1 = new LineNumberReader(new FileReader(f1));
            ArrayList<String> f1Str = new ArrayList<>();
            ArrayList<String> f2Str = new ArrayList<>();
            String line1, line2;


            while((line1 = lr1.readLine())!=null){
                f1Str.add(line1);
            }

            while((line2 = lr2.readLine())!=null){
                f2Str.add(line2);
            }
            finalArr.add(f1Str);
            finalArr.add(f2Str);
            int count1 = 0, count2 = 0;

            while(count1<matchedContentF1.size() && count2<matchedContentF2.size()){
                String li1 = f1Str.get(matchedContentF1.get(count1)-1);
                String li2 = f2Str.get(matchedContentF2.get(count2)-1);

                if(li1.equals(li2)){
                    if(!matContString.contains(li1))
                        matContString.add(li1);
                }
                count1++;
                count2++;
            }

        }catch (Exception e){
            Log.info(e.getMessage());
        }
        finally {
            lr1.close();
        }
            finalArr.add(matContString);
            finalArr.add(matContString);
            return finalArr;
        } catch (IOException e) {
           Log.info(e.getMessage());
        }
        return null;
    }



}

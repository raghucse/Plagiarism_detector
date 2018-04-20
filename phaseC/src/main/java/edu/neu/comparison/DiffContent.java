package edu.neu.comparison;

import edu.neu.astgeneration.ASTUtils;
import org.antlr.v4.runtime.RuleContext;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;

public class DiffContent {

    public  ArrayList<String> AST1String = new ArrayList<String>();
    public  ArrayList<String> AST2String = new ArrayList<String>();
    public  ArrayList<Integer> lineNum1;
    public  ArrayList<Integer> lineNum2;
    public  ASTUtils astUtils1 =new ASTUtils();
    public  ASTUtils astUtils2 = new ASTUtils();
    public ArrayList<String> matContString = new ArrayList<>();

    public  ArrayList<ArrayList<String>> getFinalDiff(File f1, File f2) throws IOException {
        RuleContext ctx1 = astUtils1.getParserFacade().parse(f1);
        RuleContext ctx2 = astUtils2.getParserFacade().parse(f2);
        AST1String = astUtils1.getAstPrinter().getASTStringeEq(ctx1);
        lineNum1 = astUtils1.getAstPrinter().lineNum;
        AST2String = astUtils2.getAstPrinter().getASTStringeEq(ctx2);
        lineNum2 = astUtils2.getAstPrinter().lineNum;
        int tree1Length = AST1String.size();
        int tree2Length = AST2String.size();
        GetContent gc = GetDiff.getMatchingLines(AST1String,AST2String,tree1Length,tree2Length,lineNum1,lineNum2);
        ArrayList<Integer> matchedContentF1 = gc.getMatchedContentF1();
        ArrayList<Integer> matchedContentF2 = gc.getMatchedContentF2();
        ArrayList<ArrayList<String>> finalArr = new ArrayList<ArrayList<String>>( 4);

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
            System.out.println(e);
        }
        finally {
            lr1.close();
        }

        finalArr.add(matContString);
        finalArr.add(matContString);
        return finalArr;
    }



}

package edu.neu.astgeneration;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;

public class AstPrinter {

    private boolean ignoringWrappers = true;

    public void setIgnoringWrappers(boolean ignoringWrappers) {
        this.ignoringWrappers = ignoringWrappers;
    }

    public ArrayList<String> getASTStringeEq(RuleContext ctx) {

        ArrayList<String> ASTStringEquiv =  new ArrayList<String>();
        ASTStringEquiv = explore(ctx,ASTStringEquiv);
        return ASTStringEquiv;
    }


    private ArrayList<String> explore(RuleContext ctx, ArrayList<String> ASTStringEquiv){

        String ruleName = Python3Parser.ruleNames[ctx.getRuleIndex()];
        boolean toBeIgnored = ignoringWrappers
                && ctx.getChildCount()==1
                && ctx.getChild(0) instanceof ParserRuleContext;

        if(!toBeIgnored)
            //System.out.println(ruleName);
            ASTStringEquiv.add(ruleName);
        for (int i=0; i<ctx.getChildCount();i++)
        {
            ParseTree element = ctx.getChild(i);
            if(element instanceof RuleContext)
            {
                explore((RuleContext)element, ASTStringEquiv);
            }
        }

        return ASTStringEquiv;

    }

}


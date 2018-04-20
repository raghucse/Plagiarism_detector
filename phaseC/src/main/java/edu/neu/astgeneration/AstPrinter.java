package edu.neu.astgeneration;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import edu.neu.Log;

import java.util.ArrayList;

public class AstPrinter {

    private boolean ignoringWrappers = true;

    /**
     * Determines whether the wrapper is to be ignored
     * @param ignoringWrappers is the boolean value which when set to true,
     *                         the wrapper is to be ignored, else its not to
     *                         be ignored
     */
    public void setIgnoringWrappers(boolean ignoringWrappers) {
        this.ignoringWrappers = ignoringWrappers;
    }

    public ArrayList<String> ASTStringEquiv =  new ArrayList<String>();
    public ArrayList<Integer> lineNum = new ArrayList<>();


    /**
     * Creates a flattened string version of the tree
     * @param ctx is the object of RuleContext class
     * @return returns a flattened list of the AST
     */
    public ArrayList<String> getASTStringeEq(RuleContext ctx) {
        Log.trace("Starting to build AST String List");
        ArrayList<String> astStringEquiv =  new ArrayList<>();
        explore(ctx,astStringEquiv);
        Log.trace("Done building AST String List");
        return astStringEquiv;
    }


    /**
     * This function is used to parse the rule context so as to get the AST
     * @param ctx is the object of the RuleContext class
     * @param astStringEquiv is the list which maintains the flattened
     *                       string version of the generated AST
     */
    private void explore(RuleContext ctx, ArrayList<String> astStringEquiv){

        String ruleName = Python3Parser.ruleNames[ctx.getRuleIndex()];
        boolean toBeIgnored = ignoringWrappers
                && ctx.getChildCount()==1
                && ctx.getChild(0) instanceof ParserRuleContext;


        if(!toBeIgnored){
            ASTStringEquiv.add(ruleName);
            int currLine =((ParserRuleContext) ctx).getStart().getLine();
            lineNum.add(currLine);
        }


        if(!toBeIgnored)
            astStringEquiv.add(ruleName);

        for (int i=0; i<ctx.getChildCount();i++)
        {
            ParseTree element = ctx.getChild(i);
            if(element instanceof RuleContext)
            {
                explore((RuleContext)element, astStringEquiv);
            }
        }


    }

}

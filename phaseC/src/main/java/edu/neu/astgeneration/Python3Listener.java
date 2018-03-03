package edu.neu.astgeneration;// Generated from Python3.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Python3Parser}.
 */
public interface Python3Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Python3Parser#single_input}.
	 * @param ctx the parse tree
	 */
	void enterSingleInput(Python3Parser.Single_inputContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#single_input}.
	 * @param ctx the parse tree
	 */
	void exitSingleInput(Python3Parser.Single_inputContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#file_input}.
	 * @param ctx the parse tree
	 */
	void enterFileInput(Python3Parser.File_inputContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#file_input}.
	 * @param ctx the parse tree
	 */
	void exitFileInput(Python3Parser.File_inputContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#eval_input}.
	 * @param ctx the parse tree
	 */
	void enterEvalInput(Python3Parser.Eval_inputContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#eval_input}.
	 * @param ctx the parse tree
	 */
	void exitEvalInput(Python3Parser.Eval_inputContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#decorator}.
	 * @param ctx the parse tree
	 */
	void enterDecorator(Python3Parser.DecoratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#decorator}.
	 * @param ctx the parse tree
	 */
	void exitDecorator(Python3Parser.DecoratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#decorators}.
	 * @param ctx the parse tree
	 */
	void enterDecorators(Python3Parser.DecoratorsContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#decorators}.
	 * @param ctx the parse tree
	 */
	void exitDecorators(Python3Parser.DecoratorsContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#decorated}.
	 * @param ctx the parse tree
	 */
	void enterDecorated(Python3Parser.DecoratedContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#decorated}.
	 * @param ctx the parse tree
	 */
	void exitDecorated(Python3Parser.DecoratedContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#async_funcdef}.
	 * @param ctx the parse tree
	 */
	void enterAsyncFuncdef(Python3Parser.Async_funcdefContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#async_funcdef}.
	 * @param ctx the parse tree
	 */
	void exitAsyncFuncdef(Python3Parser.Async_funcdefContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#funcdef}.
	 * @param ctx the parse tree
	 */
	void enterFuncdef(Python3Parser.FuncdefContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#funcdef}.
	 * @param ctx the parse tree
	 */
	void exitFuncdef(Python3Parser.FuncdefContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#parameters}.
	 * @param ctx the parse tree
	 */
	void enterParameters(Python3Parser.ParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#parameters}.
	 * @param ctx the parse tree
	 */
	void exitParameters(Python3Parser.ParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#typedargslist}.
	 * @param ctx the parse tree
	 */
	void enterTypedargslist(Python3Parser.TypedargslistContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#typedargslist}.
	 * @param ctx the parse tree
	 */
	void exitTypedargslist(Python3Parser.TypedargslistContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#tfpdef}.
	 * @param ctx the parse tree
	 */
	void enterTfpdef(Python3Parser.TfpdefContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#tfpdef}.
	 * @param ctx the parse tree
	 */
	void exitTfpdef(Python3Parser.TfpdefContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#varargslist}.
	 * @param ctx the parse tree
	 */
	void enterVarargslist(Python3Parser.VarargslistContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#varargslist}.
	 * @param ctx the parse tree
	 */
	void exitVarargslist(Python3Parser.VarargslistContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#vfpdef}.
	 * @param ctx the parse tree
	 */
	void enterVfpdef(Python3Parser.VfpdefContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#vfpdef}.
	 * @param ctx the parse tree
	 */
	void exitVfpdef(Python3Parser.VfpdefContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(Python3Parser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(Python3Parser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#simple_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSimpleStmt(Python3Parser.Simple_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#simple_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSimpleStmt(Python3Parser.Simple_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#small_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSmallStmt(Python3Parser.Small_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#small_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSmallStmt(Python3Parser.Small_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#expr_stmt}.
	 * @param ctx the parse tree
	 */
	void enterExprStmt(Python3Parser.Expr_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#expr_stmt}.
	 * @param ctx the parse tree
	 */
	void exitExprStmt(Python3Parser.Expr_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#annassign}.
	 * @param ctx the parse tree
	 */
	void enterAnnassign(Python3Parser.AnnassignContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#annassign}.
	 * @param ctx the parse tree
	 */
	void exitAnnassign(Python3Parser.AnnassignContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#testlist_star_expr}.
	 * @param ctx the parse tree
	 */
	void enterTestlistStarExpr(Python3Parser.Testlist_star_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#testlist_star_expr}.
	 * @param ctx the parse tree
	 */
	void exitTestlistStarExpr(Python3Parser.Testlist_star_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#augassign}.
	 * @param ctx the parse tree
	 */
	void enterAugassign(Python3Parser.AugassignContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#augassign}.
	 * @param ctx the parse tree
	 */
	void exitAugassign(Python3Parser.AugassignContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#del_stmt}.
	 * @param ctx the parse tree
	 */
	void enterDelStmt(Python3Parser.Del_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#del_stmt}.
	 * @param ctx the parse tree
	 */
	void exitDelStmt(Python3Parser.Del_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#pass_stmt}.
	 * @param ctx the parse tree
	 */
	void enterPassStmt(Python3Parser.Pass_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#pass_stmt}.
	 * @param ctx the parse tree
	 */
	void exitPassStmt(Python3Parser.Pass_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#flow_stmt}.
	 * @param ctx the parse tree
	 */
	void enterFlowStmt(Python3Parser.Flow_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#flow_stmt}.
	 * @param ctx the parse tree
	 */
	void exitFlowStmt(Python3Parser.Flow_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#break_stmt}.
	 * @param ctx the parse tree
	 */
	void enterBreakStmt(Python3Parser.Break_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#break_stmt}.
	 * @param ctx the parse tree
	 */
	void exitBreakStmt(Python3Parser.Break_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#continue_stmt}.
	 * @param ctx the parse tree
	 */
	void enterContinueStmt(Python3Parser.Continue_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#continue_stmt}.
	 * @param ctx the parse tree
	 */
	void exitContinueStmt(Python3Parser.Continue_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#return_stmt}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(Python3Parser.Return_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#return_stmt}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(Python3Parser.Return_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#yield_stmt}.
	 * @param ctx the parse tree
	 */
	void enterYieldStmt(Python3Parser.Yield_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#yield_stmt}.
	 * @param ctx the parse tree
	 */
	void exitYieldStmt(Python3Parser.Yield_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#raise_stmt}.
	 * @param ctx the parse tree
	 */
	void enterRaiseStmt(Python3Parser.Raise_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#raise_stmt}.
	 * @param ctx the parse tree
	 */
	void exitRaiseStmt(Python3Parser.Raise_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#import_stmt}.
	 * @param ctx the parse tree
	 */
	void enterImportStmt(Python3Parser.Import_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#import_stmt}.
	 * @param ctx the parse tree
	 */
	void exitImportStmt(Python3Parser.Import_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#import_name}.
	 * @param ctx the parse tree
	 */
	void enterImportName(Python3Parser.Import_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#import_name}.
	 * @param ctx the parse tree
	 */
	void exitImportName(Python3Parser.Import_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#import_from}.
	 * @param ctx the parse tree
	 */
	void enterImportFrom(Python3Parser.Import_fromContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#import_from}.
	 * @param ctx the parse tree
	 */
	void exitImportFrom(Python3Parser.Import_fromContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#import_as_name}.
	 * @param ctx the parse tree
	 */
	void enterImportAsName(Python3Parser.Import_as_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#import_as_name}.
	 * @param ctx the parse tree
	 */
	void exitImportAsName(Python3Parser.Import_as_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#dotted_as_name}.
	 * @param ctx the parse tree
	 */
	void enterDottedAsName(Python3Parser.Dotted_as_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#dotted_as_name}.
	 * @param ctx the parse tree
	 */
	void exitDottedAsName(Python3Parser.Dotted_as_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#import_as_names}.
	 * @param ctx the parse tree
	 */
	void enterImportAsNames(Python3Parser.Import_as_namesContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#import_as_names}.
	 * @param ctx the parse tree
	 */
	void exitImportAsNames(Python3Parser.Import_as_namesContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#dotted_as_names}.
	 * @param ctx the parse tree
	 */
	void enterDottedAsNames(Python3Parser.Dotted_as_namesContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#dotted_as_names}.
	 * @param ctx the parse tree
	 */
	void exitDottedAsNames(Python3Parser.Dotted_as_namesContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#dotted_name}.
	 * @param ctx the parse tree
	 */
	void enterDottedName(Python3Parser.Dotted_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#dotted_name}.
	 * @param ctx the parse tree
	 */
	void exitDottedName(Python3Parser.Dotted_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#global_stmt}.
	 * @param ctx the parse tree
	 */
	void enterGlobalStmt(Python3Parser.Global_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#global_stmt}.
	 * @param ctx the parse tree
	 */
	void exitGlobalStmt(Python3Parser.Global_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#nonlocal_stmt}.
	 * @param ctx the parse tree
	 */
	void enterNonlocalStmt(Python3Parser.Nonlocal_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#nonlocal_stmt}.
	 * @param ctx the parse tree
	 */
	void exitNonlocalStmt(Python3Parser.Nonlocal_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#assert_stmt}.
	 * @param ctx the parse tree
	 */
	void enterAssertStmt(Python3Parser.Assert_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#assert_stmt}.
	 * @param ctx the parse tree
	 */
	void exitAssertStmt(Python3Parser.Assert_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void enterCompoundStmt(Python3Parser.Compound_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void exitCompoundStmt(Python3Parser.Compound_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#async_stmt}.
	 * @param ctx the parse tree
	 */
	void enterAsyncStmt(Python3Parser.Async_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#async_stmt}.
	 * @param ctx the parse tree
	 */
	void exitAsyncStmt(Python3Parser.Async_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#if_stmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(Python3Parser.If_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#if_stmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(Python3Parser.If_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#while_stmt}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(Python3Parser.While_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#while_stmt}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(Python3Parser.While_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#for_stmt}.
	 * @param ctx the parse tree
	 */
	void enterForStmt(Python3Parser.For_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#for_stmt}.
	 * @param ctx the parse tree
	 */
	void exitForStmt(Python3Parser.For_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#try_stmt}.
	 * @param ctx the parse tree
	 */
	void enterTryStmt(Python3Parser.Try_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#try_stmt}.
	 * @param ctx the parse tree
	 */
	void exitTryStmt(Python3Parser.Try_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#with_stmt}.
	 * @param ctx the parse tree
	 */
	void enterWithStmt(Python3Parser.With_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#with_stmt}.
	 * @param ctx the parse tree
	 */
	void exitWithStmt(Python3Parser.With_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#with_item}.
	 * @param ctx the parse tree
	 */
	void enterWithItem(Python3Parser.With_itemContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#with_item}.
	 * @param ctx the parse tree
	 */
	void exitWithItem(Python3Parser.With_itemContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#except_clause}.
	 * @param ctx the parse tree
	 */
	void enterExceptClause(Python3Parser.Except_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#except_clause}.
	 * @param ctx the parse tree
	 */
	void exitExceptClause(Python3Parser.Except_clauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#suite}.
	 * @param ctx the parse tree
	 */
	void enterSuite(Python3Parser.SuiteContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#suite}.
	 * @param ctx the parse tree
	 */
	void exitSuite(Python3Parser.SuiteContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#test}.
	 * @param ctx the parse tree
	 */
	void enterTest(Python3Parser.TestContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#test}.
	 * @param ctx the parse tree
	 */
	void exitTest(Python3Parser.TestContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#test_nocond}.
	 * @param ctx the parse tree
	 */
	void enterTestNocond(Python3Parser.Test_nocondContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#test_nocond}.
	 * @param ctx the parse tree
	 */
	void exitTestNocond(Python3Parser.Test_nocondContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#lambdef}.
	 * @param ctx the parse tree
	 */
	void enterLambdef(Python3Parser.LambdefContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#lambdef}.
	 * @param ctx the parse tree
	 */
	void exitLambdef(Python3Parser.LambdefContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#lambdef_nocond}.
	 * @param ctx the parse tree
	 */
	void enterLambdefNocond(Python3Parser.Lambdef_nocondContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#lambdef_nocond}.
	 * @param ctx the parse tree
	 */
	void exitLambdefNocond(Python3Parser.Lambdef_nocondContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#or_test}.
	 * @param ctx the parse tree
	 */
	void enterOrTest(Python3Parser.Or_testContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#or_test}.
	 * @param ctx the parse tree
	 */
	void exitOrTest(Python3Parser.Or_testContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#and_test}.
	 * @param ctx the parse tree
	 */
	void enterAndTest(Python3Parser.And_testContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#and_test}.
	 * @param ctx the parse tree
	 */
	void exitAndTest(Python3Parser.And_testContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#not_test}.
	 * @param ctx the parse tree
	 */
	void enterNotTest(Python3Parser.Not_testContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#not_test}.
	 * @param ctx the parse tree
	 */
	void exitNotTest(Python3Parser.Not_testContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison(Python3Parser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison(Python3Parser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#comp_op}.
	 * @param ctx the parse tree
	 */
	void enterCompOp(Python3Parser.Comp_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#comp_op}.
	 * @param ctx the parse tree
	 */
	void exitCompOp(Python3Parser.Comp_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#star_expr}.
	 * @param ctx the parse tree
	 */
	void enterStarExpr(Python3Parser.Star_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#star_expr}.
	 * @param ctx the parse tree
	 */
	void exitStarExpr(Python3Parser.Star_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(Python3Parser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(Python3Parser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#xor_expr}.
	 * @param ctx the parse tree
	 */
	void enterXorExpr(Python3Parser.Xor_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#xor_expr}.
	 * @param ctx the parse tree
	 */
	void exitXorExpr(Python3Parser.Xor_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#and_expr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(Python3Parser.And_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#and_expr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(Python3Parser.And_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#shift_expr}.
	 * @param ctx the parse tree
	 */
	void enterShiftExpr(Python3Parser.Shift_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#shift_expr}.
	 * @param ctx the parse tree
	 */
	void exitShiftExpr(Python3Parser.Shift_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#arith_expr}.
	 * @param ctx the parse tree
	 */
	void enterArithExpr(Python3Parser.Arith_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#arith_expr}.
	 * @param ctx the parse tree
	 */
	void exitArithExpr(Python3Parser.Arith_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(Python3Parser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(Python3Parser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(Python3Parser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(Python3Parser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#power}.
	 * @param ctx the parse tree
	 */
	void enterPower(Python3Parser.PowerContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#power}.
	 * @param ctx the parse tree
	 */
	void exitPower(Python3Parser.PowerContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#atom_expr}.
	 * @param ctx the parse tree
	 */
	void enterAtomExpr(Python3Parser.Atom_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#atom_expr}.
	 * @param ctx the parse tree
	 */
	void exitAtomExpr(Python3Parser.Atom_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(Python3Parser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(Python3Parser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#testlist_comp}.
	 * @param ctx the parse tree
	 */
	void enterTestlistComp(Python3Parser.Testlist_compContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#testlist_comp}.
	 * @param ctx the parse tree
	 */
	void exitTestlistComp(Python3Parser.Testlist_compContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#trailer}.
	 * @param ctx the parse tree
	 */
	void enterTrailer(Python3Parser.TrailerContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#trailer}.
	 * @param ctx the parse tree
	 */
	void exitTrailer(Python3Parser.TrailerContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#subscriptlist}.
	 * @param ctx the parse tree
	 */
	void enterSubscriptlist(Python3Parser.SubscriptlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#subscriptlist}.
	 * @param ctx the parse tree
	 */
	void exitSubscriptlist(Python3Parser.SubscriptlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#subscript}.
	 * @param ctx the parse tree
	 */
	void enterSubscript(Python3Parser.SubscriptContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#subscript}.
	 * @param ctx the parse tree
	 */
	void exitSubscript(Python3Parser.SubscriptContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#sliceop}.
	 * @param ctx the parse tree
	 */
	void enterSliceop(Python3Parser.SliceopContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#sliceop}.
	 * @param ctx the parse tree
	 */
	void exitSliceop(Python3Parser.SliceopContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#exprlist}.
	 * @param ctx the parse tree
	 */
	void enterExprlist(Python3Parser.ExprlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#exprlist}.
	 * @param ctx the parse tree
	 */
	void exitExprlist(Python3Parser.ExprlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#testlist}.
	 * @param ctx the parse tree
	 */
	void enterTestlist(Python3Parser.TestlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#testlist}.
	 * @param ctx the parse tree
	 */
	void exitTestlist(Python3Parser.TestlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#dictorsetmaker}.
	 * @param ctx the parse tree
	 */
	void enterDictorsetmaker(Python3Parser.DictorsetmakerContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#dictorsetmaker}.
	 * @param ctx the parse tree
	 */
	void exitDictorsetmaker(Python3Parser.DictorsetmakerContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#classdef}.
	 * @param ctx the parse tree
	 */
	void enterClassdef(Python3Parser.ClassdefContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#classdef}.
	 * @param ctx the parse tree
	 */
	void exitClassdef(Python3Parser.ClassdefContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#arglist}.
	 * @param ctx the parse tree
	 */
	void enterArglist(Python3Parser.ArglistContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#arglist}.
	 * @param ctx the parse tree
	 */
	void exitArglist(Python3Parser.ArglistContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(Python3Parser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(Python3Parser.ArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#comp_iter}.
	 * @param ctx the parse tree
	 */
	void enterCompIter(Python3Parser.Comp_iterContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#comp_iter}.
	 * @param ctx the parse tree
	 */
	void exitCompIter(Python3Parser.Comp_iterContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#comp_for}.
	 * @param ctx the parse tree
	 */
	void enterCompFor(Python3Parser.Comp_forContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#comp_for}.
	 * @param ctx the parse tree
	 */
	void exitCompFor(Python3Parser.Comp_forContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#comp_if}.
	 * @param ctx the parse tree
	 */
	void enterCompIf(Python3Parser.Comp_ifContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#comp_if}.
	 * @param ctx the parse tree
	 */
	void exitCompIf(Python3Parser.Comp_ifContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#encoding_decl}.
	 * @param ctx the parse tree
	 */
	void enterEncodingDecl(Python3Parser.Encoding_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#encoding_decl}.
	 * @param ctx the parse tree
	 */
	void exitEncodingDecl(Python3Parser.Encoding_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#yield_expr}.
	 * @param ctx the parse tree
	 */
	void enterYieldExpr(Python3Parser.Yield_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#yield_expr}.
	 * @param ctx the parse tree
	 */
	void exitYieldExpr(Python3Parser.Yield_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#yield_arg}.
	 * @param ctx the parse tree
	 */
	void enterYieldArg(Python3Parser.Yield_argContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#yield_arg}.
	 * @param ctx the parse tree
	 */
	void exitYieldArg(Python3Parser.Yield_argContext ctx);
}
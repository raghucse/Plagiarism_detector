package edu.neu.astgeneration;

public class ASTUtils {

	ParserFacade parserFacade;
	AstPrinter astPrinter;
	
	public ASTUtils() {
		parserFacade = new ParserFacade();
		astPrinter = new AstPrinter();
	}
	
	public ParserFacade getParserFacade() {
		return this.parserFacade;
	}
	
	public AstPrinter getAstPrinter() {
		return this.astPrinter;
	}
	
}

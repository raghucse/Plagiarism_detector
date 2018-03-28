package edu.neu.astgeneration;

/**
 * This class is used to generate the AST tree for a python file
 */
public class ASTUtils {

	ParserFacade parserFacade;
	AstPrinter astPrinter;

	/**
	 * Creates an object of the parserFacade and astPrinter classes so as
	 * to generate an AST tree for the .py file
	 */
	public ASTUtils() {
		parserFacade = new ParserFacade();
		astPrinter = new AstPrinter();
	}

	/**
	 * @return object of the parserFacade class
	 */
	public ParserFacade getParserFacade() {
		return this.parserFacade;
	}

	/**
	 * @return object of the astPrinter class
	 */
	public AstPrinter getAstPrinter() {
		return this.astPrinter;
	}
	
}

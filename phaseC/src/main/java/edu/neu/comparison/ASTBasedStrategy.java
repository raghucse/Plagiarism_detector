package edu.neu.comparison;

import edu.neu.astgeneration.ASTUtils;

/**
 * This interface is used to get the necessary utilities
 * for generating ASTs and performing comparison
 */
public interface ASTBasedStrategy extends Strategy{

	/**
	 * @return returns the AST utilities
	 */
	public ASTUtils getASTUtils();
}

package plagiarismdetector;

/**
 * An interface representing an ASTNode in an ASTTree
 * An ASTNode stores the details specific to a node and produces a text
 * representation based on it
 * @author Team 106
 * @version 1.0
 */
public interface ASTNode {
	
	/**
	 * Returns the text representation for the node
	 * Text representations are normalized
	 * Two logically identical nodes should have the same text representation
	 * @return
	 */
	String textRepresentation();
}

package plagiarismdetector;

/**
 * The iterator interface to iterate over an AST
 * An AST, being a tree would need to be iterated (Iteration better than recursion)
 * This iterator interface provides the neccessary methods to iterate over the AST
 * Possible classes that would implement this interface are ASTInorderIterator, 
 * ASTPreorderIterator and ASTPostorderIterator
 * @author : Team 106
 * @version : 1.0
 */
public interface ASTIterator {

	/**
	 * Returns whether the iterator has a next value that it can return
	 * @return whether the iterators next is not null
	 */
    boolean hasNext();
    
    /**
     * Returns the next node in the ASTTree traversal
     * @return the next ASTNode
     */
    ASTNode next();
    
    /**
     * Removes the current element the iterator is pointing to
     */
    void remove();
}

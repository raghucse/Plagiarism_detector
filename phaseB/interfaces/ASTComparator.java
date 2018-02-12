package plagiarismdetector;

/**
 * The comparator interface to compare two ASTs
 * This interface contains the logic to compare two ASTs
 * @author : Team 106
 * @version : 1.0
 */
public interface ASTComparator {

	/**
	* A method to compare two ASTs.
	* @param ASTTree ast1 : The first ast tree to be compared
	* @param ASTTree ast2 : The second ast tree to be compared
	* @return the report of the comparison
	*/
    Report compare(ASTTree ast1, ASTTree ast2);
}

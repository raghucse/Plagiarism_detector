package plagiarismdetector;

import java.util.List;

/**
 * Interface of source to collect the submission of files
 * and provide their AST representation.
 * The source is an abstraction of a users submission source
 * It could be a git source or a zip source
 * @author Team 106
 * @version 1.0
 */
public interface Source {
	
	/**
	 * Returns the Path to the source downloaded at a
	 * temporary location
	 * @return The temporarily constructed source folder path as a string
	 */
    String getProject();
    
    /**
     * Returns the ASTTree representations of the files in the source.
     * For each file in the source, returns the AST tree representation
     * @return
     */
    List<ASTTree> getAstList();
}

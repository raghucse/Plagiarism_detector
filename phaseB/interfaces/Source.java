package plagiarismdetector;

import java.util.List;

/**
 * Interface of source to collect the submission of files and provide their AST representation.
 *
 * @author Team 106
 * @version 1.0
 */
public interface Source {
    String getProject();
    List<ASTTree> getAstList();
}

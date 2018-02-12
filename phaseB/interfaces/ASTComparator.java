package plagiarismdetector;

/**
 *
 */
public interface ASTComparator {

    Report compare(ASTTree ast1, ASTTree ast2);
}

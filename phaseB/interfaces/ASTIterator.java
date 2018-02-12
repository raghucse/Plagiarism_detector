package plagiarismdetector;

public interface ASTIterator {

    boolean hasNext();
    ASTNode next();
    void remove();
}

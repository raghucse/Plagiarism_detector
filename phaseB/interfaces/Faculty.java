package plagiarismdetector;

import netscape.javascript.JSObject;

public interface Faculty extends User {
	
	void runPlagiarismChecker(Assignment assignment);

	JSObject viewReport (Report report);
}

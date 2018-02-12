package plagiarismdetector;

public interface Faculty extends User {
	
	public void runPlagiarismChecker(Assignment assignment);

	@Override
	default boolean login(String email, String password) {
		return false;
	}

	@Override
	default boolean logout() {
		return false;
	}
}

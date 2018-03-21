package edu.neu;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import edu.neu.comparison.Strategy;
import edu.neu.models.Assignment;
import edu.neu.models.Source;
import edu.neu.models.Submission;
import edu.neu.testResources.AlwaysTrueComparisonStrategy;
import edu.neu.testResources.MockSource;
import edu.neu.utils.Constants;
import plagiarismdetection.PlagiarismChecker;

public class PlagiarismCheckTests {
	
	@Test
	public void testNullAssignment() {
		Strategy comparisonStrategy = new AlwaysTrueComparisonStrategy();
		Assignment assignment = null;
		PlagiarismChecker p_checker = new PlagiarismChecker(assignment, comparisonStrategy);
		p_checker.check();
		assertEquals(Constants.P_CHECK_ERROR_STRING, p_checker.getReportResult());
	}
	
	@Test
	public void testTwoSubmissionsAssignment() {
		Strategy comparisonStrategy = new AlwaysTrueComparisonStrategy();
		Assignment assignment = new Assignment();
		
		Submission sub1 = new Submission();
		Source source1 = new MockSource();
		source1.getFiles().add(new File("temp1"));
		sub1.setSource(source1);
		
		Submission sub2 = new Submission();
		Source source2 = new MockSource();
		source2.getFiles().add(new File("temp2"));
		sub2.setSource(source2);
		
		assignment.setStudentSubmissions(new HashMap<String,Submission>());
		assignment.getStudentSubmissions().put("student1", sub1);
		assignment.getStudentSubmissions().put("student2", sub2);
		
		PlagiarismChecker p_checker = new PlagiarismChecker(assignment, comparisonStrategy);
		p_checker.check();
		assertEquals(1, matchCount(p_checker.getReportResult()));
	}
	
	
	@Test
	public void testThreeSubmissionsAssignment() {
		Strategy comparisonStrategy = new AlwaysTrueComparisonStrategy();
		Assignment assignment = new Assignment();
		
		Submission sub1 = new Submission();
		Source source1 = new MockSource();
		source1.getFiles().add(new File("temp1"));
		sub1.setSource(source1);
		
		Submission sub2 = new Submission();
		Source source2 = new MockSource();
		source2.getFiles().add(new File("temp2"));
		sub2.setSource(source2);
		
		Submission sub3 = new Submission();
		Source source3 = new MockSource();
		source3.getFiles().add(new File("temp3"));
		sub3.setSource(source3);
		
		assignment.setStudentSubmissions(new HashMap<String,Submission>());
		assignment.getStudentSubmissions().put("student1", sub1);
		assignment.getStudentSubmissions().put("student2", sub2);
		assignment.getStudentSubmissions().put("student3", sub3);
		
		PlagiarismChecker p_checker = new PlagiarismChecker(assignment, comparisonStrategy);
		p_checker.check();
		assertEquals(3, matchCount(p_checker.getReportResult()));
	}
	
	@Test
	public void testTwoSubmissionsTwoFilesEachAssignment() {
		Strategy comparisonStrategy = new AlwaysTrueComparisonStrategy();
		Assignment assignment = new Assignment();
		
		Submission sub1 = new Submission();
		Source source1 = new MockSource();
		source1.getFiles().add(new File("temp-a1"));
		source1.getFiles().add(new File("temp-a2"));
		sub1.setSource(source1);
		
		Submission sub2 = new Submission();
		Source source2 = new MockSource();
		source2.getFiles().add(new File("temp-b1"));
		source2.getFiles().add(new File("temp-b2"));
		sub2.setSource(source2);
		
		assignment.setStudentSubmissions(new HashMap<String,Submission>());
		assignment.getStudentSubmissions().put("student1", sub1);
		assignment.getStudentSubmissions().put("student2", sub2);
		
		PlagiarismChecker p_checker = new PlagiarismChecker(assignment, comparisonStrategy);
		p_checker.check();
		assertEquals(4, matchCount(p_checker.getReportResult()));
	}
	
	@Test
	public void testThreeSubmissionsTwoFilesEachAssignment() {
		Strategy comparisonStrategy = new AlwaysTrueComparisonStrategy();
		Assignment assignment = new Assignment();
		
		Submission sub1 = new Submission();
		Source source1 = new MockSource();
		source1.getFiles().add(new File("temp-a1"));
		source1.getFiles().add(new File("temp-a2"));
		sub1.setSource(source1);
		
		Submission sub2 = new Submission();
		Source source2 = new MockSource();
		source2.getFiles().add(new File("temp-b1"));
		source2.getFiles().add(new File("temp-b2"));
		sub2.setSource(source2);
		
		Submission sub3 = new Submission();
		Source source3 = new MockSource();
		source3.getFiles().add(new File("temp-c1"));
		source3.getFiles().add(new File("temp-c2"));
		sub3.setSource(source3);
		
		assignment.setStudentSubmissions(new HashMap<String,Submission>());
		assignment.getStudentSubmissions().put("student1", sub1);
		assignment.getStudentSubmissions().put("student2", sub2);
		assignment.getStudentSubmissions().put("student3", sub3);
		
		PlagiarismChecker p_checker = new PlagiarismChecker(assignment, comparisonStrategy);
		p_checker.check();
		assertEquals(12, matchCount(p_checker.getReportResult()));
	}
	
	private int matchCount(String result) {
		int count = 0;
		for(String s : result.split(",")) {
			if(s.equals("Match"))
				count++;
		}
		return count;
	}
}

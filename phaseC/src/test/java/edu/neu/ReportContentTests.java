package edu.neu;

import edu.neu.comparison.ComparisonReport;
import edu.neu.comparison.Scores;
import edu.neu.models.DiffContent;
import edu.neu.models.DiffTuple;
import edu.neu.models.ReportContent;
import edu.neu.models.UnsuccessfulReportContent;

import org.junit.Test;
import org.springframework.util.SerializationUtils;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class ReportContentTests {
	
	/*
    @Test
    public void testSerializeDeserialize() {
    		ReportContent rc1 = new ReportContent();
    		rc1.setReportMessage("aaa");
    		List<ComparisonReport> l = new ArrayList<>();
    		l.add(new ComparisonReport());
    		rc1.setComparisonList(l);
    		rc1.addTOComparisonList(new ComparisonReport());
    		ReportContent rc2 = (ReportContent) SerializationUtils.deserialize(SerializationUtils.serialize(rc1));
    		assertEquals(rc1.getReportMessage(),rc2.getReportMessage());
    		assertEquals(rc1.getComparisonList().size(),rc2.getComparisonList().size());
    }*/
	
	
    
    @Test
    public void testSerializeDeserializeComparisonReport() {
    	List<String> mockContent = new ArrayList<>();
		mockContent.add("Mock");
		List<List<String>> mockDiff = new ArrayList<>();
		mockDiff.add(mockContent);
		mockDiff.add(mockContent);
		mockDiff.add(mockContent);
		mockDiff.add(mockContent);
    		ComparisonReport cr1 = new ComparisonReport("mockuser1", "mockuser2", "abc", "def", new Scores(1.0, ""), mockDiff);
    		ComparisonReport cr2 = (ComparisonReport) SerializationUtils.deserialize(SerializationUtils.serialize(cr1));
        assertEquals(cr1.getFilename1(), cr2.getFilename1());
        assertEquals(cr1.getFilename2(), cr2.getFilename2());
        assertEquals(cr1.getScores().getTotalScore(), cr2.getScores().getTotalScore(), 0.000001);
    }
    
    @Test
    public void testSerializeDeserializeUnsuccessfulReportContent() {
    		UnsuccessfulReportContent rc1 = new UnsuccessfulReportContent();
    		UnsuccessfulReportContent rc2 = (UnsuccessfulReportContent) SerializationUtils.deserialize(SerializationUtils.serialize(rc1));
    		assertEquals(rc1.getReportMessage(), rc2.getReportMessage());
    }

}
package edu.neu;

import edu.neu.models.Submission;
import edu.neu.reports.PlagiarismRun;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class PlagiarismRunTests {

    @Test
    public void test01(){
        PlagiarismRun pr = new PlagiarismRun();
        int i = 1;
        pr.setRunId(i);
        int actualVALUE = pr.getRunId();
        assertEquals(i,actualVALUE);
    }

    @Test
    public void test02(){
        PlagiarismRun pr = new PlagiarismRun();
        int i = 10;
        pr.setUserId(i);
        int actualVALUE = pr.getUserId();
        assertEquals(i,actualVALUE);
    }

    @Test
    public void test03(){
        PlagiarismRun pr = new PlagiarismRun();
        ArrayList<String> urls = new ArrayList<>();
        ArrayList<String> students = new ArrayList<>();
        String url1 = "https://github.ccs.neu.edu/cs5500/team-106";
        urls.add(url1);
        pr.setGitUrls(urls);
        students.add("mockstudent");
        pr.setStudentNames(students);
        List<String> actValues = pr.getGitUrls();
        List<Submission> submissions = pr.getStudentSubmissions();
        String val = "PlagiarismRun : [RunID:0,UserID:0,GitURLS:[https://github.ccs.neu.edu/cs5500/team-106],Students:[mockstudent]]";
        assertEquals(val, pr.toString());
        assertEquals(actValues,urls);
    }

    @Test
    public void test04(){
        PlagiarismRun pr = new PlagiarismRun();
        String description = "Describes the github url";
        pr.setDescription(description);
        String actualValue = pr.getDescription();
        assertEquals(description,actualValue);
    }


}

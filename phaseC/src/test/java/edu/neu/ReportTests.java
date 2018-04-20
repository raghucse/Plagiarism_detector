package edu.neu;

import edu.neu.reports.Report;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReportTests {

    @Test
    public void test01(){
        Report r = new Report();
        int id = 1;
        r.setId(id);
        int val = r.getId();
        assertEquals(id,val);
    }

    @Test
    public void test02(){
        Report r = new Report();
        int owner = 1;
        r.setOwner(owner);
        int val = r.getOwner();
        assertEquals(owner,val);
    }

    @Test
    public void test03(){
        Report r = new Report();
        String name = "Lexo";
        r.setRunName(name);
        String val = r.getRunName();
        assertEquals(val,name);
    }

    @Test
    public void test04(){
        Report r = new Report();
        double score = 50.0;
        r.setReportScore(score);
        double val = r.getReportScore();
        assertEquals(score,val,0.01);
    }

}

package edu.neu;

import edu.neu.reports.ReportShared;
import org.junit.Test;
import static org.junit.Assert.*;


public class ReportSharedTests {

    @Test
    public void test01(){
        ReportShared rs = new ReportShared();
        int id = 1;
        rs.setReportId(id);
        int val = rs.getReportId();
        assertEquals(id,val);
    }

    @Test
    public void test02(){
        ReportShared rs = new ReportShared();
        int id = 1;
        rs.setUserId(id);
        int val = rs.getUserId();
        assertEquals(id,val);
    }

    @Test
    public void test03(){
        ReportShared rs = new ReportShared();
        int id = 10;
        rs.setReportId(id);
        int val = rs.getReportId();
        assertEquals(id,val);
    }

}

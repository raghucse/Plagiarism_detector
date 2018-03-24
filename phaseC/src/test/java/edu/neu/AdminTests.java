package edu.neu;

import edu.neu.models.Admin;
import edu.neu.models.NonAdmin;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class AdminTests {

    @Test
    public void test01(){
        Admin ad = new Admin();
        ad.onLogin();
        ad.onLogout();
        String lo = "abc";
        ad.addUserLog(lo);
        ArrayList<String> logs = new ArrayList<>();
        logs.add(lo);
        List<String> userlog = ad.showUserLogs();
        List<String> res = new ArrayList<>();
        assertEquals(res,userlog);
    }

    @Test
    public void tes02(){
        NonAdmin ad = new NonAdmin();
        ad.onLogin();
        ad.onLogout();
        String lo = "abc";
        ad.addUserLog(lo);
        ArrayList<String> logs = new ArrayList<>();
        logs.add(lo);
        List<String> userlog = ad.showUserLogs();
        List<String> res = new ArrayList<>();
        assertEquals(res,userlog);
    }
}

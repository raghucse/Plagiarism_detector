package edu.neu;

import edu.neu.reports.PlagiarismRunRequest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PlagiarismRunRequestsTests {


    @Test
    public void test01(){
        PlagiarismRunRequest pr = new PlagiarismRunRequest();
        ArrayList<String> urls = new ArrayList<>();
        String url1 = "https://github.ccs.neu.edu/cs5500/team-106";
        urls.add(url1);
        pr.setGitUrls(urls);
        List<String> actValues = pr.getGitUrls();
        assertEquals(actValues,urls);
    }

    @Test
    public void test02(){
        PlagiarismRunRequest pr = new PlagiarismRunRequest();
        String description = "Describes the github url";
        pr.setDescription(description);
        String actualValue = pr.getDescription();
        assertEquals(description,actualValue);
    }

    @Test
    public void test03(){
        PlagiarismRunRequest pr = new PlagiarismRunRequest();
        ArrayList<String> users = new ArrayList<>();
        users.add("Ananta");
        users.add("Junhao");
        users.add("Raghu");
        users.add("Bharat");
        pr.setSharedUsers(users);
        List<String> val = pr.getSharedUsers();
        assertEquals(users,val);
    }


}
package edu.neu;

import edu.neu.user.ServerResponse;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ServerResponseTest {

    @Test
    public void test01(){
        String msg = "Logging in";
        ServerResponse sr = new ServerResponse(msg);
        String msg2 = "Logging out";
        sr.setMsg(msg2);
        assertEquals(msg2,sr.getMsg());
    }
}

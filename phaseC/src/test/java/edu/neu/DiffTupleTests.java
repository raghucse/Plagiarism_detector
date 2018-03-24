package edu.neu;

import edu.neu.models.DiffTuple;
import org.junit.Test;
import static org.junit.Assert.*;

public class DiffTupleTests {

    @Test
    public void testData(){
        DiffTuple df = new DiffTuple("abc", true);
        String expectedValue1 = "abc";
        String actualValue1 = df.getData();
        String expectedValue2 = "xyz";
        df.setData("xyz");
        String actualValue2 = df.getData();
        assertEquals(expectedValue1,actualValue1);
        assertEquals(expectedValue2,actualValue2);
    }

    @Test
    public void testMatch(){
        DiffTuple df = new DiffTuple("abc", true);
        Boolean expectedValue1 = true;
        Boolean actualValue1 = df.isMatch();
        Boolean expectedValue2 = false;
        df.setMatch(false);
        Boolean actualValue2 = df.isMatch();
        assertEquals(expectedValue1,actualValue1);
        assertEquals(expectedValue2,actualValue2);
    }

    @Test
    public void testString(){
        DiffTuple df = new DiffTuple("abc", true);
        String expValue = "abc,true";
        assertEquals(expValue,df.toString());
    }
}

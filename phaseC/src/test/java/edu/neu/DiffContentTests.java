package edu.neu;

import edu.neu.models.DiffContent;
import edu.neu.models.DiffTuple;
import org.junit.Test;
import org.springframework.util.SerializationUtils;

import static org.junit.Assert.*;
public class DiffContentTests {

    @Test
    public void test01(){
        DiffContent dfc1 = new DiffContent("abc",true,true);
        DiffContent dfc2 = new DiffContent("abc",true,false);
        DiffTuple df1 = new DiffTuple("ab",true);
        dfc1.appendReportTupleTo1(df1);
        DiffTuple df2 = new DiffTuple("cd",true);
        dfc2.appendReportTupleTo2(df2);
        String text1 = dfc1.getResult();
        String text2 = dfc2.getResult();
        assertEquals(text1,dfc1.getResult());
        assertEquals(text2,dfc2.getResult());
    }

    @Test
    public void test02(){
        DiffContent dfc1 = new DiffContent("abc",true,true);
        dfc1.appendToResult1("hey",true);
        dfc1.appendToResult2("hi",false);
        String text1 = dfc1.getResult();
        assertEquals(text1,dfc1.getResult());

    }

    @Test
    public void test03() {
        DiffContent dfc1 = new DiffContent("abc", true, true);
        DiffContent dfc2 = new DiffContent("abc", true, false);
        dfc1.addAll(dfc2);
        String text1 = dfc1.getResult();
        String text2 = dfc2.getResult();
        assertEquals(text1,dfc1.getResult());
        assertEquals(text2,dfc2.getResult());
    }
    
    
    @Test
    public void testSerializeDeserialize() {
        DiffContent dfc1 = new DiffContent("abc", true, true);
        DiffContent dfc2 = (DiffContent) SerializationUtils.deserialize(SerializationUtils.serialize(dfc1));
        assertEquals(dfc1.getResult(),dfc2.getResult());
    }

}
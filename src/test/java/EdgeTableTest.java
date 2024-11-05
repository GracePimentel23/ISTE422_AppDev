import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EdgeTableTest {
    EdgeTable testObj;

	@Before
	public void setUp() throws Exception {
		testObj = new EdgeTable("1|strName");
	}

    @Test
    public void testGetNumFigure(){
        String str1 = System.getProperty("optOne");
        final long opt1;
        if (str1 == null){
            opt1=1;  
        }
        else{
            opt1 = Long.parseLong(str1);
        }
        assertEquals("numFigure was initialied as 1 and should be 1",(long)opt1,testObj.getNumFigure());
    }

    @Test
    public void testGetName(){
        assertEquals("strName", testObj.getName());
    }

    @Test
    public void testAddRelatedTable(){
        testObj.addRelatedTable(0);
        //assertEquals("addRelatedTable was... ",0, testObj.addRelatedTable(0));
    }

    @Test
    public void testGetRelatedTableArray(){
        assertEquals(null, testObj.getRelatedTablesArray());
    }

    @Test
    public void testGetRelatedFieldsArray(){
        assertEquals(null, testObj.getRelatedFieldsArray());
    }
}
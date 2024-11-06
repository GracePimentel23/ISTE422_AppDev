import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class EdgeFieldTest {
    private static final Logger logger = LogManager.getLogger(EdgeFieldTest.class);
    private EdgeField edgeField;

    @Before
    public void setUp() {
        logger.info("Initialize EdgeField");
        edgeField = new EdgeField("1|TestField");
    }

    @Test
    public void constTest() { 
        logger.info("Testing the constuctor");
        assertNotNull("EdgeField instance should be created", edgeField);
        assertEquals("NumFigure should be 1", 1, edgeField.getNumFigure());
        assertEquals("Name should be 'TestField'", "TestField", edgeField.getName());
    }

    @Test
    public void tableIDTest() {
        logger.info("Testing whether we will recieve what we set in TableID");
        edgeField.setTableID(123);
        assertEquals("TEST PASSED: TableID should be 123", 123, edgeField.getTableID());
    }

    @Test
    public void tableBoundTest() {
        logger.info("Testing whether we will recieve what we set in TableBound");
        edgeField.setTableBound(66);
        assertEquals("TEST PASSED: TableBound should be 66", 66, edgeField.getTableBound());
    }

    @Test
    public void fieldBoundTest() {
        logger.info("Testing whether we will recieve what we set in FieldBound");
        edgeField.setFieldBound(26);
        assertEquals("TEST PASSED: FieldBound should be 26", 26, edgeField.getFieldBound());
    }


    @Test
    public void primaryKeyTest() {
        logger.info("Tests the primary key");
        edgeField.setIsPrimaryKey(true);
        assertTrue("TEST PASSED: IsPrimaryKey should be true", edgeField.getIsPrimaryKey());
        edgeField.setIsPrimaryKey(false);
        assertFalse("TEST PASSED: IsPrimaryKey should be false", edgeField.getIsPrimaryKey());
    }

    @Test
    public void defaultValueTest() {
        logger.info("Testing whether we will recieve what we set in DefaultValue");
        edgeField.setDefaultValue("Default");
        assertEquals("TEST PASSED: DefaultValue should be 'Default'", "Default", edgeField.getDefaultValue());
    }

    @Test
    public void varcharValTest() {
        logger.info("Testing whether we will recieve what we set in VarcharValue");
        edgeField.setVarcharValue(132);
        assertEquals("TEST PASSED: VarcharValue should be 132", 132, edgeField.getVarcharValue());
        
     }

    @Test
    public void dataTypeTest() {
        logger.info("Testing whether we will recieve what we set in DataType");
        edgeField.setDataType(2); 
        assertEquals("DataType should be an Integer", 2, edgeField.getDataType());
        
        logger.info("Test value that shouldnt work - Out of bounds");
        edgeField.setDataType(10); 
        assertEquals("TEST FAILED: DataType should remain at 2", 2, edgeField.getDataType());
    }
}


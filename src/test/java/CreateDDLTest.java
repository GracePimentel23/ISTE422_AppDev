import static org.junit.Assert.*;

import java.util.List;
import java.util.Map.Entry;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;

import org.junit.Test;

public class CreateDDLTest {
EdgeConvertCreateDDL ddl;
EdgeConvertCreateDDL ddl2;

@Before
public void createDDL() {
//create table and field for constructor
EdgeTable testTable = new EdgeTable("1|tblName");
testTable.addNativeField(1);
testTable.makeArrays();
testTable.setRelatedField(0,1);
EdgeField testField = new EdgeField("1|fieldName|1|1|true|false");

//for ddl2
EdgeTable testTable2 = new EdgeTable("2|anotherName");
testTable2.addNativeField(2);
testTable2.addNativeField(1);
testTable2.makeArrays();
testTable2.setRelatedField(0,1);
testTable2.setRelatedField(1,2);
EdgeField testField2 = new EdgeField("2|fieldName2|1|1|true|false");

//set arrays for constructor
EdgeTable[] tables = {testTable};
EdgeField[] fields = {testField};

//for ddl2
EdgeTable[] tables2 = {testTable, testTable2};
EdgeField[] fields2 = {testField, testField2};

// because create DDL is absctract, need to define abstract classes
ddl  = new EdgeConvertCreateDDL(tables, fields) {
@Override
public String getDatabaseName() {
return null;
}
public String getProductName(){
return null;
}
public String getSQLString(){
return null;
}
public void createDDL(){

}
};

//for ddl2
ddl2 = new EdgeConvertCreateDDL(tables2, fields2){
@Override
public String getDatabaseName(){
return null;
}
public String getProductName(){
return null;
}
public String getSQLString(){
return null;
}
public void createDDL(){

}
};
}

@Test
public void testInitializeTables() throws Exception {
assertEquals("testing the internal state of table array", "tblName", ddl.tables[0].getName());
}

@Test
public void testInitializeTables2() throws Exception {
assertEquals("testing internal state of table1 in array for ddl2", "tblName", ddl2.tables[0].getName());
assertEquals("testing internal state of table2 in array  for ddl2", "anotherName", ddl2.tables[1].getName());
}

@Test
public void testInitializeFields() throws Exception{
assertEquals("Testing the internal state of the field array", "fieldName", ddl.fields[0].getName());
}

@Test
public void testInitializeFields2() throws Exception{
assertEquals("testing internal state of field1 in array for ddl2", "fieldName", ddl2.fields[0].getName());
assertEquals("testing internal state of field2 in array for ddl2", "fieldName2", ddl2.fields[1].getName());
}

@Test
public void testInitializeMaxBound() throws Exception{
assertEquals("testing the internal state of the max bound variable", 1, ddl.maxBound);
}

@Test
public void testInitializeMaxBound2() throws Exception{
assertEquals("testing the internal state of the max bound variable for ddl2, with more fields in table2", 2, ddl2.maxBound);
}

@Test
public void testTableGetter() throws Exception{
assertEquals("testing that the table getter can access table with correct figure", "tblName", ddl.getTable(1).getName());
}

@Test
public void testTableGetter2() throws Exception{
assertEquals("testing that the table getter in ddl2 can access table with correct figure", "anotherName", ddl2.getTable(2).getName());
}

@Test
public void testTableGetterNull() throws Exception{
assertEquals("testing that the table getter returns null when given an incorrect figure)", null, ddl.getTable(2));
}

@Test
public void testFieldGetter() throws Exception{
assertEquals("testing that the field getter can access field with correct figure", "fieldName", ddl.getField(1).getName());
}

@Test
public void testFieldGetter2() throws Exception{
assertEquals("testing that the field getter in ddl2 can access field with correct figure", "fieldName2", ddl2.getField(2).getName());
}

@Test
public void testFieldGetterNull() throws Exception{
assertEquals("testing that the field getter returns null when given an incorrect figure", null, ddl.getField(2));
}



}
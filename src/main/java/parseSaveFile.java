import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class parseSaveFile extends EdgeConvertFileParser{
   private File parseFile;
   private FileReader fr;
   private BufferedReader br;
   private String currentLine;
   private ArrayList alTables, alFields, alConnectors;
    //private EdgeTable[] tables;
    //private EdgeField[] fields;
    //private EdgeField tempField;
    //private EdgeConnector[] connectors;
    //private String style;
    //private String text;
   private String tableName;
   private String fieldName;
    //private boolean isEntity, isAttribute, isUnderlined = false;
   private int numFigure, numConnector, numFields, numTables, numNativeRelatedFields;
    //private int endPoint1, endPoint2;
    //private int numLine;
    //String SAVE_ID = "EdgeConvert Save File"; //first line of save files should be this
   public static final String DELIM = "|";

   public parseSaveFile(File file)  { //this method is unclear and confusing in places
      super(file);
      
   } // parseSaveFile()

   public void parseSave() throws IOException{
      StringTokenizer stTables, stNatFields, stRelFields, stNatRelFields, stField;
      EdgeTable tempTable;
      EdgeField tempField;
      currentLine = br.readLine();
      currentLine = br.readLine(); //this should be "Table: "
      while (currentLine.startsWith("Table: ")) {
         numFigure = Integer.parseInt(currentLine.substring(currentLine.indexOf(" ") + 1)); //get the Table number
         currentLine = br.readLine(); //this should be "{"
         currentLine = br.readLine(); //this should be "TableName"
         tableName = currentLine.substring(currentLine.indexOf(" ") + 1);
         tempTable = new EdgeTable(numFigure + DELIM + tableName);
         
         currentLine = br.readLine(); //this should be the NativeFields list
         stNatFields = new StringTokenizer(currentLine.substring(currentLine.indexOf(" ") + 1), DELIM);
         numFields = stNatFields.countTokens();
         for (int i = 0; i < numFields; i++) {
            tempTable.addNativeField(Integer.parseInt(stNatFields.nextToken()));
         }
         
         currentLine = br.readLine(); //this should be the RelatedTables list
         stTables = new StringTokenizer(currentLine.substring(currentLine.indexOf(" ") + 1), DELIM);
         numTables = stTables.countTokens();
         for (int i = 0; i < numTables; i++) {
            tempTable.addRelatedTable(Integer.parseInt(stTables.nextToken()));
         }
         tempTable.makeArrays();
         
         currentLine = br.readLine(); //this should be the RelatedFields list
         stRelFields = new StringTokenizer(currentLine.substring(currentLine.indexOf(" ") + 1), DELIM);
         numFields = stRelFields.countTokens();

         for (int i = 0; i < numFields; i++) {
            tempTable.setRelatedField(i, Integer.parseInt(stRelFields.nextToken()));
         }

         alTables.add(tempTable);
         currentLine = br.readLine(); //this should be "}"
         currentLine = br.readLine(); //this should be "\n"
         currentLine = br.readLine(); //this should be either the next "Table: ", #Fields#
      }
      while ((currentLine = br.readLine()) != null) {
         stField = new StringTokenizer(currentLine, DELIM);
         numFigure = Integer.parseInt(stField.nextToken());
         fieldName = stField.nextToken();
         tempField = new EdgeField(numFigure + DELIM + fieldName);
         tempField.setTableID(Integer.parseInt(stField.nextToken()));
         tempField.setTableBound(Integer.parseInt(stField.nextToken()));
         tempField.setFieldBound(Integer.parseInt(stField.nextToken()));
         tempField.setDataType(Integer.parseInt(stField.nextToken()));
         tempField.setVarcharValue(Integer.parseInt(stField.nextToken()));
         tempField.setIsPrimaryKey(Boolean.valueOf(stField.nextToken()).booleanValue());
         tempField.setDisallowNull(Boolean.valueOf(stField.nextToken()).booleanValue());
         if (stField.hasMoreTokens()) { //Default Value may not be defined
            tempField.setDefaultValue(stField.nextToken());
         }
         alFields.add(tempField);
      }
   }

   @Override
   public void parseFile() throws IOException {
      br = openFile(file);
        while ((currentLine = br.readLine()) != null) {
            currentLine = currentLine.trim();
            if (currentLine != null) {
                System.out.println(currentLine);
            } else if (currentLine == null) {
                System.out.println("There are no more lines.");
            }
        }
        br.close();
   }

   @Override
   public EdgeTable[] getEdgeTables() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getEdgeTables'");
   }

   @Override
   public EdgeField[] getEdgeFields() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getEdgeFields'");
   }
}
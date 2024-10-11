import java.util.StringTokenizer;

import org.apache.logging.log4j.LogManager; //Grace
import org.apache.logging.log4j.Logger; //Grace

public class EdgeField {

   private static final Logger logger = LogManager.getLogger(EdgeField.class);
   private int numFigure, tableID, tableBound, fieldBound, dataType, varcharValue;
   private String name, defaultValue;
   private boolean disallowNull, isPrimaryKey;
   private static String[] strDataType = {"Varchar", "Boolean", "Integer", "Double"};
   public static final int VARCHAR_DEFAULT_LENGTH = 1;
   
   public EdgeField(String inputString) {
      logger.info("EdgeField object input: " + inputString); //Grace
      StringTokenizer st = new StringTokenizer(inputString, EdgeConvertFileParser.DELIM);
      numFigure = Integer.parseInt(st.nextToken());
      name = st.nextToken();
      tableID = 0;
      tableBound = 0;
      fieldBound = 0;
      disallowNull = false;
      isPrimaryKey = false;
      defaultValue = "";
      varcharValue = VARCHAR_DEFAULT_LENGTH;
      dataType = 0;
   }
   
   public int getNumFigure() {
      return numFigure;
   }
   
   public String getName() {
      return name;
   }
   
   public int getTableID() {
      return tableID;
   }
   
   public void setTableID(int value) {
      logger.debug("tableID value: " + value); //Grace
      tableID = value;
   }
   
   public int getTableBound() {
      return tableBound;
   }
   
   public void setTableBound(int value) {
      logger.debug("tableBound value: " + value); //Grace
      tableBound = value;
   }

   public int getFieldBound() {
      return fieldBound;
   }
   
   public void setFieldBound(int value) {
      logger.debug("fieldBound value: " + value); //Grace
      fieldBound = value;
   }

   public boolean getDisallowNull() {
      return disallowNull;
   }
   
   public void setDisallowNull(boolean value) {
      logger.debug("disallowNull value: " + value); //Grace
      disallowNull = value;
   }
   
   public boolean getIsPrimaryKey() {
      return isPrimaryKey;
   }
   
   public void setIsPrimaryKey(boolean value) {
      logger.debug("isPrimaryKey value: " + value); //Grace
      isPrimaryKey = value;
   }
   
   public String getDefaultValue() {
      return defaultValue;
   }
   
   public void setDefaultValue(String value) {
      logger.debug("defaultValue: " + value); //Grace
      defaultValue = value;
   }
   
   public int getVarcharValue() {
      return varcharValue;
   }
   
   public void setVarcharValue(int value) {
      if (value > 0) {
         logger.debug("Setting varcharValue to: " + value); //Grace
         varcharValue = value;
      } else {
         logger.warn("WARNING: Invalid dataType value: " + value); // Log a warning for invalid dataType
      }
   }
   public int getDataType() {
      return dataType;
   }
   
   public void setDataType(int value) {
      if (value >= 0 && value < strDataType.length) {
         dataType = value;
      }
   }
   
   public static String[] getStrDataType() {
      return strDataType;
   }
   
   public String toString() {
      return numFigure + EdgeConvertFileParser.DELIM +
      name + EdgeConvertFileParser.DELIM +
      tableID + EdgeConvertFileParser.DELIM +
      tableBound + EdgeConvertFileParser.DELIM +
      fieldBound + EdgeConvertFileParser.DELIM +
      dataType + EdgeConvertFileParser.DELIM +
      varcharValue + EdgeConvertFileParser.DELIM +
      isPrimaryKey + EdgeConvertFileParser.DELIM +
      disallowNull + EdgeConvertFileParser.DELIM +
      defaultValue;
   }
}

import java.util.StringTokenizer;

import org.apache.logging.log4j.LogManager;//Grace
import org.apache.logging.log4j.Logger; //Grace

public class EdgeConnector {
   private static final Logger logger = LogManager.getLogger(EdgeConnector.class); //Grace
  
   private int numConnector, endPoint1, endPoint2;
   private String endStyle1, endStyle2;
   private boolean isEP1Field, isEP2Field, isEP1Table, isEP2Table;
      
   public EdgeConnector(String inputString) {
      
      StringTokenizer st = new StringTokenizer(inputString, EdgeConvertFileParser.DELIM);
      numConnector = Integer.parseInt(st.nextToken());
      endPoint1 = Integer.parseInt(st.nextToken());
      endPoint2 = Integer.parseInt(st.nextToken());
      endStyle1 = st.nextToken();
      endStyle2 = st.nextToken();
      isEP1Field = false;
      isEP2Field = false;
      isEP1Table = false;
      isEP2Table = false;
   }
   
   public int getNumConnector() {
      logger.debug("getNumConnector returns: " + numConnector); //Grace
      return numConnector;
   }
   
   public int getEndPoint1() {
      logger.debug("getEndPoint1 returns: " + endPoint1); //Grace
      return endPoint1;
   }
   
   public int getEndPoint2() {
      logger.debug("getEndPoint2 returns: " + endPoint2); //Grace
      return endPoint2;
   }
   
   public String getEndStyle1() {
      logger.debug("getEndStyle1 returns: " + endStyle1); //Grace
      return endStyle1;
   }
   
   public String getEndStyle2() {
      logger.debug("getEndStyle2 returns: " + endStyle2); //Grace
      return endStyle2;
   }
   public boolean getIsEP1Field() {
      logger.debug("getIsEP1Field returns: " + isEP1Field); //Grace
      return isEP1Field;
   }
   
   public boolean getIsEP2Field() {
      logger.debug("getIsEP2Field returns: " + isEP2Field); //Grace
      return isEP2Field;
   }

   public boolean getIsEP1Table() {
      logger.debug("getIsEP1Table returns: " + isEP1Table); //Grace
      return isEP1Table;
   }

   public boolean getIsEP2Table() {
      logger.debug("getIsEP2Table returns: " + isEP2Table); //Grace
      return isEP2Table;
   }

   public void setIsEP1Field(boolean value) {
      logger.debug("setIsEP1Field returns: " + value); //Grace
      isEP1Field = value;
   }
   
   public void setIsEP2Field(boolean value) {
      logger.debug("setIsEP2Field returns: " + value); //Grace
      isEP2Field = value;
   }

   public void setIsEP1Table(boolean value) {
      logger.debug("setIsEP1Table returns: " + value); //Grace
      isEP1Table = value;
   }

   public void setIsEP2Table(boolean value) {
      logger.debug("setIsEP2Table returns: " + value); //Grace
      isEP2Table = value;
   }
}

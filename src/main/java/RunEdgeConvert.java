import org.apache.logging.log4j.LogManager;//Grace
import org.apache.logging.log4j.Logger; //Grace




public class RunEdgeConvert {
   private static final Logger logger = LogManager.getLogger(RunEdgeConvert.class); //Grace
   public static void main(String[] args) {
      logger.info("Running main() in RunEdgeConvert=> creating a new EdgeConvertGUI");
      EdgeConvertGUI edge = new EdgeConvertGUI();
   }
}
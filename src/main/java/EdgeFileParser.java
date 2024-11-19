import java.io.*;
import java.util.*;

public class EdgeFileParser extends EdgeConvertFileParser {

    private ArrayList<EdgeTable> tables;          
    private ArrayList<EdgeField> fields;        
    private ArrayList<EdgeConnector> connectors; 
    private String currentLine;                   
    private boolean isEntity, isAttribute, isUnderlined; 
    private String style, text;                   
    private int numFigure, numConnector;         
    private int endPoint1, endPoint2;            
    public static final String DELIM = "|";      

    public EdgeFileParser(File file) {
        super(file);
        tables = new ArrayList<>();
        fields = new ArrayList<>();
        connectors = new ArrayList<>();
    }

    @Override
    public void parseFile() throws IOException {
        br = openFile(file);
        while ((currentLine = br.readLine()) != null) {
            currentLine = currentLine.trim();
            if (currentLine.startsWith("Figure ")) {
                parseFigure(currentLine);
            } else if (currentLine.startsWith("Connector ")) {
                parseConnector(currentLine);
            }
        }
        br.close();
    }

    private void parseFigure(String currentLine) throws IOException {
        numFigure = Integer.parseInt(currentLine.substring(currentLine.indexOf(" ") + 1));
         // Skip "{"
        currentLine = br.readLine().trim();
        // Read the style
        currentLine = br.readLine().trim(); 

        if (!currentLine.startsWith("Style")) {
            return;
        }

        style = extractStyle(currentLine);

        if (style.startsWith("Relation")) {
            System.err.println("ERROR: The Edge file contains relations. Please resolve and try again.");
            EdgeConvertGUI.setReadSuccess(false);
            return;
        }

        isEntity = style.startsWith("Entity");
        isAttribute = style.startsWith("Attribute");

        if (!isEntity && !isAttribute) {
            return; // Skip if not Entity || Attribute
        }
        // Read text
        currentLine = br.readLine().trim(); 
        text = extractText(currentLine);

        if (text.isEmpty()) {
            System.err.println("ERROR: Blank names are not allowed. Please try again.");
            EdgeConvertGUI.setReadSuccess(false);
            return;
        }

        isUnderlined = checkUnderlined();

        if (isEntity) {
            if (isTableDuplicate(text)) {
                System.err.println("ERROR: Duplicate table name: " + text);
                EdgeConvertGUI.setReadSuccess(false);
                return;
            }
            tables.add(new EdgeTable(numFigure + DELIM + text));
        }

        if (isAttribute) {
            EdgeField field = new EdgeField(numFigure + DELIM + text);
            field.setIsPrimaryKey(isUnderlined);
            fields.add(field);
        }
    }

    private void parseConnector(String currentLine) throws IOException {
        numConnector = Integer.parseInt(currentLine.substring(currentLine.indexOf(" ") + 1));
        br.readLine(); // Skip "{"
        br.readLine(); // Skip "Style"
        endPoint1 = Integer.parseInt(br.readLine().trim().split(" ")[1]);
        endPoint2 = Integer.parseInt(br.readLine().trim().split(" ")[1]);
        skipLines(4); // Skip non-relevant lines
        String endStyle1 = extractStyle(br.readLine());
        String endStyle2 = extractStyle(br.readLine());
        skipToEndRecord();

        connectors.add(new EdgeConnector(numConnector + DELIM + endPoint1 + DELIM + endPoint2 + DELIM + endStyle1 + DELIM + endStyle2));
    }

    private String extractStyle(String currentLine) {
        return currentLine.substring(currentLine.indexOf("\"") + 1, currentLine.lastIndexOf("\""));
    }

    private String extractText(String currentLine) {
        String text = currentLine.substring(currentLine.indexOf("\"") + 1, currentLine.lastIndexOf("\"")).replaceAll(" ", "");
        int escapeIndex = text.indexOf("\\");
        return escapeIndex > 0 ? text.substring(0, escapeIndex) : text;
    }

    private boolean checkUnderlined() throws IOException {
    boolean underlined = false;
    while (!(currentLine = br.readLine().trim()).equals("}")) {
        if (currentLine.startsWith("TypeUnderl")) {
            underlined = true;
        }
    }
    return underlined;
}

    private boolean isTableDuplicate(String tableName) {
        for (EdgeTable table : tables) {
            if (table.getName().equals(tableName)) {
                return true;
            }
        }
        return false;
    }

    private void skipLines(int count) throws IOException {
        for (int i = 0; i < count; i++) {
            br.readLine();
        }
    }

    private void skipToEndRecord() throws IOException {
        while (!br.readLine().trim().equals("}")) {
            // Skip to the end of the record
        }
    }

    @Override
    public EdgeTable[] getEdgeTables() {
        return tables.toArray(new EdgeTable[0]);
    }

    @Override
    public EdgeField[] getEdgeFields() {
        return fields.toArray(new EdgeField[0]);
    }

    public EdgeConnector[] getEdgeConnectors() {
        return connectors.toArray(new EdgeConnector[0]);
    }
}

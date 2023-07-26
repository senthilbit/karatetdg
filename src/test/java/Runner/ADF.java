package Runner;



import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ADF {

    public static void main(String[] args) throws IOException {
       // String excelFilePath = "input_data.xlsx";
        //String jsonTemplateFilePath = "template.json";

        // Read data from Excel
        List<Map<String, Object>> excelData = readExcelData("Sheet1");

        // Read JSON template format
        JsonNode jsonTemplateFormat = readJsonTemplate();

        // Create a JSON template with Excel data
        List<String> jsonTemplateWithData = createJsonTemplate2(jsonTemplateFormat, excelData);

        // Print the JSON template with data
        System.out.println(jsonTemplateWithData);
    }

    public static List<Map<String, Object>> readExcelData(String sheetName) throws IOException {
        List<Map<String, Object>> data = new ArrayList<>();
        
        String excelFilePath = "C:\\Users\\gurjara\\IdeaProjects\\KarateDemo\\src\\test\\java\\Data\\Worksheet.xlsx";

        try (FileInputStream inputStream = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(inputStream)) {
        	Sheet sheet1 = workbook.getSheet(sheetName);
            // Assuming the data is in the first sheet (index 0)
            Sheet sheet = workbook.getSheetAt(0);

            // Read the header row to get the column names
            Row headerRow = sheet.getRow(0);
            int numColumns = headerRow.getPhysicalNumberOfCells();
            String[] columnNames = new String[numColumns];
            for (int i = 0; i < numColumns; i++) {
                columnNames[i] = headerRow.getCell(i).getStringCellValue();
            }

            // Convert data rows into a list of maps
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                Map<String, Object> rowData = new HashMap<>();

                for (int colIndex = 0; colIndex < columnNames.length; colIndex++) {
                    Cell cell = row.getCell(colIndex);
                    String columnName = columnNames[colIndex];
                    Object cellValue = getCellValue(cell);
                    rowData.put(columnName, cellValue);
                }

                data.add(rowData);
            }
        }

        return data;
    }

    public static Object getCellValue(Cell cell) {
       // if (cell == null) {
        //    return null;
        //}

        CellType cellType = cell.getCellType();
        switch (cellType) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return cell.getNumericCellValue();
                }
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                return cell.getCellFormula();
            default:
                return null;
        }
    }

    public static JsonNode readJsonTemplate() throws IOException {
        // Create an instance of the ObjectMapper from Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        String filePath = "C:\\Users\\gurjara\\IdeaProjects\\KarateDemo\\src\\test\\java\\Data\\templete.json";
        // Read the JSON template file into a JsonNode object
        FileInputStream fileInputStream = new FileInputStream(filePath);
        return objectMapper.readTree(fileInputStream);
    }

    public static String createJsonTemplate(JsonNode jsonTemplate, List<Map<String, Object>> excelData) {
        // Convert JSON template to a string
        String jsonTemplateString = jsonTemplate.toString();

        // Use a placeholder map to store placeholders and their corresponding values
        Map<String, String> placeholderMap = new HashMap<>();

        // Get the column names from the first row of the Excel data (assuming it's the header row)
        Map<String, Object> headerRow = excelData.get(0);

        // Replace placeholders in the JSON template with corresponding values from the Excel data
        for (int i = 1; i < excelData.size(); i++) { // Start from index 1 to skip the header row
            Map<String, Object> rowData = excelData.get(i);
            for (Map.Entry<String, Object> entry : rowData.entrySet()) {
                String columnName = entry.getKey();
                String placeholder = "{{" + columnName + "}}";
                String actualValue = entry.getValue().toString();
                placeholderMap.put(placeholder, actualValue);
            }
        }
        Pattern pattern = Pattern.compile("\\{\\{[a-zA-Z0-9]+}}");
        Matcher matcher = pattern.matcher(jsonTemplateString);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            String placeholder = matcher.group();
            String replacement = placeholderMap.getOrDefault(placeholder, placeholder);
            matcher.appendReplacement(sb, Matcher.quoteReplacement(replacement));
        }

        matcher.appendTail(sb);

        return sb.toString();
    }


public static String createJsonTemplate1(JsonNode jsonTemplate, List<Map<String, Object>> excelData) throws IOException {
    
    String jsonTemplateString = jsonTemplate.toString();

    // Use a placeholder map to store column names and their corresponding values
    Map<String, String> placeholderMap = new HashMap<>();

    // Replace placeholders in the JSON template with values from the Excel data
    int rowIndex = 0;
    for (Map<String, Object> rowData : excelData) {
        for (Map.Entry<String, Object> entry : rowData.entrySet()) {
            String columnName = entry.getKey();
            String placeholder = "{{" + columnName + "}}";
            String actualValue = entry.getValue().toString();
            placeholderMap.put(placeholder, actualValue);
        }

        // For each row, replace the placeholders in the JSON template
        String jsonDataForRow = jsonTemplateString;
        for (Map.Entry<String, String> entry : placeholderMap.entrySet()) {
            jsonDataForRow = jsonDataForRow.replace(entry.getKey(), entry.getValue());
        }
        
        System.out.println(  jsonDataForRow);
        
        rowIndex++;
        
     }
	return null;

}
  


public static List<String> createJsonTemplate2(JsonNode jsonTemplate, List<Map<String, Object>> excelData) throws IOException {
   
    String jsonTemplateString = jsonTemplate.toString();

    
    Map<String, String> placeholderMap = new HashMap<>();

    int rowIndex = 0;
    List<String> jsonList = new ArrayList<>();

    for (Map<String, Object> rowData : excelData) {
        for (Map.Entry<String, Object> entry : rowData.entrySet()) {
            String columnName = entry.getKey();
            String placeholder = "{{" + columnName + "}}";
            String actualValue = entry.getValue().toString();
            placeholderMap.put(placeholder, actualValue);
        }

        // For each row, replace the placeholders in the JSON template
        String jsonDataForRow = jsonTemplateString;
        for (Map.Entry<String, String> entry : placeholderMap.entrySet()) {
            jsonDataForRow = jsonDataForRow.replace(entry.getKey(), entry.getValue());
        }

        jsonList.add(jsonDataForRow);

        // Clear the placeholderMap for the next row of data
        placeholderMap.clear();

        // Increment rowIndex for the next row of data
        rowIndex++;
    }

   
    System.out.println(jsonList);

    return jsonList;
}


}






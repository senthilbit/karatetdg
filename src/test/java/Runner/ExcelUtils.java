package Runner;


	
	import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

    public static List<Map<String, String>> readExcelData( String sheetName) throws IOException {
    	String filePath = "C:\\Users\\gurjara\\IdeaProjects\\KarateDemo\\src\\test\\java\\Data\\title2.xlsx";
        FileInputStream fileInputStream = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getLastRowNum();
        int columnCount = sheet.getRow(0).getLastCellNum();
        List<Map<String, String>> data = new ArrayList<>();

        Row headerRow = sheet.getRow(0);
        for (int i = 1; i <= rowCount; i++) {
            Row row = sheet.getRow(i);
            Map<String, String> rowData = new HashMap<>();
            for (int j = 0; j < columnCount; j++) {
                Cell cell = row.getCell(j);
                String header = headerRow.getCell(j).getStringCellValue();
                rowData.put(header, cell.toString());
            }
            data.add(rowData);
        }

        workbook.close();
        fileInputStream.close();

        return data;
    }
}


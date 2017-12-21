package unclediga.poi;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import unclediga.data.DataFactory;
import unclediga.data.ExcelPerson;

import java.io.File;
import java.util.List;

public class ExcelParser {
    public static List<ExcelPerson> parse(File file) throws Exception {

        if (!file.exists()) {
            throw new Exception("File not found!");
        }

        POIFSFileSystem fs = new POIFSFileSystem(file);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(0);
        if (sheet == null) {
            throw new Exception("No sheets in workbook!");
        }

        int rows = sheet.getPhysicalNumberOfRows();
        System.out.println("Sheet \"" + wb.getSheetName(0) + "\" has " + rows
                + " row(s).");

        DataFactory.getItems().clear();

        // header testing
        HSSFRow row = sheet.getRow(1);
        if (row == null) throw new Exception("Header not found!");

        int i = 1;
//        double totalSum = 0.0;

        while (i < rows) {
            i++;
            row = sheet.getRow(i);
            if (row == null) break;
            HSSFCell cellAcc = row.getCell(4);

            if(!cellAcc.getStringCellValue().startsWith("40817810")) break;

            HSSFCell cellDept = row.getCell(1);
            HSSFCell cellFio = row.getCell(2);
            HSSFCell cellSum = row.getCell(5);

            String dept = cellDept.getStringCellValue();
            String fio = cellFio.getStringCellValue();
            String acc = cellAcc.getStringCellValue();
            Double sum = cellSum.getNumericCellValue();
            DataFactory.setItem(new ExcelPerson(fio,dept,acc,sum));

//            System.out.printf("[%2d] %s:%s:%s:%10f\n", (i - 1), dept, fio, acc, sum.doubleValue());
//            totalSum+=sum.doubleValue();

        }
//        System.out.printf("Total %d emps sum=%15f",(i-1),totalSum);
        return DataFactory.getItems();
    }
}
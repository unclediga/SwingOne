/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */

package unclediga.poi.examples;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

/**
 * Demonstrates how to use newlines in cells.
 */
public class NewLinesInCells {
    public static void main( String[] args ) throws IOException {

        HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet s = wb.createSheet();
		HSSFRow r = null;
		HSSFCell c = null;
		HSSFCellStyle cs = wb.createCellStyle();
		HSSFFont f2 = wb.createFont();

		cs = wb.createCellStyle();

		cs.setFont(f2);
		// Word Wrap MUST be turned on
		cs.setWrapText(true);

		r = s.createRow(2);
		r.setHeight((short) 0x349);
		c = r.createCell(2);
		c.setCellType(CellType.STRING);
		c.setCellValue("Use \n with word wrap on to create a new line");
		c.setCellStyle(cs);
		s.setColumnWidth(2, (int) ((50 * 8) / ((double) 1 / 20)));

		FileOutputStream fileOut = new FileOutputStream("c:/TEMP/poi/workbook.xls");
		wb.write(fileOut);
		fileOut.close();
		wb.close();
    }
}

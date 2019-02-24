/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import com.grapecity.documents.excel.SaveFileFormat;
import com.grapecity.documents.excel.Workbook;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PatternFormatting;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 *
 * @author Nante
 */
public class ExportFiles {

    public static void toExcel(String headers, List<String> lijst, int kolombreedte, float rijhoogte, String locatie) {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("overzicht");
        //shadeAlt(sheet); // is voor kleurtje van elke rij
        sheet.setDefaultColumnWidth(kolombreedte);
        sheet.setDefaultRowHeightInPoints(rijhoogte);
        Row[] rows = new Row[lijst.size() + 1];
        //header
        lijst.add(0, headers);
        //rest
        IntStream.range(0, lijst.size()).forEach(rownumber -> {
            rows[rownumber] = sheet.createRow(rownumber);
            String[] split = lijst.get(rownumber).split(",");
            IntStream.range(0, split.length - 1).forEach(columnnumber -> {
                rows[rownumber].createCell(columnnumber).setCellValue(split[columnnumber]);

            });

        });

        try {
            FileOutputStream out = new FileOutputStream(new File(locatie));
            workbook.write(out);
            out.close();
            workbook.close();
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("Het pad klopt niet");
        } catch (IOException ex) {
            throw new IllegalArgumentException("Er is een fout opgetreden");
        }

    }
//werkt nog niet
//    public static void toPdf(String locatie, String pdfNaam) {
//       
//        Workbook workbook = new Workbook();
//        workbook.open(locatie);
//        workbook.save(pdfNaam, SaveFileFormat.Pdf);
//        
//
//    }

//nog niet gebruikt
    public static void shadeAlt(Sheet sheet) {
        SheetConditionalFormatting sheetCF = sheet.getSheetConditionalFormatting();
        ConditionalFormattingRule rule1 = sheetCF.createConditionalFormattingRule("MOD(ROW(),2)");
        PatternFormatting fill1 = rule1.createPatternFormatting();
        fill1.setFillBackgroundColor(IndexedColors.WHITE1.index);
        fill1.setFillPattern(PatternFormatting.SOLID_FOREGROUND);

        ConditionalFormattingRule rule2 = sheetCF.createConditionalFormattingRule("MOD(ROW()+ 1,2)");
        PatternFormatting fill2 = rule2.createPatternFormatting();
        fill2.setFillBackgroundColor(IndexedColors.PALE_BLUE.index);
        fill2.setFillPattern(PatternFormatting.SOLID_FOREGROUND);

        CellRangeAddress[] regions = {CellRangeAddress.valueOf("A1:E9")};
        sheetCF.addConditionalFormatting(regions, rule2, rule1);
    }

}

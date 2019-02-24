/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import java.util.stream.Stream;
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

    public static void toExcel(List<String> lijst, int kolombreedte, float rijhoogte, String bestandNaam) {
        
        String locatie = System.getProperty("user.home");
        locatie += "/Desktop/";
        locatie += bestandNaam;
        locatie += ".xls";
      
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("overzicht");
       // shadeAlt(sheet); // is voor kleurtje van elke rij
        sheet.setDefaultColumnWidth(kolombreedte);
        sheet.setDefaultRowHeightInPoints(rijhoogte);
        Row[] rows = new Row[lijst.size()];
        IntStream.range(0,lijst.size()-1).forEach(rownumber -> {
            rows[rownumber] = sheet.createRow(rownumber);
            String[] split = lijst.get(rownumber).split(" ");
            IntStream.range(0,split.length -1).forEach(columnnumber -> {
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
//nog niet gebruikt
    public static void shadeAlt(Sheet sheet) {
        SheetConditionalFormatting sheetCF = sheet.getSheetConditionalFormatting();

        // Condition 1: Formula Is   =A2=A1   (White Font)
        ConditionalFormattingRule rule1 = sheetCF.createConditionalFormattingRule("MOD(ROW(),2)");
        PatternFormatting fill1 = rule1.createPatternFormatting();
        fill1.setFillBackgroundColor(IndexedColors.WHITE1.index);
        fill1.setFillPattern(PatternFormatting.SOLID_FOREGROUND);

        ConditionalFormattingRule rule2 = sheetCF.createConditionalFormattingRule("MOD(ROW()+ 1,2)");
        PatternFormatting fill2 = rule2.createPatternFormatting();
        fill2.setFillBackgroundColor(IndexedColors.PALE_BLUE.index);
        fill2.setFillPattern(PatternFormatting.SOLID_FOREGROUND);

        CellRangeAddress[] regions = {
            CellRangeAddress.valueOf("A1:E9")
        };

        sheetCF.addConditionalFormatting(regions, rule2, rule1);
    }
//nog niet gebruikt
    public static String[][] transpose(String[][] array) {
        if (array == null || array.length == 0)//empty or unset array, nothing do to here
        {
            return array;
        }

        int width = array.length;
        int height = array[0].length;

        String[][] array_new = new String[height][width];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                array_new[y][x] = array[x][y];
            }
        }
        return array_new;
    }


}

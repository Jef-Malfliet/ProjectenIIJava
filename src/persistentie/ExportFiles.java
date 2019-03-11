/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Exportable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PatternFormatting;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 *
 * @author Nante
 */
public class ExportFiles {

    public static <T extends Exportable> void toExcel(List<T> lijst, int kolombreedte, float rijhoogte, String locatie) {

        List<String> stringLijst = lijst.stream().map(t -> t.excelFormat(t)).collect(Collectors.toList());

        String headers = lijst.get(0).excelheaders();

        System.out.println(headers);
        System.out.println(stringLijst);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("overzicht");
        shadeAlt(sheet, makeLetter(headers.split(",").length), String.valueOf(lijst.size()));
        sheet.setDefaultColumnWidth(kolombreedte);
        // sheet.setDefaultRowHeightInPoints(rijhoogte);
        Row[] rows = new Row[stringLijst.size() + 1];
        //header
        stringLijst.add(0, headers);
        //rest
        CellStyle cs = workbook.createCellStyle();
        cs.setWrapText(true);
        cs.setAlignment(HorizontalAlignment.CENTER);
        cs.setVerticalAlignment(VerticalAlignment.CENTER);
        IntStream.range(0, stringLijst.size()).forEach(rownumber -> {
            rows[rownumber] = sheet.createRow(rownumber);
            String[] split = stringLijst.get(rownumber).split(",");

            IntStream.range(0, split.length).forEach(columnnumber -> {
                Cell cell = rows[rownumber].createCell(columnnumber);
                cell.setCellValue(split[columnnumber]);

                cell.setCellStyle(cs);
            });

        });

        try {
            File file = new File(locatie);
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
            workbook.close();

//            Workbook workbook2 = new Workbook();
//            InputStream fileStream = this.getClass().getResourceAsStream(locatie);
//            workbook2.open(fileStream);
//            workbook2.save(locatie + ".pdf", SaveFileFormat.Pdf);
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("Het pad klopt niet");
        } catch (IOException ex) {
            throw new IllegalArgumentException("Er is een fout opgetreden");
        }

    }
//werkt nog niet

    public static void toPdf(String locatie, String pdfNaam) {

        //            PDDocument document = new PDDocument();
//            PDPage page = new PDPage();
//            document.addPage(page);
//            document.save(locatie);
//            System.out.println("created");
//            document.close();
//        Workbook workbook2 = new Workbook();
//        InputStream fileStream = this.getClass().getResourceAsStream(locatie);
//        workbook2.open(fileStream);
//        workbook2.save(locatie + ".pdf", SaveFileFormat.Pdf);
//        IWorksheets worksheets = workbook.getWorksheets();
//        IWorksheet get = worksheets.get(0);
//        get.getCells().setText("hoi");
//
//        workbook.save(pdfNaam, SaveFileFormat.Pdf);
    }

//nog niet gebruikt
    public static void shadeAlt(Sheet sheet, String rij, String kolom) {
        SheetConditionalFormatting sheetCF = sheet.getSheetConditionalFormatting();
        ConditionalFormattingRule rule1 = sheetCF.createConditionalFormattingRule("MOD(ROW(),2)");
        PatternFormatting fill1 = rule1.createPatternFormatting();
        fill1.setFillBackgroundColor(IndexedColors.WHITE1.index);
        fill1.setFillPattern(PatternFormatting.SOLID_FOREGROUND);

        ConditionalFormattingRule rule2 = sheetCF.createConditionalFormattingRule("MOD(ROW()+ 1,2)");
        PatternFormatting fill2 = rule2.createPatternFormatting();
        fill2.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.index);
        fill2.setFillPattern(PatternFormatting.SOLID_FOREGROUND);

        CellRangeAddress[] regions = {CellRangeAddress.valueOf("A1:" + rij + kolom)};
        sheetCF.addConditionalFormatting(regions, rule2, rule1);
    }

    private static String makeLetter(int size) {
        //int repeat = size / 26;
        //int letter = size % 26;
        String[] letters = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z".split(" ");
        return letters[size - 1];// + letters[letter];
    }

}

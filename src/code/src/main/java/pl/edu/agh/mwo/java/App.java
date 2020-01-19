package pl.edu.agh.mwo.java;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;


public class App {
    public static void main(String[] args) {

        Workbook f1Wb = WorkbookLoader.openF1Workbook();

        zad1(f1Wb);
        zad2(f1Wb);
        zad3(f1Wb);
        zad4(f1Wb);
        zad5(f1Wb);

    }

    public static void zad1(Workbook wb){
        HashSet<String> winners = new HashSet<>();
        for (Sheet sheet: wb){
            winners.add( sheet.getRow(0).getCell(2).getStringCellValue()) ;
        }
        for (String winner: winners){
            System.out.println(winner);
        }
    }

    public static void zad2(Workbook wb){
        int points = 0;
        for (Sheet sheet: wb){
            for (Row row: sheet ){
                if (row.getCell(2).getStringCellValue().contains("Lewis Hamilton")) {
                    points += Integer.parseInt(row.getCell(5).getStringCellValue());
                    }
                }
            }

        System.out.println("\nLewis Hamilton got " + points + " points");
    }

    public static void zad3(Workbook wb){
        int points = 0;
        for (Sheet sheet: wb){
            for (Row row: sheet ){
                if (row.getCell(3).getStringCellValue().equals("Ferrari")) {
                    points += Integer.parseInt(row.getCell(5).getStringCellValue());
                }
            }
        }

        System.out.println("\nFerrari got " + points + " points\n");
    }

    public static void zad4(Workbook wb){
        Map<String, Integer> mapPoints = new HashMap<>();

        for (Sheet sheet: wb){
            for (Row row: sheet){
                String name = row.getCell(2).getStringCellValue();
                Integer points = Integer.valueOf(row.getCell(5).getStringCellValue());

                if (mapPoints.containsKey(name)) {
                    points += mapPoints.get(name);
                }
                mapPoints.put(name, points );
            }
        }
        List<Map.Entry<String,Integer>> listPoints = new ArrayList<>(mapPoints.entrySet());
        listPoints.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));

        for (Map.Entry<String,Integer> entry: listPoints){
            System.out.println(String.format("%s -> %d", entry.getKey(), entry.getValue()));
        }
    }

    private static Workbook buildWorkbook(Workbook wb){
        Workbook newWb = new XSSFWorkbook();
        Sheet newSheet = newWb.createSheet("Standings");
        Set<String> drivers = new HashSet<>();
        Row row = newSheet.createRow(0);
        row.createCell(0);
        for (int sheetNumber=0; sheetNumber<wb.getNumberOfSheets(); sheetNumber++){
            Sheet sheet = wb.getSheetAt(sheetNumber);
            row.createCell(sheetNumber+1).setCellValue(sheet.getSheetName());
            for (Row row1: sheet){
                drivers.add(row1.getCell(2).getStringCellValue());
            }
        }

        int rowCount = 1;
        for (String driver: drivers ){
            newSheet.createRow(rowCount).createCell(0).setCellValue(driver);
            rowCount += 1;
        }
        return newWb;
    }


    public static void zad5(Workbook wb){
        
        Workbook newWb = buildWorkbook(wb);
        Sheet sheet = newWb.getSheetAt(0);

        for (int driverNumber = 1; driverNumber <= sheet.getLastRowNum(); driverNumber++){
            Row standingsRow = sheet.getRow(driverNumber);
            String driverName = standingsRow.getCell(0).getStringCellValue();
            for (int raceNumber = 0; raceNumber < wb.getNumberOfSheets(); raceNumber++) {
                Sheet raceSheet = wb.getSheetAt(raceNumber);
                int points = 0;

                for (Row driverRow: raceSheet){
                    String candidateDriverName = driverRow.getCell(2).getStringCellValue();
                    if (candidateDriverName == driverName){

                        points = Integer.parseInt(driverRow.getCell(5).getStringCellValue());
                        break;
                    }
                }
                int previousPoints = 0;
                try{
                    previousPoints = (int)standingsRow.getCell(raceNumber).getNumericCellValue();
                }
                catch (Exception ignored){}

                Cell newStandingsCell = standingsRow.createCell(raceNumber+1);
                newStandingsCell.setCellValue(points + previousPoints);
            }
        }

        try (OutputStream fileOut = new FileOutputStream("Standings.xls")) {
            newWb.write(fileOut);
            System.out.println("\nStandings saved.");
        }
        catch (IOException e){
            System.out.println("\nError while saving the workbook");
        }
    }
}

package pl.edu.agh.mwo.java;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.HashSet;

public class SudokuBoardChecker {
    private Workbook workbook;

    public Workbook getWorkbook() {
        return workbook;
    }

    public SudokuBoardChecker(Workbook workbook) {
        this.workbook = workbook;
    }

    private boolean checkStructure(ArrayList<ArrayList<Integer>> structure) {
        HashSet<Integer> structureAsSet = new HashSet<>();
        boolean result = true;

        for (ArrayList<Integer> row : structure) {
            for (int number : row) {
                if (structureAsSet.contains(number)) {
                    System.out.print(number + " appears twice ");
                    result = false;
                } else {
                    structureAsSet.add(number);
                }
            }
        }
        return result;
    }

    private boolean checkRows(Sheet sheet) {
        boolean result = true;
        for (int rowNumber = 0; rowNumber < 9; rowNumber++) {
            Row row = sheet.getRow(rowNumber);
            ArrayList<ArrayList<Integer>> structureAsArray = new ArrayList<>();
            ArrayList<Integer> rowAsArray = new ArrayList<>();

            for (int columnNumber = 0; columnNumber < 9; columnNumber++) {
                try {
                    rowAsArray.add((int) row.getCell(columnNumber, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getNumericCellValue());
                } catch (NullPointerException e) {
                    continue;
                }
            }
            structureAsArray.add(rowAsArray);
            if (!checkStructure(structureAsArray)) {
                System.out.println(String.format(" in row number %d: ", rowNumber + 1));
                result = false;
            }
        }
        return result;
    }

    private boolean checkColumns(Sheet sheet) {
        boolean result = true;

        for (int columnNumber = 0; columnNumber < 9; columnNumber++) {
            ArrayList<ArrayList<Integer>> structureAsArray = new ArrayList<>();
            ArrayList<Integer> rowAsArray = new ArrayList<>();

            for (int rowNumber = 0; rowNumber < 9; rowNumber++) {
                Row row = sheet.getRow(rowNumber);
                try {
                    rowAsArray.add((int) row.getCell(columnNumber, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getNumericCellValue());
                } catch (NullPointerException e) {
                    continue;
                }
            }
            structureAsArray.add(rowAsArray);
            if (!checkStructure(structureAsArray)) {
                System.out.println(String.format(" in column number %d: ", columnNumber + 1));
                result = false;
            }
        }
        return result;
    }

    private boolean checkSquares(Sheet sheet) {
        boolean result = true;

        for (int squareRowNumber = 0; squareRowNumber < 3; squareRowNumber++) {
            for (int squareColumnNumber = 0; squareColumnNumber < 3; squareColumnNumber++) {
                ArrayList<ArrayList<Integer>> structureAsArray = new ArrayList<>();

                for (int rowNumber = 0; rowNumber < 3; rowNumber++) {
                    ArrayList<Integer> rowAsArray = new ArrayList<>();
                    for (int columnNumber = 0; columnNumber < 3; columnNumber++) {

                        Row row = sheet.getRow(3 * squareRowNumber + rowNumber);
                        try {
                            rowAsArray.add((int) row.getCell(3 * squareColumnNumber + columnNumber, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getNumericCellValue());
                        } catch (NullPointerException e) {
                            continue;
                        }
                    }
                    structureAsArray.add(rowAsArray);
                }

                if (!checkStructure(structureAsArray)) {
                    System.out.println(String.format(" in square (%d,%d).", squareColumnNumber+1, squareRowNumber+1));
                    result = false;
                }
            }
        }

        return result;
    }

    public boolean verifyBoard(int sheetIndex) {
        Sheet sheet = getWorkbook().getSheetAt(sheetIndex);
        boolean result = true;

        if (!checkRows(sheet)) result = false;
        if (!checkColumns(sheet)) result = false;
        if (!checkSquares(sheet)) result = false;

        return result;
    }

    public boolean verifyBoardStructure(int sheetIndex) {
        Sheet sheet = getWorkbook().getSheetAt(sheetIndex);
        boolean result = true;

        for (int rowNumber = 0; rowNumber < 9; rowNumber++) {
            Row row = sheet.getRow(rowNumber);
            for (int columnNumber = 0; columnNumber < 9; columnNumber++) {
                Cell cell = row.getCell(columnNumber, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                int cellValue = -1;
                try {
                    cellValue = (int) cell.getNumericCellValue();
                } catch (NullPointerException e) {
                    continue;
                } catch (IllegalStateException e) {
                    System.out.println(String.format("Not a number at (%d, %d): ", columnNumber + 1, rowNumber + 1) + cell.getStringCellValue());
                    result = false;
                    continue;
                }
                if ((cellValue < 1) || (cellValue > 9) || ((cellValue - (int) cellValue) != 0)) {
                    System.out.println(String.format("Value out of range or not an integer at (%d, %d): ", columnNumber + 1, rowNumber + 1) + cellValue);
                    result = false;
                }
            }
        }
        return result;
    }
}

package pl.edu.agh.mwo.java;

import org.apache.poi.ss.usermodel.Workbook;

public class App {
    public static void main(String[] args) {

        Workbook sudokuWb = WorkbookLoader.openSudokuWorkbook();
        SudokuBoardChecker sudokuBoardChecker = new SudokuBoardChecker(sudokuWb);
        int numberOfSheets = sudokuWb.getNumberOfSheets();
        for (int sheetNumber = 0; sheetNumber < numberOfSheets; sheetNumber++) {
            System.out.println("\nChecking board number " + (sheetNumber + 1));

            if (sudokuBoardChecker.verifyBoardStructure(sheetNumber)) {
                if (sudokuBoardChecker.verifyBoard(sheetNumber)) {
                    System.out.println("Board OK!");
                }
            }
        }
    }
}

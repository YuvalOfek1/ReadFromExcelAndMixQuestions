package org.example;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    File file;
    FileInputStream fis;
    Workbook workbook;
    Sheet sheet=null;
    boolean endOfFile = false;


    public ExcelReader(String pathName){
        file = new File(pathName);
        try {
            fis = new FileInputStream(file);
            workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheetAt(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close(){
        try {
            workbook.close();
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Sheet getSheet(){
        return sheet;
    }

    public List<Question> getAllQustionsList(int NUM_OF_QUESTIONS){
        List<Question> questionList = new ArrayList<>();
        List<String> questionArgs = new ArrayList<>();
        for (Row row : sheet) {
            questionArgs.clear();
            if(questionList.size()==NUM_OF_QUESTIONS)
                break;
            // Iterate over each cell in the row
            if (row.getRowNum() == 0 || row.getRowNum() == 1)
                continue;
            for (Cell cell : row) {
                if (cell.getColumnIndex() == 0)
                    continue;
                if (cell.getColumnIndex() == 6)
                    break;
                questionArgs.add(cell.getStringCellValue());
            }
            if (questionArgs.size() > 0)
                questionList.add(new Question(questionArgs.get(0), questionArgs.get(1), questionArgs.get(2), questionArgs.get(3), questionArgs.get(4)));
        }
        return questionList;

    }

    public static void main(String[] args) {
        try {
            // Create a file object for the Excel file
            File file = new File("C:\\Users\\Yuval\\Desktop\\BDIKAEXCEL.xlsx");

            // Create a FileInputStream to read the Excel file
            FileInputStream fis = new FileInputStream(file);

            // Create a Workbook object from the Excel file
            Workbook workbook = WorkbookFactory.create(fis);

            // Get the first sheet of the workbook
            Sheet sheet = workbook.getSheetAt(0);

            // Iterate over each row in the sheet
            for (Row row : sheet) {
                // Iterate over each cell in the row
                for (Cell cell : row) {
                    if(cell.getColumnIndex() == 0)
                        continue;
                    if(cell.getColumnIndex() == 6)
                        break;
                    String cellValue = cell.getStringCellValue();
                    System.out.print(cellValue + "\t");
                }
                System.out.println(); // Print a new line after each row
            }

            // Close the workbook and file input stream
            workbook.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
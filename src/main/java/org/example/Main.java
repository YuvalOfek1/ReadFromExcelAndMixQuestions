package org.example;

import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;
public class Main {

    public static void main(String[] args) {

        String EXCEL_PATH = "BDIKAEXCEL.xlsx";
        int NUM_OF_QUESTIONS = 16;

        ExcelReader er = new ExcelReader(EXCEL_PATH);
        Sheet sheet = er.getSheet();
        List<Question> questionList = er.getAllQustionsList(NUM_OF_QUESTIONS);

        QuestionsMixer qm = new QuestionsMixer();
        for(Question q: questionList){
            qm.addQuestion(q);
        }
        qm.printQuestions();
        qm.printAnswers();
        qm.createTxtFile();


    }
}


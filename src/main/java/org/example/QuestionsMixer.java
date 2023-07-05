package org.example;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionsMixer {

    private List<Question> questions;

    public QuestionsMixer() {
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question q){
        q.mixOptions();
        questions.add(q);
    }

   public void printQuestions(){
        int j=1;
       for(Question q : questions){
           System.out.println("Q."+j++ + ") "+q.getTitle() + ":");
           int i=1;
           for(String opt : q.getOptions()){
               System.out.println(i++ + ") " + opt);
           }
           System.out.println("\n----------------\n");
       }
   }

    public List<Question> getQuestions() {
        Collections.shuffle(questions);
        return questions;
    }

    public void printAnswers(){
       System.out.println("Answers:");
       for(Question q : questions){
           System.out.print(q.getTitle() + ": \n");
           System.out.println(q.getAnswer());
           System.out.println("");
       }
   }

   public void addQuestions(Question... qs){
         for(Question q : qs){
              addQuestion(q);
         }
   }

   public void createTxtFile(){
       String path = "myQuestions.txt";
       try {
           FileWriter fw = new FileWriter(path);
           int i = 1;
           List<Question> shuffledQuestions = this.questions;
           for (Question q : shuffledQuestions) {
               fw.write("Q." + i++ + ") ");
               fw.write(q.toString());
               fw.write("\n--------------\n\n");
           }
           fw.write("Answers: \n");
           for (Question q : shuffledQuestions) {
               fw.write("***" + q.getTitle() + "***\n");
               fw.write(q.getAnswer());
               fw.write("\n\n");
           }
           fw.close();
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
   }

}

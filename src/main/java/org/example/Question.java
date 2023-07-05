package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {
    private String title;
    private List<String> options;
    private String answer;

    public Question(String title, String... options) {
        this.title = title;
        answer=options[0];
        this.options = new ArrayList<>();
        for(String opt : options){
            this.options.add(opt);
        }
    }

    public String getTitle() {
        return title;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOptions(String... opts) {
        this.options = new ArrayList<>();
        answer=opts[0];
        for(String opt : opts){
            this.options.add(opt);
        }
    }

    public void mixOptions(){
        Collections.shuffle(options);
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i=1;
        sb.append(title+"\n\n");
        for(String opt:options){
            sb.append(i++ +") "+opt+"\n");
        }
        return sb.toString();
    }

}



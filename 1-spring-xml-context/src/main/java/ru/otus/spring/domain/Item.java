package ru.otus.spring.domain;

import com.opencsv.bean.CsvBindByName;

public class Item {

    public Item() {
    }

    public Item(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    @CsvBindByName(column = "Question")
    private String question;

    @CsvBindByName(column = "Answer")
    private String answer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Item{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}

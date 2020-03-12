package com.example.helloword;

public class storeword {
    private String Word,meaning,examples;

    public storeword() {
    }

    public String getWord() {
        return Word;
    }

    public void setWord(String word) {
        Word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getExamples() {
        return examples;
    }

    public void setExamples(String examples) {
        this.examples = examples;
    }

    public storeword(String word, String meaning, String examples) {
        Word = word;
        this.meaning = meaning;
        this.examples = examples;
    }
}

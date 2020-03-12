package com.example.helloword;

public class winnerclass {

    String Name;
    Long Score;
    String year;
    String contestTaken;

    public winnerclass() {
    }



    public winnerclass(String name, String year, String contestTaken, Long score) {
        Name = name;
        this.year = year;
        this.contestTaken = contestTaken;
        Score = score;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Long getScore() {
        return Score;
    }

    public void setScore(Long score) {
        Score = score;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getContestTaken() {
        return contestTaken;
    }

    public void setContestTaken(String contestTaken) {
        this.contestTaken = contestTaken;
    }
}

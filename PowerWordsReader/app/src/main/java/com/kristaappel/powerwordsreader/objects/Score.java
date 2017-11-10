// Krista Appel
// Application Deployment 2: Android
// Score.java

package com.kristaappel.powerwordsreader.objects;


import java.io.Serializable;

public class Score implements Serializable{

    private final String score;
    private final String scoreNumbers;
    private final String time;
    private final String color;
    private final String level;


    public Score(String _score, String _scoreNumbers, String _time, String _color, String _level){
        this.score = _score;
        this.scoreNumbers = _scoreNumbers;
        this.time = _time;
        this.color = _color;
        this.level = _level;
    }


    public String getScore() {
        return score;
    }

    public String getScoreNumbers(){
        return scoreNumbers;
    }


    public String getTime() {
        return time;
    }

    public String getColor(){
        return color;
    }

    public String getLevel(){
        return level;
    }


}

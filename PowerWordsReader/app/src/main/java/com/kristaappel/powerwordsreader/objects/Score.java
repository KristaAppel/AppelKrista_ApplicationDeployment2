// Krista Appel
// Application Deployment 2: Android
// Score.java

package com.kristaappel.powerwordsreader.objects;


import java.io.Serializable;

public class Score implements Serializable{

    private final String score;
    private final String time;
    private final String color;


    public Score(String _score, String _time, String _color){
        this.score = _score;
        this.time = _time;
        this.color = _color;
    }


    public String getScore() {
        return score;
    }


    public String getTime() {
        return time;
    }

    public String getColor(){
        return color;
    }


}

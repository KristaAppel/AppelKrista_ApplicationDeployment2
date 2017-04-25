// Krista Appel
// Application Deployment 2: Android
// Score.java

package com.kristaappel.powerwordsreader.objects;


import java.io.Serializable;

public class Score implements Serializable{

    private final String score;
    private final String time;


    public Score(String _score, String _time){
        this.score = _score;
        this.time = _time;
    }


    public String getScore() {
        return score;
    }


    public String getTime() {
        return time;
    }


}

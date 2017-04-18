// Krista Appel
// Application Deployment 2: Android
// PowerWordFrag.java

package com.kristaappel.powerwordsreader.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kristaappel.powerwordsreader.R;


public class PowerWordFrag extends Fragment implements View.OnClickListener {

    TextView textView_powerWord;
    ImageButton arrowButton;
    static String[] powerWords;
    int wordIndex = 0;

    public static PowerWordFrag newInstance(String[] _powerWords){
        powerWords = _powerWords;
        return new PowerWordFrag();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.power_word_frag, container, false);
    }


    @SuppressWarnings("ConstantConditions")
    @Override
    public void onResume() {
        super.onResume();
        try{
            textView_powerWord = (TextView) getView().findViewById(R.id.textView_power_word);
            arrowButton = (ImageButton) getView().findViewById(R.id.arrowButton);
            arrowButton.setOnClickListener(this);
            textView_powerWord.setText(powerWords[wordIndex]);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.arrowButton){
            // Show the next Power Word in the collection:
            if (wordIndex < powerWords.length-1){
                wordIndex ++;
                textView_powerWord.setText(powerWords[wordIndex]);
            }else{
                wordIndex = 0;
                textView_powerWord.setText(powerWords[wordIndex]);
            }
        }
    }
}

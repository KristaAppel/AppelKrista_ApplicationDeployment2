// Krista Appel
// Application Deployment 2: Android
// PowerWordFrag.java

package com.kristaappel.powerwordsreader.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.kristaappel.powerwordsreader.R;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class PowerWordFrag extends Fragment implements View.OnClickListener {

    TextView textView_powerWord;
    ImageButton arrowButton;
    ImageButton helpButton;
    ImageButton micButton;
    static String[] powerWords;
    int wordIndex = 0;
    private static final int REQUEST_CODE = 10101;

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
            textView_powerWord.setText(powerWords[wordIndex]);

            arrowButton = (ImageButton) getView().findViewById(R.id.arrowButton);
            arrowButton.setOnClickListener(this);

            helpButton = (ImageButton) getView().findViewById(R.id.helpButton);
            helpButton.setOnClickListener(this);

            micButton = (ImageButton) getView().findViewById(R.id.micButton);
            micButton.setOnClickListener(this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.arrowButton:
                // Show the next Power Word in the collection:
                if (wordIndex < powerWords.length-1){
                    wordIndex ++;
                    textView_powerWord.setText(powerWords[wordIndex]);
                }else{
                    wordIndex = 0;
                    textView_powerWord.setText(powerWords[wordIndex]);
                }
                break;
            case R.id.helpButton:
                Log.i("TAG", "use text to speech");
                break;
            case R.id.micButton:
                Log.i("TAG", "use speech recognition");
                PackageManager packageManager = getActivity().getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
                if (activities.size() == 0){
                    micButton.setEnabled(false);
                    Toast.makeText(getActivity(), "Voice Recognition Not Available", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass().getPackage().getName());
                    intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Listening...");
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
                    startActivityForResult(intent, REQUEST_CODE);
                }

                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            ArrayList<String> resultsList = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String result = String.valueOf(resultsList.get(0));

            resultsList = checkForHomophones(result, resultsList);


            Log.i("TAG", "result:" + result);
            Log.i("TAG", "resultList: " + resultsList);
            if (resultsList.contains(textView_powerWord.getText())){
                Log.i("TAG", "it matches");
            }else{
                Log.i("TAG", "does not match");
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private ArrayList<String> checkForHomophones(String result, ArrayList<String> resultsList){
        // Speech recognition is a little buggy.  These will help:
        switch (result){
            case "B":
                resultsList.add("be");
                break;
            case "m":
                resultsList.add("am");
                break;
            case "4":
                resultsList.add("for");
                break;
            case "I":
                resultsList.add("eye");
                break;
            case "two":
                resultsList.add("to");
                resultsList.add("too");
                break;
            case "bye":
                resultsList.add("by");
                resultsList.add("buy");
                break;
            case "sent":
                resultsList.add("cent");
                break;
            case "hi":
                resultsList.add("high");
                break;
            case "Ben":
                resultsList.add("been");
                break;
            case "doctor":
                resultsList.add("Dr.");
                break;
            case "our":
                resultsList.add("hour");
                break;
            case "new":
                resultsList.add("knew");
                break;
            case "mister":
                resultsList.add("Mr.");
                break;
            case "mrs.":
                resultsList.add("Mrs.");
                break;
            case "Miss":
                resultsList.add("Ms.");
                break;
            case "o clock":
                resultsList.add("o'clock");
                break;
            case "peace":
                resultsList.add("piece");
                break;
            case "way":
                resultsList.add("weigh");
        }
        return resultsList;
    }

}

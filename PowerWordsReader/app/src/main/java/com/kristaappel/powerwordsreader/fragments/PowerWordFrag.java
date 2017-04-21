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
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.kristaappel.powerwordsreader.HomophoneChecker;
import com.kristaappel.powerwordsreader.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;


public class PowerWordFrag extends Fragment implements View.OnClickListener {

    private TextView textView_powerWord;
    private ImageButton micButton;
    private static String[] powerWords;
    private int wordIndex = 0;
    private static final int REQUEST_CODE = 10101;
    private TextToSpeech textToSpeech;


    public static PowerWordFrag newInstance(String[] _powerWords){
        powerWords = _powerWords;
        return new PowerWordFrag();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.power_word_frag, container, false);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textToSpeech = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i != TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });
    }


    @SuppressWarnings("ConstantConditions")
    @Override
    public void onResume() {
        super.onResume();
        try{
            textView_powerWord = (TextView) getView().findViewById(R.id.textView_power_word);
            textView_powerWord.setText(powerWords[wordIndex]);

            ImageButton nextButton = (ImageButton) getView().findViewById(R.id.nextButton);
            nextButton.setOnClickListener(this);

            ImageButton previousButton = (ImageButton) getView().findViewById(R.id.previousButton);
            previousButton.setOnClickListener(this);

            ImageButton helpButton = (ImageButton) getView().findViewById(R.id.helpButton);
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
            case R.id.nextButton:
                // Show the next Power Word in the collection:
                if (wordIndex < powerWords.length-1){
                    wordIndex ++;
                    textView_powerWord.setText(powerWords[wordIndex]);
                }else{
                    wordIndex = 0;
                    textView_powerWord.setText(powerWords[wordIndex]);
                }
                break;

            case R.id.previousButton:
                // Show the previous Power Word in the collection:
                if (wordIndex > 0){
                    wordIndex --;
                    textView_powerWord.setText(powerWords[wordIndex]);
                }else{
                    wordIndex = powerWords.length-1;
                    textView_powerWord.setText(powerWords[wordIndex]);
                }
                break;

            case R.id.helpButton:
                // Make the app "speak" the current Power Word:
                textToSpeech.speak(textView_powerWord.getText(), TextToSpeech.QUEUE_FLUSH, null, null);
                break;

            case R.id.micButton:
                // Activate speech recognition:
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

            resultsList = HomophoneChecker.checkForHomophones(result, resultsList);

            Log.i("PowerWordFrag", "result:" + result);
            Log.i("PowerWordFrag", "resultList: " + resultsList);
            if (resultsList.contains(textView_powerWord.getText().toString())){
                String correct = "Correct!";
                textToSpeech.speak(correct, TextToSpeech.QUEUE_FLUSH, null, null);
            }else{
                String incorrect = "Try again!";
                textToSpeech.speak(incorrect, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onStop() {
        if (textToSpeech != null){
            textToSpeech.shutdown();
        }
        super.onStop();
    }


}

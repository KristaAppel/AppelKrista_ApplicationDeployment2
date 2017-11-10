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

import com.kristaappel.powerwordsreader.objects.HomophoneChecker;
import com.kristaappel.powerwordsreader.R;
import com.kristaappel.powerwordsreader.objects.FileUtil;
import com.kristaappel.powerwordsreader.objects.Score;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;
import static com.kristaappel.powerwordsreader.R.id.nextButton;
import static com.kristaappel.powerwordsreader.R.id.textView;
import static com.kristaappel.powerwordsreader.R.id.textView_power_word_progress;


public class PowerWordFrag extends Fragment implements View.OnClickListener, TextToSpeech.OnInitListener {

    private TextView textView_powerWord;
    private TextView textView_powerWordProgress;
    private ImageButton micButton;
    private static String[] powerWords;
    private int wordIndex = 0;
    private static final int REQUEST_CODE = 10101;
    private TextToSpeech textToSpeech;
    private int numberOfAttempts = 0;
    private int numberCorrect = 0;
    private static String color = "";
    private static String level = "";


    public static PowerWordFrag newInstance(String[] _powerWords, String _color, String _level){
        powerWords = _powerWords;
        color = _color;
        level = _level;
        return new PowerWordFrag();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.power_word_frag, container, false);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textToSpeech = new TextToSpeech(getActivity(), this);
    }


    @SuppressWarnings("ConstantConditions")
    @Override
    public void onResume() {
        super.onResume();
        try{
            textView_powerWord = (TextView) getView().findViewById(R.id.textView_power_word);
            showWord();

            textView_powerWordProgress = (TextView) getView().findViewById(R.id.textView_power_word_progress);
            showProgress();

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

    private void showProgress(){
        String progressString = wordIndex+1 + "/" + powerWords.length;
        textView_powerWordProgress.setText(progressString);
    }

    private void showWord(){
        textView_powerWord.setText(powerWords[wordIndex]);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case nextButton:
                // Show the next Power Word in the collection:
                if (wordIndex < powerWords.length-1){
                    wordIndex ++;
                }else{
                    wordIndex = 0;
                }
                showWord();
                showProgress();
                break;

            case R.id.previousButton:
                // Show the previous Power Word in the collection:
                if (wordIndex > 0){
                    wordIndex --;
                }else{
                    wordIndex = powerWords.length-1;
                }
                showWord();
                showProgress();
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
                    Toast.makeText(getActivity(), R.string.voice_recognition_not_available, Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass().getPackage().getName());
                    intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.listening));
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

            numberOfAttempts ++;

            // Check if the user said the right word:
            if (resultsList.contains(textView_powerWord.getText().toString())){
                numberCorrect ++;
                String correct = getString(R.string.correct);
                textToSpeech.speak(correct, TextToSpeech.QUEUE_FLUSH, null, null);
            }else{
                String incorrect = getString(R.string.try_again);
                textToSpeech.speak(incorrect, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onStop() {
        // Calculate the score:
        if (numberOfAttempts != 0){
            int score = Math.round(numberCorrect * 100/numberOfAttempts);
            String scoreString = score + "%";
            String scoreNumbersString = numberCorrect + "/" + numberOfAttempts;
            // Get the current date/time:
            String time = new SimpleDateFormat("MM/dd/yyyy \nhh:mm a", Locale.US).format(new Date());
            // Create and save new Score object:
            Score newScore = new Score(scoreString, scoreNumbersString, time, color, level);
            ArrayList<Score> scores = FileUtil.read(getActivity());
            scores.add(newScore);
            FileUtil.write(getActivity(), scores);
        }
        

        if (textToSpeech != null){
            textToSpeech.shutdown();
        }
        super.onStop();
    }


    @Override
    public void onInit(int status) {
        if (status != TextToSpeech.ERROR){
            textToSpeech.setLanguage(Locale.ENGLISH);
        }
    }
}

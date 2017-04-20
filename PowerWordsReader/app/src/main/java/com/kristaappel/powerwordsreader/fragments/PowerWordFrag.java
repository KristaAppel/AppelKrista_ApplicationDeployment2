// Krista Appel
// Application Deployment 2: Android
// PowerWordFrag.java

package com.kristaappel.powerwordsreader.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.Image;
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

import com.kristaappel.powerwordsreader.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;


public class PowerWordFrag extends Fragment implements View.OnClickListener {

    TextView textView_powerWord;
    ImageButton nextButton;
    ImageButton helpButton;
    ImageButton micButton;
    ImageButton previousButton;
    static String[] powerWords;
    int wordIndex = 0;
    private static final int REQUEST_CODE = 10101;
    private TextToSpeech textToSpeech;
    String correct = "Correct!";
    String incorrect = "Try again!";


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

            nextButton = (ImageButton) getView().findViewById(R.id.nextButton);
            nextButton.setOnClickListener(this);

            previousButton = (ImageButton) getView().findViewById(R.id.previousButton);
            previousButton.setOnClickListener(this);

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

            resultsList = checkForHomophones(result, resultsList);

            Log.i("PowerWordFrag", "result:" + result);
            Log.i("PowerWordFrag", "resultList: " + resultsList);
            if (resultsList.contains(textView_powerWord.getText())){
                textToSpeech.speak(correct, TextToSpeech.QUEUE_FLUSH, null, null);
            }else{
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


    private ArrayList<String> checkForHomophones(String result, ArrayList<String> resultsList){
        // Speech recognition is a little buggy.  These will help:
        switch (result){
            case "B":
                resultsList.add("be");
                break;
            case "m": case "AM":
                resultsList.add("am");
                break;
            case "Anne":
                resultsList.add("an");
                break;
            case "4":
                resultsList.add("for");
                break;
            case "1":
                resultsList.add("one");
                break;
            case "C":
                resultsList.add("see");
                break;
            case "their":
                resultsList.add("there");
                resultsList.add("they're");
                break;
            case "cant":
                resultsList.add("can't");
                break;
            case "c**":
                resultsList.add("come");
                break;
            case "no":
                resultsList.add("know");
                break;
            case "He":
                resultsList.add("he");
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
            case "bored":
                resultsList.add("board");
                break;
            case "City":
                resultsList.add("city");
                break;
            case "close":
                resultsList.add("clothes");
                break;
            case "Giant":
                resultsList.add("giant");
                break;
            case "Library":
                resultsList.add("library");
                break;
            case "Magic":
                resultsList.add("magic");
                break;
            case "Moon":
                resultsList.add("moon");
                break;
            case "Mountain":
                resultsList.add("mountain");
                break;
            case "mr.":
                resultsList.add("Mr.");
                break;
            case "Secret":
                resultsList.add("secret");
                break;
            case "Sal":case "shell":
                resultsList.add("shall");
                break;
            case "stud":
                resultsList.add("stood");
                break;
            case "Wild":
                resultsList.add("wild");
                break;
            case "Beauty":
                resultsList.add("beauty");
                break;
            case "Bowl":
                resultsList.add("bowl");
                break;
            case "come":case "call":
                resultsList.add("calm");
                resultsList.add("comb");
                break;
            case "Danger":
                resultsList.add("danger");
                break;
            case "Escape":
                resultsList.add("escape");
                break;
            case "Island":
                resultsList.add("island");
                break;
            case "mint":
                resultsList.add("meant");
                break;
            case "Pleasant":
                resultsList.add("pleasant");
                break;
            case "too":
                resultsList.add("to");
                resultsList.add("two");
                break;
            case "Wii":
                resultsList.add("we");
                break;
            case "pool":
                resultsList.add("pull");
                break;
            case "School":
                resultsList.add("school");
                break;
            case "there":
                resultsList.add("their");
                resultsList.add("they're");
                break;
            case "Wok":
                resultsList.add("walk");
                break;
            case "who's":
                resultsList.add("whose");
                break;
            case "right":
                resultsList.add("write");
                break;
            case "Carrie":
                resultsList.add("carry");
                break;
            case "Earth":
                resultsList.add("earth");
                break;
            case "sore":
                resultsList.add("sure");
                break;
            case "Young":
                resultsList.add("young");
                break;
            case "y":
                resultsList.add("why");
                break;
            case "Boy":
                resultsList.add("boy");
                break;
            case "butt":
                resultsList.add("but");
                break;
            case "buy":
                resultsList.add("by");
                resultsList.add("bye");
                break;
            case "by":
                resultsList.add("bye");
                resultsList.add("buy");
                break;
            case "wood":
                resultsList.add("would");
                break;
            case "your":
                resultsList.add("you're");
                break;
            case "doctor":
                resultsList.add("Dr.");
                break;
            case "our":
                resultsList.add("hour");
                resultsList.add("are");
                break;
            case "aight":
                resultsList.add("I");
                resultsList.add("eye");
                break;
            case "Inn":
                resultsList.add("in");
                break;
            case "Of":
                resultsList.add("of");
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
                break;
        }
        return resultsList;
    }

}

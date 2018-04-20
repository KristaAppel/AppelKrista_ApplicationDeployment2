// Krista Appel
// Application Deployment 2: Android
// PowerWordActivity.java

package com.kristaappel.powerwordsreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.kristaappel.powerwordsreader.fragments.MainListFrag;
import com.kristaappel.powerwordsreader.fragments.PowerWordFrag;


public class PowerWordActivity extends AppCompatActivity {

    private String[] powerWords;
    private String color = "";
    private String level = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_word);

        int levelIndex = getIntent().getIntExtra(MainListFrag.EXTRA_IRLA_LEVEL_INDEX, 0);
        // This index tells us the index of the irla levels collection that was selected
        // Use the index to get the name of the IRLA Level:
        String irlaLevel = getResources().getStringArray(R.array.irla_levels)[levelIndex];

        // Set activity label :
        setTitle(irlaLevel);

        View view = findViewById(R.id.power_word_view);

        // Set the appropriate background color to match the chosen IRLA level:
        // Get the collection of power words for the chosen IRLA level:
        switch (levelIndex){
            case 0:
                view.setBackgroundResource(R.color.myGreen);
                powerWords = getResources().getStringArray(R.array.power_words_1g);
                level = "1G";
                color = "green";
                break;
            case 1:
                view.setBackgroundResource(R.color.myGreen);
                powerWords = getResources().getStringArray(R.array.power_words_2g);
                level = "2G";
                color="green";
                break;
            case 2:
                view.setBackgroundResource(R.color.myBlue);
                powerWords = getResources().getStringArray(R.array.power_words_1b);
                level = "1B";
                color="blue";
                break;
            case 3:
                view.setBackgroundResource(R.color.myBlue);
                powerWords = getResources().getStringArray(R.array.power_words_2b);
                level = "2B";
                color="blue";
                break;
            case 4:
                view.setBackgroundResource(R.color.myRed);
                powerWords = getResources().getStringArray(R.array.power_words_1r);
                level = "1R";
                color = "red";
                break;
            case 5:
                view.setBackgroundResource(R.color.myRed);
                powerWords = getResources().getStringArray(R.array.power_words_2r);
                level = "2R";
                color="red";
                break;
            case 6:
                view.setBackgroundResource(R.color.myWhite);
                powerWords = getResources().getStringArray(R.array.power_words_wt);
                level = "Wt";
                color="white";
                break;
        }

        // Create and display a PowerWordFrag:
        PowerWordFrag powerWordFrag = PowerWordFrag.newInstance(powerWords, color, level);
        getFragmentManager().beginTransaction().replace(R.id.power_word_frame, powerWordFrag).commit();

    }


}

package com.kristaappel.powerwordsreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.kristaappel.powerwordsreader.fragments.MainListFrag;


public class PowerWordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_word);

        int index = getIntent().getIntExtra(MainListFrag.EXTRA_IRLA_LEVEL_INDEX, 0);
        // TODO - This index tells us the index of the irla levels collection that was selected
        // TODO - use the index to get the appropriate collection of power words
        Log.i("PowerWordActivity", "the index is: " + index);
        Log.i("PowerWordActivity", "You chose: " + getResources().getStringArray(R.array.irla_levels)[index]);

        // Set activity label :
        setTitle(getResources().getStringArray(R.array.irla_levels)[index]);
    }


}

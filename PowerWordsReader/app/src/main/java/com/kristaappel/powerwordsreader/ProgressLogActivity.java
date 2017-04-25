// Krista Appel
// Application Deployment 2: Android
// ProgressLogActivity.java

package com.kristaappel.powerwordsreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kristaappel.powerwordsreader.fragments.LogListFrag;

public class ProgressLogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_log);

        LogListFrag logListFrag = LogListFrag.newInstance();
        getFragmentManager().beginTransaction().replace(R.id.log_frame, logListFrag).commit();
    }


}

// Krista Appel
// Application Deployment 2: Android
// ListActivity.java

package com.kristaappel.powerwordsreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.kristaappel.powerwordsreader.fragments.ListFrag;


public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListFrag listFragment = ListFrag.newInstance();
        getFragmentManager().beginTransaction().replace(R.id.list_frame, listFragment).commit();
    }


}

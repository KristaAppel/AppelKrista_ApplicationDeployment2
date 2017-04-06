// Krista Appel
// Application Deployment 2: Android
// ListActivity.java

package com.kristaappel.powerwordsreader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.kristaappel.powerwordsreader.fragments.MainListFrag;


public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        MainListFrag listFragment = MainListFrag.newInstance();
        getFragmentManager().beginTransaction().replace(R.id.list_frame, listFragment).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_screen, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item_progress_log){
            Intent progressIntent = new Intent(this, ProgressLogActivity.class);
            startActivity(progressIntent);
        }
        return true;
    }

    
}

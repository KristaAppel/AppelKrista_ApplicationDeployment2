// Krista Appel
// Application Deployment 2: Android
// MainListFrag.java

package com.kristaappel.powerwordsreader.fragments;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.kristaappel.powerwordsreader.PowerWordActivity;
import com.kristaappel.powerwordsreader.R;


public class MainListFrag extends ListFragment {

    private static final int ID_CONSTANT = 0x01010;
    public static final String EXTRA_IRLA_LEVEL_INDEX = "EXTRA_IRLA_LEVEL_INDEX";
    private String[] level_names;
    private String[] level_abbrevs;


    public static MainListFrag newInstance(){
        return new MainListFrag();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        level_names = getResources().getStringArray(R.array.irla_levels);
        level_abbrevs = getResources().getStringArray(R.array.irla_levels_abbrev);
        Log.i("MainListFrag", "length: " + getResources().getStringArray(R.array.power_words_2r).length);
        Log.i("MainListFrag", "" + getResources().getStringArray(R.array.power_words_2r)[10]);
        setListAdapter(new ListAdapter());
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent powerWordScreenIntent = new Intent(getActivity(), PowerWordActivity.class);
        powerWordScreenIntent.putExtra(EXTRA_IRLA_LEVEL_INDEX, position);
        startActivity(powerWordScreenIntent);
    }


    private class ListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return level_names.length;
        }


        @Override
        public Object getItem(int position) {
            return level_names[position];
        }


        @Override
        public long getItemId(int position) {
            return ID_CONSTANT + position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            }

            TextView textAbbrev = (TextView) convertView.findViewById(R.id.textview_list_item_colored_square);
            TextView textTitle = (TextView) convertView.findViewById(R.id.textView_list_item_title);

            // Set the proper background color for each level:
            switch (position){
                case 0:case 1:
                    textAbbrev.setBackgroundResource(R.color.myGreen);
                    break;
                case 2:case 3:
                    textAbbrev.setBackgroundResource(R.color.myBlue);
                    break;
                case 4:case 5:
                    textAbbrev.setBackgroundResource(R.color.myRed);
                    break;
            }

            // Set text:
            textAbbrev.setText(level_abbrevs[position]);
            textTitle.setText(level_names[position]);

            return convertView;
        }


    }


}

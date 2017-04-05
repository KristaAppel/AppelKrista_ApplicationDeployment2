// Krista Appel
// Application Deployment 2: Android
// ListFrag.java

package com.kristaappel.powerwordsreader.fragments;

import android.app.ListFragment;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.kristaappel.powerwordsreader.R;


public class ListFrag extends ListFragment {

    private static final int ID_CONSTANT = 0x01010;
    private String[] level_names;
    private String[] level_abbrevs;


    public static ListFrag newInstance(){
        return new ListFrag();
    }

    
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        level_names = getResources().getStringArray(R.array.irla_levels);
        level_abbrevs = getResources().getStringArray(R.array.irla_levels_abbrev);
        Log.i("ListFrag", "length: " + getResources().getStringArray(R.array.power_words_2r).length);
        Log.i("ListFrag", "" + getResources().getStringArray(R.array.power_words_2r)[10]);
        setListAdapter(new ListAdapter());
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
                    textAbbrev.setBackgroundColor(getResources().getColor(R.color.myGreen));
                    break;
                case 2:case 3:
                    textAbbrev.setBackgroundColor(getResources().getColor(R.color.myBlue));
                    break;
                case 4:case 5:
                    textAbbrev.setBackgroundColor(getResources().getColor(R.color.myRed));
                    break;
            }

            // Set text:
            textAbbrev.setText(level_abbrevs[position]);
            textTitle.setText(level_names[position]);

            return convertView;
        }


    }


}

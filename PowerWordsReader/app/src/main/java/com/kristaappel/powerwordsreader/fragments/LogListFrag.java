// Krista Appel
// Application Deployment 2: Android
// LogListFrag.java

package com.kristaappel.powerwordsreader.fragments;

import android.app.ListFragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.kristaappel.powerwordsreader.R;
import com.kristaappel.powerwordsreader.objects.FileUtil;
import com.kristaappel.powerwordsreader.objects.Score;

import java.util.ArrayList;


public class LogListFrag extends ListFragment {

    private ArrayList<Score> scores;
    private static final int ID_CONSTANT = 0x02020;


    public static LogListFrag newInstance(){
        return new LogListFrag();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        scores = FileUtil.read(getActivity());
        setListAdapter(new ListAdapter());
    }


    private class ListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return scores.size();
        }

        @Override
        public Object getItem(int position) {
            return scores.get(position);
        }

        @Override
        public long getItemId(int position) {
            return ID_CONSTANT + position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.log_list_item, parent, false);
            }

            TextView textDate = (TextView) convertView.findViewById(R.id.textView_log_date);
            TextView textAccuracy = (TextView) convertView.findViewById(R.id.textView_log_accuracy);
            TextView levelColorSquare = (TextView) convertView.findViewById(R.id.textView_log_color);

            // Display the dates and scores in reverse for chronological order:
            textDate.setText(scores.get(getCount() -1 - position).getTime());
            levelColorSquare.setText(scores.get(getCount() -1 -position).getLevel());
            textAccuracy.setText(scores.get(getCount() -1 - position).getScore() + "\n" + scores.get(getCount() -1 -position).getScoreNumbers());

            // Display the level color:
            String color = scores.get(getCount() -1 - position).getColor();
            if (color.equals("green")){
                levelColorSquare.setBackgroundResource(R.color.myGreen);
            }else if (color.equals("blue")){
                levelColorSquare.setBackgroundResource(R.color.myBlue);
            }else if (color.equals("red")){
                levelColorSquare.setBackgroundResource(R.color.myRed);
            }


            return convertView;
        }
    }


}

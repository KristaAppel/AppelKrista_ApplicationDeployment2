// Krista Appel
// Application Deployment 2: Android
// FileUtil.java

package com.kristaappel.powerwordsreader.objects;


import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileUtil {


    public static void write(Context context, ArrayList<Score> scores) {
        try {
            // Save the arraylist to local storage:
            FileOutputStream fileOutputStream = context.openFileOutput("scores.txt", Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(scores);
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<Score> read(Context context) {
        ArrayList<Score> scores = new ArrayList<>();
        try {
            // Read from local storage:
            FileInputStream fileInputStream = context.openFileInput("scores.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object readObject = objectInputStream.readObject();

            // Get objects; add them to arraylist to be returned:
            if (readObject instanceof ArrayList<?>) {
                ArrayList<?> arrayList = (ArrayList<?>) readObject;
                if (arrayList.size() > 0) {
                    for (int i = 0; i < arrayList.size(); i++) {
                        Object arrayListItem = arrayList.get(i);
                        if (arrayListItem instanceof Score) {
                            Score person = (Score) arrayListItem;
                            scores.add(person);
                        }
                    }
                }
            }
            objectInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scores;
    }


}

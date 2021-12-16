package com.jnu.myhomework.classpack;

import android.content.Context;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ShouruDataBank {
    private ArrayList<ShouruData> arrayListShouru = new ArrayList<>();
    private Context context;
    private final String Shouru_FILE_NAME = "ShouruData.txt";
    public ShouruDataBank(Context context){this. context = context;}
    public ArrayList<ShouruData> getShouruData(){return arrayListShouru;}

    public void Save()
    {
        ObjectOutputStream oos = null;
        try{
            oos = new ObjectOutputStream(context.openFileOutput(Shouru_FILE_NAME,Context.MODE_PRIVATE));
            oos.writeObject(arrayListShouru);
            oos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void Load()
    {
        ObjectInputStream ois = null;
        arrayListShouru = new ArrayList<>();
        try{
            ois = new ObjectInputStream(context.openFileInput(Shouru_FILE_NAME));
            arrayListShouru = (ArrayList<ShouruData>) ois.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

package com.jnu.myhomework.classpack;

import android.content.Context;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ZhichuDataBank {
    private ArrayList<ZhichuData> arrayListZhichu = new ArrayList<>();
    private Context context;
    private final String Zhichu_FILE_NAME = "ZhichuData.txt";
    public ZhichuDataBank(Context context){this. context = context;}
    public ArrayList<ZhichuData> getSuiLiData(){return arrayListZhichu;}

    public void Save()
    {
        ObjectOutputStream oos = null;
        try{
            oos = new ObjectOutputStream(context.openFileOutput(Zhichu_FILE_NAME,Context.MODE_PRIVATE));
            oos.writeObject(arrayListZhichu);
            oos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void Load()
    {
        ObjectInputStream ois = null;
        arrayListZhichu = new ArrayList<>();
        try{
            ois = new ObjectInputStream(context.openFileInput(Zhichu_FILE_NAME));
            arrayListZhichu = (ArrayList<ZhichuData>) ois.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

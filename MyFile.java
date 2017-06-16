package fileprac;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 조은지 on 2017-06-14.
 */
public class MyFile {

    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader("src/fileprac/myFile.dat");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String st = bufferedReader.readLine();
            ArrayList<MyKeyValue> list = new ArrayList<>();
            Gson gson = new Gson();
            list = gson.fromJson(st, new TypeToken<List<MyKeyValue>>(){}.getType());
            for(MyKeyValue value : list){
                System.out.println(value);
            }
            bufferedReader.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void writeJsonToFIle() {
        String s = buildJson();
        try {
            FileWriter file = new FileWriter("src/fileprac/myFile.dat");
            BufferedWriter bufferedWriter = new BufferedWriter(file);
            bufferedWriter.write("s"+s);

            bufferedWriter.close();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String buildJson() {
        ArrayList<MyKeyValue> myKeyValueArrayList = new ArrayList<>();
        Gson gson = new Gson();
        MyKeyValue myKeyValue = new MyKeyValue();
        myKeyValue.gradeSpeed = 15;
        myKeyValue.name = "조은지";
        myKeyValue.path = "adfsa";
        myKeyValue.ax = 10;
        myKeyValue.maxDx = 30;
        myKeyValue.brakingForce = 5;
        myKeyValue.percent = new HashMap<>();
        myKeyValue.percent.put("A", 15);
        myKeyValue.percent.put("B", 16);
        myKeyValue.percent.put("C", 17);
        myKeyValue.percent.put("D", 18);

        myKeyValueArrayList.add(myKeyValue);
        myKeyValueArrayList.add(myKeyValue);
        myKeyValueArrayList.add(myKeyValue);
        myKeyValueArrayList.add(myKeyValue);
        myKeyValueArrayList.add(myKeyValue);
        return gson.toJson(myKeyValueArrayList);

    }

    private static void fileWrite() {
        try {
            FileWriter fin = new FileWriter("myFile.dat");
            BufferedWriter bin = new BufferedWriter(fin);
            bin.write("hello java!");
            bin.close();
            fin.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fileRead() {
        try {
            FileReader fin = new FileReader("src/fileprac/myFile.dat");
            BufferedReader bin = new BufferedReader(fin);
            while (bin.ready()) {
                String a = bin.readLine();
                System.out.println(a);
            }
            bin.close();
            fin.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

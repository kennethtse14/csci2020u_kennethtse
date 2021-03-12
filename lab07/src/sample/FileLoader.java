package sample;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class FileLoader {
    private String filename;
    private static HashMap<String, Integer> map = new HashMap<String, Integer>();

    public FileLoader (String filename){
        this.filename = filename;
        this.map = map;
    }

    public void loadFile(){
        String line = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(this.filename));
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                System.out.println(columns[5]);
                if (map.containsKey(columns[5])){
                    int previous = map.get(columns[5]);
                    map.put(columns[5], previous + 1);
                }else{
                    map.put(columns[5], 1);
                }
            }

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static HashMap<String, Integer> getMap(){
        return map;
    }
}

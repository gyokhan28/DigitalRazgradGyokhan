package src;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class test {
    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("cityList.txt"));
        String line = "";
        String allCities = "";
        try{
            while((line=reader.readLine())!=null){
                allCities+=line;
                allCities+=";";
            }
        } catch (Exception e){
            System.out.println(e);
        }
        String[] cityList = allCities.split(";");
        for(int i=0;i<cityList.length;i++){
            System.out.println(cityList[i]);
        }
    }
}

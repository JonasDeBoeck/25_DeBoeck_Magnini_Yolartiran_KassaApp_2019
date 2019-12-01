package database;

import java.io.*;

import java.util.Properties;


public class PropertiesLoadSave {

    public static String load(String property){
        String db = "";
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("src/bestanden/properties.properties"));
            db = prop.getProperty(property);
        } catch (Exception e){
        }
        return db;
    }


    public static void save(String keuze, String property){
       Properties prop = new Properties();
       prop.setProperty(property, keuze);
       try{
           prop.store(new FileOutputStream("src/bestanden/properties.properties"), null);
       }catch (Exception e ){
       }
    }
}

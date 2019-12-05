package database;

import javafx.scene.control.Alert;

import java.io.*;

import java.util.Properties;


public class PropertiesLoadSave {

    public static String load(String property){
        String db = "";
        Properties prop = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("src/bestanden/properties.properties")){
            prop.load(fileInputStream);
            db = prop.getProperty(property);
        } catch (Exception e){
            Alert fout = new Alert(Alert.AlertType.ERROR);
            fout.setTitle("Fout bij het inlezen van properties");
            fout.setHeaderText("Het properties bestand kan niet worden ingelezen");
            fout.setContentText(e.getMessage());
            fout.showAndWait();
        }
        return db;
    }

    public static void save(String keuze, String property) {

        try {
            FileInputStream a =new FileInputStream("src/bestanden/properties.properties");
            Properties prop = new Properties();
            prop.load(a);
            a.close();

            FileOutputStream fileOutputStream = new FileOutputStream("src/bestanden/properties.properties");
            prop.setProperty(property, keuze);
            prop.store(fileOutputStream, null);
            fileOutputStream.close();

        }catch (Exception e ) {
            Alert fout = new Alert(Alert.AlertType.ERROR);
            fout.setTitle("Fout bij het wegschrijven naar properties");
            fout.setHeaderText("Er kan niet naar het properties bestand worden geschreven");
            fout.setContentText(e.getMessage());
            fout.showAndWait();
        }
    }

}

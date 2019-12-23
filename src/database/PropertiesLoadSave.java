package database;

import javafx.scene.control.Alert;

import java.io.*;

import java.util.Properties;

/**
 * @Author Thibault Magnini
 * */

public class PropertiesLoadSave {

    //Returned de value van de property met waarde 'property'
    public static String load(String property){
        String db = "";
        Properties prop = new Properties();
        try (InputStream fileInputStream = PropertiesLoadSave.class.getResourceAsStream("/bestanden/config.properties")){
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

    //Schrijft de value met waarde 'keuze' weg naar de property met waarde 'property'
    public static void save(String keuze, String property) {

        try {
            FileInputStream a = new FileInputStream("src/bestanden/config.properties");
            Properties prop = new Properties();
            prop.load(a);
            a.close();

            FileOutputStream fileOutputStream = new FileOutputStream("src/bestanden/config.properties");
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

    public static boolean propertyBestaat (String property) {
        return !PropertiesLoadSave.load(property).equals("");
    }
}

package database;

import database.strategy.loadSaveStrategy.LoadSaveStrategy;
import excel.ExcelPlugin;
import javafx.scene.control.Alert;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.Artikel;
import model.factory.ArtikelFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Jonas De Boeck, Sinan Yolartiran
 * */

public class ExcelAdapter implements LoadSaveStrategy {
    ExcelPlugin excelPlugin;

    public ExcelAdapter () {
        excelPlugin = new ExcelPlugin();
    }

    //Leest het excel bestand in
    @Override
    public Map<String, Artikel> load(String filename) {
        ArtikelFactory factory = ArtikelFactory.getInstance();
        Map<String, Artikel> artikels = new HashMap<>();
        try {
            List<ArrayList<String>> lijst = this.excelPlugin.read(convertToFileObject(filename));
            for (List<String> artikelLijst : lijst) {
                Artikel artikel = factory.createArtikel(artikelLijst.get(0), artikelLijst.get(1), convertToDouble(artikelLijst.get(3)), convertToInt(artikelLijst.get(4)), artikelLijst.get(2));
                artikels.put(artikel.getArtikelId(), artikel);
            }
        } catch (BiffException | IOException e) {
            Alert fout = new Alert(Alert.AlertType.ERROR);
            fout.setTitle("Fout bij het inlezen van excel bestand");
            fout.setHeaderText("Het opgegeven excel bestand kan niet worden gevonden");
            fout.setContentText(e.getMessage());
            fout.showAndWait();
        }
        return artikels;
    }

    //Schrijft een lijst van artikels weg naar het excel bestand
    @Override
    public void save(String filename, Map<String, Artikel> artikels) {
        try {
            ArrayList<ArrayList<String>> artikelsLijst = new ArrayList<>();
            for (Artikel artikel : artikels.values()) {
                artikelsLijst.add(artikel.lijstArtikel());
            }
            this.excelPlugin.write(convertToFileObject(filename), artikelsLijst);
        } catch (BiffException | IOException | WriteException e) {
            Alert fout = new Alert(Alert.AlertType.ERROR);
            fout.setTitle("Fout bij het wegschrijven naar excel bestand");
            fout.setHeaderText("Het opgegeven excel bestand kan niet worden gevonden");
            fout.setContentText(e.getMessage());
            fout.showAndWait();
        }
    }

    private File convertToFileObject (String filename) {
        return new File("src/bestanden/" + filename);
    }

    private int convertToInt(String integer) {
        return Integer.parseInt(integer);
    }

    private double convertToDouble (String doub) {
        return Double.parseDouble(doub);
    }
}

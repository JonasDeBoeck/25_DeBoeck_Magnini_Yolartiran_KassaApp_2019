package database;

import com.sun.deploy.net.socket.UnixDomainSocketException;
import excel.ExcelPlugin;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.Artikel;
import model.ArtikelFactory;

import java.io.File;
import java.io.IOException;
import java.security.DomainCombiner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelAdapter implements LoadSaveStrategy {
    ExcelPlugin excelPlugin;

    public ExcelAdapter () {
        excelPlugin = new ExcelPlugin();
    }

    @Override
    public Map<String, Artikel> load(String filename) {
        ArtikelFactory factory = ArtikelFactory.getInstance();
        try {
            List<ArrayList<String>> lijst = this.excelPlugin.read(convertToFileObject(filename));
            Map<String, Artikel> artikels = new HashMap<>();
            for (List<String> artikelLijst : lijst) {
                Artikel artikel = factory.createArtikel(artikelLijst.get(0), artikelLijst.get(1), convertToDouble(artikelLijst.get(3)), convertToInt(artikelLijst.get(4)), artikelLijst.get(2));
                artikels.put(artikel.getArtikelId(), artikel);
            }
            return artikels;
        } catch (BiffException | IOException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public void save(String filename, Map<String, Artikel> artikels) {
        try {
            ArrayList<ArrayList<String>> artikelsLijst = new ArrayList<>();
            for (Artikel artikel : artikels.values()) {
                artikelsLijst.add(artikel.lijstArtikel());
            }
            this.excelPlugin.write(convertToFileObject(filename), artikelsLijst);
        } catch (BiffException | IOException | WriteException e) {
            throw new DatabaseException(e.getMessage());
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

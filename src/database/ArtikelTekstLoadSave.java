package database;

import javafx.scene.control.Alert;
import model.Artikel;
import model.factory.ArtikelFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author Jonas De Boeck, Sinan Yolartiran
 * */

public class ArtikelTekstLoadSave extends TekstLoadSaveTemplate {

    //Leest de text file in
    @Override
    public Map<String, Artikel> read(String toRead) {
        HashMap<String, Artikel> artikels = new HashMap<>();
        ArtikelFactory artikelFactory = ArtikelFactory.getInstance();
        try (Scanner scanner = new Scanner(this.getClass().getResourceAsStream(toRead), "UTF-8")){
            while (scanner.hasNextLine()) {
                Scanner lijnScanner = new Scanner(scanner.nextLine());
                lijnScanner.useDelimiter(",");
                String artikelId = lijnScanner.next();
                String naam = lijnScanner.next();
                String artikelCategorie = lijnScanner.next();
                String prijsAlsString = lijnScanner.next();
                double prijs = Double.parseDouble(prijsAlsString.replace(". ", ","));
                String aantalInStockAlsString = lijnScanner.next();
                int aantalInStock = Integer.parseInt(aantalInStockAlsString);
                Artikel artikel = artikelFactory.createArtikel(artikelId, naam, prijs, aantalInStock, artikelCategorie);
                artikels.put(artikelId, artikel);
                lijnScanner.close();
            }
        } catch (Exception e) {
            Alert fout = new Alert(Alert.AlertType.ERROR);
            fout.setTitle("Fout bij het inlezen");
            fout.setHeaderText("Het opgegeven bestand kan niet worden gevonden");
            fout.setContentText(e.getMessage());
            fout.showAndWait();
        }
        return artikels;
    }

    //Schrijft de lijst weg naar een text file
    @Override
    public void write(File toWrite, Map<String, Artikel> artikels) {
        try (Writer w = new OutputStreamWriter(new FileOutputStream(toWrite), StandardCharsets.UTF_8); PrintWriter writer = new PrintWriter(w)) {
            for (Artikel artikel : artikels.values()) {
                writer.println(artikel.toString());
            }
        } catch (IOException e) {
            Alert fout = new Alert(Alert.AlertType.ERROR);
            fout.setTitle("Fout bij het wegschrijven");
            fout.setHeaderText("Het opgegeven bestand kan niet worden gevonden");
            fout.setContentText(e.getMessage());
            fout.showAndWait();
        }
    }
}

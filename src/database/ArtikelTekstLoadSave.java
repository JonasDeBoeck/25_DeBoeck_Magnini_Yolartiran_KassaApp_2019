package database;

import javafx.scene.control.Alert;
import model.Artikel;
import model.ArtikelFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ArtikelTekstLoadSave extends TekstLoadSaveTemplate{

    @Override
    public Map<String, Artikel> read(File toRead) {
        HashMap<String, Artikel> artikels = new HashMap<>();
        ArtikelFactory artikelFactory = ArtikelFactory.getInstance();
        try (Scanner scanner = new Scanner(toRead)){
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
        } catch (FileNotFoundException e) {
            Alert fout = new Alert(Alert.AlertType.ERROR);
            fout.setTitle("Fout bij het inlezen");
            fout.setHeaderText("Het opgegeven bestand kan niet worden gevonden");
            fout.setContentText(e.getMessage());
            fout.showAndWait();
        }
        return artikels;
    }

    @Override
    public void write(File toWrite, Map<String, Artikel> artikels) {
        try (PrintWriter writer = new PrintWriter(toWrite)) {
            for (Artikel artikel : artikels.values()) {
                writer.println(artikel.toString());
            }
        } catch (FileNotFoundException e) {
            Alert fout = new Alert(Alert.AlertType.ERROR);
            fout.setTitle("Fout bij het wegschrijven");
            fout.setHeaderText("Het opgegeven bestand kan niet worden gevonden");
            fout.setContentText(e.getMessage());
            fout.showAndWait();
        }
    }
}

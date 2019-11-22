package database;

import model.Artikel;
import model.ArtikelFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ArtikelDbInMemory {
    public static HashMap<String, Artikel> load(String fileNaam) {
        HashMap<String, Artikel> artikels = new HashMap<>();
        File file = new File("src/bestanden/" + fileNaam);
        ArtikelFactory artikelFactory = ArtikelFactory.getInstance();
        try (Scanner scanner = new Scanner(file)){
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
            System.out.println(e.getMessage());
        }
        return artikels;
    }

    public static void save(String fileNaam, Map<String, Artikel> artikels) {
        File file = new File("src/bestanden/" + fileNaam);
        try (PrintWriter writer = new PrintWriter(file);) {
            for (Artikel artikel : artikels.values()) {
                writer.println(artikel.toString());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}

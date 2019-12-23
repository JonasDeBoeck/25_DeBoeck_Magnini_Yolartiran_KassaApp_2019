package model.factory;

import model.Artikel;

/**
 * @Author Jonas De Boeck
 * */

public class ArtikelFactory {
    private static ArtikelFactory uniqueFactory;

    private ArtikelFactory() {}

    public static ArtikelFactory getInstance() {
        if (uniqueFactory == null) {
            uniqueFactory = new ArtikelFactory();
        }
        return uniqueFactory;
    }

    public Artikel createArtikel(String artikelId, String naam, double prijs, int aantalInStock, String artikelCategorie) {
        Artikel artikel;
        artikel = new Artikel(artikelId, naam, prijs, aantalInStock, artikelCategorie);
        return artikel;
    }
}

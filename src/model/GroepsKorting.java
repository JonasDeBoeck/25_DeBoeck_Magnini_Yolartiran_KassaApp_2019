package model;

import database.PropertiesLoadSave;

import java.util.List;

public class GroepsKorting implements KortingStrategy {

    private ArtikelCategorie categorie;
    private int aantal;
    private String type;

    public GroepsKorting () {
        String cat = PropertiesLoadSave.load("CATEGORIE");
        setCategorie(ArtikelCategorie.valueOf(cat.toUpperCase()));
        setAantal(Integer.parseInt((PropertiesLoadSave.load("AANTAL"))));
        setType(PropertiesLoadSave.load("TYPE"));
    }

    public double berekenPrijs(List<Artikel> lijst){
        double prijs = 0;
         switch (this.type) {
             case "Euro":
                 prijs = berekenEuro(lijst);
                 break;
             case "Percentage":
                 prijs = berekenProcent(lijst);
         }
         return prijs;
    }

    private double berekenProcent (List<Artikel> lijst) {
        for (Artikel a : lijst) {

        }
    }

    private double berekenEuro (List<Artikel> lijst) {
        for (Artikel a : lijst) {

        }
    }

    private void setCategorie(ArtikelCategorie categorie) {
        this.categorie = categorie;
    }

    private void setType(String type) {
        this.type = type;
    }

    private void setAantal(int aantal) {
        this.aantal = aantal;
    }
}

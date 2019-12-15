package model.kortingStrategy;

import database.PropertiesLoadSave;
import model.Artikel;
import model.ArtikelCategorie;

import java.util.List;

public class GroepsKorting implements KortingStrategy {

    private ArtikelCategorie categorie;
    private int aantal;
    private String type;

    public GroepsKorting () {
        String cat = PropertiesLoadSave.load("CATEGORIE");
        setCategorie(ArtikelCategorie.valueOf(cat.toUpperCase()));
        setAantal(Integer.parseInt((PropertiesLoadSave.load("GETAL"))));
        setType(PropertiesLoadSave.load("TYPE"));
    }

    //Delegeert de te berekenen korting door naar de juiste methode voor een bepaald type
    public void berekenPrijs(List<Artikel> lijst){
         switch (this.type) {
             case "Euro":
                 berekenEuro(lijst);
                 break;
             case "Percentage":
                 berekenProcent(lijst);
                 break;
             default:
                 berekenEuro(lijst);
         }
    }

    private void berekenProcent (List<Artikel> lijst) {
        for (Artikel a : lijst) {
            if (a.getArtikelCategorie().getArtikelCategorieAlsString().equals(PropertiesLoadSave.load("CATEGORIE").toLowerCase())) {
                double korting = a.getPrijs() - ((a.getPrijs() / 100) * Integer.parseInt(PropertiesLoadSave.load("GETAL")));
                if (a.getPrijs() > korting) {
                    a.setKorting(korting);
                } else {
                    a.setKorting(a.getPrijs());
                }
            }
        }
    }

    private void berekenEuro (List<Artikel> lijst) {
        for (Artikel a : lijst) {
            if (a.getArtikelCategorie().getArtikelCategorieAlsString().equals(PropertiesLoadSave.load("CATEGORIE").toLowerCase())) {
                if (a.getPrijs() > Integer.parseInt(PropertiesLoadSave.load("GETAL"))) {
                    a.setKorting(Integer.parseInt(PropertiesLoadSave.load("GETAL")));
                } else {
                    a.setKorting(a.getPrijs());
                }
            }
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

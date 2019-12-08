package model;

import database.PropertiesLoadSave;

import java.util.List;

public class DuursteKorting implements KortingStrategy {

    private ArtikelCategorie categorie;
    private int aantal;
    private String type;

    public DuursteKorting() {
        String cat = PropertiesLoadSave.load("CATEGORIE");
        setCategorie(ArtikelCategorie.valueOf(cat.toUpperCase()));
        setAantal(Integer.parseInt((PropertiesLoadSave.load("GETAL"))));
        setType(PropertiesLoadSave.load("TYPE"));
    }

    public void berekenPrijs(List<Artikel> lijst){
        switch (this.type) {
            case "Euro":
                berekenEuro(lijst);
                break;
            case "Percentage":
                berekenProcent(lijst);
        }
    }

    private void berekenProcent (List<Artikel> lijst) {
        Artikel duurste = lijst.get(0);
        for (Artikel a : lijst) {
            if (a.getPrijs() > duurste.getPrijs()) {
                duurste = a;
            }
        }
        duurste.setKorting(duurste.getPrijs() - ((duurste.getPrijs()/100)*Integer.parseInt(PropertiesLoadSave.load("GETAL"))));
    }

    private void berekenEuro (List<Artikel> lijst) {
        Artikel duurste = lijst.get(0);
        for (Artikel a : lijst) {
            if (a.getPrijs() > duurste.getPrijs()) {
                duurste = a;
            }
        }
        duurste.setKorting(Integer.parseInt(PropertiesLoadSave.load("GETAL")));
    }

    public void setCategorie(ArtikelCategorie categorie) {
        this.categorie = categorie;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }

    public void setType(String type) {
        this.type = type;
    }
}

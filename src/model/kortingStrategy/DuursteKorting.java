package model.kortingStrategy;

import database.PropertiesLoadSave;
import model.Artikel;

import java.util.List;

public class DuursteKorting implements KortingStrategy {

    //private ArtikelCategorie categorie;
    private int aantal;
    private String type;

    public DuursteKorting() {
        //String cat = PropertiesLoadSave.load("CATEGORIE");
        //setCategorie(ArtikelCategorie.valueOf(cat.toUpperCase()));
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
                break;
            default:
                berekenEuro(lijst);
        }
    }

    private void berekenProcent (List<Artikel> lijst) {
        Artikel duurste = lijst.get(0);
        for (Artikel a : lijst) {
            if (a.getPrijs() > duurste.getPrijs()) {
                duurste = a;
            }
        }
        double korting = duurste.getPrijs() - ((duurste.getPrijs()/100)*Integer.parseInt(PropertiesLoadSave.load("GETAL")));
        if (duurste.getPrijs() > korting) {
            duurste.setKorting(korting);
        } else {
            duurste.setKorting(duurste.getPrijs());
        }
    }

    private void berekenEuro (List<Artikel> lijst) {
        Artikel duurste = lijst.get(0);
        for (Artikel a : lijst) {
            if (a.getPrijs() > duurste.getPrijs()) {
                duurste = a;
            }
        }
        if (duurste.getPrijs() > Integer.parseInt(PropertiesLoadSave.load("GETAL"))) {
            duurste.setKorting(Integer.parseInt(PropertiesLoadSave.load("GETAL")));
        } else {
            duurste.setKorting(duurste.getPrijs());
        }
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }

    public void setType(String type) {
        this.type = type;
    }
}

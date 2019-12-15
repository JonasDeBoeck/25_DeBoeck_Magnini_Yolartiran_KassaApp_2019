package model.kortingStrategy;

import database.PropertiesLoadSave;
import model.Artikel;

import java.util.List;

public class DrempelKorting implements KortingStrategy {

    private int aantal;
    private String type;

    public DrempelKorting () {
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
            if (a.getPrijs() >= Double.parseDouble(PropertiesLoadSave.load("DREMPEL"))) {
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
            if (a.getPrijs() >= Double.parseDouble(PropertiesLoadSave.load("DREMPEL"))) {
                if (a.getPrijs() > Double.parseDouble(PropertiesLoadSave.load("GETAL"))) {
                    a.setKorting(Integer.parseInt(PropertiesLoadSave.load("GETAL")));
                } else {
                    a.setKorting(a.getPrijs());
                }
            }
        }
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }

    public void setType(String type) {
        this.type = type;
    }
}

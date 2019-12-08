package model;

import database.PropertiesLoadSave;

import java.util.List;

public class DrempelKorting implements KortingStrategy {

    private int aantal;
    private String type;

    public DrempelKorting () {
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
        for (Artikel a : lijst) {
            if (a.getPrijs() >= Double.parseDouble(PropertiesLoadSave.load("DREMPEL"))) {
                a.setKorting(a.getPrijs() - ((a.getPrijs()/100)*Integer.parseInt(PropertiesLoadSave.load("GETAL"))));
            }
        }
    }

    private void berekenEuro (List<Artikel> lijst) {
        for (Artikel a : lijst) {
            if (a.getPrijs() >= Double.parseDouble(PropertiesLoadSave.load("DREMPEL"))) {
                a.setKorting(Integer.parseInt(PropertiesLoadSave.load("GETAL")));
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

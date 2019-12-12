package model;

import java.util.List;

public class Actief implements State {
    private Winkelwagentje winkelwagentje;

    public Actief(Winkelwagentje winkelwagentje){
        this.winkelwagentje = winkelwagentje;
    }

    @Override
    public void sluitAf() {
        winkelwagentje.setState(this.winkelwagentje.getInactief());
    }

    @Override
    public void zetOnHold() {
        winkelwagentje.setState(this.winkelwagentje.getOnHold());
        System.out.println("staat on hold");
    }

    @Override
    public void voegArtikelToe(Artikel artikel, List<Artikel> artikels) {
        artikels.add(artikel);
        System.out.println(artikel.toString() + " toegevoegd");
    }

    @Override
    public void verwijderArtikel(Artikel artikel, List<Artikel> artikels) {
        artikels.remove(artikel);
        System.out.println(artikel.toString() + "verwijderd");
    }

    @Override
    public String toString() {
        return "staat op actief";
    }
}

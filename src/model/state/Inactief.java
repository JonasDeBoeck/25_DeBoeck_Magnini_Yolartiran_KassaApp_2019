package model.state;

import model.Artikel;
import model.Winkelwagentje;

import java.util.List;

/**
 * @Author Thibault Magnini
 * */

public class Inactief implements State {

    private Winkelwagentje winkelwagentje;
    public Inactief(Winkelwagentje winkelwagentje){
        this.winkelwagentje = winkelwagentje;
    }

    @Override
    public void betaal() {
        winkelwagentje.setState(this.winkelwagentje.getBetaald());
    }

    @Override
    public void annuleer() {
        winkelwagentje.setState(this.winkelwagentje.getGeannuleerd());
    }

    @Override
    public void verwijderArtikel(Artikel artikel, List<Artikel> artikels) {
        artikels.remove(artikel);
    }

    @Override
    public String toString() {
        return "State: Inactief";
    }
}

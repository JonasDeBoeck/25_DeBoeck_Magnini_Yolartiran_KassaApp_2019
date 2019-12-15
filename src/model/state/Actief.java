package model.state;

import model.Artikel;
import model.Winkelwagentje;

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
    }

    @Override
    public void voegArtikelToe(Artikel artikel, List<Artikel> artikels) {
        artikels.add(artikel);
    }

    @Override
    public void verwijderArtikel(Artikel artikel, List<Artikel> artikels) {
        artikels.remove(artikel);
    }

    @Override
    public void maakLeeg(List<Artikel> artikels) {
        if (artikels.isEmpty()){
            throw new IllegalArgumentException("Kan niet afsluiten want winkelkar is leeg");
        }
        artikels.clear();
    }

    @Override
    public String toString() {
        return "State: Actief";
    }
}

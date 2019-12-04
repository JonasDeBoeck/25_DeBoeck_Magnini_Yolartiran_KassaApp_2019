package model;

import java.util.ArrayList;
import java.util.List;

public class Winkelwagentje {
    private List<Artikel> artikels;

    public Winkelwagentje () {
        this.artikels = new ArrayList<>();
    }

    public void removeArtikel(Artikel artikel) {
        this.artikels.remove(artikel);
    }

    public void addArtikel (Artikel artikel) {
        this.artikels.add(artikel);
    }

    public void maakLeeg () {
        this.artikels.clear();
    }

    public List<Artikel> getArtikels () {
        return this.artikels;
    }
}

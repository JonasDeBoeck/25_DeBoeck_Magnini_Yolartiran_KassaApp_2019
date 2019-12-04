package model;

import database.DatabaseContext;
import database.PropertiesLoadSave;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Winkel {
    private Map<String, Artikel> artikels;
    private Winkelwagentje winkelwagentje;
    private DatabaseContext db;

    public Winkel () {
        setDb(new DatabaseContext());
        this.artikels = new HashMap<>();
        this.winkelwagentje = new Winkelwagentje();
        artikels.putAll(db.getAll("artikel." + PropertiesLoadSave.load("DATABASE")));
    }

    public Artikel vindArtikel(String id){
        return artikels.get(id);
    }

    public Map<String, Artikel> getArtikels() {
        return artikels;
    }

    public void save(String filename, List<Artikel> cart) {
        for (Artikel artikel : cart) {
            artikels.get(artikel.getArtikelId()).setAantalInStock(artikel.getAantalInStock() - 1);
        }
        db.saveAll(filename, artikels);
    }

    private void setDb(DatabaseContext db) {
        this.db = db;
    }

    public Winkelwagentje getWinkelwagentje() {
        return winkelwagentje;
    }

    public void addToCart (Artikel artikel) {
        this.winkelwagentje.addArtikel(artikel);
    }

    public void clearCart () {
        this.winkelwagentje.maakLeeg();
    }

    public void deleteFromCart(Artikel artikel) {
        this.winkelwagentje.removeArtikel(artikel);
    }
}

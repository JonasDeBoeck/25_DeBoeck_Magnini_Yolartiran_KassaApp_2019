package model;

import database.DatabaseContext;
import database.PropertiesLoadSave;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Winkel {
    private Map<String, Artikel> artikels;
    private Winkelwagentje winkelwagentje, onHoldWinkelwagentje;
    private DatabaseContext db;
    private KortingContext kortingen;

    public Winkel () {
        setDb(new DatabaseContext());
        setKortingen(new KortingContext());
        this.artikels = new HashMap<>();
        this.winkelwagentje = new Winkelwagentje();
        this.onHoldWinkelwagentje = new Winkelwagentje();
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

    private void setKortingen(KortingContext korting) {
        this.kortingen = korting;
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

    public void setOnHold(){
        onHoldWinkelwagentje.getArtikels().addAll(this.winkelwagentje.getArtikels());
        clearCart();
    }

    public void setOffHold(){
        winkelwagentje.getArtikels().addAll(this.onHoldWinkelwagentje.getArtikels());
        this.onHoldWinkelwagentje.getArtikels().clear();
    }

    public void setKorting() {
        this.kortingen.getKorting(this.winkelwagentje.getArtikels());
    }

    public Winkelwagentje getOnHoldWinkelwagentje() {
        return onHoldWinkelwagentje;
    }

    public void clearOnHold(){
        this.onHoldWinkelwagentje.getArtikels().clear();
    }

    public double getTotaalPrijsMetKorting() {
        return winkelwagentje.getTotaalPrijsMetKorting();
    }
}

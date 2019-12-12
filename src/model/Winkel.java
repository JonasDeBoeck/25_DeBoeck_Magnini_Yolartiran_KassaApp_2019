package model;

import database.DatabaseContext;
import database.PropertiesLoadSave;

import java.util.*;

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
        winkelwagentje.setState(this.winkelwagentje.getActief());
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
        winkelwagentje.setState(this.winkelwagentje.getActief());
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
        onHoldWinkelwagentje.setState(this.winkelwagentje.getOnHold());
        System.out.println(this.onHoldWinkelwagentje.getState().toString());
    }

    public void setOffHold(){
        winkelwagentje.getArtikels().addAll(this.onHoldWinkelwagentje.getArtikels());
        this.onHoldWinkelwagentje.getArtikels().clear();
        winkelwagentje.setState(this.winkelwagentje.getActief());
        System.out.println(this.winkelwagentje.getState().toString());
    }

    public void setKorting() {
        this.kortingen.getKorting(this.winkelwagentje.getArtikels());
    }

    public Winkelwagentje getOnHoldWinkelwagentje() {
        return onHoldWinkelwagentje;
    }

    public void clearOnHold(){
        this.onHoldWinkelwagentje.getArtikels().clear();
        winkelwagentje.setState(this.winkelwagentje.getGeannuleerd());
        System.out.println(this.winkelwagentje.getState().toString());
    }

    public double getTotaalPrijsMetKorting() {
        return winkelwagentje.getTotaalPrijsMetKorting();
    }

    //6 spaties, 3 spaties, 2 spaties
    public void printTicket () {
        Set<Artikel> mandje = new HashSet<>(winkelwagentje.getArtikels());
        String tussenLijn = "**********************************" ;
        String ticket = "Omschrijving      Aantal   Prijs  \n";
        System.out.println(ticket + tussenLijn);
        for (Artikel artikel : mandje) {
            String art = "%-18s%-8s%s%n";
            System.out.printf(art, artikel.getNaam(), winkelwagentje.getCount(artikel), artikel.getPrijs());
        }
        System.out.println(tussenLijn);
    }
}

package model;

import model.state.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Winkelwagentje implements Serializable{

    private State actief, inactief, onHold, betaald, geannuleerd, state;
    private List<Artikel> artikels;

    public Winkelwagentje () {
        this.artikels = new ArrayList<>();
        actief = new Actief(this);
        inactief = new Inactief(this);
        onHold = new OnHold(this);
        geannuleerd = new Geannuleerd(this);
        betaald = new Betaald(this);
        state = actief;
    }

    public void removeArtikel(Artikel artikel) {
        this.state.verwijderArtikel(artikel, artikels);
    }

    public void addArtikel (Artikel artikel) {
        this.state.voegArtikelToe(artikel, artikels);
    }

    public void maakLeeg () {
        System.out.println(state.toString());
        this.state.maakLeeg(artikels);
    }


    public List<Artikel> getArtikels () {
        return this.artikels;
    }

    public double getTotaalPrijsMetKorting() {
        return Bereken.berekenTotaalPrijsMetKorting(this.artikels);
    }

    public State getActief() {
        return actief;
    }

    public State getInactief() {
        return inactief;
    }

    public State getOnHold() {
        return onHold;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public State getBetaald() {
        return betaald;
    }

    public State getGeannuleerd() {
        return geannuleerd;
    }
}

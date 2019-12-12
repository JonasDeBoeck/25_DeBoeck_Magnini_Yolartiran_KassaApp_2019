package model;

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
        this.artikels.clear();
    }

    public List<Artikel> getArtikels () {
        return this.artikels;
    }

    public double getTotaalPrijsMetKorting() {
        double totaal = 0;
        for (Artikel artikel : artikels) {
            totaal += (artikel.getPrijs() - artikel.getKorting());
        }
        return totaal;
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

    public void setOnHold(State onHold) {
        this.onHold = onHold;
    }

    public void setActief(State actief) {
        this.actief = actief;
    }

    public void setInactief(State inactief) {
        this.inactief = inactief;
    }

    public void setBetaald(State betaald) {
        this.betaald = betaald;
    }

    public void setGeannuleerd(State geannuleerd) {
        this.geannuleerd = geannuleerd;
    }

    public State getBetaald() {
        return betaald;
    }

    public State getGeannuleerd() {
        return geannuleerd;
    }
}

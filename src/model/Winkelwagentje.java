package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
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

    public State getBetaald() {
        return betaald;
    }

    public State getGeannuleerd() {
        return geannuleerd;
    }
}

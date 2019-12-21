package model;

import model.state.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
/**
 * @Author Jonas De Boeck, Thibault Magnini
 * */

public class Winkelwagentje implements Serializable{

    private State actief, inactief, onHold, betaald, geannuleerd, state;
    private List<Artikel> artikels;
    private String artikelen;
    private LocalDate betaalDatum;
    private LocalTime betaalTijd;

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
        this.state.maakLeeg(artikels);
    }

    public void setActief() {
        setState(actief);
    }

    public void setBetaald() {
        setState(betaald);
    }

    public void setGeannuleerd() {
        setState(geannuleerd);
    }

    public void setOnHold() {
        setState(onHold);
    }

    public void setInactief() {
        setState(inactief);
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

    public void setBetaalDatum(LocalDate betaalDatum) {
        this.betaalDatum = betaalDatum;
    }

    public void setBetaalTijd(LocalTime betaalTijd) {
        this.betaalTijd = betaalTijd.withNano(0);
    }

    public LocalDate getBetaalDatum() {
        return betaalDatum;
    }

    public LocalTime getBetaalTijd() {
        return betaalTijd;
    }

    public String getArtikelen() {
        return artikelen;
    }

    public void setArtikelen() {
        String info = "";
        for (Artikel artikel : artikels) {
            info += artikel.getNaam() + ", ";
        }
        this.artikelen = info.substring(0, info.length()-2);
    }
}

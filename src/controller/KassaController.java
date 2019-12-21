package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Artikel;
import model.Bereken;
import model.Winkel;
import model.Winkelwagentje;
import model.observer.Observer;
import model.observer.Subject;
import view.KassaView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author Jonas De Boeck, Thibault Magnini
 * */

public class KassaController implements Subject {
    private KassaView view;
    private Winkel model;
    private List<Observer>observers;
    private int counter;

    public KassaController(Winkel model) {
        setModel(model);
        this.observers = new ArrayList<>();
        counter = 0;
    }

    public Map<String, Artikel> getProducten () {
        return model.getArtikels();
    }

    public void setView(KassaView view) {
        this.view = view;
    }

    public Winkel getModel() {
        return model;
    }

    private void setModel(Winkel model) {
        this.model = model;
    }

    public Artikel vindArtikel(String artikelId) {
        return model.vindArtikel(artikelId);
    }

    public void save (String filename, List<Artikel> cart) {
        model.save(filename, cart);
    }

    public void load (String filename){model.load(filename);}

    public void addToCart (Artikel artikel) {
        model.addToCart(artikel);
    }

    public void clearCart () {
        if (!model.getOnHoldWinkelwagentje().getArtikels().isEmpty()){
            counter++;
            if (counter > 3){
                model.clearOnHold();
            }
        }
        model.clearCart();
    }

    public ObservableList<Artikel> getWinkelWagentje () {
        Winkelwagentje winkelwagentje = model.getWinkelwagentje();
        return FXCollections.observableArrayList(winkelwagentje.getArtikels());
    }

    public double updateTotaalPrijs() {
        return Bereken.berekenTotaalPrijs(model.getWinkelwagentje().getArtikels());
    }

    public void deleteFromCart(Artikel artikel) {
        model.deleteFromCart(artikel);
    }

    //Notified de geregistreerde observers
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(getWinkelWagentje(), Double.toString(updateTotaalPrijs()));
        }
    }

    //Notified de geregistreerde observers
    @Override
    public void notifyObserversAfsluit() {
        for (Observer observer : observers){
            observer.updateAfsluit(Double.toString(updateTotaalPrijs()), Double.toString(getTotaleKorting()), Double.toString(getTotaalPrijsMetKorting()));
        }
    }

    @Override
    public void notifyObserversBetaal() {
        for (Observer observer : observers) {
            observer.updateBetaal();
        }
    }

    //Registreer observer
    @Override
    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    //Zet winkelkarretje on hold
    public void zetOnHold(){
        counter = 0;
        model.setOnHold();
    }

    //Haal winkelkarretje of hold
    public void zetOffHold(){
        counter = 0;
        model.setOffHold();
    }

    public void setKorting() {
        model.setKorting();
    }

    public double getTotaalPrijsMetKorting() {
        return model.getTotaalPrijsMetKorting();
    }

    public double getTotaleKorting() {
        return Bereken.berekenTotaalKorting(model.getWinkelwagentje().getArtikels());
    }

    //Returned true als het on hold winkelkarretje leeg is
    public boolean legeOnHold(){
        return model.getOnHoldWinkelwagentje().getArtikels().isEmpty();
    }

    public void setStateOnInactief(){
        model.setStateOnInactief();
    }

    public void setStateOnBetaald(){
        model.setStateOnBetaald();
    }

    public void setStateOnGeannuleerd(){
        model.setStateOnGeannuleerd();
    }

    public void setStateOnActief(){
        model.setStateOnActief();
    }

    public String getState(){
        return model.getState().toString();
    }

    //Print de kassa bon
    public void printKassaBon () {
        model.printTicket();
    }
}

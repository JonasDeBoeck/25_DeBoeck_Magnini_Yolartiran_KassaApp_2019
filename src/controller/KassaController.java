package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Artikel;
import model.Winkel;
import model.Winkelwagentje;
import view.KassaView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KassaController implements Subject{
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
        double totaal1 = 0;
        Winkelwagentje winkelwagentje = model.getWinkelwagentje();
        for(Artikel a : winkelwagentje.getArtikels()){
            totaal1 += a.getPrijs();
        }
        return totaal1;
    }

    public void deleteFromCart(Artikel artikel) {
        model.deleteFromCart(artikel);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(getWinkelWagentje(), Double.toString(updateTotaalPrijs()));
        }
    }


    @Override
    public void notifyObserversAfsluit() {
        for (Observer observer : observers){
            observer.updateAfsluit(Double.toString(updateTotaalPrijs()), Double.toString(getTotaleKorting()), Double.toString(getTotaalPrijsMetKorting()));
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void zetOnHold(){
        counter = 0;
        model.setOnHold();
    }

    public void zetOffHold(){
        counter = 0;
        model.setOffHold();
    }

    public double getTotaalPrijsMetKorting() {
        model.setKorting();
        return model.getTotaalPrijsMetKorting();
    }

    public double getTotaleKorting() {
        return updateTotaalPrijs() - getTotaalPrijsMetKorting();
    }

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

    public void printKassaBon () {
        model.printTicket();
    }
}

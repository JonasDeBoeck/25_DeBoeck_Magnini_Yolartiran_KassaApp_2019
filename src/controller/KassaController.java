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

    public KassaController(Winkel model) {
        setModel(model);
        this.observers = new ArrayList<>();
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

    public void addToCart (Artikel artikel) {
        model.addToCart(artikel);
    }

    public void clearCart () {
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
    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }
}

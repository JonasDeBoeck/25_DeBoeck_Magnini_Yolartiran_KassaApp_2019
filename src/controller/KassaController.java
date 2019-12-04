package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Artikel;
import model.Winkel;
import model.Winkelwagentje;
import view.KassaView;

import java.util.List;
import java.util.Map;

public class KassaController {
    private KassaView view;
    private Winkel model;

    public KassaController(Winkel model) {
        setModel(model);
    }

    public Map<String, Artikel> getProducten () {
        return model.getArtikels();
    }

    public KassaView getView() {
        return view;
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
}

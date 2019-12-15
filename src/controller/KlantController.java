package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import model.Artikel;
import model.Winkel;
import model.observer.Observer;
import view.KlantMainPane;

public class KlantController implements Observer {
    private KlantMainPane view;
    private Winkel model;

    public KlantController (Winkel model) {
        setModel(model);
    }

    //Update de waardes in de view
    @Override
    public void update(ObservableList<Artikel> artikels, String prijs) {
        ObservableMap<Artikel, Integer> winkelwagentje = FXCollections.observableHashMap();
        for (Artikel artikel : artikels) {
            if (!winkelwagentje.containsKey(artikel)) {
                winkelwagentje.put(artikel, 1);
            } else {
                winkelwagentje.put(artikel, winkelwagentje.get(artikel)+1);
            }
        }
        view.update(winkelwagentje, prijs);
    }

    //Update de waardes in de view
    @Override
    public void updateAfsluit(String totalePrijs, String korting, String totalePrijsMetKorting) {
        view.updateAfsluit(totalePrijs, korting, totalePrijsMetKorting);
    }

    private void setModel(Winkel model) {
        this.model = model;
    }

    public void setView(KlantMainPane view) {
        this.view = view;
    }
}

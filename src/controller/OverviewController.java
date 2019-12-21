package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Artikel;
import model.Winkel;
import model.Winkelwagentje;
import model.observer.Observer;
import view.panels.ProductOverviewPane;

import java.util.Map;

public class OverviewController implements Observer {
        private Winkel model;
        private ProductOverviewPane view;

        public OverviewController (Winkel model) {
            setModel(model);
        }

    public void load (String filename){model.load(filename);}

    public Map<String, Artikel> getProducten () {
        return model.getArtikels();
    }



    @Override
        public void update(ObservableList<Artikel> artikels, String prijs) {
            //Geen implementatie nodig
        }

        @Override
        public void updateAfsluit(String totalePrijs, String korting, String totalePrijsMetKorting) {
            //Geen implementatie
        }

        @Override
        public void updateBetaal() {
            view.updateStock();
        }

        private void setModel(Winkel model) {
            this.model = model;
        }

        public void setView(ProductOverviewPane view) {
            this.view = view;
        }
    }

package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Artikel;
import model.Winkel;
import model.Winkelwagentje;
import model.observer.Observer;
import view.panels.LogTabPane;

import java.time.LocalDate;
import java.time.LocalTime;

public class LogController implements Observer {
    private Winkel model;
    private LogTabPane view;
    private ObservableList<Winkelwagentje> verkopen;

    public LogController (Winkel model) {
        setModel(model);
        verkopen = FXCollections.observableArrayList();
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
        model.getWinkelwagentje().setBetaalDatum(LocalDate.now());
        model.getWinkelwagentje().setBetaalTijd(LocalTime.now());
        model.getWinkelwagentje().setArtikelen();
        verkopen.add(model.getWinkelwagentje());
        view.update(verkopen);
    }

    private void setModel(Winkel model) {
        this.model = model;
    }

    public void setView(LogTabPane view) {
        this.view = view;
    }
}

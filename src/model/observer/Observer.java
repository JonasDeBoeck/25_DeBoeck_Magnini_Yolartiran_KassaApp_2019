package model.observer;

import javafx.collections.ObservableList;
import model.Artikel;
import model.Winkelwagentje;

import java.time.LocalDate;
import java.time.LocalTime;

public interface Observer {
    void update(ObservableList<Artikel> artikels, String prijs);
    void updateAfsluit(String totalePrijs, String korting, String totalePrijsMetKorting);

}

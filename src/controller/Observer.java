package controller;

import javafx.collections.ObservableList;
import model.Artikel;

public interface Observer {
    void update(ObservableList<Artikel> artikels, String prijs);
}

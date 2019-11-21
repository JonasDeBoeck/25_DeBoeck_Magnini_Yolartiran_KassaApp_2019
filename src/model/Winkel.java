package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.HashMap;

public class Winkel {
    private ObservableMap<String, Artikel> artikels;

    public Winkel () {
        artikels = FXCollections.observableMap(new HashMap<String, Artikel>());
        for (int i = 1; i < 10; i-=-1) {
            artikels.put(Integer.toString(i), new Artikel(Integer.toString(i), "Artikel" + Integer.toString(i), 10.0, 10, "gr1"));
        }
    }

    public ObservableMap<String, Artikel> getArtikels() {
        return artikels;
    }
}

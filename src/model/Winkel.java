package model;

import database.ArtikelTekstLoadSave;
import database.TekstLoadSaveTemplate;

import java.util.HashMap;
import java.util.Map;

public class Winkel {
    private Map<String, Artikel> artikels;
    private TekstLoadSaveTemplate db;

    public Winkel () {
        db = new ArtikelTekstLoadSave();
        artikels = new HashMap<>();
        artikels.putAll(db.load("artikel.txt"));
    }

    public Map<String, Artikel> getArtikels() {
        return artikels;
    }
}

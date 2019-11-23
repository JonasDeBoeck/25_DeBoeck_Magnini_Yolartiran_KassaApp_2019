package model;

import database.ArtikelTekstLoadSave;
import database.DatabaseContext;

import java.util.HashMap;
import java.util.Map;

public class Winkel {
    private Map<String, Artikel> artikels;
    private DatabaseContext db;

    public Winkel (DatabaseContext databaseContext) {
        db = new DatabaseContext(new ArtikelTekstLoadSave());
        artikels = new HashMap<>();
        artikels.putAll(db.load("artikel.txt"));
    }

    public Map<String, Artikel> getArtikels() {
        return artikels;
    }
}

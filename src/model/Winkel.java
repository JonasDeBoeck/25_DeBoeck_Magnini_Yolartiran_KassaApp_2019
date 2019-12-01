package model;

import database.DatabaseContext;

import java.util.HashMap;
import java.util.Map;

public class Winkel {
    private Map<String, Artikel> artikels;
    private DatabaseContext db;

    public Winkel () {
        setDb(new DatabaseContext());
        artikels = new HashMap<>();
        artikels.putAll(db.getAll("artikel.xls"));
    }

    public Map<String, Artikel> getArtikels() {
        return artikels;
    }

    private void setDb(DatabaseContext db) {
        this.db = db;
    }
}

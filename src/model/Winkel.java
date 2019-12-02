package model;

import database.DatabaseContext;
import database.PropertiesLoadSave;

import java.util.HashMap;
import java.util.Map;

public class Winkel {
    private Map<String, Artikel> artikels;
    private DatabaseContext db;

    public Winkel () {
        setDb(new DatabaseContext());
        artikels = new HashMap<>();
        artikels.putAll(db.getAll("artikel." + PropertiesLoadSave.load("DATABASE")));
    }

    public Artikel vindArtikel(String id){
        return artikels.get(id);
    }

    public Map<String, Artikel> getArtikels() {
        return artikels;
    }

    private void setDb(DatabaseContext db) {
        this.db = db;
    }
}

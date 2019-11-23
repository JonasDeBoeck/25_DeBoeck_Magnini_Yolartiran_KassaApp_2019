package database;

import model.Artikel;

import java.util.HashMap;
import java.util.Map;

public abstract class TekstLoadSaveTemplate {

    public Map<String, Artikel> load(String filename) {
        return read(filename);
    }

    public void save(String filename, HashMap<String, Artikel> artikels) {
        write(filename, artikels);
    }

    abstract void write(String filename, Map<String, Artikel> artikels);

    abstract Map<String, Artikel> read(String filename);
}

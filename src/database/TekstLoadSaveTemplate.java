package database;

import model.Artikel;

import java.util.Map;

public abstract class TekstLoadSaveTemplate implements LoadSaveStrategy{

    public Map<String, Artikel> load(String filename) {
        return read(filename);
    }

    public void save(String filename, Map<String, Artikel> artikels) {
        write(filename, artikels);
    }

    public abstract void write(String filename, Map<String, Artikel> artikels);

    public abstract Map<String, Artikel> read (String filename);
}

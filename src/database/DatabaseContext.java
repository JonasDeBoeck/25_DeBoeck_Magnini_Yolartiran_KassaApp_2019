package database;

import model.Artikel;

import java.util.Map;

public class DatabaseContext {
    private ArtikelDBStrategy artikelDBStrategy;

    public DatabaseContext(ArtikelDBStrategy artikelDBStrategy) {
        setArtikelDBStrategy(artikelDBStrategy);
    }

    public void setArtikelDBStrategy(ArtikelDBStrategy artikelDBStrategy) {
        this.artikelDBStrategy = artikelDBStrategy;
    }

    public Map<String, Artikel> getAll (String filename) {
        return artikelDBStrategy.getAll(filename);
    }

    public void saveAll (String filename, Map<String, Artikel> artikels) {
        artikelDBStrategy.saveAll(filename, artikels);
    }
}

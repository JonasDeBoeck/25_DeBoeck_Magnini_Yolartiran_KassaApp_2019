package database.strategy.artikelDbStrategy;

import model.Artikel;

import java.util.Map;

public interface ArtikelDBStrategy {
    Map<String, Artikel> getAll (String filename);
    void saveAll (String filename, Map<String, Artikel> artikels);
}

package database.strategy.loadSaveStrategy;

import model.Artikel;

import java.util.Map;

/**
 * @Author Jonas De Boeck
 * */

public interface LoadSaveStrategy {
    Map<String, Artikel> load(String filename);
    void save(String filename, Map<String, Artikel> artikels);
}

package database.strategy.artikelDbStrategy;

import database.PropertiesLoadSave;
import database.factory.LoadSaveFactory;
import database.strategy.loadSaveStrategy.LoadSaveStrategy;
import model.Artikel;

import java.util.Map;

/**
 * @Author Jonas De Boeck
 * */

public class ArtikelDBInMemory implements ArtikelDBStrategy {
    private LoadSaveStrategy loadSaveStrategy;

    public ArtikelDBInMemory() {
        String database = PropertiesLoadSave.load("DATABASE");
        LoadSaveFactory factory = LoadSaveFactory.getInstance();
        LoadSaveStrategy strategy = factory.getStrategy(database);
        setLoadSaveStrategy(strategy);
    }

    public void setLoadSaveStrategy(LoadSaveStrategy loadSaveStrategy) {
        this.loadSaveStrategy = loadSaveStrategy;
    }

    @Override
    public Map<String, Artikel> getAll(String filename) {
        return loadSaveStrategy.load(filename);
    }

    @Override
    public void saveAll(String filename, Map<String, Artikel> artikels) {
        loadSaveStrategy.save(filename, artikels);
    }
}

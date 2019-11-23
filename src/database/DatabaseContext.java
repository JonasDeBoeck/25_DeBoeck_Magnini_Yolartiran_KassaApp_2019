package database;

import model.Artikel;

import java.util.Map;

public class DatabaseContext {
    private LoadSaveStrategy loadSaveStrategy;

    public DatabaseContext(LoadSaveStrategy loadSaveStrategy) {
        setLoadSaveStrategy(loadSaveStrategy);
    }

    public void setLoadSaveStrategy(LoadSaveStrategy loadSaveStrategy) {
        this.loadSaveStrategy = loadSaveStrategy;
    }

    public Map<String, Artikel> load (String filename) {
        return loadSaveStrategy.load(filename);
    }

    public void save(String filename, Map<String, Artikel> artikels) {
        loadSaveStrategy.save(filename, artikels);
    }
}

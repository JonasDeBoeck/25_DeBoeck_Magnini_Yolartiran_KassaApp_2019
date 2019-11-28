package database;

import model.Artikel;

import java.util.Map;

public class ArtikelDBInMemory implements ArtikelDBStrategy {
    private LoadSaveStrategy loadSaveStrategy;

    public ArtikelDBInMemory(LoadSaveStrategy loadSaveStrategy) {
        setLoadSaveStrategy(loadSaveStrategy);
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

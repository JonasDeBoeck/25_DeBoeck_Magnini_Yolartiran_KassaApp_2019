package database;

import database.strategy.loadSaveStrategy.LoadSaveStrategy;
import model.Artikel;

import java.io.File;
import java.util.Map;

public abstract class TekstLoadSaveTemplate implements LoadSaveStrategy {

    public Map<String, Artikel> load(String filename) {
        String fullPath = fullPathName(filename);
        File toRead = pathToFile(fullPath);
        return read(toRead);
    }

    public void save(String filename, Map<String, Artikel> artikels) {
        String fullPath = fullPathName(filename);
        File toWrite = pathToFile(fullPath);
        write(toWrite, artikels);
    }

    public abstract void write(File toWrite, Map<String, Artikel> artikels);

    public abstract Map<String, Artikel> read (File toRead);

    public String fullPathName (String filename) {
        return "src/bestanden/" + filename;
    }

    public File pathToFile (String fullPath) {
        return new File(fullPath);
    }
}

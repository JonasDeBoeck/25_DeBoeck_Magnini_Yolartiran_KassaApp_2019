package database;

import database.strategy.loadSaveStrategy.LoadSaveStrategy;
import model.Artikel;

import java.io.File;
import java.util.Map;

/**
 * @Author Jonas De Boeck
 * */

public abstract class TekstLoadSaveTemplate implements LoadSaveStrategy {

    //Template methode voor het inlezen
    public Map<String, Artikel> load(String filename) {
        String fullPath = fullPathNameJar(filename);
        return read(fullPath);
    }

    //Template methode voor het wegschrijven
    public void save(String filename, Map<String, Artikel> artikels) {
        String fullPath = fullPathName(filename);
        File toWrite = pathToFile(fullPath);
        write(toWrite, artikels);
    }

    public abstract void write(File toWrite, Map<String, Artikel> artikels);

    public abstract Map<String, Artikel> read (String toRead);

    public String fullPathNameJar (String filename) {
        return "/bestanden/" + filename;
    }

    public String fullPathName (String filename) {
        return "src/bestanden/" + filename;
    }

    public File pathToFile (String fullPath) {
        return new File(fullPath);
    }
}

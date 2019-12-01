package database;

import java.lang.reflect.InvocationTargetException;

public class LoadSaveFactory {
    private static LoadSaveFactory uniqueFactory;

    private LoadSaveFactory() {}

    public static LoadSaveFactory getInstance() {
        if (uniqueFactory == null) {
            uniqueFactory = new LoadSaveFactory();
        }
        return uniqueFactory;
    }

    public LoadSaveStrategy getStrategy(String type) {
        //TODO: reflection
        /*String strategy = "";
        switch (type) {
            case "xls":
                strategy = "ExcelAdapter";
            case "txt":
                strategy = "ArtikelTekstLoadSave";
        }
        LoadSaveStrategy loadSave = null;
        try {
            Class loadSaveClass = Class.forName("src/database/" + strategy);
            Object loadSaveObject = loadSaveClass.getConstructor().newInstance();
            loadSave = (LoadSaveStrategy) loadSaveObject;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new DatabaseException();
        }
        return loadSave;*/
        switch (type) {
            case "xls":
                return new ExcelAdapter();
            case "txt":
                return new ArtikelTekstLoadSave();
            default:
                return new ArtikelTekstLoadSave();
        }
    }
}

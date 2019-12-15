package database.factory;

import database.DatabaseException;
import database.strategy.loadSaveStrategy.LoadSaveStrategy;

public class LoadSaveFactory {
    private static LoadSaveFactory uniqueFactory;

    private LoadSaveFactory() {}

    public static LoadSaveFactory getInstance() {
        if (uniqueFactory == null) {
            uniqueFactory = new LoadSaveFactory();
        }
        return uniqueFactory;
    }

    //Returned de juiste LoadSaveStrategy aan de hand van de waarde in de properties file, de switch zorgt er voor dat het juiste object aangemaakt wordt
    public LoadSaveStrategy getStrategy(String type) {
        String strategy = "";
        switch (type) {
            case "xls":
                strategy = "ExcelAdapter";
                break;
            case "txt":
                strategy = "ArtikelTekstLoadSave";
                break;
            default:
                strategy = "ArtikelTekstLoadSave";
        }
        LoadSaveStrategy loadSave = null;
        try {
            Class loadSaveClass = Class.forName("database.strategy.loadSaveStrategy." + strategy);
            Object loadSaveObject = loadSaveClass.newInstance();
            loadSave = (LoadSaveStrategy) loadSaveObject;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException  e) {
            throw new DatabaseException();
        }
        return loadSave;
    }
}

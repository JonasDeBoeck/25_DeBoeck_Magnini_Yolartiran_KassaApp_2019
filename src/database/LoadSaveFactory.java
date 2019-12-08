package database;

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
        String strategy = "";
        switch (type) {
            case "xls":
                strategy = "ExcelAdapter";
                break;
            case "txt":
                strategy = "ArtikelTekstLoadSave";
        }
        LoadSaveStrategy loadSave = null;
        try {
            Class loadSaveClass = Class.forName("database." + strategy);
            Object loadSaveObject = loadSaveClass.newInstance();
            loadSave = (LoadSaveStrategy) loadSaveObject;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException  e) {
            throw new DatabaseException();
        }
        return loadSave;
    }
}

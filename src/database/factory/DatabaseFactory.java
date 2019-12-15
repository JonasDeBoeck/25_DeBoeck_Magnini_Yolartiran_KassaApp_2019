package database.factory;

import database.strategy.artikelDbStrategy.ArtikelDBStrategy;
import database.DatabaseException;

public class DatabaseFactory {
    private static DatabaseFactory uniqueFactory;

    private DatabaseFactory() {}

    public static DatabaseFactory getInstance() {
        if (uniqueFactory == null) {
            uniqueFactory = new DatabaseFactory();
        }
        return uniqueFactory;
    }

    public ArtikelDBStrategy getDatabase(String type) {
        String db;
        switch (type) {
            default:
                db = "ArtikelDBInMemory";
        }
        ArtikelDBStrategy database = null;
        try {
            Class databaseClass = Class.forName("database.strategy.artikelDbStrategy." + db);
            Object databaseObject = databaseClass.newInstance();
            database = (ArtikelDBStrategy) databaseObject;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new DatabaseException(e.getMessage());
        }
        return database;
    }
}

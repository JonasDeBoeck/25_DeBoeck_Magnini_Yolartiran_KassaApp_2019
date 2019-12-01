package database;

import java.lang.reflect.InvocationTargetException;

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
        //TODO: reflection
        /*String db;
        switch (type) {
            default:
                db = "ArtikelDBInMemory";
        }
        ArtikelDBStrategy database = null;
        try {
            Class databaseClass = Class.forName("src.database." + db);
            Object databaseObject = databaseClass.getConstructor().newInstance();
            database = (ArtikelDBStrategy) databaseObject;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new DatabaseException(e.getMessage());
        }
        return database;*/
        return new ArtikelDBInMemory();
    }
}

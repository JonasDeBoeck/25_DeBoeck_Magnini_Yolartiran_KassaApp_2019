package model;

import database.DatabaseException;
import database.LoadSaveFactory;
import database.LoadSaveStrategy;

public class KortingStrategyFactory {
    private static KortingStrategyFactory uniqueFactory;

    private KortingStrategyFactory() {}

    public static KortingStrategyFactory getInstance() {
        if (uniqueFactory == null) {
            uniqueFactory = new KortingStrategyFactory();
        }
        return uniqueFactory;
    }

    public KortingStrategy getStrategy(String type) {
        String soort = "";
        switch (type) {
            case "GRENS":
                soort = "DrempelKorting";
                break;
            case "DUURSTE":
                soort = "DuursteKorting";
                break;
            case "GROEP":
                soort = "GroepsKorting";
        }
        KortingStrategy kortingStrategy = null;
        try {
            Class kortingStrategyClass = Class.forName("model." + soort);
            Object kortingStrategyObject = kortingStrategyClass.newInstance();
            kortingStrategy = (KortingStrategy) kortingStrategyObject;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException  e) {
            throw new DatabaseException();
        }
        return kortingStrategy;
    }
}

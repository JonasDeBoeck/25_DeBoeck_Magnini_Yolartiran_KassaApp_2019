package model.factory;

import database.DatabaseException;
import model.kortingStrategy.KortingStrategy;

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
                break;
            default:
                soort = "GeenKorting";
        }
        KortingStrategy kortingStrategy = null;
        if (!soort.equals("GeenKorting")) {
            try {
                Class kortingStrategyClass = Class.forName("model.kortingStrategy." + soort);
                Object kortingStrategyObject = kortingStrategyClass.newInstance();
                kortingStrategy = (KortingStrategy) kortingStrategyObject;
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                throw new DatabaseException();
            }
        }
        return kortingStrategy;
    }
}

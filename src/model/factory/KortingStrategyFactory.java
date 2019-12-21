package model.factory;

import database.DatabaseException;
import model.kortingStrategy.KortingStrategy;

/**
 * @Author Jonas De Boeck, Sinan Yolartiran
 * */

public class KortingStrategyFactory {
    private static KortingStrategyFactory uniqueFactory;

    private KortingStrategyFactory() {}

    public static KortingStrategyFactory getInstance() {
        if (uniqueFactory == null) {
            uniqueFactory = new KortingStrategyFactory();
        }
        return uniqueFactory;
    }

    //Returned de juiste korting aan de hand van de waarde in de properties file, de switch zorgt er voor dat het juiste object wordt aangemaakt
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

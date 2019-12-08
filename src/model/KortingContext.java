package model;

import database.PropertiesLoadSave;

public class KortingContext {
    private KortingStrategy kortingStrategy;

    public KortingContext() {
        String soortKorting = PropertiesLoadSave.load("SOORT");
        KortingStrategyFactory factory = KortingStrategyFactory.getInstance();
        kortingStrategy = factory.getStrategy(soortKorting);
    }
}

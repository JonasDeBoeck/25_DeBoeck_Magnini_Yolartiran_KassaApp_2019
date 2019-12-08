package model;

import database.PropertiesLoadSave;

import java.util.List;

public class KortingContext {
    private KortingStrategy kortingStrategy;

    public KortingContext() {
        String soortKorting = PropertiesLoadSave.load("SOORT");
        KortingStrategyFactory factory = KortingStrategyFactory.getInstance();
        kortingStrategy = factory.getStrategy(soortKorting);
    }

    public void getKorting (List<Artikel> artikels) {
        kortingStrategy.berekenPrijs(artikels);
    }
}

package model.kortingStrategy;

import database.PropertiesLoadSave;
import model.Artikel;
import model.factory.KortingStrategyFactory;

import java.util.List;

/**
 * @Author Jonas De Boeck, Sinan Yolartiran
 * */

public class KortingContext {
    private KortingStrategy kortingStrategy;

    public KortingContext() {
        String soortKorting = PropertiesLoadSave.load("SOORT");
        KortingStrategyFactory factory = KortingStrategyFactory.getInstance();
        kortingStrategy = factory.getStrategy(soortKorting);
    }

    //Berekent de korting per product van "artikels"
    public void getKorting (List<Artikel> artikels) {
        if (!PropertiesLoadSave.load("SOORT").equals("GEEN")) {
            kortingStrategy.berekenPrijs(artikels);
        }
    }
}

package model;

import java.util.List;

/**
 * @Author Jonas De Boeck
 * */
public class Bereken {
    //Berekent totaalprijs ex. korting
    public static double berekenTotaalPrijs(List<Artikel> artikels) {
        double totaal = 0;;
        for(Artikel a : artikels){
            totaal += a.getPrijs();
        }
        return totaal;
    }

    //Berekend totale korting
    public static double berekenTotaalKorting(List<Artikel> artikels) {
        double korting = 0;
        for (Artikel artikel : artikels) {
            korting += artikel.getPrijs() - artikel.getKorting();
        }
        return korting;
    }

    //Berekent totale prijs inc. korting
    public static double berekenTotaalPrijsMetKorting(List<Artikel> artikels) {
        double prijs = 0;
        for (Artikel artikel : artikels) {
            prijs += artikel.getKorting();
        }
        return prijs;
    }

    //Berekent de totale BTW
    public static double berekenBTW(List<Artikel> artikels) {
        double btw = 0;
        for (Artikel artikel : artikels) {
            btw += (artikel.getPrijs() / 100) * 6;
        }
        return btw;
    }

    //Berekent de totale prijs ex. BTW
    public static double berekenTotaalPrijsExBTW(List<Artikel> artikels) {
        double prijs = 0;
        for (Artikel artikel : artikels) {
            prijs += artikel.getPrijs();
        }
        return prijs - berekenBTW(artikels);
    }
}

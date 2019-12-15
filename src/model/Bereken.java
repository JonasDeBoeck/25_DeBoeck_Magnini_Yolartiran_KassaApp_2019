package model;

import java.util.List;

public class Bereken {
    public static double berekenTotaalPrijs(List<Artikel> artikels) {
        double totaal = 0;;
        for(Artikel a : artikels){
            totaal += a.getPrijs();
        }
        return totaal;
    }

    public static double berekenTotaalKorting(List<Artikel> artikels) {
        double korting = 0;
        for (Artikel artikel : artikels) {
            korting += artikel.getKorting();
        }
        return korting;
    }

    public static double berekenTotaalPrijsMetKorting(List<Artikel> artikels) {
        return berekenTotaalPrijs(artikels) - berekenTotaalKorting(artikels);
    }

    public static double berekenBTW(List<Artikel> artikels) {
        double btw = 0;
        for (Artikel artikel : artikels) {
            btw += (artikel.getPrijs() / 100) * 6;
        }
        return btw;
    }

    public static double berekenTotaalPrijsExBTW(List<Artikel> artikels) {
        double prijs = 0;
        for (Artikel artikel : artikels) {
            prijs += artikel.getPrijs();
        }
        return prijs - berekenBTW(artikels);
    }
}

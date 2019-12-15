package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KassaTicket implements Ticket {

    public KassaTicket () {}

    @Override
    public void printTicket(List<Artikel> artikelList) {
        Set<Artikel> mandje = new HashSet<>(artikelList);
        String tussenLijn = "**********************************" ;
        String ticket = "Omschrijving      Aantal   Prijs  \n";
        System.out.println(ticket + tussenLijn);
        for (Artikel artikel : mandje) {
            String art = "%-18s%-9s%s%n";
            System.out.printf(art, artikel.getNaam(), telVoorkomen(artikel, artikelList), artikel.getPrijs());
        }
        System.out.println(tussenLijn);
        System.out.println("Betaald (inc. korting): ");
    }

    private int telVoorkomen (Artikel artikel, List<Artikel> artikels) {
        int count = 0;
        for (Artikel art : artikels) {
            if (art.equals(artikel)) {
                count ++;
            }
        }
        return count;
    }
}

package model.decorator;

import model.Artikel;

import java.util.List;

public class TotalePrijsFooter implements TicketDecorator {
    private Ticket ticket;

    public TotalePrijsFooter (Ticket ticket) {
        this.ticket = ticket;
    }
    @Override
    public void printTicket(List<Artikel> artikelList) {
        ticket.printTicket(artikelList);
        System.out.println("**********************************");
        System.out.println("Totale prijs (ex. korting): " + getTotaalPrijs(artikelList));
        System.out.println("Totale Korting: " + getTotaalKorting(artikelList));
    }

    private double getTotaalPrijs (List<Artikel> artikelList) {
        double prijs = 0;
        for (Artikel artikel : artikelList) {
            prijs += artikel.getPrijs();
        }
        return prijs;
    }

    private double getTotaalKorting (List<Artikel> artikelsList) {
        double korting = 0;
        for (Artikel artikel : artikelsList) {
            korting += artikel.getKorting();
        }
        return korting;
    }
}

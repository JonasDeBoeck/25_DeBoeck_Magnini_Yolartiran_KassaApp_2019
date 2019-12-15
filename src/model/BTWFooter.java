package model;

import java.util.List;

public class BTWFooter implements TicketDecorator {
    private Ticket ticket;

    public BTWFooter (Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public void printTicket(List<Artikel> artikelList) {
        ticket.printTicket(artikelList);
        System.out.println("**********************************");
        System.out.println("Prijs ex. BTW: " + totalePrijsMinBtw(artikelList));
        System.out.println("Totale BTW: " + berekenBTW(artikelList));
    }

    private double berekenBTW (List<Artikel> artikelList) {
        double btw = 0;
        for (Artikel artikel : artikelList) {
            btw += (artikel.getPrijs() / 100) * 6;
        }
        return btw;
    }

    private double totalePrijsMinBtw (List<Artikel> artikelList) {
        double prijs = 0;
        for (Artikel artikel : artikelList) {
            prijs += artikel.getPrijs();
        }
        return prijs - berekenBTW(artikelList);
    }
}

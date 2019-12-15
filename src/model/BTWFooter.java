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
        System.out.println("Prijs ex. BTW: " + Bereken.berekenTotaalPrijsExBTW(artikelList));
        System.out.println("Totale BTW: " + Bereken.berekenBTW(artikelList));
    }
}

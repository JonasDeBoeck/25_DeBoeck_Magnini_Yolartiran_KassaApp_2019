package model.decorator;

import model.Artikel;
import model.Bereken;

import java.util.List;

public class BTWFooter implements TicketDecorator {
    private Ticket ticket;

    public BTWFooter (Ticket ticket) {
        this.ticket = ticket;
    }

    //Print het ticket met extra waarden
    @Override
    public void printTicket(List<Artikel> artikelList) {
        ticket.printTicket(artikelList);
        System.out.println("**********************************");
        System.out.println("Prijs ex. BTW: " + Bereken.berekenTotaalPrijsExBTW(artikelList));
        System.out.println("Totale BTW: " + Bereken.berekenBTW(artikelList));
    }
}

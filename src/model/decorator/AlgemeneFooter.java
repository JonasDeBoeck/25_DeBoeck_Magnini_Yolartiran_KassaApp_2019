package model.decorator;

import model.Artikel;

import java.util.List;

public class AlgemeneFooter implements TicketDecorator {
    private Ticket ticket;
    private String message;

    public AlgemeneFooter (Ticket ticket, String message) {
        this.ticket = ticket;
        this.message = message;
    }

    //Print het ticket met een extra boodschap
    @Override
    public void printTicket(List<Artikel> artikelList) {
        ticket.printTicket(artikelList);
        System.out.println("**********************************");
        System.out.println(message);
    }
}

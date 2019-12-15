package model;

import java.util.List;

public class AlgemeneFooter implements TicketDecorator {
    private Ticket ticket;
    private String message;

    public AlgemeneFooter (Ticket ticket, String message) {
        this.ticket = ticket;
        this.message = message;
    }

    @Override
    public void printTicket(List<Artikel> artikelList) {
        ticket.printTicket(artikelList);
        System.out.println("**********************************");
        System.out.println(message);
    }
}

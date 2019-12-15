package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class DatumTijdHeader implements TicketDecorator {
    private Ticket ticket;

    public DatumTijdHeader (Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public void printTicket(List<Artikel> artikelList) {
        System.out.println("Datum: " + LocalDate.now() + " Tijd: " + LocalTime.now());
        System.out.println("**********************************");
        ticket.printTicket(artikelList);
    }
}

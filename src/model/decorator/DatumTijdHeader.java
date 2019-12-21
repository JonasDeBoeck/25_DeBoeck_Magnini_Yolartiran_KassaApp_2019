package model.decorator;

import model.Artikel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class DatumTijdHeader implements TicketDecorator {
    private Ticket ticket;

    public DatumTijdHeader (Ticket ticket) {
        this.ticket = ticket;
    }

    //Print het ticket met extra waarden
    @Override
    public void printTicket(List<Artikel> artikelList) {
        System.out.println("Datum: " + LocalDate.now() + " Tijd: " + LocalTime.now().withNano(0));
        System.out.println("**********************************");
        ticket.printTicket(artikelList);
    }
}

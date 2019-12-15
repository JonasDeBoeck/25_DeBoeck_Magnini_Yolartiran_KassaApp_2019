package model;

import java.util.List;

public class AlgemeneHeader implements TicketDecorator{
    private Ticket ticket;
    private String tekst;

    public AlgemeneHeader (Ticket ticket, String algemeneHeaderTekst) {
        this.ticket = ticket;
        tekst = algemeneHeaderTekst;
    }

    @Override
    public void printTicket(List<Artikel> artikelList) {
        System.out.println(tekst);
        System.out.println("**********************************");
        ticket.printTicket(artikelList);
    }
}

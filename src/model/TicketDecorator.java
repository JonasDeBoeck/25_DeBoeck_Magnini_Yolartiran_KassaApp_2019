package model;

import java.util.List;

public interface TicketDecorator extends Ticket{
    @Override
    void printTicket(List<Artikel> artikelList);
}

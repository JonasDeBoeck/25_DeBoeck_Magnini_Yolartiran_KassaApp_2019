package model.decorator;

import model.Artikel;

import java.util.List;

/**
 * @Author Jonas De Boeck
 * */

public interface TicketDecorator extends Ticket{
    @Override
    void printTicket(List<Artikel> artikelList);
}

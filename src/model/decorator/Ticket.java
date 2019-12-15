package model.decorator;

import model.Artikel;

import java.util.List;

public interface Ticket {
    void printTicket (List<Artikel> artikelList);
}

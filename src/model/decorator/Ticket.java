package model.decorator;

import model.Artikel;

import java.util.List;

/**
 * @Author Jonas De Boeck
 * */

public interface Ticket {
    void printTicket (List<Artikel> artikelList);
}

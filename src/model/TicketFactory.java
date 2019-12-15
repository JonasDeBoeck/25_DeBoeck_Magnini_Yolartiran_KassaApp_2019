package model;

import database.PropertiesLoadSave;

public class TicketFactory {
    private static TicketFactory uniqueFactory;

    private TicketFactory() {}

    public static TicketFactory getInstance() {
        if (uniqueFactory == null) {
            uniqueFactory = new TicketFactory();
        }
        return uniqueFactory;
    }

    public Ticket getTicket () {
        Ticket ticket = new KassaTicket();
        if (!PropertiesLoadSave.load("HEADER").equals("")) {
            switch (PropertiesLoadSave.load("HEADER")) {
                case "Algemene boodschap":
                    ticket = new AlgemeneHeader(ticket, PropertiesLoadSave.load("HBOODSCHAP"));
                    break;
                case "Datum en tijd van afprinten":
                    ticket = new DatumTijdHeader(ticket);
                    break;
            }
        }
        if (!PropertiesLoadSave.load("FOOTER").equals("")) {
            switch (PropertiesLoadSave.load("FOOTER")) {
                case "Totale prijs zonder en met korting":
                    ticket = new TotalePrijsFooter(ticket);
                    break;
                case "Betaalde prijs ex. BTW en totale BTW":
                    ticket = new BTWFooter(ticket);
                    break;
                case "Algemene boodschap":
                    ticket = new AlgemeneFooter(ticket, PropertiesLoadSave.load("FBOODSCHAP"));
                    break;
            }
        }
        return ticket;
    }
}

package model;

import database.ArtikelDbInMemory;

import java.util.HashMap;

public class Winkel {
    private HashMap<String, Artikel> artikels;

    public Winkel () {
        artikels = ArtikelDbInMemory.load("artikel.txt");
    }

    public HashMap<String, Artikel> getArtikels() {
        return artikels;
    }
}

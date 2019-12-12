package model;

import java.util.ArrayList;
import java.util.Objects;

public class Artikel {
    private String artikelId, naam;
    private double prijs, korting;
    private int aantalInStock;
    private ArtikelCategorie artikelCategorie;

    public Artikel (String artikelId, String naam, double prijs, int aantalInStock, String artikelCategorie) {
        setArtikelId(artikelId);
        setNaam(naam);
        setPrijs(prijs);
        setAantalInStock(aantalInStock);
        setArtikelCategorie(artikelCategorie);
        korting = 0;
    }

    public String toString() {
        return artikelId + "," + naam + "," + artikelCategorie.getArtikelCategorieAlsString() + "," + parsePrijs(prijs) + "," + aantalInStock;
    }

    public ArrayList<String> lijstArtikel() {
        ArrayList<String> lijst = new ArrayList<>();
        lijst.add(this.artikelId);
        lijst.add(this.naam);
        lijst.add(this.artikelCategorie.getArtikelCategorieAlsString());
        lijst.add(Double.toString(this.prijs));
        lijst.add(Integer.toString(this.aantalInStock));
        return lijst;
    }

    public double getKorting() {
        return korting;
    }

    private String parsePrijs(double prijs) {
        return Double.toString(prijs).replace(",", ". ");
    }

    public String getArtikelId() {
        return artikelId;
    }

    public String getNaam() {
        return naam;
    }

    public double getPrijs() {
        return prijs;
    }

    public int getAantalInStock() {
        return aantalInStock;
    }

    private void setArtikelId(String artikelId) {
        this.artikelId = artikelId;
    }

    private void setNaam(String naam) {
        this.naam = naam;
    }

    private void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public void setAantalInStock(int aantalInStock) {
        this.aantalInStock = aantalInStock;
    }

    public ArtikelCategorie getArtikelCategorie() {
        return artikelCategorie;
    }

    private void setArtikelCategorie(String artikelCategorie) {
        this.artikelCategorie = ArtikelCategorie.valueOf(artikelCategorie.toUpperCase());
    }

    public void setKorting (double korting) {
        this.korting = korting;
    }

    @Override
    public boolean equals (Object o) {
        boolean result = false;
        if (o instanceof Artikel) {
            Artikel a = (Artikel) o;
            if (this.artikelId.equals(((Artikel) o).artikelId)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(artikelId);
    }
}

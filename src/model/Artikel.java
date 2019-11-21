package model;

public class Artikel {
    private String artikelId, naam;
    private double prijs;
    private int aantalInStock;
    private ArtikelCategorie artikelCategorie;

    public Artikel (String artikelId, String naam, double prijs, int aantalInStock, String artikelCategorie) {
        setArtikelId(artikelId);
        setNaam(naam);
        setPrijs(prijs);
        setAantalInStock(aantalInStock);
        setArtikelCategorie(artikelCategorie);
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

    private void setAantalInStock(int aantalInStock) {
        this.aantalInStock = aantalInStock;
    }

    public ArtikelCategorie getArtikelCategorie() {
        return artikelCategorie;
    }

    private void setArtikelCategorie(String artikelCategorie) {
        this.artikelCategorie = ArtikelCategorie.valueOf(artikelCategorie.toUpperCase());
    }
}

package model;

public class KortingContext {
    private ArtikelCategorie categorie;
    private int percentage;
    private KortingStrategy kortingStrategy;

    public KortingContext(int percentage, ArtikelCategorie categorie) {
        setCategorie(categorie);
        setPercentage(percentage);
    }

    private void setCategorie(ArtikelCategorie categorie) {
        this.categorie = categorie;
    }

    public ArtikelCategorie getCategorie() {
        return categorie;
    }

    private void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public int getPercentage() {
        return percentage;
    }
}

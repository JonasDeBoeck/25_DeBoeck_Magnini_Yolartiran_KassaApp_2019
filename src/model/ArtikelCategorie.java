package model;

/**
 * @Author Jonas De Boeck
 * */

public enum ArtikelCategorie {
    GR1 ("gr1"),
    GR2 ("gr2");

    private String artikelCategorieAlsString;

    private ArtikelCategorie (String artikelCategorieAlsString) {
        setArtikelCategorieAlsString(artikelCategorieAlsString);
    }

    public String getArtikelCategorieAlsString() {
        return artikelCategorieAlsString;
    }

    private void setArtikelCategorieAlsString(String artikelCategorieAlsString) {
        this.artikelCategorieAlsString = artikelCategorieAlsString;
    }
}

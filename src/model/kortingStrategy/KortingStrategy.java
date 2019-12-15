package model.kortingStrategy;

import model.Artikel;

import java.util.List;

public interface KortingStrategy {
    void berekenPrijs(List<Artikel> lijst);
}

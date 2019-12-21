package model.kortingStrategy;

import model.Artikel;

import java.util.List;

/**
 * @Author Jonas De Boeck, Sinan Yolartiran
 * */

public interface KortingStrategy {
    void berekenPrijs(List<Artikel> lijst);
}

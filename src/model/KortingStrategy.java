package model;

import java.util.List;

public interface KortingStrategy {
    double berekenPrijs(List<Artikel> lijst);
}

package model.state;

import model.Artikel;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Thibault Magnini
 * */

public interface State extends Serializable{

    default void betaal(){
        throw new IllegalArgumentException("Betalen is niet mogelijk!");
    }

    default void annuleer(){
        throw new IllegalArgumentException("niet annuleerbaar!");
    }

    default void sluitAf(){
        throw new IllegalArgumentException("niet kunnen afsluiten");
    }

    default void zetOnHold(){
        throw new IllegalArgumentException("niet mogelijk om op hold te zetten");
    }

    default void zetOffHold(){
        throw new IllegalArgumentException("niet mogelijk om uit hold te halen");
    }

    default void verwijderKarOnHold(){
        throw new IllegalArgumentException("niet mogelijk om te verwijderem");
    }

    default void voegArtikelToe(Artikel artikel, List<Artikel> artikels){
        throw new IllegalArgumentException("kan niet worden toegevoegd");
    }

    default void verwijderArtikel(Artikel artikel, List<Artikel> artikels){
        throw new IllegalArgumentException("kan niet worden verwijderd");
    }

    default void maakLeeg(List<Artikel> artikels){
        throw new IllegalArgumentException("kan niet worden leeg gemaakt");
    }
}

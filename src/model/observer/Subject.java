package model.observer;

/**
 * @Author Jonas De Boeck, Thibault Magnini
 * */
public interface Subject {
    void notifyObservers ();
    void registerObserver(Observer observer);
    void notifyObserversAfsluit();
    void notifyObserversBetaal();
}

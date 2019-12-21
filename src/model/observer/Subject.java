package model.observer;

public interface Subject {
    void notifyObservers ();
    void registerObserver(Observer observer);
    void notifyObserversAfsluit();
    void notifyObserversBetaal();
}

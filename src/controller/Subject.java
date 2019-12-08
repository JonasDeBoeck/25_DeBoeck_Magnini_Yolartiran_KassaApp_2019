package controller;

public interface Subject {
    void notifyObservers ();
    void registerObserver(Observer observer);
    void notifyObserversAfsluit();
}

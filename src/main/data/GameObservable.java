package main.data;

import java.util.Observable;
import java.util.Observer;

public abstract class GameObservable extends Observable {

    @Override
    public abstract void addObserver(Observer o);

    @Override
    public abstract void notifyObservers(Object arg);
}

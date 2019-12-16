package main.data;

import java.util.Observable;
import java.util.Observer;

public interface GameObserver extends Observer {

    @Override
    public void update(Observable o, Object arg);
}

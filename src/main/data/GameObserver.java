package main.data;

import java.util.Observable;
import java.util.Observer;

// TODO decide if worth keeping or not

public interface GameObserver extends Observer {

    @Override
    public void update(Observable o, Object arg);
}

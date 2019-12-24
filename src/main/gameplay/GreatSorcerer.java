package main.gameplay;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public final class GreatSorcerer implements Observer {

    private static GreatSorcerer instance = null;
    private List<String> info;

    private GreatSorcerer() {
        info = new ArrayList<>();
    }

    public static GreatSorcerer getInstance() {
        if (instance == null) {
            instance = new GreatSorcerer();
        }
        return instance;
    }

    List<String> getInfo() {
        return info;
    }

    void newRound(final int roundNumber) {
        if (roundNumber != 1) {
            this.info.add("\n");
        }
        this.info.add("~~ Round " + roundNumber + " ~~\n");
    }

    void startFinalResults() {
        this.info.add("\n~~ Results ~~\n");
    }

    void writeResult(final String heroInfo) {
        this.info.add(heroInfo);
    }

    @Override
    public void update(final Observable object,
                       final Object message) {
        info.add((String) message);
    }
}

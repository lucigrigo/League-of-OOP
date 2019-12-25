package main.gameplay;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public final class GreatSorcerer implements Observer {

    private static GreatSorcerer instance = null;
    private List<String> gameInfo;

    private GreatSorcerer() {
        gameInfo = new ArrayList<>();
    }

    public static GreatSorcerer getInstance() {
        if (instance == null) {
            instance = new GreatSorcerer();
        }
        return instance;
    }

    List<String> getInfo() {
        return gameInfo;
    }

    void newRound(final int roundNumber) {
        if (roundNumber != 1) {
            this.gameInfo.add("\n");
//            this.gameInfo.add("-------------- END OF ROUND --------------\n");
        }
        this.gameInfo.add("~~ Round " + roundNumber + " ~~\n");
    }

    void startFinalResults() {
        this.gameInfo.add("\n~~ Results ~~\n");
    }

    void writeResult(final String heroInfo) {
        this.gameInfo.add(heroInfo);
    }

    @Override
    public void update(final Observable object,
                       final Object message) {
        gameInfo.add((String) message);
    }
}

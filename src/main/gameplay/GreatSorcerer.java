package main.gameplay;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Implements the OBSERVER of the game.
 */
public final class GreatSorcerer implements Observer {

    private static GreatSorcerer instance = null;
    /*
    Game information, gathered throughout its unfolding.
     */
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

    /**
     * @return all game information gathered so far
     */
    List<String> getInfo() {
        return gameInfo;
    }

    /**
     * Adds info about beginning of a new round.
     *
     * @param roundNumber new round number
     */
    void newRound(final int roundNumber) {
        if (roundNumber != 1) {
            this.gameInfo.add("\n");
        }
        this.gameInfo.add("~~ Round " + roundNumber + " ~~\n");
    }

    /**
     * Adds title for final results.
     */
    void startFinalResults() {
        this.gameInfo.add("\n~~ Results ~~\n");
    }

    /**
     * Writes result about a hero to the info.
     *
     * @param heroInfo information about a specific hero
     */
    void writeResult(final String heroInfo) {
        this.gameInfo.add(heroInfo);
    }

    /**
     * Saves received info from one of the observable objects.
     *
     * @param object  instance of an observable object, such as an angel, or a hero
     * @param message the computed observation that ought to be saved
     */
    @Override
    public void update(final Observable object,
                       final Object message) {
        gameInfo.add((String) message);
    }
}

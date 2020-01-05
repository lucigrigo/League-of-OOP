/*
    Programare Orientata pe Obiecte
    League of OOP - Stage 1 & Stage 2
    Grigore Lucian-Florin 324CD
    Facultatea de Automatica si Calculatoare
    Universitatea Politehnica, Bucuresti
 */

package main;

import main.data.InputData;
import main.gameplay.Game;
import main.gameplay.IOAssistant;

/**
 * Main starting point of the game.
 */
public final class Main {

    private Main() {
        // just to trigger checkstyle :)
    }

    public static void main(final String[] args) {
        // creating input-output instance
        IOAssistant ioAssistant = new IOAssistant(args[0], args[1]);
        // reading input data
        InputData inputData = ioAssistant.readInput();
        // starting the game
        Game.getInstance().startGame(inputData);
        // writing endgame results
        ioAssistant.writeFinalResults(inputData.getCharacters());
    }
}

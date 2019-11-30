package main;

import main.data.IOAssistant;
import main.data.InputData;
import main.gameplay.Game;

public final class Main {

    private Main() {
        // just to trigger checkstyle :)
    }

    public static void main(final String[] args) {
        IOAssistant ioAssistant = new IOAssistant(args[0], args[1]);
        InputData inputData = ioAssistant.readInput();

        Game.getInstance().startGame(inputData);

        ioAssistant.writeFinalResults(inputData.getCharacters());
    }
}

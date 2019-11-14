package main.gameplay;

import main.data.InputData;

public final class Game {

    private static Game instance = null;

    private Game() {
        // TODO add anything if needed
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void startGame(final InputData inputData,
                          final Statistics statistics) {
        // TODO implement the game logic
    }
}

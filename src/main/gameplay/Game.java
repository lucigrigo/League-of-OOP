package main.gameplay;

import main.characters.GameCharacter;
import main.data.InputData;
import main.data.MovementType;

import java.util.List;

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

    private void applyCurrentRoundMoves(final List<GameCharacter> characters,
                                        final MovementType[] roundMoves) {
        //int charactersNumber = characters.size();
        for (int i = 0; i < roundMoves.length; i++) {
            characters.get(i).applyMove(roundMoves[i]);
        }
    }

    public void startGame(final InputData data,
                          final Statistics statistics) {
        int maxRounds = data.getNrRounds();
        //int charactersNumber = data.getNrCharacters();
        for (int i = 0; i < maxRounds; i++) {
            applyCurrentRoundMoves(data.getCharacters(), data.getCurrentRoundMoves(i));
            // TODO check for fights

            // TODO and make them happen :)
        }
    }
}

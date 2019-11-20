package main.gameplay;

import main.characters.GameCharacter;
import main.data.InputData;
import main.data.LocationType;
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
            if (!characters.get(i).isCurrentlyFighting()
                    || !characters.get(i).isIncapacitated())
                characters.get(i).applyMove(roundMoves[i]);
        }
    }

    private void manageFight(final GameCharacter character1,
                             final GameCharacter character2,
                             final LocationType location) {
        if (character1.getAbilityOverTime(character2, location).getTotalDamage() >= character2.getHealth()) {

        }
        if (character2.getAbilityOverTime(character1, location).getTotalDamage() >= character1.getHealth()) {

        }
    }

    private void searchForFights(final List<GameCharacter> characters,
                                 LocationType[][] map) {
        for (GameCharacter character1 : characters) {
            for (GameCharacter character2 : characters) {
                if ((character1 != character2)
                        && (character1.getColon() == character2.getColon())
                        && (character1.getRow() == character2.getRow())) {
                    manageFight(character1, character2,
                            map[character1.getRow()][character1.getColon()]);
                }
            }
        }
    }

    public void startGame(final InputData data,
                          final Statistics statistics) {
        int maxRounds = data.getNrRounds();
        int currentRound = 0;
        while (currentRound < maxRounds) {
            applyCurrentRoundMoves(data.getCharacters(), data.getCurrentRoundMoves(currentRound));
            searchForFights(data.getCharacters(), data.getMap());
            currentRound++;
        }
    }
}

package main.gameplay;

import main.characters.GameCharacter;
import main.data.InputData;
import main.data.LocationType;
import main.data.MovementType;

import java.util.List;

public final class Game {

    private static Game instance = null;
    private int maxRounds;
    private int currentRound;

    private Game() {
        // TODO add anything if needed
        maxRounds = 0;
        currentRound = 0;
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    private void applyCurrentRoundMoves(final List<GameCharacter> characters,
                                        final MovementType[] roundMoves) {
        for (int i = 0; i < roundMoves.length; i++) {
            if (!characters.get(i).isIncapacitated()) {
                characters.get(i).applyMove(roundMoves[i]);
            }
        }
    }

    private void manageFight(final GameCharacter character1,
                             final GameCharacter character2,
                             final LocationType location) {
        // todo "lupta nu va mai avea loc?"
        if (character1.getTotalOverTimeDamage(location, character2, maxRounds - currentRound)
                >= character2.getHealth()) {
//            character1.fightWon();
//            character2.hasDied();
//            return;
        }
        if (character2.getAbilityOverTime(character1, location).getTotalDamage() >= character1.getHealth()) {
//            character2.hasDied();
//            character1.fightWon();
//            return;
        }
        // todo add win event
        OverTimeAbility overTimeAbility1 = character1.getAbilityOverTime(character2, location);
        OverTimeAbility overTimeAbility2 = character2.getAbilityOverTime(character1, location);
        character1.setAbilityAffectedBy(overTimeAbility2);
        character2.setAbilityAffectedBy(overTimeAbility1);

        int instantDamage1 = character1.computeDamageAgainst(character2, location);
        int instantDamage2 = character2.computeDamageAgainst(character1, location);
        character1.takeDamage(instantDamage2);
        character2.takeDamage(instantDamage1);
    }

    private void searchForFights(final List<GameCharacter> characters,
                                 final LocationType[][] map) {
        boolean[][] checkedFights = new boolean[map.length][map.length];
        for (GameCharacter character1 : characters) {
            for (GameCharacter character2 : characters) {
                if ((character1 != character2)
                        && (character1.getColon() == character2.getColon())
                        && (character1.getRow() == character2.getRow())
                        && (!checkedFights[character1.getRow()][character1.getColon()])) {
                    manageFight(character1, character2,
                            map[character1.getRow()][character1.getColon()]);
                    checkedFights[character1.getRow()][character1.getColon()] = true;
                }
            }
        }
    }

    private void applyOverTimeDamage(final List<GameCharacter> characters) {
        for (GameCharacter character : characters) {
            if (character.getAbilityAffectedBy() != null) {
                character.takeDamage(character.getAbilityAffectedBy().getDamage());
                character.getAbilityAffectedBy().roundPassed();
            }
        }
    }

    public void checkForOverTimeAbilitiesEnd(final List<GameCharacter> characters) {
        for (GameCharacter character : characters) {
            if (character.getAbilityAffectedBy() != null) {
                if (character.getAbilityAffectedBy().getDuration() == 0) {
                    character.setAbilityAffectedBy(null);
                }
            }
        }
    }

    private void manageOvertimeFights(final List<GameCharacter> characters) {
        for (GameCharacter character : characters) {
            if (character.getAbilityAffectedBy() != null) {
                character.takeDamage(character.getAbilityAffectedBy().getDamage());
                character.getAbilityAffectedBy().roundPassed();
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
            applyOverTimeDamage(data.getCharacters());
            checkForOverTimeAbilitiesEnd(data.getCharacters());
            currentRound++;
        }
        manageOvertimeFights(data.getCharacters());
    }
}

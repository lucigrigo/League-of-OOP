package main.gameplay;

import main.angels.Angel;
import main.data.InputData;
import main.data.LocationType;
import main.data.MovementType;
import main.heroes.Hero;

import java.util.List;

/**
 * Class to manage game unfolding.
 */
public final class Game {

    private static Game instance = null;

    private Game() {
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    /**
     * Function that applies current round moves.
     *
     * @param characters game heroes
     * @param roundMoves current round moves
     */
    private void applyCurrentRoundMoves(final List<Hero> characters,
                                        final MovementType[] roundMoves) {
        for (int i = 0; i < roundMoves.length; i++) {
            if (!characters.get(i).isIncapacitated()
                    && !characters.get(i).isDead()) {
                characters.get(i).applyMove(roundMoves[i]);
            } else if (characters.get(i).isIncapacitated()) {
                characters.get(i).getAbilityAffectedBy().incapacityUsed();
            }
        }
    }

    /**
     * Function to apply damage from overtime abilities and reducing
     * their duration.
     *
     * @param characters heroes of the game
     */
    private void applyOverTimeDamage(final List<Hero> characters) {
        for (Hero character : characters) {
            if (character.getAbilityAffectedBy() != null
                    && !character.isDead()
                    && character.getAbilityAffectedBy().getDuration() > 0) {
                character.takeDamage(character.getAbilityAffectedBy().getOvertimeDamage(),
                        true, false);
                character.getAbilityAffectedBy().damageDealt();
            }
        }
    }

    private void resetAngelBonuses(final List<Hero> characters) {
        for (Hero hero : characters) {
            hero.sendMessages();
            hero.resetAngelBonus();
            hero.checkForLevelUp();
        }
    }

    /**
     * Function to search for potential fights in the current round.
     *
     * @param characters heroes of the game
     * @param map        map of the game
     */
    private void searchForFights(final List<Hero> characters,
                                 final LocationType[][] map) {
        boolean[][] checkedFights = new boolean[map.length][map.length];
        for (Hero character1 : characters) {
            for (Hero character2 : characters) {
                if ((character1 != character2)
                        && (character1.getColon() == character2.getColon())
                        && (character1.getRow() == character2.getRow())
                        && (!checkedFights[character1.getRow()][character1.getColon()])
                        && ((!character1.isDead())
                        && (!character2.isDead()))) {
//                    System.out.println(character1.getFullName() + " health is " + character1.getHealth());
//                    System.out.println(character2.getFullName() + " health is " + character2.getHealth());
                    manageFight(character1, character2,
                            map[character1.getRow()][character1.getColon()]);
                    checkedFights[character1.getRow()][character1.getColon()] = true;
                }
            }
        }
    }

    /**
     * Manages the fight between two characters.
     *
     * @param character1 first hero
     * @param character2 second hero
     * @param location   the place where the fight takes place
     */
    private void manageFight(final Hero character1,
                             final Hero character2,
                             final LocationType location) {
        character2.getAttackedBy(character1, location);
        character1.getAttackedBy(character2, location);

        character2.getAffectedBy(character1, location);
        character1.getAffectedBy(character2, location);
    }

    /**
     * Function that does round ending routine for every hero.
     *
     * @param characters heroes of the game
     */
    private void roundEnding(final List<Hero> characters) {
        for (Hero character : characters) {
            character.doRoundEndingRoutine();
        }
    }

    private void checkForInteraction(final Angel angel,
                                     final List<Hero> heroes) {
        for (Hero hero : heroes) {
            if (angel.getRow() == hero.getRow()
                    && angel.getCol() == hero.getColon()) {
                hero.getHelpedBy(angel);
            }
        }
    }

    private void angelSpawning(final InputData data,
                               final int currentRound) {
        List<Angel> currentRoundAngels = data.getAngels().get(currentRound + 1);
        if (currentRoundAngels.isEmpty()) {
            return;
        }
        for (Angel angel : currentRoundAngels) {
            angel.spawn();
            checkForInteraction(angel, data.getCharacters());
        }
    }

    /**
     * Main function that manages the unfolding of the game.
     *
     * @param data game data (information)
     */
    public void startGame(final InputData data) {
        int maxRounds = data.getNrRounds();
        int currentRound = 0;
//        angelSpawning(data, -1);
        while (currentRound < maxRounds) {
//            System.out.println("RUNDA " + currentRound);
            GreatSorcerer.getInstance().newRound(currentRound + 1);
            // moving the heroes
            applyCurrentRoundMoves(data.getCharacters(),
                    data.getCurrentRoundMoves(currentRound));
            // applying overtime damage
            applyOverTimeDamage(data.getCharacters());
            // looking for fights
            searchForFights(data.getCharacters(), data.getMap());
            resetAngelBonuses(data.getCharacters());

            // todo angel interaction
            angelSpawning(data, currentRound);

            // doing round ending routines
            roundEnding(data.getCharacters());
            // increasing round number
            currentRound++;
        }
    }
}

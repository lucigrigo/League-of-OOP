package main.gameplay;

import main.data.InputData;
import main.data.LocationType;
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

    /**
     * Manages the fight between two characters.
     *
     * @param hero1    first hero
     * @param hero2    second hero
     * @param location the place where the fight takes place
     */
    void manageFight(final Hero hero1,
                     final Hero hero2,
                     final LocationType location) {
        hero2.getAttackedBy(hero1, location);
        hero1.getAttackedBy(hero2, location);

        hero2.getAffectedBy(hero1, location);
        hero1.getAffectedBy(hero2, location);
    }

    /**
     * Checks for level-up possibility for each hero.
     *
     * @param characters heroes of the game
     */
    private void checkLevelUp(final List<Hero> characters) {
        for (Hero hero : characters) {
            hero.checkForLevelUp();
        }
    }

    /**
     * Function that does round ending routine for every hero.
     *
     * @param heroes heroes of the game
     */
    private void roundEnding(final List<Hero> heroes) {
        for (Hero hero : heroes) {
            hero.doRoundEndingRoutine();
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
        while (currentRound < maxRounds) {
            GreatSorcerer.getInstance().newRound(currentRound + 1);
            // applying overtime damage
            applyOverTimeDamage(data.getCharacters());
            // moving the heroes
            Map.getInstance().applyCurrentRoundMoves(data.getCurrentRoundMoves((currentRound)));

            // looking for fights
            Map.getInstance().lookForFights();
            checkLevelUp(data.getCharacters());

            // angel interaction
            Map.getInstance().spawnAngels(data.getAngels(), currentRound);

            // doing round ending routines
            roundEnding(data.getCharacters());
            // increasing round number
            currentRound++;
        }
    }
}

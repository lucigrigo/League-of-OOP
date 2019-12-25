package main.gameplay;

import main.angels.Angel;
import main.data.LocationType;
import main.data.MovementType;
import main.heroes.Hero;

import java.util.HashMap;
import java.util.List;

final class Map {

    private static Map instance = null;
    private LocationType[][] map;
    private List<Hero> heroes;

    private Map() {

    }

    static Map getInstance() {
        if (instance == null) {
            instance = new Map();
        }
        return instance;
    }

    void setHeroes(final List<Hero> gameHeroes) {
        this.heroes = gameHeroes;
    }

    void setMap(final LocationType[][] map) {
        this.map = map;
    }

    /**
     * Function that applies current round moves.
     *
     * @param currentRoundMoves current round moves
     */
    void applyCurrentRoundMoves(final MovementType[] currentRoundMoves) {
        for (int i = 0; i < currentRoundMoves.length; i++) {
            if (!heroes.get(i).isIncapacitated()
                    && !heroes.get(i).isDead()) {
                heroes.get(i).applyMove(currentRoundMoves[i]);
            } else if (heroes.get(i).isIncapacitated()) {
                heroes.get(i).getAbilityAffectedBy().incapacityUsed();
            }
        }
    }

    /**
     * Function to search for potential fights in the current round.
     */
    void lookForFights() {
        boolean[][] checkedFights = new boolean[map.length][map.length];
        for (Hero hero1 : heroes) {
            for (Hero hero2 : heroes) {
                if ((hero1 != hero2)
                        && (hero1.getColon() == hero2.getColon())
                        && (hero1.getRow() == hero2.getRow())
                        && (!checkedFights[hero1.getRow()][hero1.getColon()])
                        && ((!hero1.isDead())
                        && (!hero2.isDead()))) {
                    Game.getInstance().manageFight(hero1, hero2,
                            map[hero1.getRow()][hero2.getColon()]);
                    checkedFights[hero1.getRow()][hero1.getColon()] = true;
                }
            }
        }
    }

    void spawnAngels(final HashMap<Integer, List<Angel>> angels,
                     final int currentRoundNumber) {
        List<Angel> currentRoundAngels = angels.get(currentRoundNumber + 1);
        if (currentRoundAngels.isEmpty()) {
            return;
        }
        for (Angel angel : currentRoundAngels) {
            angel.spawn();
            checkForAngelInteraction(angel);
        }
    }

    private void checkForAngelInteraction(final Angel angel) {
        for (Hero hero : heroes) {
            if (angel.getRow() == hero.getRow()
                    && angel.getCol() == hero.getColon()) {
                hero.getHelpedBy(angel);
            }
        }
    }
}

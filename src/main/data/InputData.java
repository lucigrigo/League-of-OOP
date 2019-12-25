package main.data;

import main.angels.Angel;
import main.heroes.Hero;

import java.util.HashMap;
import java.util.List;

/**
 * Class that represents input data for the game.
 */
public final class InputData {
    private List<Hero> characters;
    private int nrRounds;
    private MovementType[][] instructions;
    private LocationType[][] map;
    private HashMap<Integer, List<Angel>> angels;

    // Constructor
    public InputData(final LocationType[][] map,
                     final List<Hero> characters,
                     final int nrRounds,
                     final MovementType[][] instructions,
                     final HashMap<Integer, List<Angel>> angels) {
        this.characters = characters;
        this.nrRounds = nrRounds;
        this.instructions = instructions;
        this.map = map;
        this.angels = angels;
    }

    // returning number of rounds
    public int getNrRounds() {
        return nrRounds;
    }

    // returning heroes of the game
    public List<Hero> getCharacters() {
        return characters;
    }

    // returning movements of the round
    public MovementType[] getCurrentRoundMoves(final int roundNumber) {
        return this.instructions[roundNumber];
    }

    public HashMap<Integer, List<Angel>> getAngels() {
        return angels;
    }
}

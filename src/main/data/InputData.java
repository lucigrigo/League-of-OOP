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
    private HashMap<Integer, List<Angel>> angels;

    /*
     Constructor
     */
    public InputData(final List<Hero> characters,
                     final int nrRounds,
                     final MovementType[][] instructions,
                     final HashMap<Integer, List<Angel>> angels) {
        this.characters = characters;
        this.nrRounds = nrRounds;
        this.instructions = instructions;
        this.angels = angels;
    }

    /**
     * @return maximum number of rounds of the game
     */
    public int getNrRounds() {
        return nrRounds;
    }

    /**
     * @return all the heroes of the game
     */
    public List<Hero> getCharacters() {
        return characters;
    }

    /**
     * @param roundNumber current round number
     * @return movements for all the heros of the current round
     */
    public MovementType[] getCurrentRoundMoves(final int roundNumber) {
        return this.instructions[roundNumber];
    }

    /**
     * @return all the angels of the game
     */
    public HashMap<Integer, List<Angel>> getAngels() {
        return angels;
    }
}

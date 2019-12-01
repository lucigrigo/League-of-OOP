package main.data;

import main.characters.GameCharacter;

import java.util.List;

/**
 * Class that represents input data for the game.
 */
public class InputData {
    private List<GameCharacter> characters;
    private int nrRounds;
    private MovementType[][] instructions;
    private LocationType[][] map;

    // Constructor
    public InputData(final int mapHeight,
                     final int mapWidth,
                     final LocationType[][] map,
                     final int nrCharacters,
                     final List<GameCharacter> characters,
                     final int nrRounds,
                     final MovementType[][] instructions) {
        this.characters = characters;
        this.nrRounds = nrRounds;
        this.instructions = instructions;
        this.map = map;
    }

    // returning number of rounds
    public final int getNrRounds() {
        return nrRounds;
    }

    // returning heroes of the game
    public final List<GameCharacter> getCharacters() {
        return characters;
    }

    // returning movements of the round
    public final MovementType[] getCurrentRoundMoves(final int roundNumber) {
        return this.instructions[roundNumber];
    }

    // returning the map
    public final LocationType[][] getMap() {
        return map;
    }
}

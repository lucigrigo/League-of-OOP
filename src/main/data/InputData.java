package main.data;

import main.characters.GameCharacter;

import java.util.List;

public class InputData {
    private int mapHeight;
    private int mapWidth;
    private int nrCharacters;
    private List<GameCharacter> characters;
    private int nrRounds;
    private MovementType[][] instructions;
    private LocationType[][] map;

    InputData(final int mapHeight,
              final int mapWidth,
              final LocationType[][] map,
              final int nrCharacters,
              final List<GameCharacter> characters,
              final int nrRounds,
              final MovementType[][] instructions) {
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
        this.nrCharacters = nrCharacters;
        this.characters = characters;
        this.nrRounds = nrRounds;
        this.instructions = instructions;
        this.map = map;
    }

    public final int getNrRounds() {
        return nrRounds;
    }

    public final List<GameCharacter> getCharacters() {
        return characters;
    }

    public final MovementType[][] getInstructions() {
        return instructions;
    }

    public final int getNrCharacters() {
        return this.characters.size();
    }

    public final MovementType[] getCurrentRoundMoves(final int roundNumber) {
        return this.instructions[roundNumber];
    }

    public final LocationType[][] getMap() {
        return map;
    }
}

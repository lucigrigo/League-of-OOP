package main.data;

import main.characters.GameCharacter;
import main.characters.Knight;
import main.characters.Pyromancer;
import main.characters.Rogue;
import main.characters.Wizard;
import main.gameplay.Statistics;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IOAssistant {
    private String inputPath;
    private String outputPath;

    public IOAssistant(final String inputPath,
                       final String outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    public final InputData readInput() {
        int mapWidth;
        int mapHeight;
        int nrCharacters;
        int nrRounds;
        List<GameCharacter> characters = new ArrayList<>();
        char[][] instructions;
        LocationType[][] map;

        File inputFile = new File(inputPath);
        try {
            Scanner scanner = new Scanner(inputFile);

            mapHeight = scanner.nextInt();
            mapWidth = scanner.nextInt();

            map = new LocationType[mapHeight][mapWidth];
            for (int i = 0; i < mapHeight; i++) {
                String line = scanner.next();
                for (int j = 0; j < mapWidth; j++) {
                    switch (line.charAt(j)) {
                        case 'L':
                            map[i][j] = LocationType.LAND;
                            break;
                        case 'V':
                            map[i][j] = LocationType.VOLCANIC;
                            break;
                        case 'D':
                            map[i][j] = LocationType.DESERT;
                            break;
                        case 'W':
                            map[i][j] = LocationType.WOODS;
                            break;
                        default:
                            System.out.println("Invalid input for the map!");
                            System.exit(1);
                    }
                }
            }

            nrCharacters = scanner.nextInt();

            for (int i = 0; i < nrCharacters; i++) {
                String line = scanner.next();
                int initialLine = scanner.nextInt();
                int initialColumn = scanner.nextInt();
                switch (line.charAt(0)) {
                    case 'W':
                        characters.add(new Wizard(initialColumn, initialLine));
                        break;
                    case 'R':
                        characters.add(new Rogue(initialColumn, initialLine));
                        break;
                    case 'P':
                        characters.add(new Pyromancer(initialColumn, initialLine));
                        break;
                    case 'K':
                        characters.add(new Knight(initialColumn, initialLine));
                        break;
                    default:
                        System.out.println("Invalid input for the characters!");
                        System.exit(2);
                }
            }

            nrRounds = scanner.nextInt();

            instructions = new char[nrRounds][nrCharacters];
            for (int i = 0; i < nrRounds; i++) {
                String instructionLine = scanner.next();
                for (int j = 0; j < nrCharacters; j++) {
                    instructions[i][j] = instructionLine.charAt(j);
                }
            }

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return new InputData(mapHeight, mapWidth, map, nrCharacters, characters,
                nrRounds, instructions);
    }

    public void writeFinalResults(final Statistics finalStatistics) {
        // TODO write output
        // rasa_pers level_pers xp_pers hp_pers row col
    }
}

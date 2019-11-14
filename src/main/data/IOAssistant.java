package main.data;

import main.characters.*;
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
        char[][] map;

        File inputFile = new File(inputPath);
        try {
            Scanner scanner = new Scanner(inputFile);

            mapHeight = scanner.nextInt();
            mapWidth = scanner.nextInt();

            map = new char[mapHeight][mapWidth];
            for (int i = 0; i < mapHeight; i++) {
                String line = scanner.next();
                for (int j = 0; j < mapWidth; j++) {
                    map[i][j] = line.charAt(j);
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
                        System.out.println("Invalid input!");
                        System.exit(1);
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
    }
}

package main.data;

import main.characters.*;

import java.io.*;
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
        MovementType[][] instructions;
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

            instructions = new MovementType[nrRounds][nrCharacters];
            for (int i = 0; i < nrRounds; i++) {
                String instructionLine = scanner.next();
                for (int j = 0; j < nrCharacters; j++) {
                    switch (instructionLine.charAt(j)) {
                        case 'R':
                            instructions[i][j] = MovementType.RIGHT;
                            break;
                        case 'L':
                            instructions[i][j] = MovementType.LEFT;
                            break;
                        case 'U':
                            instructions[i][j] = MovementType.UP;
                            break;
                        case 'D':
                            instructions[i][j] = MovementType.DOWN;
                            break;
                        case '_':
                            instructions[i][j] = MovementType.NONE;
                            break;
                        default:
                            System.out.println("Invalid movement input!");
                            System.exit(3);
                    }
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

    public void writeFinalResults(final List<GameCharacter> characters) {
        File outputFile = new File(this.outputPath);
        try {
            FileWriter fileWriter = new FileWriter(outputFile);

            for (GameCharacter character : characters) {
                StringBuilder sb = new StringBuilder();
                sb.append(character.getName());
                sb.append(" ");
                if (character.isDead()) {
                    sb.append("dead");
                    sb.append("\n");
                    fileWriter.write(sb.toString());
                    continue;
                }
                sb.append(character.getLevel());
                sb.append(" ");
                sb.append(character.getExperience());
                sb.append(" ");
                sb.append(character.getHealth());
                sb.append(" ");
                sb.append(character.getRow());
                sb.append(" ");
                sb.append(character.getColon());
                sb.append("\n");
                fileWriter.write(sb.toString());
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

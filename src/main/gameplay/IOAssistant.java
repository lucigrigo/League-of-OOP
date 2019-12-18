package main.gameplay;

import main.angels.Angel;
import main.data.*;
import main.factories.AngelFactory;
import main.factories.HeroFactory;
import main.heroes.Hero;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Class that does the reading and writing duties.
 */
public class IOAssistant {
    private String inputPath;
    private String outputPath;

    public IOAssistant(final String inputPath,
                       final String outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    /**
     * Function that reads input data.
     *
     * @return input data
     */
    public final InputData readInput() {
        int mapWidth;
        int mapHeight;
        int nrCharacters;
        int nrRounds;
        List<Hero> characters = new ArrayList<>();
        HashMap<Integer, List<Angel>> angels = new HashMap<>();
        MovementType[][] instructions;
        LocationType[][] map;

        // opening file
        File inputFile = new File(inputPath);
        try {
            // opening scanner
            Scanner scanner = new Scanner(inputFile);

            // reading map dimensions
            mapHeight = scanner.nextInt();
            mapWidth = scanner.nextInt();

            // reading the map
            map = new LocationType[mapHeight][mapWidth];
//            Map.getInstance().setDimensions(mapHeight, mapWidth);
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
            Map.getInstance().setMap(map);

            // reading heroes count
            nrCharacters = scanner.nextInt();

            // reading heroes types
            HeroFactory heroFactory = new HeroFactory();
            for (int i = 0; i < nrCharacters; i++) {
                String line = scanner.next();
                int initialLine = scanner.nextInt();
                int initialColumn = scanner.nextInt();
                switch (line.charAt(0)) {
                    case 'W':
                        characters.add(heroFactory.createHero(HeroType.WIZARD,
                                initialLine, initialColumn, i));
                        break;
                    case 'R':
                        characters.add(heroFactory.createHero(HeroType.ROGUE,
                                initialLine, initialColumn, i));
                        break;
                    case 'P':
                        characters.add(heroFactory.createHero(HeroType.PYROMANCER,
                                initialLine, initialColumn, i));
                        break;
                    case 'K':
                        characters.add(heroFactory.createHero(HeroType.KNIGHT,
                                initialLine, initialColumn, i));
                        break;
                    default:
                        System.out.println("Invalid input for the characters!");
                        System.exit(1);
                }
            }

            // reading rounds number
            nrRounds = scanner.nextInt();

            // reading instructions (moves) for every hero
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
                            System.exit(1);
                    }
                }
            }

            // reading the angels
            AngelFactory angelFactory = new AngelFactory();
            for (int i = 0; i <= nrRounds; i++) {
                String line = scanner.nextLine();
                String[] arguments = line.split(" ");
                List<Angel> singleRoundAngels = new ArrayList<>(arguments.length - 1);
                for (int j = 1; j < arguments.length; j++) {
                    String[] singleAngel = arguments[j].split(",");
                    singleRoundAngels.add(angelFactory.createAngel(AngelType.valueOf(singleAngel[0]),
                            Integer.parseInt(singleAngel[1]), Integer.parseInt(singleAngel[2])));
                }
                angels.put(i, singleRoundAngels);
            }

            // closing scanner
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        // returning data
        return new InputData(mapHeight, mapWidth, map, nrCharacters, characters,
                nrRounds, instructions, angels);
    }

    /**
     * Function that writes endgame results.
     *
     * @param characters heroes of the game
     */
    public final void writeFinalResults(final List<Hero> characters) {
        // opening file
        File outputFile = new File(this.outputPath);
        try {
            // opening file writer
            FileWriter fileWriter = new FileWriter(outputFile);

            GreatSorcerer.getInstance().startFinalResults();
            // writing information about each hero
            for (Hero character : characters) {
                GreatSorcerer.getInstance().writeResult(character.toString());
            }

            for (String info : GreatSorcerer.getInstance().getInfo()) {
                System.out.print(info);
                fileWriter.write(info);
            }

            // flushing informations
            fileWriter.flush();

            // closing file writer
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

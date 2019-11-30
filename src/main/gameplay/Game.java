package main.gameplay;

import main.characters.GameCharacter;
import main.data.InputData;
import main.data.LocationType;
import main.data.MovementType;

import java.util.List;

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

    private void applyCurrentRoundMoves(final List<GameCharacter> characters,
                                        final MovementType[] roundMoves) {
        for (int i = 0; i < roundMoves.length; i++) {
            if (!characters.get(i).isIncapacitated()
                    && !characters.get(i).isDead()) {
                characters.get(i).applyMove(roundMoves[i]);
            } else if (characters.get(i).isIncapacitated()) {
                characters.get(i).getAbilityAffectedBy().incapacityUsed();
            }
        }
    }

    private void manageFight(final GameCharacter character1,
                             final GameCharacter character2,
                             final LocationType location) {
        character2.getAttackedBy(character1, location);
        character1.getAttackedBy(character2, location);

        character2.getAffectedBy(character1, location);
        character1.getAffectedBy(character2, location);
    }

    private void searchForFights(final List<GameCharacter> characters,
                                 final LocationType[][] map) {
        boolean[][] checkedFights = new boolean[map.length][map.length];
        for (GameCharacter character1 : characters) {
            for (GameCharacter character2 : characters) {
                if ((character1 != character2)
                        && (character1.getColon() == character2.getColon())
                        && (character1.getRow() == character2.getRow())
                        && (!checkedFights[character1.getRow()][character1.getColon()])
                        && ((!character1.isDead())
                        && (!character2.isDead()))) {
                    manageFight(character1, character2,
                            map[character1.getRow()][character1.getColon()]);
                    checkedFights[character1.getRow()][character1.getColon()] = true;
                }
            }
        }
    }

    private void applyOverTimeDamage(final List<GameCharacter> characters) {
        for (GameCharacter character : characters) {
            if (character.getAbilityAffectedBy() != null
                    && !character.isDead()
                    && character.getAbilityAffectedBy().getDuration() > 0) {
                character.takeDamage(character.getAbilityAffectedBy().getOvertimeDamage(), true);
                character.getAbilityAffectedBy().damageDealt();
            }
        }
    }

    private void roundEnding(List<GameCharacter> characters) {
        for (GameCharacter character : characters) {
            character.doRoundEndingRoutine();
        }
    }

    public void startGame(final InputData data) {
        int maxRounds = data.getNrRounds();
        int currentRound = 0;
        while (currentRound < maxRounds) {
//            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~ Runda " + currentRound + "~~~~~~~~~~~~~~~~~~~~~");
//            System.out.println("\n" +
//                    "Inainte sa-si schimbe pozitia si sa primeasca overtime damage");
//            System.out.println(data.getCharacters().toString());

            applyCurrentRoundMoves(data.getCharacters(), data.getCurrentRoundMoves(currentRound));
            applyOverTimeDamage(data.getCharacters());

//            System.out.println("Dup ce si-au schimbat pozitia si sa primeasca overtime damage");
//            System.out.println(data.getCharacters().toString());

            searchForFights(data.getCharacters(), data.getMap());
            roundEnding(data.getCharacters());
//            checkForOverTimeAbilitiesEnd(data.getCharacters());

//            System.out.println("Dupa lupte");
//            System.out.println(data.getCharacters().toString());
            System.out.println("Round: " + currentRound);
            for (GameCharacter character : data.getCharacters()) {
                System.out.println(character.toString());
            }
            System.out.println("-----------END ROUND--------");
            currentRound++;
        }
//        System.out.println("\nRezultat final:");
//        System.out.println(data.getCharacters().toString());
    }
}

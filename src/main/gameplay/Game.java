package main.gameplay;

import main.characters.GameCharacter;
import main.data.InputData;
import main.data.LocationType;
import main.data.MovementType;

import java.util.List;

public final class Game {

    private static Game instance = null;
    private int maxRounds;
    private int currentRound;

    private Game() {
        maxRounds = 0;
        currentRound = 0;
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
//            System.out.println("miscarea " + roundMoves[i] + " lui " + characters.get(i).getName());
//            System.out.println(characters.get(i).getName() + i + characters.get(i).isIncapacitated());
            if (!characters.get(i).isIncapacitated()
                    && (!characters.get(i).isDead())) {
                characters.get(i).applyMove(roundMoves[i]);
            }
//            if (characters.get(i).isIncapacitated()) {
//                System.out.println(characters.get(i).getName() + " nu se poate misca!");
//            }
        }
    }

    private void manageFight(final GameCharacter character1,
                             final GameCharacter character2,
                             final LocationType location) {

//        if (character1.isDead() || character2.isDead()) {
//            return;
//        }
        if ((character1.getAbilityAffectedBy() != null
                && (character1.getAbilityAffectedBy().getOvertimeDamage() >= character1.getHealth())
                && (character1.getAbilityAffectedBy().getCaster() != character2))
                || (character2.getAbilityAffectedBy() != null
                && (character2.getAbilityAffectedBy().getOvertimeDamage() >= character1.getHealth())
                && (character2.getAbilityAffectedBy().getCaster() != character1))) {
            return;
        }


//        System.out.println("sper ca aici");

//        if(character1.isDead() || character2.isDead()) {
//            return;
//        }
        Ability firstCharacterAbility = character1.computeDamageAgainst(character2, location, true);
        Ability secondCharacterAbility = character2.computeDamageAgainst(character1, location, true);
        character2.takeDamage(firstCharacterAbility, character1, location, false);
//        if(character2.isDead()) {
//            return;
//        }
        character1.takeDamage(secondCharacterAbility, character2, location, false);

        if ((character2.getAbilityAffectedBy() == null)
                || (character2.getAbilityAffectedBy().getCaster() != character1)) {
            OverTimeAbility overTimeAbility1 = character1.getAbilityOverTime(character2, location);
            character2.setAbilityAffectedBy(overTimeAbility1);
//            System.out.println("aicea?");
        }
        if ((character1.getAbilityAffectedBy() == null)
                || (character1.getAbilityAffectedBy().getCaster() != character2)) {
            OverTimeAbility overTimeAbility2 = character2.getAbilityOverTime(character1, location);
            character1.setAbilityAffectedBy(overTimeAbility2);
//            System.out.println("aiceas?");
        }
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
                    && !character.isDead()) {
//                    && !character.getAbilityAffectedBy().isFirstRound()) {
//                    && !character.getAbilityAffectedBy().getName().equals("Paralysis")) {
                System.out.println(character.getName() + " ia DoT");
//                System.out.println(character.getAbilityAffectedBy().isFirstRound());
//                System.out.println("eventual si cu tata ?! " + character.getAbilityAffectedBy().getDamageWithoutRaceModifier());
                character.takeDamage(new Ability(character.getAbilityAffectedBy().getName(),
                                character.getAbilityAffectedBy().getOvertimeDamage(),
                                character.getAbilityAffectedBy().getDamageWithoutRaceModifier()),
                        character.getAbilityAffectedBy().getCaster(),
                        character.getAbilityAffectedBy().getLocation(),
                        true);
//                character.getAbilityAffectedBy().roundPassed();
            }
        }
    }

//    private void checkForOverTimeAbilitiesEnd(final List<GameCharacter> characters) {
//        for (GameCharacter character : characters) {
//            if (character.getAbilityAffectedBy() != null) {
//                if (character.getAbilityAffectedBy().getDuration() == 0) {
//                    character.setAbilityAffectedBy(null);
//                }
//            }
//        }
//    }


    private boolean checkForOnlySurvivor(final List<GameCharacter> characters) {
        int nrOfAliveChampions = 0;
        for (GameCharacter character : characters) {
            if (!character.isDead()) {
                nrOfAliveChampions++;
            }
        }
        return nrOfAliveChampions <= 1;
    }

    private void roundEnding(List<GameCharacter> characters) {
        for (GameCharacter character : characters) {
            if (character.getAbilityAffectedBy() != null) {
                if (!character.getAbilityAffectedBy().isFirstRound()) {
                    character.getAbilityAffectedBy().roundPassed();
                }
//                System.out.println(character.getAbilityAffectedBy().isFirstRound());
            }

            character.doRoundEndingRoutine();
        }
    }

    public void startGame(final InputData data,
                          final Statistics statistics) {
        int maxRounds = data.getNrRounds();
        this.maxRounds = maxRounds;
        int currentRound = 0;
        while (currentRound < maxRounds) {
            System.out.println("======= Runda " + currentRound + "=======");
            applyCurrentRoundMoves(data.getCharacters(), data.getCurrentRoundMoves(currentRound));

            applyOverTimeDamage(data.getCharacters());
            this.currentRound = currentRound;


            searchForFights(data.getCharacters(), data.getMap());

            roundEnding(data.getCharacters());
//            checkForOverTimeAbilitiesEnd(data.getCharacters());
            currentRound++;
            System.out.println("HP la finalul rundei " + currentRound);
            for (GameCharacter character : data.getCharacters()) {
                System.out.println("\t" + character.getName() + " " + character.getHealth() + " " + character.getRow()
                        + " " + character.getColon());
            }
            if (checkForOnlySurvivor(data.getCharacters())) {
                break;
            }
        }
//        manageOvertimeFights(data.getCharacters());
    }
}

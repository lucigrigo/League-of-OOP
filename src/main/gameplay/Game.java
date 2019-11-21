package main.gameplay;

import main.characters.CharacterType;
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
            if (!characters.get(i).isIncapacitated()
                    && (!characters.get(i).isDead())) {
                characters.get(i).applyMove(roundMoves[i]);
            }
        }
    }

    private void manageFight(final GameCharacter character1,
                             final GameCharacter character2,
                             final LocationType location) {
        // todo "lupta nu va mai avea loc?"
        // todo check for DoT killing potential

        if ((character1.getAbilityAffectedBy() == null)
                || (character1.getAbilityAffectedBy().getCaster() != character2)) {
            OverTimeAbility overTimeAbility2 = character2.getAbilityOverTime(character1, location);
            character1.setAbilityAffectedBy(overTimeAbility2);
        }
        if ((character2.getAbilityAffectedBy() == null)
                || (character2.getAbilityAffectedBy().getCaster() != character1)) {
            OverTimeAbility overTimeAbility1 = character1.getAbilityOverTime(character2, location);
            character2.setAbilityAffectedBy(overTimeAbility1);
        }
        Ability secondCharacterAbility = character2.computeDamageAgainst(character1, location, true);
        Ability firstCharacterAbility = character1.computeDamageAgainst(character2, location, true);
        character2.takeDamage(firstCharacterAbility, character1, location);
        character1.takeDamage(secondCharacterAbility, character2, location);
    }

    private void searchForFights(final List<GameCharacter> characters,
                                 final LocationType[][] map) {
        boolean[][] checkedFights = new boolean[map.length][map.length];
        for (GameCharacter character1 : characters) {
            for (GameCharacter character2 : characters) {
                if ((character1 != character2)
                        && (character1.getColon() == character2.getColon())
                        && (character1.getRow() == character2.getRow())
                        && (!checkedFights[character1.getRow()][character1.getColon()])) {
//                        && (!character1.isDead())
//                        && (!character2.isDead())) {
                    manageFight(character1, character2,
                            map[character1.getRow()][character1.getColon()]);
                    checkedFights[character1.getRow()][character1.getColon()] = true;
                }
            }
        }
    }

    private void applyOverTimeDamage(final List<GameCharacter> characters) {
        for (GameCharacter character : characters) {
            // todo clarify rogue paralysis duration
            if (character.getAbilityAffectedBy() != null
                    && !character.isDead()) {
//                    && !character.getAbilityAffectedBy().getName().equals("Paralysis")) {
//                System.out.println("eventual si cu tata ?! " + character.getAbilityAffectedBy().getDamageWithoutRaceModifier());
                character.takeDamage(new Ability(character.getAbilityAffectedBy().getName(),
                                character.getAbilityAffectedBy().getOvertimeDamage(),
                                character.getAbilityAffectedBy().getDamageWithoutRaceModifier()),
                        character.getAbilityAffectedBy().getCaster(),
                        character.getAbilityAffectedBy().getLocation());
                character.getAbilityAffectedBy().roundPassed();
            }
        }
    }

    public void checkForOverTimeAbilitiesEnd(final List<GameCharacter> characters) {
        for (GameCharacter character : characters) {
            if (character.getAbilityAffectedBy() != null) {
                if (character.getAbilityAffectedBy().getDuration() == 0) {
                    character.setAbilityAffectedBy(null);
                }
            }
        }
    }

    private void manageOvertimeFights(final List<GameCharacter> characters) {
        boolean done = false;
        while (!done) {
            done = true;
            for (GameCharacter character : characters) {
                if (character.getAbilityAffectedBy() != null
                        && (!character.isDead()
                        && (character.getAbilityAffectedBy().getDuration() > 0))) {
//                    done = false;
                    character.takeDamage(new Ability(character.getAbilityAffectedBy().getName(),
                                    character.getAbilityAffectedBy().getOvertimeDamage(),
                                    character.getAbilityAffectedBy().getDamageWithoutRaceModifier()),
                            character.getAbilityAffectedBy().getCaster(),
                            character.getAbilityAffectedBy().getLocation());
                    character.getAbilityAffectedBy().roundPassed();
                }
            }
        }

    }

    private void checkForDeadCharacters(final List<GameCharacter> characters) {
        for (GameCharacter character : characters) {
            if (character.getHealth() <= 0) {
                character.setAbilityAffectedBy(null);
                character.hasDied();
            }
        }
    }

    private void checkForWizardDeflect(final List<GameCharacter> characters) {
        for (GameCharacter character : characters) {
            if (character.getType() == CharacterType.WIZARD) {
                character.deflectDamage();
            }
        }
    }

    public void startGame(final InputData data,
                          final Statistics statistics) {
        int maxRounds = data.getNrRounds();
        this.maxRounds = maxRounds;
        int currentRound = 0;
        while (currentRound < maxRounds) {
            // todo check for won fights + add exp
            this.currentRound = currentRound;
            applyOverTimeDamage(data.getCharacters());
            applyCurrentRoundMoves(data.getCharacters(), data.getCurrentRoundMoves(currentRound));
//            checkForDeadCharacters(data.getCharacters());
            searchForFights(data.getCharacters(), data.getMap());
//            checkForDeadCharacters(data.getCharacters());
//            applyOverTimeDamage(data.getCharacters());
//            checkForOverTimeAbilitiesEnd(data.getCharacters());
            checkForWizardDeflect(data.getCharacters());
            checkForOverTimeAbilitiesEnd(data.getCharacters());

            currentRound++;
            System.out.println("HP la finalul rundei " + currentRound);
            for (GameCharacter character : data.getCharacters()) {
                System.out.println("\t" + character.getName() + " " + character.getHealth());
            }
        }
        manageOvertimeFights(data.getCharacters());
    }
}

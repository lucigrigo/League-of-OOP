package main.characters;

import main.data.LocationType;
import main.data.MovementType;

public abstract class GameCharacter {
    private int colon;
    private int line;
    private int currentHealth;
    private int currentExperience;
    private int level;
    private CharacterType type;
    private boolean isIncapacitated;
    private boolean isCurrentlyFighting;
    private String name;

//    public GameCharacter(final int initCol,
//                         final int initLin) {
//        this.colon = initCol;
//        this.line = initLin;
//    }

    public GameCharacter(final int initCol,
                         final int initLin,
                         final int currentHealth,
                         final int currentExperience,
                         final CharacterType type,
                         final String name) {
        this.colon = initCol;
        this.line = initLin;
        this.currentHealth = currentHealth;
        this.currentExperience = currentExperience;
        this.level = 0;
        this.type = type;
        this.isIncapacitated = false;
        this.isCurrentlyFighting = false;
        this.name = name;
    }

    public int getColon() {
        return colon;
    }

    public int getRow() {
        return line;
    }

    public int getHealth() {
        return currentHealth;
    }

    public int getExperience() {
        return currentExperience;
    }

    public CharacterType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public boolean isCurrentlyFighting() {
        return isCurrentlyFighting;
    }

    public boolean isIncapacitated() {
        return isIncapacitated;
    }

    public void applyMove(MovementType move) {
        switch (move) {
            case DOWN:
                this.line = this.line + 1;
                break;
            case LEFT:
                this.colon = this.colon - 1;
                break;
            case RIGHT:
                this.colon = this.colon + 1;
                break;
            case UP:
                this.line = this.line - 1;
                break;
            case NONE:
                // do nothing
                break;
            default:
                System.out.println("Error applying move!");
                System.exit(4);
                break;
        }
    }

    public abstract float computeDamageAgainst(GameCharacter enemy, LocationType location);

    public abstract OverTimeAbility getAbilityOverTime(GameCharacter enemy, LocationType location);
}

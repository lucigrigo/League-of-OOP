package main.characters;

import main.data.Constants;
import main.data.LocationType;
import main.data.MovementType;
import main.gameplay.OverTimeAbility;

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
    private OverTimeAbility abilityAffectedBy;

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
        this.abilityAffectedBy = null;
    }

    public final int getColon() {
        return colon;
    }

    public final int getRow() {
        return line;
    }

    public final int getHealth() {
        return currentHealth;
    }

    public final int getExperience() {
        return currentExperience;
    }

    public final CharacterType getType() {
        return type;
    }

    public final String getName() {
        return name;
    }

    public final int getLevel() {
        return level;
    }

    public final boolean isCurrentlyFighting() {
        return isCurrentlyFighting;
    }

    public final boolean isIncapacitated() {
        return isIncapacitated;
    }

    public final void applyMove(MovementType move) {
        if (!this.isDead() ||
                !this.isIncapacitated) {
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
                    System.exit(0);
                    break;
            }
        }
    }

    public abstract int computeDamageAgainst(GameCharacter enemy,
                                             LocationType location);

    public abstract OverTimeAbility getAbilityOverTime(GameCharacter enemy,
                                                       LocationType location);

    public abstract int getTotalOverTimeDamage(LocationType location,
                                               GameCharacter enemy,
                                               int roundsRemaining);

    public final OverTimeAbility getAbilityAffectedBy() {
        return abilityAffectedBy;
    }

    public void setAbilityAffectedBy(OverTimeAbility abilityAffectedBy) {
        this.abilityAffectedBy = abilityAffectedBy;
    }

    public void takeDamage(int damage) {
//        System.out.println(currentHealth);
        this.currentHealth -= damage;
    }

    public void fightWon(final int loserLevel) {
        this.currentExperience = this.currentExperience
                + Math.max(0, Constants.getInstance().getWinMagic200()
                - (this.getLevel() - loserLevel)
                * Constants.getInstance().getWinMagic40());
    }

    public void hasDied() {
        this.currentHealth = 0;
    }

    public final boolean isDead() {
        return this.currentHealth <= 0;
    }
}

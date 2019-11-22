package main.characters;

import main.data.Constants;
import main.data.LocationType;
import main.data.MovementType;
import main.gameplay.Ability;
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

    public abstract Ability computeDamageAgainst(GameCharacter enemy,
                                                 LocationType location,
                                                 boolean addRaceModifier);

    public abstract OverTimeAbility getAbilityOverTime(GameCharacter enemy,
                                                       LocationType location);

    public void deflectDamage() {
        // is implemented in the Wizard class
    }

    public abstract int getMaxHealth();

//    public abstract int getDamageWithoutRaceModifier(GameCharacter enemy, LocationType location);

    public final OverTimeAbility getAbilityAffectedBy() {
        return abilityAffectedBy;
    }

    public void setAbilityAffectedBy(OverTimeAbility abilityAffectedBy) {
        this.abilityAffectedBy = abilityAffectedBy;
    }

    public void takeDamage(final Ability ability,
                           final GameCharacter enemy,
                           final LocationType location,
                           final boolean isOvertimeAbility) {
//        System.out.println("vr sa ma fut cu edi " + ability.getDamageWithoutRaceModifier());
//        if (!enemy.isDead()) {
        if (ability != null) {
            System.out.println(this.name + " are hp " + this.getHealth() + " si ia dmg " + ability.getDamage());
            this.currentHealth -= ability.getDamage();
//        }
            if (this.currentHealth <= 0
                    && !isOvertimeAbility) {
                enemy.fightWon(this.getLevel());
                this.hasDied();
//            this.setAbilityAffectedBy(null);
            }
        }

    }

    public void fightWon(final int loserLevel) {
//        System.out.println(this.getName() + " a castigat lupta!");
        this.currentExperience = this.currentExperience
                + Math.max(0, Constants.getInstance().getWinMagic200()
                - (this.getLevel() - loserLevel)
                * Constants.getInstance().getWinMagic40());
        System.out.println("experienta " + this.currentExperience);
        if (this.currentExperience
                >= Constants.getInstance().getExperienceBase()
                + this.getLevel()
                * Constants.getInstance().getExperienceScaling()) {
            this.level++;
            this.currentHealth = this.getMaxHealth();
        }
    }

    public void hasDied() {
        this.currentHealth = 0;
    }

    public final boolean isDead() {
        return this.currentHealth <= 0;
    }
}

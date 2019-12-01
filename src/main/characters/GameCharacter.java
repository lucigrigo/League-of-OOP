package main.characters;

import main.data.CharacterType;
import main.data.Constants;
import main.data.LocationType;
import main.data.MovementType;
import main.gameplay.OverTimeAbility;

/**
 * Abstract class for a hero.
 */
public abstract class GameCharacter {
    private int colon;
    private int line;
    private int currentHealth;
    private int currentExperience;
    private int level;
    private String name;
    private OverTimeAbility abilityAffectedBy;
    private boolean hasMadeAKillThisRound;
    private int initialRoundHealth;

    // Constructor
    GameCharacter(final int initCol,
                  final int initLin,
                  final int currentHealth,
                  final int currentExperience,
                  final CharacterType type,
                  final String name) {
        this.colon = initCol;
        this.line = initLin;
        this.currentHealth = currentHealth;
        this.initialRoundHealth = currentHealth;
        this.currentExperience = currentExperience;
        this.level = 0;
        this.name = name;
        this.abilityAffectedBy = null;
        this.hasMadeAKillThisRound = false;
    }

    // if the hero has made a kill this round
    private void setHasMadeAKillThisRound() {
        this.hasMadeAKillThisRound = true;
    }

    // returning current colon
    public final int getColon() {
        return colon;
    }

    // returning current row
    public final int getRow() {
        return line;
    }

    // returning current health
    final int getHealth() {
        return currentHealth;
    }

    // returning current experience
    private int getExperience() {
        return currentExperience;
    }

    // returning name
    private String getName() {
        return name;
    }

    // returning current level
    final int getLevel() {
        return level;
    }

    // hero has died
    final void hasDied() {
        this.currentHealth = 0;
    }

    // checking if the hero is dead
    public final boolean isDead() {
        return this.currentHealth <= 0;
    }

    // returning health at the start of the current round
    final int getInitialRoundHealth() {
        return initialRoundHealth;
    }

    // checking if the hero can move;
    public final boolean isIncapacitated() {
        return this.abilityAffectedBy != null
                && this.abilityAffectedBy.isAbilityToIncapacitate()
                && this.abilityAffectedBy.getIncapacityDuration() > 0;
    }

    // moving the hero in a certain direction
    public final void applyMove(final MovementType move) {
        if (!this.isDead()
                && !this.isIncapacitated()) {
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

    // returning the overtime ability that affects the hero
    public final OverTimeAbility getAbilityAffectedBy() {
        return abilityAffectedBy;
    }

    // setting the overtime ability to affect the hero
    public final void setAbilityAffectedBy(final OverTimeAbility abilityAffectedBy) {
        this.abilityAffectedBy = abilityAffectedBy;
    }

    // the hero taking damage
    public final boolean takeDamage(final int damage,
                                    final boolean isOverTimeAbility) {
        this.currentHealth -= damage;
        if (this.currentHealth <= 0
                && !isOverTimeAbility) {
            this.hasDied();
            return true;
        } else if (this.currentHealth <= 0) {
            this.hasDied();
        }
        return false;
    }

    // the hero won a fight
    final void fightWon(final int loserLevel) {
        this.currentExperience = this.currentExperience
                + Math.max(0, Constants.WIN_MAGIC_200
                - (this.getLevel() - loserLevel)
                * Constants.WIN_MAGIC_40);
        this.setHasMadeAKillThisRound();
    }

    // checking for level up potential
    private void checkForLevelUp() {
        if ((this.currentExperience
                >= Constants.EXPERIENCE_BASE
                + this.getLevel()
                * Constants.EXPERIENCE_SCALING)
                && !this.isDead()) {
            this.level = (this.currentExperience
                    - Constants.EXPERIENCE_BASE)
                    / Constants.EXPERIENCE_SCALING + 1;
            this.currentHealth = this.getMaxHealth();
        }
    }

    /**
     * Method that checks for level up potential at the end of the round
     * and refreshes round-starting health (for execute calculus done correctly).
     */
    public void doRoundEndingRoutine() {
        if (this.hasMadeAKillThisRound) {
            this.checkForLevelUp();
            this.hasMadeAKillThisRound = false;
        }
        this.initialRoundHealth = currentHealth;
    }

    // writing the hero to output
    @Override
    public final String toString() {
        if (!this.isDead()) {
            return this.getName() + " " + this.getLevel() + " " + this.getExperience() + " "
                    + this.getHealth() + " " + this.getRow() + " " + this.getColon() + "\n";
        }
        return this.name + " dead\n";
    }

    // returning maximum health of the hero
    public abstract int getMaxHealth();

    // computing initial damage of the fight
    public abstract float computeInitialDamage(LocationType location);

    // computing initial overtime damage of the fight
    public abstract float computeInitialOvertimeDamage(LocationType location);

    // getting attacked
    public abstract void getAttackedBy(GameCharacter enemy,
                                       LocationType location);

    // attacking a WIZARD
    public abstract float attack(Wizard enemy,
                                 LocationType location,
                                 boolean addRaceModifier,
                                 boolean isForDeflectPurpose);

    // attacking a ROGUE
    public abstract void attack(Rogue enemy,
                                LocationType location,
                                boolean addRaceModifier,
                                boolean isForDeflectPurpose);

    // attacking a PYROMANCER
    public abstract void attack(Pyromancer enemy,
                                LocationType location,
                                boolean addRaceModifier,
                                boolean isForDeflectPurpose);

    // attacking a KNIGHT
    public abstract void attack(Knight enemy,
                                LocationType location,
                                boolean addRaceModifier,
                                boolean isForDeflectPurpose);

    // getting affected overtime
    public abstract void getAffectedBy(GameCharacter enemy,
                                       LocationType location);

    // affecting overtime a WIZARD
    public abstract float affectOvertime(Wizard enemy,
                                         LocationType location,
                                         boolean startNow,
                                         boolean addRaceModifier);

    // affecting overtime a ROGUE
    public abstract float affectOvertime(Rogue enemy,
                                         LocationType location,
                                         boolean startNow,
                                         boolean addRaceModifier);

    // affecting overtime a KNIGHT
    public abstract float affectOvertime(Knight enemy,
                                         LocationType location,
                                         boolean startNow,
                                         boolean addRaceModifier);

    // affecting overtime a PYROMANCER
    public abstract float affectOvertime(Pyromancer enemy,
                                         LocationType location,
                                         boolean startNow,
                                         boolean addRaceModifier);
}

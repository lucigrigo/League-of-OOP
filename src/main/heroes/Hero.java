package main.heroes;

import main.data.Constants;
import main.data.LocationType;
import main.data.MovementType;
import main.data.OverTimeAbility;
import main.data.Visitable;
import main.gameplay.GreatSorcerer;
import main.strategies.Strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Abstract class for a hero.
 */
public abstract class Hero extends Observable implements Visitable {
    private int colon;
    private int line;
    private float currentHealth;
    private int currentExperience;
    private int level;
    private String name;
    private String fullName;
    private OverTimeAbility abilityAffectedBy;
    private boolean hasMadeAKillThisRound;
    private float initialRoundHealth;
    private int index;
    private Strategy strategy;
    private float strategyBonus;
    private boolean revivedThisRound;
    private int initialRoundExperience;
    private List<Float> angelB;

    /*
     Constructor
     */
    Hero(final int initCol,
         final int initLin,
         final int currentHealth,
         final int currentExperience,
         final String name,
         final String fullName,
         final int index) {
        this.colon = initCol;
        this.line = initLin;
        this.currentHealth = currentHealth;
        this.initialRoundHealth = currentHealth;
        this.currentExperience = currentExperience;
        this.initialRoundExperience = currentExperience;
        this.level = 0;
        this.name = name;
        this.fullName = fullName;
        this.abilityAffectedBy = null;
        this.hasMadeAKillThisRound = false;
        this.index = index;
        this.strategy = null;
        this.strategyBonus = 0f;
        this.angelB = new ArrayList<>();
        this.revivedThisRound = false;

        // adding the sole observer of the game
        addObserver(GreatSorcerer.getInstance());
    }

    /**
     * Adds a new angel bonus to the list for a single hero.
     *
     * @param bonus new angel bonus
     */
    public final void addAngelBonus(final float bonus) {
        angelB.add(bonus);
    }

    /**
     * @return list of angel bonuses for current hero
     */
    public final List<Float> getAngelBonuses() {
        return angelB;
    }

    /**
     * Adds/Substracts a new strategy bonus to the one already existing.
     *
     * @param bonus new bonus
     */
    public final void addStrategyBonus(final float bonus) {
        this.strategyBonus = Math.max(-1f, strategyBonus + bonus);
    }

    /**
     * Sets a new strategy for a hero.
     *
     * @param strategy new adopted strategy
     */
    public final void setStrategy(final Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * @return strategy bonus
     */
    final float getStrategyBonus() {
        return strategyBonus;
    }

    /**
     * @return current colon
     */
    public final int getColon() {
        return colon;
    }

    /**
     * @return current row
     */
    public final int getRow() {
        return line;
    }

    /**
     * @return current health
     */
    public final float getHealth() {
        return currentHealth;
    }

    /**
     * @return current experience
     */
    private int getExperience() {
        return currentExperience;
    }

    /**
     * @return index in current game
     */
    public final int getIndex() {
        return index;
    }

    /**
     * @return full name
     */
    public final String getFullName() {
        return fullName;
    }

    /**
     * @return current level
     */
    final int getLevel() {
        return level;
    }

    /*
     Hero has died.
     */
    public final void hasDied(final boolean isAngelInteraction) {
        this.currentHealth = 0;
        if (isAngelInteraction) {
            initialRoundExperience = currentExperience;

            // computing observation
            String message = "Player " + fullName + " "
                    + index + " was killed by an angel\n";
            setChanged();
            notifyObservers(message);
        }
    }

    /**
     * Gets current hero to the next level.
     */
    public final void getToNextLevel() {
        int nextLevelXp = Constants.EXPERIENCE_BASE
                + level
                * Constants.EXPERIENCE_SCALING;
        nextLevelXp -= currentExperience;
        increaseXP(nextLevelXp);
    }

    /**
     * Increasing health after getting healed by an angel.
     *
     * @param hpAmount amount of healing
     */
    public final void increaseHP(final float hpAmount) {
        currentHealth = Math.min(getMaxHealth(),
                currentHealth + hpAmount);
    }

    /**
     * Increasing xp after interacting with an angel.
     *
     * @param xpAmount amount of experience gained
     */
    public final void increaseXP(final int xpAmount) {
        currentExperience = currentExperience + xpAmount;
        checkForLevelUp();
    }

    /**
     * Reviving current hero.
     *
     * @param reviveHp new hp
     */
    public final void revive(final int reviveHp) {
        // restoring parameters
        currentExperience = initialRoundExperience;
        currentHealth = reviveHp;
        revivedThisRound = true;

        // computing observation
        String message = "Player " + fullName
                + " " + index + " was brought to life by an angel\n";
        setChanged();
        notifyObservers(message);
    }

    /*
    Checking if the hero is dead.
     */
    public final boolean isDead() {
        return currentHealth <= 0;
    }

    /*
     Returning health at the start of the current round.
     */
    final float getInitialRoundHealth() {
        return initialRoundHealth;
    }

    /*
     Checking if the hero can move.
     */
    public final boolean isIncapacitated() {
        return abilityAffectedBy != null
                && abilityAffectedBy.isAbilityToIncapacitate()
                && abilityAffectedBy.getIncapacityDuration() > 0;
    }

    /*
     Moving the hero in a certain direction.
     Also checks the possibility of adopting a new strategy and applies it if so.
     */
    public final void applyMove(final MovementType move) {
        // deleting current strategy, if it exists
        strategy = null;

        // looking for a new strategy
        lookForStrategy();

        // applying current strategy, it is exists
        if (strategy != null) {
            strategy.applyStrategy();
        }

        // moving the hero
        if (!isDead()
                && !isIncapacitated()) {
            switch (move) {
                case DOWN:
                    line = line + 1;
                    break;
                case LEFT:
                    colon = colon - 1;
                    break;
                case RIGHT:
                    colon = colon + 1;
                    break;
                case UP:
                    line = line - 1;
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

    /**
     * @return the overtime ability that affects the hero.
     */
    public final OverTimeAbility getAbilityAffectedBy() {
        return abilityAffectedBy;
    }

    /*
     Setting the overtime ability to affect the hero.
     */
    public final void setAbilityAffectedBy(final OverTimeAbility abilityAffectedBy) {
        this.abilityAffectedBy = abilityAffectedBy;
    }

    /*
     The hero takes damage.
     */

    /**
     * The hero takes damage.
     *
     * @param damage             amount of damage taken.
     * @param isOverTimeAbility  checking if it is from an overtime ability or not
     * @param isAngelInteraction checking if it is angel related or not
     * @return true or false it current hero dies or not
     */
    public final boolean takeDamage(final int damage,
                                    final boolean isOverTimeAbility,
                                    final boolean isAngelInteraction) {
        this.currentHealth -= damage;
        if (currentHealth <= 0
                && !isOverTimeAbility) {
            hasDied(isAngelInteraction);
            return true;
        } else if (currentHealth <= 0) {
            hasDied(isAngelInteraction);
            return true;
        }
        return false;
    }

    /*
     The hero won a fight.
     */
    final void fightWon(final int loserLevel) {
        this.currentExperience = currentExperience
                + Math.max(0, Constants.WIN_MAGIC_200
                - (level - loserLevel)
                * Constants.WIN_MAGIC_40);
        hasMadeAKillThisRound = true;
    }

    /*
     Checking for level up.
     */
    public final void checkForLevelUp() {
        if ((currentExperience
                >= Constants.EXPERIENCE_BASE
                + level
                * Constants.EXPERIENCE_SCALING)
                && !isDead()) {
            int lastLevel = level + 1;
            level = (currentExperience
                    - Constants.EXPERIENCE_BASE)
                    / Constants.EXPERIENCE_SCALING + 1;
            currentHealth = getMaxHealth();

            // computing level-up observation
            for (int i = lastLevel; i <= level; ++i) {
                String message = fullName + " " + index + " reached level " + i + "\n";
                setChanged();
                notifyObservers(message);
            }
        }
    }

    /**
     * Method that checks for level up potential at the end of the round
     * and refreshes round-starting health (for execute calculus done correctly).
     */
    public void doRoundEndingRoutine() {
        // storing current experience
        if (!revivedThisRound) {
            initialRoundExperience = currentExperience;
        }

        // checking for level up
        if (hasMadeAKillThisRound
                && !revivedThisRound) {
            checkForLevelUp();
            hasMadeAKillThisRound = false;
        }

        initialRoundHealth = currentHealth;
        revivedThisRound = false;
    }

    /*
     Writing the hero and info about it to output.
     */
    @Override
    public final String toString() {
        if (!isDead()) {
            return name + " " + level + " " + currentExperience
                    + " " + Math.round(currentHealth) + " " + line + " "
                    + colon + "\n";
        }
        return name + " dead\n";
    }

    /**
     * Computing death observation.
     *
     * @param enemy enemy that killed current hero
     */
    public final void computeObservation(final Hero enemy) {
        String message = "Player " + enemy.getFullName() + " " + enemy.getIndex()
                + " was killed by " + fullName + " " + index + "\n";
        setChanged();
        notifyObservers(message);
    }

    /*
     Returning maximum health of the hero.
     */
    public abstract int getMaxHealth();

    /**
     * Computing initial damage of the fight.
     *
     * @param location location type
     * @return initial damage
     */
    public abstract float computeInitialDamage(LocationType location);

    /**
     * Computing initial damage for the overtime ability of the fight.
     *
     * @param location location type
     * @return initial damage for the overtime ability
     */
    public abstract float computeInitialOvertimeDamage(LocationType location);

    /**
     * Getting attacked by a hero.
     *
     * @param enemy    enemy that attacks current hero
     * @param location location type
     */
    public abstract void getAttackedBy(Hero enemy,
                                       LocationType location);

    /*
     * Attacking a WIZARD.
     */
    public abstract float attack(Wizard wizard,
                                 LocationType location,
                                 boolean addRaceModifier,
                                 boolean isForDeflectPurpose);

    /*
     Attacking a ROGUE.
     */
    public abstract void attack(Rogue enemy,
                                LocationType location,
                                boolean addRaceModifier);

    /*
     Attacking a PYROMANCER.
     */
    public abstract void attack(Pyromancer enemy,
                                LocationType location,
                                boolean addRaceModifier);

    /*
     Attacking a KNIGHT.
     */
    public abstract void attack(Knight enemy,
                                LocationType location,
                                boolean addRaceModifier);

    /**
     * Getting affected overtime.
     *
     * @param enemy    enemy that is fighting current hero
     * @param location location type
     */
    public abstract void getAffectedBy(Hero enemy,
                                       LocationType location);

    /*
     Affecting overtime a WIZARD.
     */
    public abstract float affectOvertime(Wizard enemy,
                                         LocationType location,
                                         boolean startNow,
                                         boolean addRaceModifier);

    /*
     Affecting overtime a ROGUE.
     */
    public abstract float affectOvertime(Rogue enemy,
                                         LocationType location,
                                         boolean startNow,
                                         boolean addRaceModifier);

    /*
     Affecting overtime a KNIGHT.
     */
    public abstract float affectOvertime(Knight enemy,
                                         LocationType location,
                                         boolean startNow,
                                         boolean addRaceModifier);

    /*
     Affecting overtime a PYROMANCER.
     */
    public abstract float affectOvertime(Pyromancer enemy,
                                         LocationType location,
                                         boolean startNow,
                                         boolean addRaceModifier);

    /*
    Checking for a new strategy to adopt, if possible.
     */
    public abstract void lookForStrategy();
}

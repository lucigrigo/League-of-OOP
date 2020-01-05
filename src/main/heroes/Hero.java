package main.heroes;

import main.data.*;
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
    private float angelBonus;
    private float strategyBonus;
    private boolean revivedThisRound;
    private int initialRoundExperience;

    // Constructor
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
        this.angelBonus = 0f;
        this.strategyBonus = 0f;
        this.revivedThisRound = false;
        addObserver(GreatSorcerer.getInstance());
    }

    private List<Float> angelB = new ArrayList<>();

    public final void addAngelBonus(final float bonus) {
        this.angelBonus = Math.max(-1f, angelBonus + bonus);
//        if (fullName.equals("Rogue")) {
//            System.out.println(fullName + " " + index + " got angel bonus = " + bonus);
//        }
        angelB.add(bonus);
    }

    public List<Float> getAngelBonuses() {
        return angelB;
    }


    public final void addStrategyBonus(final float bonus) {
        this.strategyBonus = Math.max(-1f, strategyBonus + bonus);
    }

    public final Strategy getStrategy() {
        return strategy;
    }

    public final void setStrategy(final Strategy strategy) {
        this.strategy = strategy;
    }

    final float getAngelBonus() {
        return angelBonus;
    }

    final float getStrategyBonus() {
        return strategyBonus;
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
    public final float getHealth() {
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

    public final int getIndex() {
        return index;
    }

    public final String getFullName() {
        return fullName;
    }

    // returning current level
    final int getLevel() {
        return level;
    }

    // hero has died
    public final void hasDied(final boolean isAngelInteraction) {
        this.currentHealth = 0;
        if (isAngelInteraction) {
            this.initialRoundExperience = currentExperience;
            String message = "Player " + getFullName() + " "
                    + getIndex() + " was killed by an angel\n";
            setChanged();
            notifyObservers(message);
        }
    }

    public final void getToNextLevel() {
        int nextLevelXp = Constants.EXPERIENCE_BASE
                + (level)
                * Constants.EXPERIENCE_SCALING;
        nextLevelXp -= currentExperience;
        increaseXP(nextLevelXp);
    }

    public final void increaseHP(final float hpAmount) {
        this.currentHealth = Math.min(getMaxHealth(),
                currentHealth + hpAmount);
    }

    public final void increaseXP(final int xpAmount) {
        this.currentExperience = this.currentExperience + xpAmount;
        checkForLevelUp();
    }

    public final void revive(final int reviveHp) {
        this.currentExperience = initialRoundExperience;
        this.currentHealth = reviveHp;
        this.revivedThisRound = true;
        String message = "Player " + getFullName()
                + " " + getIndex() + " was brought to life by an angel\n";
        setChanged();
        notifyObservers(message);
    }

    // checking if the hero is dead
    public final boolean isDead() {
        return this.currentHealth <= 0;
    }

    // returning health at the start of the current round
    final float getInitialRoundHealth() {
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
        strategy = null;
        if (!this.isDead()) {
            lookForStrategy();
        }
        if (strategy != null) {
            strategy.applyStrategy();
        }
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
                                    final boolean isOverTimeAbility,
                                    final boolean isAngelInteraction) {
        this.currentHealth -= damage;
        if (this.currentHealth <= 0
                && !isOverTimeAbility) {
            this.hasDied(isAngelInteraction);
            return true;
        } else if (this.currentHealth <= 0) {
            this.hasDied(isAngelInteraction);
            return true;
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
    public final void checkForLevelUp() {
        if ((this.currentExperience
                >= Constants.EXPERIENCE_BASE
                + this.getLevel()
                * Constants.EXPERIENCE_SCALING)
                && !this.isDead()) {
            int lastLevel = level + 1;
            this.level = (this.currentExperience
                    - Constants.EXPERIENCE_BASE)
                    / Constants.EXPERIENCE_SCALING + 1;
            this.currentHealth = this.getMaxHealth();
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
        if (!revivedThisRound) {
            this.initialRoundExperience = currentExperience;
        }
        if (hasMadeAKillThisRound
                && !revivedThisRound) {
            this.checkForLevelUp();
            this.hasMadeAKillThisRound = false;
        }
        this.initialRoundHealth = currentHealth;
        revivedThisRound = false;
    }

    // writing the hero to output
    @Override
    public final String toString() {
        if (!this.isDead()) {
            return this.getName() + " " + this.getLevel() + " " + this.getExperience()
                    + " " + Math.round(this.getHealth()) + " " + this.getRow() + " "
                    + this.getColon() + "\n";
        }
        return this.name + " dead\n";
    }

    public final void computeObservation(final Hero enemy) {
        String message = "Player " + enemy.getFullName() + " " + enemy.getIndex()
                + " was killed by " + getFullName() + " " + getIndex() + "\n";
        setChanged();
        notifyObservers(message);
    }

    // returning maximum health of the hero
    public abstract int getMaxHealth();

    // computing initial damage of the fight
    public abstract float computeInitialDamage(LocationType location);

    // computing initial overtime damage of the fight
    public abstract float computeInitialOvertimeDamage(LocationType location);

    // getting attacked
    public abstract void getAttackedBy(Hero enemy,
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
    public abstract void getAffectedBy(Hero enemy,
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

    public abstract void lookForStrategy();
}

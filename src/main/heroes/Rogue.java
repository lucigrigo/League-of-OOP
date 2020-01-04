package main.heroes;

import main.angels.Angel;
import main.data.Constants;
import main.data.LocationType;
import main.data.Visitable;
import main.data.OverTimeAbility;
import main.strategies.RogueAttackStrategy;
import main.strategies.RogueDefenceStrategy;

/**
 * Class to implement ROGUE logic.
 */
public final class Rogue extends Hero implements Visitable {

    private int backStabCount;
    private boolean appliedBackStabThisRound;

    public Rogue(final int initCol,
                 final int initLin,
                 final int index) {
        super(initCol, initLin, Constants.ROGUE_INITIAL_HEALTH, 0,
                "R", "Rogue", index);
        this.backStabCount = 0;
        this.appliedBackStabThisRound = false;
    }

    // returning maximum health as a ROGUE
    @Override
    public int getMaxHealth() {
        return Constants.ROGUE_INITIAL_HEALTH
                + Constants.ROGUE_HEALTH_RATIO
                * this.getLevel();
    }

    // increasing the count of the backstabs applied if it is the case
    @Override
    public void doRoundEndingRoutine() {
        super.doRoundEndingRoutine();
        if (appliedBackStabThisRound) {
            this.backStabCount++;
            appliedBackStabThisRound = false;
        }
    }

    // applied backstab this round
    private void hasAppliedBackStab() {
        this.appliedBackStabThisRound = true;
    }

    // computing initial damage as a ROGUE
    @Override
    public float computeInitialDamage(final LocationType location) {
        float damage = Math.round(Constants.ROGUE_BACKSTAB_BASE_DAMAGE
                + Constants.ROGUE_BACKSTAB_LEVEL_SCALING_BASE_DAMAGE
                * this.getLevel());
        if (location == LocationType.WOODS) { // adding location bonus
            if (this.backStabCount
                    % Constants.ROGUE_BACKSTAB_CRITICAL_HIT_OCCURENCE == 0) {
                // adding critical hit
                damage *= Constants.ROGUE_BACKSTAB_CRITICAL_HIT_RATIO;
                damage = Math.round(damage);
            }
            damage *= Constants.ROGUE_WOODS_BONUS;
        }
        return Math.round(damage);
    }

    // computing initial overtime damage as a ROGUE
    @Override
    public float computeInitialOvertimeDamage(final LocationType location) {
        float damage = Math.round(Constants.ROGUE_PARALYSIS_BASE_DAMAGE
                + Constants.ROGUE_PARALYSIS_LEVEL_SCALING_BASE_DAMAGE
                * this.getLevel());
        if (location == LocationType.WOODS) { // adding location bonus
            damage *= Constants.ROGUE_WOODS_BONUS;
        }
        return Math.round(damage);
    }

    // getting attacked as a ROGUE
    @Override
    public void getAttackedBy(final Hero enemy,
                              final LocationType location) {
        enemy.attack(this, location, true, false);
    }

    // attacking a WIZARD as a ROGUE
    @Override
    public float attack(final Wizard enemy,
                        final LocationType location,
                        final boolean addRaceModifier,
                        final boolean isForDeflectPurpose) {
        float damage = computeInitialDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= (Constants.ROGUE_BACKSTAB_BONUS_VERSUS_WIZARD
                    + getAngelBonus()
                    + getStrategyBonus());
        }
        damage = Math.round(damage);
        if (!isForDeflectPurpose) { // if interrogated by a WIZARD
            damage += Math.round(this.affectOvertime(enemy, location,
                    false, true));
            if (enemy.takeDamage(Math.round(damage), false, false)) {
                computeObservation(enemy);
                this.fightWon(enemy.getLevel());
            }
            this.hasAppliedBackStab();
        }
        damage += Math.round(this.affectOvertime(enemy, location,
                false, false));
        return damage;
    }

    // attacking a ROGUE as a ROGUE
    @Override
    public void attack(final Rogue enemy,
                       final LocationType location,
                       final boolean addRaceModifier,
                       final boolean isForDeflectPurpose) {
        float damage = computeInitialDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= (Constants.ROGUE_BACKSTAB_BONUS_VERSUS_ROGUE
                    + getAngelBonus()
                    + getStrategyBonus());
        }
        // TODO solve fightRRD aproximation issue
        damage = Math.round(damage);
        if (!isForDeflectPurpose) { // if interrogated by a WIZARD
            damage += Math.round(this.affectOvertime(enemy, location,
                    false, true));
            if (enemy.takeDamage(Math.round(damage), false, false)) {
                computeObservation(enemy);
                this.fightWon(enemy.getLevel());
            }
            this.hasAppliedBackStab();
        }
    }

    // attacking a PYROMANCER as a ROGUE
    @Override
    public void attack(final Pyromancer enemy,
                       final LocationType location,
                       final boolean addRaceModifier,
                       final boolean isForDeflectPurpose) {
        float damage = computeInitialDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= (Constants.ROGUE_BACKSTAB_BONUS_VERSUS_PYROMANCER
                    + getAngelBonus()
                    + getStrategyBonus());
        }
        damage = Math.round(damage);
        if (!isForDeflectPurpose) { // if interrogated by a WIZARD
            damage += Math.round(this.affectOvertime(enemy, location,
                    false, true));
            if (enemy.takeDamage(Math.round(damage), false, false)) {
                computeObservation(enemy);
                this.fightWon(enemy.getLevel());
            }
            this.hasAppliedBackStab();
            return;
        }
        damage += Math.round(this.affectOvertime(enemy, location,
                false, false));
    }

    // attacking a KNIGHT as a ROGUE
    @Override
    public void attack(final Knight enemy,
                       final LocationType location,
                       final boolean addRaceModifier,
                       final boolean isForDeflectPurpose) {
        float damage = computeInitialDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= (Constants.ROGUE_BACKSTAB_BONUS_VERSUS_KNIGHT
                    + getAngelBonus()
                    + getStrategyBonus());
        }
        damage = Math.round(damage);
        if (!isForDeflectPurpose) { // if interrogated by a WIZARD
            damage += Math.round(this.affectOvertime(enemy, location,
                    false, true));
            if (enemy.takeDamage(Math.round(damage), false, false)) {
                computeObservation(enemy);
                this.fightWon(enemy.getLevel());
            }
            this.hasAppliedBackStab();
            return;
        }
        damage += Math.round(this.affectOvertime(enemy, location,
                false, false));
    }

    // getting affected as a ROGUE
    @Override
    public void getAffectedBy(final Hero enemy,
                              final LocationType location) {
        enemy.affectOvertime(this, location, true, true);
    }

    // affecting overtime a WIZARD as a ROGUE
    @Override
    public float affectOvertime(final Wizard enemy,
                                final LocationType location,
                                final boolean startNow,
                                final boolean addRaceModifier) {
        float damage = computeInitialOvertimeDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= (Constants.ROGUE_PARALYSIS_BONUS_VERSUS_WIZARD
                    + getAngelBonus()
                    + getStrategyBonus());
        }
        if (startNow) { // starting the ability now
            int duration;
            if (location == LocationType.WOODS) { // increasing duration
                duration = Constants.ROGUE_PARALYSIS_ROUNDS_NUMBER_WOODS;
            } else {
                duration = Constants.ROGUE_PARALYSIS_ROUNDS_NUMBER_NORMAL;
            }
            enemy.setAbilityAffectedBy(new OverTimeAbility(this, enemy, "Paralysis",
                    location, Math.round(damage), duration, true));
            return 0f;
        }
        return damage;
    }

    // affecting overtime a ROGUE as a ROGUE
    @Override
    public float affectOvertime(final Rogue enemy,
                                final LocationType location,
                                final boolean startNow,
                                final boolean addRaceModifier) {
        float damage = computeInitialOvertimeDamage(location);
        if (addRaceModifier) { // adding race modifier
            // TODO fightRRD solve aproximation issue
//            System.out.println("damage BEFORE modifiers " + damage);
//            System.out.println("angel bonus is " + getAngelBonus());
//            System.out.println("strategy bonus is " + getStrategyBonus());
            float percent = Constants.ROGUE_PARALYSIS_BONUS_VERSUS_ROGUE
                    + getAngelBonus()
                    + getStrategyBonus();
//            System.out.println("resulting bonus is "
//                    + percent);
            damage = Math.round((percent - 0.0001f) * damage);
//            damage = percent * damage;
//            System.out.println("damage AFTER modifiers " + damage);
//            System.out.println("---");
        }
        damage = Math.round(damage);
        if (startNow) { // starting the ability now
            int duration;
            if (location == LocationType.WOODS) { // increasing duration
                duration = Constants.ROGUE_PARALYSIS_ROUNDS_NUMBER_WOODS;
            } else {
                duration = Constants.ROGUE_PARALYSIS_ROUNDS_NUMBER_NORMAL;
            }
            enemy.setAbilityAffectedBy(new OverTimeAbility(this, enemy, "Paralysis",
                    location, Math.round(damage), duration, true));
            return 0f;
        }
        return damage;
    }

    // affecting overtime a KNIGHT as a ROGUE
    @Override
    public float affectOvertime(final Knight enemy,
                                final LocationType location,
                                final boolean startNow,
                                final boolean addRaceModifier) {
        float damage = computeInitialOvertimeDamage(location);
        if (addRaceModifier) { // adding race modifier
//            System.out.println("damage BEFORE modifiers " + damage);
//            System.out.println("race modifier is " + Constants.ROGUE_PARALYSIS_BONUS_VERSUS_KNIGHT);
//            System.out.println("angel bonus is " + getAngelBonus());
//            System.out.println("strategy bonus is " + getStrategyBonus());
//            System.out.println("resulting bonus is "
//                    + (Constants.ROGUE_PARALYSIS_BONUS_VERSUS_KNIGHT
//                    + getAngelBonus()
//                    + getStrategyBonus()));
            // TODO solve fightRKD aproximation issue
            damage *= (Constants.ROGUE_PARALYSIS_BONUS_VERSUS_KNIGHT
                    + getAngelBonus()
                    + getStrategyBonus()
                    - 0.0001f);
//            System.out.println("damage AFTER modifiers " + damage);
//            System.out.println("---");
        }
        damage = Math.round(damage);
        if (startNow) { // starting the ability now
            int duration;
            if (location == LocationType.WOODS) { // increasing duration
                duration = Constants.ROGUE_PARALYSIS_ROUNDS_NUMBER_WOODS;
            } else {
                duration = Constants.ROGUE_PARALYSIS_ROUNDS_NUMBER_NORMAL;
            }
            enemy.setAbilityAffectedBy(new OverTimeAbility(this, enemy, "Paralysis",
                    location, Math.round(damage), duration, true));
            return 0f;
        }
        return damage;
    }

    // affecting overtime a PYROMANCER as a ROGUE
    @Override
    public float affectOvertime(final Pyromancer enemy,
                                final LocationType location,
                                final boolean startNow,
                                final boolean addRaceModifier) {
        float damage = computeInitialOvertimeDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= (Constants.ROGUE_PARALYSIS_BONUS_VERSUS_PYROMANCER
                    + getAngelBonus()
                    + getStrategyBonus());
        }
        if (startNow) { // starting the ability now
            int duration;
            if (location == LocationType.WOODS) { // increasing duration
                duration = Constants.ROGUE_PARALYSIS_ROUNDS_NUMBER_WOODS;
            } else {
                duration = Constants.ROGUE_PARALYSIS_ROUNDS_NUMBER_NORMAL;
            }
            enemy.setAbilityAffectedBy(new OverTimeAbility(this, enemy, "Paralysis",
                    location, Math.round(damage), duration, true));
            return 0f;
        }
        return damage;
    }

    @Override
    public void getHelpedBy(final Angel angel) {
        angel.helpHero(this);
    }

    @Override
    public void lookForStrategy() {
        if (getHealth() < Constants.ROGUE_ATTACK_STRATEGY_HIGH_MARGIN * getMaxHealth()
                && getHealth() > Constants.ROGUE_ATTACK_STRATEGY_LOW_MARGIN * getMaxHealth()) {
            setStrategy(new RogueAttackStrategy(this));
        } else if (getHealth() < Constants.ROGUE_DEFENCE_STRATEGY_HIGH_MARGIN * getMaxHealth()) {
            setStrategy(new RogueDefenceStrategy(this));
        }
    }
}

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

    /*
     Returning maximum health as a ROGUE.
     */
    @Override
    public int getMaxHealth() {
        return Constants.ROGUE_INITIAL_HEALTH
                + Constants.ROGUE_HEALTH_RATIO
                * this.getLevel();
    }

    /*
     Increasing the count of the backstabs applied if it is the case.
     */
    @Override
    public void doRoundEndingRoutine() {
        super.doRoundEndingRoutine();
        if (appliedBackStabThisRound) {
            this.backStabCount++;
            appliedBackStabThisRound = false;
        }
    }

    /*
     Applied backstab this round.
     */
    private void hasAppliedBackStab() {
        this.appliedBackStabThisRound = true;
    }

    /*
     Computing initial damage as a ROGUE.
     */
    @Override
    public float computeInitialDamage(final LocationType location) {
        float damage = Math.round(Constants.ROGUE_BACKSTAB_BASE_DAMAGE
                + Constants.ROGUE_BACKSTAB_LEVEL_SCALING_BASE_DAMAGE
                * this.getLevel());

        // adding location bonus
        if (location == LocationType.WOODS) {
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

    /*
     Computing initial overtime damage as a ROGUE.
     */
    @Override
    public float computeInitialOvertimeDamage(final LocationType location) {
        float damage = Math.round(Constants.ROGUE_PARALYSIS_BASE_DAMAGE
                + Constants.ROGUE_PARALYSIS_LEVEL_SCALING_BASE_DAMAGE
                * this.getLevel());

        // adding location bonus
        if (location == LocationType.WOODS) {
            damage *= Constants.ROGUE_WOODS_BONUS;
        }
        return Math.round(damage);
    }

    /*
     Getting attacked as a ROGUE.
     */
    @Override
    public void getAttackedBy(final Hero enemy,
                              final LocationType location) {
        enemy.attack(this, location, true);
    }

    /*
     Attacking a WIZARD as a ROGUE.
     */
    @Override
    public float attack(final Wizard wizard,
                        final LocationType location,
                        final boolean addRaceModifier,
                        final boolean isForDeflectPurpose) {
        float damage = computeInitialDamage(location);

        // adding race modifier
        if (addRaceModifier) {
            float percent = Constants.ROGUE_BACKSTAB_BONUS_VERSUS_WIZARD;
            for (Float bonus : getAngelBonuses()) {
                percent += bonus;
            }
            percent += getStrategyBonus();
            damage *= percent;
        }
        damage = Math.round(damage);

        // if interrogated by a WIZARD
        if (!isForDeflectPurpose) {
            damage += Math.round(this.affectOvertime(wizard, location,
                    false, true));

            // making enemy take computed damage
            if (wizard.takeDamage(Math.round(damage), false, false)) {
                computeObservation(wizard);
                this.fightWon(wizard.getLevel());
            }
            this.hasAppliedBackStab();
        }
        damage += Math.round(this.affectOvertime(wizard, location,
                false, false));
        return damage;
    }

    /*
    Attacking a ROGUE as a ROGUE.
     */
    @Override
    public void attack(final Rogue enemy,
                       final LocationType location,
                       final boolean addRaceModifier) {
        float damage = computeInitialDamage(location);

        // adding race modifier
        if (addRaceModifier) {
            float percent = Constants.ROGUE_BACKSTAB_BONUS_VERSUS_ROGUE;
            for (Float bonus : getAngelBonuses()) {
                percent += bonus;
            }
            percent += getStrategyBonus();
            damage *= percent;
        }
        damage = Math.round(damage);

        // adding overtime damage
        damage += Math.round(this.affectOvertime(enemy, location,
                false, true));

        // making enemy take computed damage
        if (enemy.takeDamage(Math.round(damage), false, false)) {
            computeObservation(enemy);
            this.fightWon(enemy.getLevel());
        }
        this.hasAppliedBackStab();
    }

    /*
     Attacking a PYROMANCER as a ROGUE.
     */
    @Override
    public void attack(final Pyromancer enemy,
                       final LocationType location,
                       final boolean addRaceModifier) {
        float damage = computeInitialDamage(location);

        // adding race modifier
        if (addRaceModifier) {
            float percent = Constants.ROGUE_BACKSTAB_BONUS_VERSUS_PYROMANCER;
            for (Float bonus : getAngelBonuses()) {
                percent += bonus;
            }
            percent += getStrategyBonus();
            damage *= percent;
        }
        damage = Math.round(damage);

        // adding overtime damage
        damage += Math.round(this.affectOvertime(enemy, location,
                false, true));

        // making enemy take computed damage
        if (enemy.takeDamage(Math.round(damage), false, false)) {
            computeObservation(enemy);
            this.fightWon(enemy.getLevel());
        }
        this.hasAppliedBackStab();
    }

    /*
     Attacking a KNIGHT as a ROGUE.
     */
    @Override
    public void attack(final Knight enemy,
                       final LocationType location,
                       final boolean addRaceModifier) {
        float damage = computeInitialDamage(location);

        // adding race modifier
        if (addRaceModifier) {
            float percent = Constants.ROGUE_BACKSTAB_BONUS_VERSUS_KNIGHT;
            for (Float bonus : getAngelBonuses()) {
                percent += bonus;
            }
            percent += getStrategyBonus();
            damage *= percent;
        }
        damage = Math.round(damage);

        // adding overtime damage
        damage += Math.round(this.affectOvertime(enemy, location,
                false, true));

        // making enemy take computed damage
        if (enemy.takeDamage(Math.round(damage), false, false)) {
            computeObservation(enemy);
            this.fightWon(enemy.getLevel());
        }
        this.hasAppliedBackStab();
    }

    /**
     * @param enemy    enemy that is fighting current hero
     * @param location location type
     */
    @Override
    public void getAffectedBy(final Hero enemy,
                              final LocationType location) {
        enemy.affectOvertime(this, location, true, true);
    }

    /*
     Affecting overtime a WIZARD as a ROGUE.
     */
    @Override
    public float affectOvertime(final Wizard enemy,
                                final LocationType location,
                                final boolean startNow,
                                final boolean addRaceModifier) {
        float damage = computeInitialOvertimeDamage(location);

        // adding race modifier
        if (addRaceModifier) {
            float percent = Constants.ROGUE_PARALYSIS_BONUS_VERSUS_WIZARD;
            for (Float bonus : getAngelBonuses()) {
                percent += bonus;
            }
            percent += getStrategyBonus();
            damage *= percent;
        }

        // starting the ability now
        if (startNow) {
            int duration;
            if (location == LocationType.WOODS) {
                // increasing duration
                duration = Constants.ROGUE_PARALYSIS_ROUNDS_NUMBER_WOODS;
            } else {
                duration = Constants.ROGUE_PARALYSIS_ROUNDS_NUMBER_NORMAL;
            }
            enemy.setAbilityAffectedBy(new OverTimeAbility(enemy,
                    Math.round(damage), duration, true));
            return 0f;
        }
        return damage;
    }

    /*
     Affecting overtime a ROGUE as a ROGUE.
     */
    @Override
    public float affectOvertime(final Rogue enemy,
                                final LocationType location,
                                final boolean startNow,
                                final boolean addRaceModifier) {
        float damage = computeInitialOvertimeDamage(location);

        // adding race modifier
        if (addRaceModifier) {
            float percent = Constants.ROGUE_PARALYSIS_BONUS_VERSUS_ROGUE;
            for (Float bonus : getAngelBonuses()) {
                percent += bonus;
            }
            percent += getStrategyBonus();
            damage *= percent;
        }
        damage = Math.round(damage);

        // starting the ability now
        if (startNow) {
            int duration;
            if (location == LocationType.WOODS) {
                // increasing duration
                duration = Constants.ROGUE_PARALYSIS_ROUNDS_NUMBER_WOODS;
            } else {
                duration = Constants.ROGUE_PARALYSIS_ROUNDS_NUMBER_NORMAL;
            }
            enemy.setAbilityAffectedBy(new OverTimeAbility(enemy,
                    Math.round(damage), duration, true));
            return 0f;
        }
        return damage;
    }

    /*
     Affecting overtime a KNIGHT as a ROGUE.
     */
    @Override
    public float affectOvertime(final Knight enemy,
                                final LocationType location,
                                final boolean startNow,
                                final boolean addRaceModifier) {
        float damage = computeInitialOvertimeDamage(location);

        // adding race modifier
        if (addRaceModifier) {
            float percent = Constants.ROGUE_PARALYSIS_BONUS_VERSUS_KNIGHT;
            for (Float bonus : getAngelBonuses()) {
                percent += bonus;
            }
            percent += getStrategyBonus();
            damage *= percent;
        }
        damage = Math.round(damage);

        // starting the ability now
        if (startNow) {
            int duration;
            if (location == LocationType.WOODS) {
                // increasing duration
                duration = Constants.ROGUE_PARALYSIS_ROUNDS_NUMBER_WOODS;
            } else {
                duration = Constants.ROGUE_PARALYSIS_ROUNDS_NUMBER_NORMAL;
            }
            enemy.setAbilityAffectedBy(new OverTimeAbility(enemy,
                    Math.round(damage), duration, true));
            return 0f;
        }
        return damage;
    }

    /*
     Affecting overtime a PYROMANCER as a ROGUE.
     */
    @Override
    public float affectOvertime(final Pyromancer enemy,
                                final LocationType location,
                                final boolean startNow,
                                final boolean addRaceModifier) {
        float damage = computeInitialOvertimeDamage(location);

        // adding race modifier
        if (addRaceModifier) {
            float percent = Constants.ROGUE_PARALYSIS_BONUS_VERSUS_PYROMANCER;
            for (Float bonus : getAngelBonuses()) {
                percent += bonus;
            }
            percent += getStrategyBonus();
            damage *= percent;
        }

        // starting the ability now
        if (startNow) {
            int duration;
            if (location == LocationType.WOODS) {
                // increasing duration
                duration = Constants.ROGUE_PARALYSIS_ROUNDS_NUMBER_WOODS;
            } else {
                duration = Constants.ROGUE_PARALYSIS_ROUNDS_NUMBER_NORMAL;
            }
            enemy.setAbilityAffectedBy(new OverTimeAbility(enemy,
                    Math.round(damage), duration, true));
            return 0f;
        }
        return damage;
    }

    /*
    Getting helped by an angel.
     */
    @Override
    public void getHelpedBy(final Angel angel) {
        angel.helpHero(this);
    }

    /*
    Looking for strategy from ROGUE perspective.
     */
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

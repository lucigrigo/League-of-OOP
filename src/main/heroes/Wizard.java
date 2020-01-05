package main.heroes;

import main.angels.Angel;
import main.data.Constants;
import main.data.LocationType;
import main.data.Visitable;
import main.strategies.WizardAttackStrategy;
import main.strategies.WizardDefenceStrategy;

/**
 * Class to implement WIZARD logic.
 */
public final class Wizard extends Hero implements Visitable {

    public Wizard(final int initCol,
                  final int initLin,
                  final int index) {
        super(initCol, initLin, Constants.WIZARD_INITIAL_HEALTH, 0,
                "W", "Wizard", index);
    }

    /*
     Returning maximum health as a WIZARD
     */
    @Override
    public int getMaxHealth() {
        return Constants.WIZARD_INITIAL_HEALTH
                + Constants.WIZARD_HEALTH_RATIO
                * this.getLevel();
    }

    /**
     * Computing initial DRAIN damage, adding location bonus if possible.
     *
     * @param location battle location type
     * @return initial damage for DRAIN
     */
    @Override
    public float computeInitialDamage(final LocationType location) {
        // initial percentage
        float percent = Constants.WIZARD_DRAIN_BASE_PERCENTAGE
                + Constants.WIZARD_DRAIN_LEVEL_SCALING_PERCENTAGE
                * this.getLevel();

        // adding location bonus
        if (location == LocationType.DESERT) {
            percent *= Constants.WIZARD_DESERT_BONUS;
        }
        return percent;
    }

    /**
     * Computing initial DEFLECT damage, adding location bonus if possible.
     *
     * @param location battle location type
     * @return initial DEFLECT damage
     */
    @Override
    public float computeInitialOvertimeDamage(final LocationType location) {
        // initial percentage
        float percent = Constants.WIZARD_DEFLECT_BASE_PERCENTAGE
                + Constants.WIZARD_DEFLECT_LEVEL_SCALING_BASE_PERCENTAGE
                * this.getLevel();
        percent = Math.min(Constants.WIZARD_DEFLECT_MAXIMUM_PERCENTAGE, percent);

        // adding location bonus
        if (location == LocationType.DESERT) {
            percent *= Constants.WIZARD_DESERT_BONUS;
        }
        return percent;
    }

    /*
     Getting attacked as a WIZARD.
     */
    @Override
    public void getAttackedBy(final Hero enemy,
                              final LocationType location) {
        enemy.attack(this, location, true, false);
    }

    /*
     Attacking a WIZARD as a WIZARD.
     */
    @Override
    public float attack(final Wizard wizard,
                        final LocationType location,
                        final boolean addRaceModifier,
                        final boolean isForDeflectPurpose) {
        float percent = computeInitialDamage(location);
        // adding race modifier if requested
        if (addRaceModifier) {
            float multiplier = Constants.WIZARD_DRAIN_BONUS_VERSUS_WIZARD;
            for (Float bonus : getAngelBonuses()) {
                multiplier += bonus;
            }
            multiplier += getStrategyBonus();
            percent *= multiplier;
        }

        // computing drain damage
        float damage = percent
                * Math.min(Constants.WIZARD_DRAIN_HEALTH_PERCENTAGE
                * wizard.getMaxHealth(), wizard.getHealth());

        // making enemy take computed damage
        if (wizard.takeDamage(Math.round(damage), false, false)) {
            computeObservation(wizard);
            this.fightWon(wizard.getLevel());
        }
        return 0f;
    }

    /*
     Attacking a ROGUE as a WIZARD.
     */
    @Override
    public void attack(final Rogue enemy,
                       final LocationType location,
                       final boolean addRaceModifier) {
        float percent = computeInitialDamage(location);

        // adding race modifier
        if (addRaceModifier) {
            float multiplier = Constants.WIZARD_DRAIN_BONUS_VERSUS_ROGUE;
            for (Float bonus : getAngelBonuses()) {
                multiplier += bonus;
            }
            multiplier += getStrategyBonus();
            percent *= multiplier;
        }

        // computing drain damage
        float damage = percent
                * Math.min(Constants.WIZARD_DRAIN_HEALTH_PERCENTAGE
                * enemy.getMaxHealth(), enemy.getHealth());
        damage = Math.round(damage);

        // adding overtime damage
        damage += Math.round(affectOvertime(enemy, location, false, true));

        // making enemy take damage
        if (enemy.takeDamage(Math.round(damage), false, false)) {
            computeObservation(enemy);
            this.fightWon(enemy.getLevel());
        }
    }

    /*
     Attacking a PYROMANCER as a WIZARD.
     */
    @Override
    public void attack(final Pyromancer enemy,
                       final LocationType location,
                       final boolean addRaceModifier) {
        float percent = computeInitialDamage(location);

        // adding race modifier
        if (addRaceModifier) {
            float multiplier = Constants.WIZARD_DRAIN_BONUS_VERSUS_PYROMANCER;
            for (Float bonus : getAngelBonuses()) {
                multiplier += bonus;
            }
            multiplier += getStrategyBonus();
            percent *= multiplier;
        }

        // computing drain damage
        float damage = Math.round(percent
                * Math.min(Constants.WIZARD_DRAIN_HEALTH_PERCENTAGE
                * enemy.getMaxHealth(), enemy.getHealth()));

        // adding overtime damage
        damage += Math.round(affectOvertime(enemy, location, false, true));

        // making enemy take damage
        if (enemy.takeDamage(Math.round(damage), false, false)) {
            computeObservation(enemy);
            this.fightWon(enemy.getLevel());
        }
    }


    /*
     Attacking a KNIGHT as a WIZARD.
     */
    @Override
    public void attack(final Knight enemy,
                       final LocationType location,
                       final boolean addRaceModifier) {
        float percent = computeInitialDamage(location);

        // adding race modifier
        if (addRaceModifier) {
            float multiplier = Constants.WIZARD_DRAIN_BONUS_VERSUS_KNIGHT;
            for (Float bonus : getAngelBonuses()) {
                multiplier += bonus;
            }
            multiplier += getStrategyBonus();
            percent *= multiplier;
        }

        // computing drain damage
        float damage = Math.round(percent
                * Math.min(Constants.WIZARD_DRAIN_HEALTH_PERCENTAGE
                * enemy.getMaxHealth(), enemy.getHealth()));

        // adding overtime damage
        damage += Math.round(affectOvertime(enemy, location, false, true));

        // making enemy take damage
        if (enemy.takeDamage(Math.round(damage), false, false)) {
            computeObservation(enemy);
            this.fightWon(enemy.getLevel());
        }
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
     WIZARD does not have a deflect ability against WIZARD.
     */
    @Override
    public float affectOvertime(final Wizard enemy,
                                final LocationType location,
                                final boolean startNow,
                                final boolean addRaceModifier) {
        return 0f;
    }

    /*
     Computing DEFLECT damage versus a ROGUE.
     */
    @Override
    public float affectOvertime(final Rogue enemy,
                                final LocationType location,
                                final boolean startNow,
                                final boolean addRaceModifier) {
        float enemyDamage = enemy.attack(this, location, false, true);
        float percent = computeInitialOvertimeDamage(location);
        enemyDamage *= percent;

        // adding race modifier
        if (addRaceModifier) {
            percent = Constants.WIZARD_DEFLECT_BONUS_VERSUS_ROGUE;
            for (Float bonus : getAngelBonuses()) {
                percent += bonus;
            }
            percent += getStrategyBonus();
            enemyDamage *= percent;
        }
        return Math.round(enemyDamage);
    }

    /*
     Computing DEFLECT damage versus a KNIGHT.
     */
    @Override
    public float affectOvertime(final Knight enemy,
                                final LocationType location,
                                final boolean startNow,
                                final boolean addRaceModifier) {
        float enemyDamage = enemy.attack(this, location, false, true);
        float percent = computeInitialOvertimeDamage(location);
        enemyDamage *= percent;

        // adding race modifier
        if (addRaceModifier) {
            percent = Constants.WIZARD_DEFLECT_BONUS_VERSUS_KNIGHT;
            for (Float bonus : getAngelBonuses()) {
                percent += bonus;
            }
            percent += getStrategyBonus();
            enemyDamage *= percent;
        }
        return Math.round(enemyDamage);
    }

    /*
     Computing DEFLECT damage versus a PYROMANCER.
     */
    @Override
    public float affectOvertime(final Pyromancer enemy,
                                final LocationType location,
                                final boolean startNow,
                                final boolean addRaceModifier) {
        float enemyDamage = enemy.attack(this, location, false, true);
        float percent = computeInitialOvertimeDamage(location);
        enemyDamage *= percent;

        // adding race modifier
        if (addRaceModifier) {
            percent = Constants.WIZARD_DEFLECT_BONUS_VERSUS_PYROMANCER;
            for (Float bonus : getAngelBonuses()) {
                percent += bonus;
            }
            percent += getStrategyBonus();
            enemyDamage *= percent;
        }
        return Math.round(enemyDamage);
    }

    /**
     * @param angel angel that influences the hero
     */
    @Override
    public void getHelpedBy(final Angel angel) {
        angel.helpHero(this);
    }

    /*
    Looking for strategy from WIZARD perspective.
     */
    @Override
    public void lookForStrategy() {
        if (getHealth() > Constants.WIZARD_ATTACK_STRATEGY_LOW_MARGIN * getMaxHealth()
                && getHealth() < Constants.WIZARD_ATTACK_STRATEGY_HIGH_MARGIN * getMaxHealth()) {
            setStrategy(new WizardAttackStrategy(this));
        } else if (getHealth() < Constants.WIZARD_DEFENCE_STRATEGY_HIGH_MARGIN * getMaxHealth()) {
            setStrategy(new WizardDefenceStrategy(this));
        }
    }
}

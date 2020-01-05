package main.heroes;

import main.angels.Angel;
import main.data.Constants;
import main.data.LocationType;
import main.data.Visitable;
import main.data.OverTimeAbility;
import main.strategies.PyromancerAttackStrategy;
import main.strategies.PyromancerDefenceStrategy;

/**
 * Class to implement PYROMANCER logic.
 */
public final class Pyromancer extends Hero implements Visitable {

    public Pyromancer(final int initCol,
                      final int initLin,
                      final int index) {
        super(initCol, initLin, Constants.PYROMANCER_INITIAL_HEALTH, 0,
                "P", "Pyromancer", index);
    }

    /*
     Returning maximum health for a PYROMANCER.
     */
    @Override
    public int getMaxHealth() {
        return Constants.PYROMANCER_INITIAL_HEALTH
                + Constants.PYROMANCER_HEALTH_RATIO
                * this.getLevel();
    }

    /*
     Computing initial damage as a PYROMANCER.
     */
    @Override
    public float computeInitialDamage(final LocationType location) {
        float damage = Constants.PYROMANCER_FIREBLAST_BASE_DAMAGE
                + Constants.PYROMANCER_FIREBLAST_LEVEL_SCALING_BASE_DAMAGE
                * this.getLevel();

        // adding location bonus
        if (location == LocationType.VOLCANIC) {
            damage *= Constants.PYROMANCER_VOLCANIC_BONUS;
        }
        return Math.round(damage);
    }

    /*
     Computing initial overtime damage as a PYROMANCER.
     */
    @Override
    public float computeInitialOvertimeDamage(final LocationType location) {
        float damage = Constants.PYROMANCER_IGNITE_BASE_DAMAGE
                + Constants.PYROMANCER_IGNITE_LEVEL_SCALING_BASE_DAMAGE
                * this.getLevel();

        // adding location bonus
        if (location == LocationType.VOLCANIC) {
            damage *= Constants.PYROMANCER_VOLCANIC_BONUS;
        }
        return Math.round(damage);
    }

    /*
     Getting attacked as a PYROMANCER.
     */
    @Override
    public void getAttackedBy(final Hero enemy,
                              final LocationType location) {
        enemy.attack(this, location, true);
    }

    /*
     Attacking a WIZARD as a PYROMANCER.
     */
    @Override
    public float attack(final Wizard wizard,
                        final LocationType location,
                        final boolean addRaceModifier,
                        final boolean isForDeflectPurpose) {
        float damage = computeInitialDamage(location);

        // adding race modifier
        if (addRaceModifier) {
            float percent = Constants.PYROMANCER_FIREBLAST_BONUS_VERSUS_WIZARD;
            for (Float bonus : getAngelBonuses()) {
                percent += bonus;
            }
            percent += getStrategyBonus();
            damage *= percent;
        }
        damage = Math.round(damage);

        // if not interrogated by a WIZARD
        if (!isForDeflectPurpose) {
            damage += Math.round(this.affectOvertime(wizard, location, false, true));

            // making enemy take computed damage
            if (wizard.takeDamage(Math.round(damage), false, false)) {
                computeObservation(wizard);
                this.fightWon(wizard.getLevel());
            }
            return 0f;
        }
        damage += Math.round(this.affectOvertime(wizard, location,
                false, false));
        return damage;
    }

    /*
     Attacking a ROGUE as a PYROMANCER.
     */
    @Override
    public void attack(final Rogue enemy,
                       final LocationType location,
                       final boolean addRaceModifier) {
        float damage = computeInitialDamage(location);

        // adding race modifier
        if (addRaceModifier) {
            float percent = Constants.PYROMANCER_FIREBLAST_BONUS_VERSUS_ROGUE;
            for (Float bonus : getAngelBonuses()) {
                percent += bonus;
            }
            percent += getStrategyBonus();
            damage *= percent;
        }
        damage = Math.round(damage);

        damage += Math.round(this.affectOvertime(enemy, location, false, true));

        // making enemy take computed damage
        if (enemy.takeDamage(Math.round(damage), false, false)) {
            computeObservation(enemy);
            this.fightWon(enemy.getLevel());
        }
    }

    /*
     Attacking a PYROMANCER as a PYROMANCER.
     */
    @Override
    public void attack(final Pyromancer enemy,
                       final LocationType location,
                       final boolean addRaceModifier) {
        float damage = computeInitialDamage(location);

        // adding race modifier
        if (addRaceModifier) {
            float percent = Constants.PYROMANCER_FIREBLAST_BONUS_VERSUS_PYROMANCER;
            for (Float bonus : getAngelBonuses()) {
                percent += bonus;
            }
            percent += getStrategyBonus();
            damage *= percent;
        }
        damage = Math.round(damage);

        // adding overtime damage
        damage += Math.round(this.affectOvertime(enemy, location, false, true));

        // making enemy take computed damage
        if (enemy.takeDamage(Math.round(damage), false, false)) {
            computeObservation(enemy);
            this.fightWon(enemy.getLevel());
        }
    }

    /*
     Attacking a KNIGHT as a PYROMANCER.
     */
    @Override
    public void attack(final Knight enemy,
                       final LocationType location,
                       final boolean addRaceModifier) {

        float damage = computeInitialDamage(location);

        // adding race modifier
        if (addRaceModifier) {
            float percent = Constants.PYROMANCER_FIREBLAST_BONUS_VERSUS_KNIGHT;
            for (Float bonus : getAngelBonuses()) {
                percent += bonus;
            }
            percent += getStrategyBonus();
            damage *= percent;
        }
        damage = Math.round(damage);

        // adding overtime damage
        damage += Math.round(this.affectOvertime(enemy, location, false, true));

        // making enemy take computed damage
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
     Affecting overtime a WIZARD as a PYROMANCER.
     */
    @Override
    public float affectOvertime(final Wizard enemy,
                                final LocationType location,
                                final boolean startNow,
                                final boolean addRaceModifier) {
        float damage;

        if (startNow) {
            // initial damage
            damage = Constants.PYROMANCER_IGNITE_ROUND_DAMAGE
                    + Constants.PYROMANCER_IGNITE_LEVEL_SCALING_ROUND_DAMAGE
                    * this.getLevel();
            if (location == LocationType.VOLCANIC) {
                damage *= Constants.PYROMANCER_VOLCANIC_BONUS;
            }
            damage = Math.round(damage);
        } else {
            // subsequent damage
            damage = computeInitialOvertimeDamage(location);
        }
        damage = Math.round(damage);

        // adding race modifier
        if (addRaceModifier) {
            float percent = Constants.PYROMANCER_IGNITE_BONUS_VERSUS_WIZARD;
            for (Float bonus : getAngelBonuses()) {
                percent += bonus;
            }
            percent += getStrategyBonus();
            damage *= percent;
        }

        // starting the ability now
        if (startNow) {
            enemy.setAbilityAffectedBy(new OverTimeAbility(enemy,
                    Math.round(damage), Constants.PYROMANCER_IGNITE_DURATION));
            return 0f;
        }
        return damage;
    }

    /*
     Affecting overtime a ROGUE as a PYROMANCER.
     */
    @Override
    public float affectOvertime(final Rogue enemy,
                                final LocationType location,
                                final boolean startNow,
                                final boolean addRaceModifier) {
        float damage;

        if (startNow) {
            // initial damage
            damage = Constants.PYROMANCER_IGNITE_ROUND_DAMAGE
                    + Constants.PYROMANCER_IGNITE_LEVEL_SCALING_ROUND_DAMAGE
                    * this.getLevel();
            if (location == LocationType.VOLCANIC) {
                damage *= Constants.PYROMANCER_VOLCANIC_BONUS;
            }
        } else {
            // subsequent damage
            damage = computeInitialOvertimeDamage(location);
        }
        damage = Math.round(damage);

        // adding race modifier
        if (addRaceModifier) {
            float percent = Constants.PYROMANCER_IGNITE_BONUS_VERSUS_ROGUE;
            for (Float bonus : getAngelBonuses()) {
                percent += bonus;
            }
            percent += getStrategyBonus();
            damage *= percent;
        }

        // starting the ability now
        if (startNow) {
            enemy.setAbilityAffectedBy(new OverTimeAbility(enemy,
                    Math.round(damage), Constants.PYROMANCER_IGNITE_DURATION));
            return 0f;
        }
        return damage;
    }

    /*
     Affecting overtime a KNIGHT as a PYROMANCER.
     */
    @Override
    public float affectOvertime(final Knight enemy,
                                final LocationType location,
                                final boolean startNow,
                                final boolean addRaceModifier) {
        float damage;

        if (startNow) {
            // initial damage
            damage = Constants.PYROMANCER_IGNITE_ROUND_DAMAGE
                    + Constants.PYROMANCER_IGNITE_LEVEL_SCALING_ROUND_DAMAGE
                    * this.getLevel();
            if (location == LocationType.VOLCANIC) {
                damage *= (Constants.PYROMANCER_VOLCANIC_BONUS);
            }
        } else {
            // subsequent damage
            damage = computeInitialOvertimeDamage(location);
        }
        damage = Math.round(damage);

        // adding race modifier
        if (addRaceModifier) {
            float percent = Constants.PYROMANCER_IGNITE_BONUS_VERSUS_KNIGHT;
            for (Float bonus : getAngelBonuses()) {
                percent += bonus;
            }
            percent += getStrategyBonus();
            damage *= percent;
        }

        // starting the ability now
        if (startNow) {
            enemy.setAbilityAffectedBy(new OverTimeAbility(enemy,
                    Math.round(damage), Constants.PYROMANCER_IGNITE_DURATION));
            return 0f;
        }
        return damage;
    }

    /*
     Affecting overtime a PYROMANCER as a PYROMANCER.
     */
    @Override
    public float affectOvertime(final Pyromancer enemy,
                                final LocationType location,
                                final boolean startNow,
                                final boolean addRaceModifier) {
        float damage;

        if (startNow) {
            // initial damage
            damage = Constants.PYROMANCER_IGNITE_ROUND_DAMAGE
                    + Constants.PYROMANCER_IGNITE_LEVEL_SCALING_ROUND_DAMAGE
                    * this.getLevel();
            if (location == LocationType.VOLCANIC) {
                damage *= Constants.PYROMANCER_VOLCANIC_BONUS;
            }
        } else {
            // subsequent damage
            damage = computeInitialOvertimeDamage(location);
        }
        damage = Math.round(damage);

        // adding race modifier
        if (addRaceModifier) {
            float percent = Constants.PYROMANCER_IGNITE_BONUS_VERSUS_PYROMANCER;
            for (Float bonus : getAngelBonuses()) {
                percent += bonus;
            }
            percent += getStrategyBonus();
            damage *= percent;
        }

        // starting the ability now
        if (startNow) {
            enemy.setAbilityAffectedBy(new OverTimeAbility(enemy,
                    Math.round(damage), Constants.PYROMANCER_IGNITE_DURATION));
            return 0f;
        }
        return damage;
    }

    /*
      Getting influenced by an angel.
     */
    @Override
    public void getHelpedBy(final Angel angel) {
        angel.helpHero(this);
    }

    /**
     * Looking for strategy from PYROMANCER perspective.
     */
    @Override
    public void lookForStrategy() {
        if (getHealth() > Constants.PYROMANCER_ATTACK_STRATEGY_LOW_MARGIN * getMaxHealth()
                && getHealth() < Constants.PYROMANCER_ATTACK_STRATEGY_HIGH_MARGIN
                * getMaxHealth()) {
            setStrategy(new PyromancerAttackStrategy(this));
        } else if (getHealth() < Constants.PYROMANCER_DEFENCE_STRATEGY_HIGH_MARGIN
                * getMaxHealth()) {
            setStrategy(new PyromancerDefenceStrategy(this));
        }
    }
}

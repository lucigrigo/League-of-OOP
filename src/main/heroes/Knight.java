package main.heroes;

import main.angels.Angel;
import main.data.Constants;
import main.data.LocationType;
import main.data.Visitable;
import main.data.OverTimeAbility;
import main.strategies.KnightAttackStrategy;
import main.strategies.KnightDefenceStrategy;

/**
 * Class to implement Knight logic.
 */
public final class Knight extends Hero implements Visitable {

    public Knight(final int initCol,
                  final int initLin,
                  final int index) {
        super(initCol, initLin, Constants.KNIGHT_INITIAL_HEALTH, 0,
                "K", "Knight", index);
    }

    /*
     Returning maximum health for KNIGHT.
     */
    @Override
    public int getMaxHealth() {
        return Constants.KNIGHT_INITIAL_HEALTH
                + Constants.KNIGHT_HEALTH_RATIO
                * this.getLevel();
    }

    /*
     Computing initial EXECUTE damage.
     */
    @Override
    public float computeInitialDamage(final LocationType location) {
        float damage = Constants.KNIGHT_EXECUTE_BASE_DAMAGE
                + Constants.KNIGHT_EXECUTE_LEVEL_SCALING_BASE_DAMAGE
                * this.getLevel();
        if (location == LocationType.LAND) { // adding location bonus
            damage *= Constants.KNIGHT_LAND_BONUS;
        }
        return damage;
    }

    /*
     Looking to see if execute is possible.
     */
    private boolean checkExecutePossibility(final float enemyCurrentHealth,
                                            final int enemyMaxHealth) {
        float damagePercent = Constants.KNIGHT_EXECUTE_HP_LIMIT_PERCENTAGE
                + Constants.KNIGHT_EXECUTE_HP_LIMIT_LEVEL_SCALING_PERCENTAGE
                * this.getLevel();
        damagePercent = Math.min(damagePercent,
                Constants.KNIGHT_EXECUTE_MAXIMUM_HP_PERCENTAGE);
        return damagePercent * enemyMaxHealth
                >= enemyCurrentHealth;
    }

    /*
     Computing initial SLAM damage.
     */
    public float computeInitialOvertimeDamage(final LocationType location) {
        float damage = Constants.KNIGHT_SLAM_BASE_DAMAGE
                + Constants.KNIGHT_SLAM_LEVEL_SCALING_BASE_DAMAGE
                * this.getLevel();
        if (location == LocationType.LAND) { // adding location bonus
            damage *= Constants.KNIGHT_LAND_BONUS;
        }
        return damage;
    }

    /*
     Getting attacked as KNIGHT.
     */
    @Override
    public void getAttackedBy(final Hero enemy,
                              final LocationType location) {
        enemy.attack(this, location, true);
    }

    /*
     Attacking a WIZARD as a KNIGHT.
     */
    @Override
    public float attack(final Wizard wizard,
                        final LocationType location,
                        final boolean addRaceModifier,
                        final boolean isForDeflectPurpose) {
        // looking for execute potential
        if (checkExecutePossibility(wizard.getInitialRoundHealth(), wizard.getMaxHealth())) {
            if (!isForDeflectPurpose) {
                computeObservation(wizard);
                this.fightWon(wizard.getLevel());
                wizard.hasDied(false);
            }
        }
        float damage = computeInitialDamage(location);

        // adding race modifier
        if (addRaceModifier) {
            float percent = Constants.KNIGHT_EXECUTE_BONUS_VERSUS_WIZARD;
            for (Float bonus : getAngelBonuses()) {
                percent += bonus;
            }
            percent += getStrategyBonus();
            damage *= percent;
        }
        damage = Math.round(damage);

        // if not interrogated by a wizard
        if (!isForDeflectPurpose) {
            damage += Math.round(this.affectOvertime(wizard,
                    location, false, true));

            // making enemy take computed damage
            if (!wizard.isDead()
                    && wizard.takeDamage(Math.round(damage), false, false)) {
                computeObservation(wizard);
                this.fightWon(wizard.getLevel());
            }
        }
        damage += Math.round(this.affectOvertime(wizard, location,
                false, false));
        return damage;
    }

    /*
     Attacking a ROGUE as a KNIGHT.
     */
    @Override
    public void attack(final Rogue enemy,
                       final LocationType location,
                       final boolean addRaceModifier) {
        // looking for execute potential
        if (checkExecutePossibility(enemy.getInitialRoundHealth(), enemy.getMaxHealth())) {
            computeObservation(enemy);
            this.fightWon(enemy.getLevel());
            enemy.hasDied(false);
            return;
        }
        float damage = computeInitialDamage(location);

        // adding race modifier
        if (addRaceModifier) {
            float percent = Constants.KNIGHT_EXECUTE_BONUS_VERSUS_ROGUE;
            for (Float bonus : getAngelBonuses()) {
                percent += bonus;
            }
            percent += getStrategyBonus();
            damage *= percent;
        }
        damage = Math.round(damage);

        // adding overtime damage
        damage += Math.round(this.affectOvertime(enemy,
                location, false, true));

        // making enemy take computed damage
        if (enemy.takeDamage(Math.round(damage), false, false)) {
            computeObservation(enemy);
            this.fightWon(enemy.getLevel());
        }
    }

    /*
     Attacking a PYROMANCER as a KNIGHT.
     */
    @Override
    public void attack(final Pyromancer enemy,
                       final LocationType location,
                       final boolean addRaceModifier) {
        // looking for execute potential
        if (checkExecutePossibility(enemy.getInitialRoundHealth(), enemy.getMaxHealth())) {
            computeObservation(enemy);
            this.fightWon(enemy.getLevel());
            enemy.hasDied(false);
            return;
        }
        float damage = computeInitialDamage(location);

        // adding race modifier
        if (addRaceModifier) {
            float percent = Constants.KNIGHT_EXECUTE_BONUS_VERSUS_PYROMANCER;
            for (Float bonus : getAngelBonuses()) {
                percent += bonus;
            }
            percent += getStrategyBonus();
            damage *= percent;
        }
        damage = Math.round(damage);

        // adding overtime damage
        damage += Math.round(this.affectOvertime(enemy,
                location, false, true));

        // making enemy take computed damage
        if (enemy.takeDamage(Math.round(damage), false, false)) {
            computeObservation(enemy);
            this.fightWon(enemy.getLevel());
        }
    }

    /*
     Attacking a KNIGHT as a KNIGHT.
     */
    @Override
    public void attack(final Knight enemy,
                       final LocationType location,
                       final boolean addRaceModifier) {
        // looking for execute potential
        if (checkExecutePossibility(enemy.getInitialRoundHealth(), enemy.getMaxHealth())) {
            computeObservation(enemy);
            this.fightWon(enemy.getLevel());
            enemy.hasDied(false);
            return;
        }
        float damage = computeInitialDamage(location);

        // adding race modifier
        if (addRaceModifier) {
            damage *= Constants.KNIGHT_EXECUTE_BONUS_VERSUS_KNIGHT;
        }
        damage = Math.round(damage);

        // adding overtime damage
        damage += Math.round(this.affectOvertime(enemy,
                location, false, true));

        // making enemy take computed damage
        if (enemy.takeDamage(Math.round(damage), false, false)) {
            this.fightWon(enemy.getLevel());
            computeObservation(enemy);
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
     Affecting overtime a WIZARD as a KNIGHT.
     */
    @Override
    public float affectOvertime(final Wizard enemy,
                                final LocationType location,
                                final boolean startNow,
                                final boolean addRaceModifier) {
        float damage = computeInitialOvertimeDamage(location);

        // adding race modifier
        if (addRaceModifier) {
            float percent = Constants.KNIGHT_SLAM_BONUS_VERSUS_WIZARD;
            for (Float bonus : getAngelBonuses()) {
                percent += bonus;
            }
            percent += getStrategyBonus();
            damage *= percent;
        }

        // starting overtime ability now
        if (startNow) {
            enemy.setAbilityAffectedBy(new OverTimeAbility(enemy,
                    0, Constants.KNIGHT_SLAM_DURATION,
                    true));
            return 0f;
        }
        // returning damage
        return damage;
    }

    /*
     Affecting overtime a ROGUE as a KNIGHT.
     */
    @Override
    public float affectOvertime(final Rogue enemy,
                                final LocationType location,
                                final boolean startNow,
                                final boolean addRaceModifier) {
        float damage = computeInitialOvertimeDamage(location);

        // adding race modifier
        if (addRaceModifier) {
            float percent = Constants.KNIGHT_SLAM_BONUS_VERSUS_ROGUE;
            for (Float bonus : getAngelBonuses()) {
                percent += bonus;
            }
            percent += getStrategyBonus();
            damage *= percent;
        }

        // starting overtime ability now
        if (startNow) {
            enemy.setAbilityAffectedBy(new OverTimeAbility(enemy,
                    0, Constants.KNIGHT_SLAM_DURATION,
                    true));
            return 0f;
        }
        // returning damage
        return damage;
    }

    /*
     Affecting overtime a KNIGHT as a KNIGHT.
     */
    @Override
    public float affectOvertime(final Knight enemy,
                                final LocationType location,
                                final boolean startNow,
                                final boolean addRaceModifier) {
        float damage = computeInitialOvertimeDamage(location);

        // adding race modifier
        if (addRaceModifier) {
            float percent = Constants.KNIGHT_SLAM_BONUS_VERSUS_KNIGHT;
            for (Float bonus : getAngelBonuses()) {
                percent += bonus;
            }
            percent += getStrategyBonus();
            damage *= percent;
        }

        // starting overtime ability now
        if (startNow) {
            enemy.setAbilityAffectedBy(new OverTimeAbility(enemy,
                    0, Constants.KNIGHT_SLAM_DURATION,
                    true));
            return 0f;
        }
        // returning damage
        return damage;
    }

    /*
     Affecting overtime a PYROMANCER as a KNIGHT.
     */
    @Override
    public float affectOvertime(final Pyromancer enemy,
                                final LocationType location,
                                final boolean startNow,
                                final boolean addRaceModifier) {
        float damage = computeInitialOvertimeDamage(location);

        // adding race modifier
        if (addRaceModifier) {
            float percent = Constants.KNIGHT_SLAM_BONUS_VERSUS_PYROMANCER;
            for (Float bonus : getAngelBonuses()) {
                percent += bonus;
            }
            percent += getStrategyBonus();
            damage *= percent;
        }

        // starting overtime ability now
        if (startNow) {
            enemy.setAbilityAffectedBy(new OverTimeAbility(enemy,
                    0, Constants.KNIGHT_SLAM_DURATION,
                    true));
            return 0f;
        }
        // returning damage
        return damage;
    }

    /*
    Getting influenced by an angel.
     */
    @Override
    public void getHelpedBy(final Angel angel) {
        angel.helpHero(this);
    }

    /*
    Looking for strategy from KNIGHT perspective.
     */
    @Override
    public void lookForStrategy() {
        if (getHealth() > Constants.KNIGHT_ATTACK_STRATEGY_LOW_MARGIN * getMaxHealth()
                && getHealth() < Constants.KNIGHT_ATTACK_STRATEGY_HIGH_MARGIN * getMaxHealth()) {
            setStrategy(new KnightAttackStrategy(this));
        } else if (getHealth() < Constants.KNIGHT_DEFENCE_STRATEGY_HIGH_MARGIN * getMaxHealth()) {
            setStrategy(new KnightDefenceStrategy(this));
        }
    }
}

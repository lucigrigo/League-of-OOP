package main.heroes;

import main.angels.Angel;
import main.data.HeroType;
import main.data.Constants;
import main.data.LocationType;
import main.data.Visitable;
import main.gameplay.OverTimeAbility;
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
                HeroType.KNIGHT, "K", "Knight", index);
    }

    // returning maximum health for KNIGHT
    @Override
    public int getMaxHealth() {
        return Constants.KNIGHT_INITIAL_HEALTH
                + Constants.KNIGHT_HEALTH_RATIO
                * this.getLevel();
    }

    // computing initial EXECUTE damage
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

    // looking to see if execute is possible
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

    // computing initial SLAM damage
    public float computeInitialOvertimeDamage(final LocationType location) {
        float damage = Constants.KNIGHT_SLAM_BASE_DAMAGE
                + Constants.KNIGHT_SLAM_LEVEL_SCALING_BASE_DAMAGE
                * this.getLevel();
        if (location == LocationType.LAND) { // adding location bonus
            damage *= Constants.KNIGHT_LAND_BONUS;
        }
        return damage;
    }

    // getting attacked as KNIGHT
    @Override
    public void getAttackedBy(final Hero enemy,
                                    final LocationType location) {
        enemy.attack(this, location, true, false);
    }

    // attacking a WIZARD as a KNIGHT
    @Override
    public float attack(final Wizard enemy,
                              final LocationType location,
                              final boolean addRaceModifier,
                              final boolean isForDeflectPurpose) {
        // looking for execute potential
        if (checkExecutePossibility(enemy.getInitialRoundHealth(), enemy.getMaxHealth())) {
            if (!isForDeflectPurpose) {
                computeObservation(enemy);
                this.fightWon(enemy.getLevel());
                enemy.hasDied(false);
            }
//            return enemy.getInitialRoundHealth();
        }
        float damage = computeInitialDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= (Constants.KNIGHT_EXECUTE_BONUS_VERSUS_WIZARD
                    + getAngelBonus()
                    + getStrategyBonus());
        }
        damage = Math.round(damage);
        if (!isForDeflectPurpose) { // if not interrogated by a wizard
            damage += Math.round(this.affectOvertime(enemy,
                    location, false, true));
            if (!enemy.isDead()
                    && enemy.takeDamage(Math.round(damage), false, false)) {
//                System.out.println("nu aici?");
                computeObservation(enemy);
                this.fightWon(enemy.getLevel());
            }
//            return 0f;
        }
        damage += Math.round(this.affectOvertime(enemy, location,
                false, false));
        return damage;
    }

    // attacking a ROGUE as a KNIGHT
    @Override
    public void attack(final Rogue enemy,
                             final LocationType location,
                             final boolean addRaceModifier,
                             final boolean isForDeflectPurpose) {
        // looking for execute potential
        if (checkExecutePossibility(enemy.getInitialRoundHealth(), enemy.getMaxHealth())) {
            computeObservation(enemy);
            this.fightWon(enemy.getLevel());
            enemy.hasDied(false);
            return;
        }
        float damage = computeInitialDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= (Constants.KNIGHT_EXECUTE_BONUS_VERSUS_ROGUE
                    + getAngelBonus()
                    + getStrategyBonus());
//            System.out.println(angelBonus);
        }
        damage = Math.round(damage);
        if (!isForDeflectPurpose) { // if not interrogated by a wizard
            damage += Math.round(this.affectOvertime(enemy,
                    location, false, true));
            if (enemy.takeDamage(Math.round(damage), false, false)) {
                computeObservation(enemy);
                this.fightWon(enemy.getLevel());
            }
//            System.out.println(damage);
            return;
        }
        damage += Math.round(this.affectOvertime(enemy, location,
                false, false));
    }

    // attacking a PYROMANCER as a KNIGHT
    @Override
    public void attack(final Pyromancer enemy,
                             final LocationType location,
                             final boolean addRaceModifier,
                             final boolean isForDeflectPurpose) {
        // looking for execute potential
        if (checkExecutePossibility(enemy.getInitialRoundHealth(), enemy.getMaxHealth())) {
            computeObservation(enemy);
            this.fightWon(enemy.getLevel());
            enemy.hasDied(false);
            return;
        }
        float damage = computeInitialDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= (Constants.KNIGHT_EXECUTE_BONUS_VERSUS_PYROMANCER
                    + getAngelBonus()
                    + getStrategyBonus());
        }
        damage = Math.round(damage);
        if (!isForDeflectPurpose) { // if not interrogated by a wizard
            damage += Math.round(this.affectOvertime(enemy,
                    location, false, true));
            if (enemy.takeDamage(Math.round(damage), false, false)) {
                computeObservation(enemy);
                this.fightWon(enemy.getLevel());
            }
//            System.out.println(damage);
            return;
        }
        damage += Math.round(this.affectOvertime(enemy, location,
                false, false));
    }

    // attacking a KNIGHT as a KNIGHT
    @Override
    public void attack(final Knight enemy,
                             final LocationType location,
                             final boolean addRaceModifier,
                             final boolean isForDeflectPurpose) {
        // looking for execute potential
        if (checkExecutePossibility(enemy.getInitialRoundHealth(), enemy.getMaxHealth())) {
            computeObservation(enemy);
            this.fightWon(enemy.getLevel());
            enemy.hasDied(false);
            return;
        }
        float damage = computeInitialDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= Constants.KNIGHT_EXECUTE_BONUS_VERSUS_KNIGHT;
        }
        damage = Math.round(damage);
        if (!isForDeflectPurpose) { // if not interrogated by a wizard
            damage += Math.round(this.affectOvertime(enemy,
                    location, false, true));
            if (enemy.takeDamage(Math.round(damage), false, false)) {
                this.fightWon(enemy.getLevel());
                computeObservation(enemy);
            }
//            System.out.println(damage);
            return;
        }
        damage += Math.round(this.affectOvertime(enemy, location,
                false, false));
    }

    // getting affected overtime as a KNIGHT
    @Override
    public void getAffectedBy(final Hero enemy,
                                    final LocationType location) {
        enemy.affectOvertime(this, location, true, true);
    }

    // affecting overtime a WIZARD as a KNIGHT
    @Override
    public float affectOvertime(final Wizard enemy,
                                      final LocationType location,
                                      final boolean startNow,
                                      final boolean addRaceModifier) {
        float damage = computeInitialOvertimeDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= (Constants.KNIGHT_SLAM_BONUS_VERSUS_WIZARD
                    + getAngelBonus()
                    + getStrategyBonus());
        }
        if (startNow) { // starting overtime ability now
            enemy.setAbilityAffectedBy(new OverTimeAbility(this, enemy,
                    "Slam", location, 0, Constants.KNIGHT_SLAM_DURATION,
                    true));
            return 0f;
        }
        return damage; // returning damage
    }

    // affecting overtime a ROGUE as a KNIGHT
    @Override
    public float affectOvertime(final Rogue enemy,
                                      final LocationType location,
                                      final boolean startNow,
                                      final boolean addRaceModifier) {
        float damage = computeInitialOvertimeDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= (Constants.KNIGHT_SLAM_BONUS_VERSUS_ROGUE
                    + getAngelBonus()
                    + getStrategyBonus());
        }
        if (startNow) { // starting overtime ability now
            enemy.setAbilityAffectedBy(new OverTimeAbility(this, enemy,
                    "Slam", location, 0, Constants.KNIGHT_SLAM_DURATION,
                    true));
            return 0f;
        }
        return damage; // returning damage
    }

    // affecting overtime a KNIGHT as a KNIGHT
    @Override
    public float affectOvertime(final Knight enemy,
                                      final LocationType location,
                                      final boolean startNow,
                                      final boolean addRaceModifier) {
        float damage = computeInitialOvertimeDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= (Constants.KNIGHT_SLAM_BONUS_VERSUS_KNIGHT
                    + getAngelBonus()
                    + getStrategyBonus());
        }
        if (startNow) { // starting overtime ability now
            enemy.setAbilityAffectedBy(new OverTimeAbility(this, enemy,
                    "Slam", location, 0, Constants.KNIGHT_SLAM_DURATION,
                    true));
            return 0f;
        }
        return damage; // returning damage
    }

    // affecting overtime a PYROMANCER as a KNIGHT
    @Override
    public float affectOvertime(final Pyromancer enemy,
                                      final LocationType location,
                                      final boolean startNow,
                                      final boolean addRaceModifier) {
        float damage = computeInitialOvertimeDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= (Constants.KNIGHT_SLAM_BONUS_VERSUS_PYROMANCER
                    + getAngelBonus()
                    + getStrategyBonus());
        }
        if (startNow) { // starting overtime ability now
            enemy.setAbilityAffectedBy(new OverTimeAbility(this, enemy,
                    "Slam", location, 0, Constants.KNIGHT_SLAM_DURATION,
                    true));
            return 0f;
        }
        return damage; // returning damage
    }

    @Override
    public void getHelpedBy(final Angel angel) {
        angel.helpHero(this);
    }

    @Override
    public void lookForStrategy() {
        if (getHealth() > Constants.KNIGHT_ATTACK_STRATEGY_LOW_MARGIN * getMaxHealth()
                && getHealth() < Constants.KNIGHT_ATTACK_STRATEGY_HIGH_MARGIN * getMaxHealth()) {
            setStrategy(new KnightAttackStrategy(this));
//            System.out.println("attack");
        } else if (getHealth() < Constants.KNIGHT_DEFENCE_STRATEGY_HIGH_MARGIN * getMaxHealth()) {
            setStrategy(new KnightDefenceStrategy(this));
//            System.out.println("defence");
        }
//        if (strategy != null) {
//            strategy.applyStrategy();
//        }
    }
}

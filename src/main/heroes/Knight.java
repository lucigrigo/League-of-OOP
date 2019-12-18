package main.heroes;

import main.angels.Angel;
import main.data.HeroType;
import main.data.Constants;
import main.data.LocationType;
import main.data.Visitable;
import main.gameplay.OverTimeAbility;

/**
 * Class to implement Knight logic.
 */
public class Knight extends Hero implements Visitable {

    public Knight(final int initCol,
                  final int initLin,
                  final int index) {
        super(initCol, initLin, Constants.KNIGHT_INITIAL_HEALTH, 0,
                HeroType.KNIGHT, "K", "Knight", index);
    }

    // returning maximum health for KNIGHT
    @Override
    public final int getMaxHealth() {
        return Constants.KNIGHT_INITIAL_HEALTH
                + Constants.KNIGHT_HEALTH_RATIO
                * this.getLevel();
    }

    // computing initial EXECUTE damage
    @Override
    public final float computeInitialDamage(final LocationType location) {
        float damage = Constants.KNIGHT_EXECUTE_BASE_DAMAGE
                + Constants.KNIGHT_EXECUTE_LEVEL_SCALING_BASE_DAMAGE
                * this.getLevel();
        if (location == LocationType.LAND) { // adding location bonus
            damage *= Constants.KNIGHT_LAND_BONUS;
        }
        return damage;
    }

    // looking to see if execute is possible
    private boolean checkExecutePossibility(final int enemyCurrentHealth,
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
    public final float computeInitialOvertimeDamage(final LocationType location) {
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
    public final void getAttackedBy(final Hero enemy,
                                    final LocationType location) {
        enemy.attack(this, location, true, false);
    }

    // attacking a WIZARD as a KNIGHT
    @Override
    public final float attack(final Wizard enemy,
                              final LocationType location,
                              final boolean addRaceModifier,
                              final boolean isForDeflectPurpose) {
        // looking for execute potential
        if (checkExecutePossibility(enemy.getInitialRoundHealth(), enemy.getMaxHealth())) {
            if (!isForDeflectPurpose) {
                this.fightWon(enemy.getLevel());
                enemy.hasDied(false);
            }
            return enemy.getHealth();
        }
        float damage = computeInitialDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= (Constants.KNIGHT_EXECUTE_BONUS_VERSUS_WIZARD
                    + angelBonus);
        }
        damage = Math.round(damage);
        if (!isForDeflectPurpose) { // if not interrogated by a wizard
            damage += Math.round(this.affectOvertime(enemy,
                    location, false, true));
            if (enemy.takeDamage(Math.round(damage), false, false)) {
                this.fightWon(enemy.getLevel());
                computeObservation(enemy);
            }
            return 0f;
        }
        damage += Math.round(this.affectOvertime(enemy, location,
                false, false));
        return damage;
    }

    // attacking a ROGUE as a KNIGHT
    @Override
    public final void attack(final Rogue enemy,
                             final LocationType location,
                             final boolean addRaceModifier,
                             final boolean isForDeflectPurpose) {
        // looking for execute potential
        if (checkExecutePossibility(enemy.getInitialRoundHealth(), enemy.getMaxHealth())) {
            this.fightWon(enemy.getLevel());
            enemy.hasDied(false);
            return;
        }
        float damage = computeInitialDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= (Constants.KNIGHT_EXECUTE_BONUS_VERSUS_ROGUE
                    + angelBonus);
        }
        damage = Math.round(damage);
        if (!isForDeflectPurpose) { // if not interrogated by a wizard
            damage += Math.round(this.affectOvertime(enemy,
                    location, false, true));
            if (enemy.takeDamage(Math.round(damage), false, false)) {
                this.fightWon(enemy.getLevel());
                computeObservation(enemy);
            }
            return;
        }
        damage += Math.round(this.affectOvertime(enemy, location,
                false, false));
    }

    // attacking a PYROMANCER as a KNIGHT
    @Override
    public final void attack(final Pyromancer enemy,
                             final LocationType location,
                             final boolean addRaceModifier,
                             final boolean isForDeflectPurpose) {
        // looking for execute potential
        if (checkExecutePossibility(enemy.getInitialRoundHealth(), enemy.getMaxHealth())) {
            this.fightWon(enemy.getLevel());
            enemy.hasDied(false);
            return;
        }
        float damage = computeInitialDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= (Constants.KNIGHT_EXECUTE_BONUS_VERSUS_PYROMANCER
                    + angelBonus);
        }
        damage = Math.round(damage);
        if (!isForDeflectPurpose) { // if not interrogated by a wizard
            damage += Math.round(this.affectOvertime(enemy,
                    location, false, true));
            if (enemy.takeDamage(Math.round(damage), false, false)) {
                this.fightWon(enemy.getLevel());
                computeObservation(enemy);
            }
            return;
        }
        damage += Math.round(this.affectOvertime(enemy, location,
                false, false));
    }

    // attacking a KNIGHT as a KNIGHT
    @Override
    public final void attack(final Knight enemy,
                             final LocationType location,
                             final boolean addRaceModifier,
                             final boolean isForDeflectPurpose) {
        // looking for execute potential
        if (checkExecutePossibility(enemy.getInitialRoundHealth(), enemy.getMaxHealth())) {
            this.fightWon(enemy.getLevel());
            enemy.hasDied(false);
            return;
        }
        float damage = computeInitialDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= (Constants.KNIGHT_EXECUTE_BONUS_VERSUS_KNIGHT
                    + angelBonus);
        }
        damage = Math.round(damage);
        if (!isForDeflectPurpose) { // if not interrogated by a wizard
            damage += Math.round(this.affectOvertime(enemy,
                    location, false, true));
            if (enemy.takeDamage(Math.round(damage), false, false)) {
                this.fightWon(enemy.getLevel());
                computeObservation(enemy);
            }
            return;
        }
        damage += Math.round(this.affectOvertime(enemy, location,
                false, false));
    }

    // getting affected overtime as a KNIGHT
    @Override
    public final void getAffectedBy(final Hero enemy,
                                    final LocationType location) {
        enemy.affectOvertime(this, location, true, true);
    }

    // affecting overtime a WIZARD as a KNIGHT
    @Override
    public final float affectOvertime(final Wizard enemy,
                                      final LocationType location,
                                      final boolean startNow,
                                      final boolean addRaceModifier) {
        float damage = computeInitialOvertimeDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= (Constants.KNIGHT_SLAM_BONUS_VERSUS_WIZARD
                    + angelBonus);
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
    public final float affectOvertime(final Rogue enemy,
                                      final LocationType location,
                                      final boolean startNow,
                                      final boolean addRaceModifier) {
        float damage = computeInitialOvertimeDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= (Constants.KNIGHT_SLAM_BONUS_VERSUS_ROGUE
                    + angelBonus);
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
    public final float affectOvertime(final Knight enemy,
                                      final LocationType location,
                                      final boolean startNow,
                                      final boolean addRaceModifier) {
        float damage = computeInitialOvertimeDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= (Constants.KNIGHT_SLAM_BONUS_VERSUS_KNIGHT
                    + angelBonus);
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
    public final float affectOvertime(final Pyromancer enemy,
                                      final LocationType location,
                                      final boolean startNow,
                                      final boolean addRaceModifier) {
        float damage = computeInitialOvertimeDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= (Constants.KNIGHT_SLAM_BONUS_VERSUS_PYROMANCER
                    + angelBonus);
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
    public final void getHelpedBy(final Angel angel) {
        angel.helpHero(this);
    }
}

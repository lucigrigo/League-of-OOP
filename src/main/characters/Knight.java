package main.characters;

import main.data.CharacterType;
import main.data.Constants;
import main.data.LocationType;
import main.gameplay.OverTimeAbility;

/**
 * Class to implement Knight logic.
 */
public class Knight extends GameCharacter {

    public Knight(final int initCol,
                  final int initLin) {
        super(initCol, initLin, Constants.KNIGHT_INITIAL_HEALTH, 0,
                CharacterType.KNIGHT, "K");
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
    public final void getAttackedBy(final GameCharacter enemy,
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
                enemy.hasDied();
            }
            return enemy.getHealth();
        }
        float damage = computeInitialDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= Constants.KNIGHT_EXECUTE_BONUS_VERSUS_WIZARD;
        }
        damage = Math.round(damage);
        if (!isForDeflectPurpose) { // if not interrogated by a wizard
            damage += Math.round(this.affectOvertime(enemy,
                    location, false, true));
            if (enemy.takeDamage(Math.round(damage), false)) {
                this.fightWon(enemy.getLevel());
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
            enemy.hasDied();
            return;
        }
        float damage = computeInitialDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= Constants.KNIGHT_EXECUTE_BONUS_VERSUS_ROGUE;
        }
        damage = Math.round(damage);
        if (!isForDeflectPurpose) { // if not interrogated by a wizard
            damage += Math.round(this.affectOvertime(enemy,
                    location, false, true));
            if (enemy.takeDamage(Math.round(damage), false)) {
                this.fightWon(enemy.getLevel());
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
            enemy.hasDied();
            return;
        }
        float damage = computeInitialDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= Constants.KNIGHT_EXECUTE_BONUS_VERSUS_PYROMANCER;
        }
        damage = Math.round(damage);
        if (!isForDeflectPurpose) { // if not interrogated by a wizard
            damage += Math.round(this.affectOvertime(enemy,
                    location, false, true));
            if (enemy.takeDamage(Math.round(damage), false)) {
                this.fightWon(enemy.getLevel());
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
            enemy.hasDied();
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
            if (enemy.takeDamage(Math.round(damage), false)) {
                this.fightWon(enemy.getLevel());
            }
            return;
        }
        damage += Math.round(this.affectOvertime(enemy, location,
                false, false));
    }

    // getting affected overtime as a KNIGHT
    @Override
    public final void getAffectedBy(final GameCharacter enemy,
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
            damage *= Constants.KNIGHT_SLAM_BONUS_VERSUS_WIZARD;
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
            damage *= Constants.KNIGHT_SLAM_BONUS_VERSUS_ROGUE;
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
            damage *= Constants.KNIGHT_SLAM_BONUS_VERSUS_KNIGHT;
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
            damage *= Constants.KNIGHT_SLAM_BONUS_VERSUS_PYROMANCER;
        }
        if (startNow) { // starting overtime ability now
            enemy.setAbilityAffectedBy(new OverTimeAbility(this, enemy,
                    "Slam", location, 0, Constants.KNIGHT_SLAM_DURATION,
                    true));
            return 0f;
        }
        return damage; // returning damage
    }
}

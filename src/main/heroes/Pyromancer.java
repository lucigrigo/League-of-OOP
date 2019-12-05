package main.heroes;

import main.angels.Angel;
import main.data.HeroType;
import main.data.Constants;
import main.data.LocationType;
import main.data.Visitable;
import main.gameplay.OverTimeAbility;

/**
 * Class to implement PYROMANCER logic.
 */
public class Pyromancer extends Hero implements Visitable {

    public Pyromancer(final int initCol,
                      final int initLin,
                      final int index) {
        super(initCol, initLin, Constants.PYROMANCER_INITIAL_HEALTH, 0,
                HeroType.PYROMANCER, "P", index);
    }

    // returning maximum health for a PYROMANCER
    @Override
    public final int getMaxHealth() {
        return Constants.PYROMANCER_INITIAL_HEALTH
                + Constants.PYROMANCER_HEALTH_RATIO
                * this.getLevel();
    }

    // computing initial damage as a PYROMANCER
    @Override
    public final float computeInitialDamage(final LocationType location) {
        float damage = Constants.PYROMANCER_FIREBLAST_BASE_DAMAGE
                + Constants.PYROMANCER_FIREBLAST_LEVEL_SCALING_BASE_DAMAGE
                * this.getLevel();
        if (location == LocationType.VOLCANIC) { // adding location bonus
            damage *= Constants.PYROMANCER_VOLCANIC_BONUS;
        }
        return damage;
    }

    // computing initial overtime damage as a PYROMANCER
    @Override
    public final float computeInitialOvertimeDamage(final LocationType location) {
        float damage = Constants.PYROMANCER_IGNITE_BASE_DAMAGE
                + Constants.PYROMANCER_IGNITE_LEVEL_SCALING_BASE_DAMAGE
                * this.getLevel();
        if (location == LocationType.VOLCANIC) { // adding location bonus
            damage *= Constants.PYROMANCER_VOLCANIC_BONUS;
        }
        return damage;
    }

    // getting attacked as a PYROMANCER
    @Override
    public final void getAttackedBy(final Hero enemy,
                                    final LocationType location) {
        enemy.attack(this, location, true, false);
    }

    // attacking a WIZARD as a PYROMANCER
    @Override
    public final float attack(final Wizard enemy,
                              final LocationType location,
                              final boolean addRaceModifier,
                              final boolean isForDeflectPurpose) {
        float damage = computeInitialDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= Constants.PYROMANCER_FIREBLAST_BONUS_VERSUS_WIZARD;
        }
        damage = Math.round(damage);
        if (!isForDeflectPurpose) { // if not interrogated by a WIZARD
            damage += Math.round(this.affectOvertime(enemy, location, false, true));
            if (enemy.takeDamage(Math.round(damage), false)) {
                this.fightWon(enemy.getLevel());
            }
            return 0f;
        }
        damage += Math.round(this.affectOvertime(enemy, location,
                false, false));
        return damage;
    }

    // attacking a ROGUE as a PYROMANCER
    @Override
    public final void attack(final Rogue enemy,
                             final LocationType location,
                             final boolean addRaceModifier,
                             final boolean isForDeflectPurpose) {
        float damage = computeInitialDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= Constants.PYROMANCER_FIREBLAST_BONUS_VERSUS_ROGUE;
        }
        damage = Math.round(damage);
        if (!isForDeflectPurpose) { // if not interrogated by a WIZARD
            damage += Math.round(this.affectOvertime(enemy, location, false, true));
            if (enemy.takeDamage(Math.round(damage), false)) {
                this.fightWon(enemy.getLevel());
            }
            return;
        }
        damage += Math.round(this.affectOvertime(enemy, location,
                false, false));
    }

    // attacking a PYROMANCER as a PYROMANCER
    @Override
    public final void attack(final Pyromancer enemy,
                             final LocationType location,
                             final boolean addRaceModifier,
                             final boolean isForDeflectPurpose) {
        float damage = computeInitialDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= Constants.PYROMANCER_FIREBLAST_BONUS_VERSUS_PYROMANCER;
        }
        damage = Math.round(damage);
        if (!isForDeflectPurpose) { // if not interrogated by a WIZARD
            damage += Math.round(this.affectOvertime(enemy, location, false, true));
            if (enemy.takeDamage(Math.round(damage), false)) {
                this.fightWon(enemy.getLevel());
            }
            return;
        }
        damage += Math.round(this.affectOvertime(enemy, location,
                false, false));
    }

    // attacking a KNIGHT as a PYROMANCER
    @Override
    public final void attack(final Knight enemy,
                             final LocationType location,
                             final boolean addRaceModifier,
                             final boolean isForDeflectPurpose) {

        float damage = computeInitialDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= Constants.PYROMANCER_FIREBLAST_BONUS_VERSUS_KNIGHT;
        }
        damage = Math.round(damage);
        if (!isForDeflectPurpose) { // if not interrogated by a WIZARD
            damage += Math.round(this.affectOvertime(enemy, location, false, true));
            if (enemy.takeDamage(Math.round(damage), false)) {
                this.fightWon(enemy.getLevel());
            }
            return;
        }
        damage += Math.round(this.affectOvertime(enemy, location,
                false, false));
    }

    // getting affected overtime as a PYROMANCER
    @Override
    public final void getAffectedBy(final Hero enemy,
                                    final LocationType location) {
        enemy.affectOvertime(this, location, true, true);
    }

    // affecting overtime a WIZARD as a PYROMANCER
    @Override
    public final float affectOvertime(final Wizard enemy,
                                      final LocationType location,
                                      final boolean startNow,
                                      final boolean addRaceModifier) {
        float damage;
        if (startNow) { // initial damage
            damage = Constants.PYROMANCER_IGNITE_ROUND_DAMAGE
                    + Constants.PYROMANCER_IGNITE_LEVEL_SCALING_ROUND_DAMAGE
                    * this.getLevel();
            if (location == LocationType.VOLCANIC) {
                damage *= Constants.PYROMANCER_VOLCANIC_BONUS;
            }
        } else { // subsequent damage
            damage = computeInitialOvertimeDamage(location);
        }
        if (addRaceModifier) { // adding race modifier
            damage *= Constants.PYROMANCER_IGNITE_BONUS_VERSUS_WIZARD;
        }
        if (startNow) { // starting the ability now
            enemy.setAbilityAffectedBy(new OverTimeAbility(this, enemy, "Ignite",
                    location, Math.round(damage), Constants.PYROMANCER_IGNITE_DURATION));
            return 0f;
        }
        return damage;
    }

    // affecting overtime a ROGUE as a PYROMANCER
    @Override
    public final float affectOvertime(final Rogue enemy,
                                      final LocationType location,
                                      final boolean startNow,
                                      final boolean addRaceModifier) {
        float damage;
        if (startNow) { // initial damage
            damage = Constants.PYROMANCER_IGNITE_ROUND_DAMAGE
                    + Constants.PYROMANCER_IGNITE_LEVEL_SCALING_ROUND_DAMAGE
                    * this.getLevel();
            if (location == LocationType.VOLCANIC) {
                damage *= Constants.PYROMANCER_VOLCANIC_BONUS;
            }
        } else { // subsequent damage
            damage = computeInitialOvertimeDamage(location);
        }
        if (addRaceModifier) { // adding race modifier
            damage *= Constants.PYROMANCER_IGNITE_BONUS_VERSUS_ROGUE;
        }
        if (startNow) { // starting the ability now
            enemy.setAbilityAffectedBy(new OverTimeAbility(this, enemy, "Ignite",
                    location, Math.round(damage), Constants.PYROMANCER_IGNITE_DURATION));
            return 0f;
        }
        return damage;
    }

    // affecting overtime a KNIGHT as a PYROMANCER
    @Override
    public final float affectOvertime(final Knight enemy,
                                      final LocationType location,
                                      final boolean startNow,
                                      final boolean addRaceModifier) {
        float damage;
        if (startNow) { // initial damage
            damage = Constants.PYROMANCER_IGNITE_ROUND_DAMAGE
                    + Constants.PYROMANCER_IGNITE_LEVEL_SCALING_ROUND_DAMAGE
                    * this.getLevel();
            if (location == LocationType.VOLCANIC) {
                damage *= Constants.PYROMANCER_VOLCANIC_BONUS;
            }
        } else { // subsequent damage
            damage = computeInitialOvertimeDamage(location);
        }
        if (addRaceModifier) { // adding race modifier
            damage *= Constants.PYROMANCER_IGNITE_BONUS_VERSUS_KNIGHT;
        }
        if (startNow) { // starting the ability now
            enemy.setAbilityAffectedBy(new OverTimeAbility(this, enemy, "Ignite",
                    location, Math.round(damage), Constants.PYROMANCER_IGNITE_DURATION));
            return 0f;
        }
        return damage;
    }

    // affecting overtime a PYROMANCER as a PYROMANCER
    @Override
    public final float affectOvertime(final Pyromancer enemy,
                                      final LocationType location,
                                      final boolean startNow,
                                      final boolean addRaceModifier) {
        float damage;
        if (startNow) { // initial damage
            damage = Constants.PYROMANCER_IGNITE_ROUND_DAMAGE
                    + Constants.PYROMANCER_IGNITE_LEVEL_SCALING_ROUND_DAMAGE
                    * this.getLevel();
            if (location == LocationType.VOLCANIC) {
                damage *= Constants.PYROMANCER_VOLCANIC_BONUS;
            }
        } else { // subsequent damage
            damage = computeInitialOvertimeDamage(location);
        }
        if (addRaceModifier) { // adding race modifier
            damage *= Constants.PYROMANCER_IGNITE_BONUS_VERSUS_PYROMANCER;
        }
        if (startNow) { // starting the ability now
            enemy.setAbilityAffectedBy(new OverTimeAbility(this, enemy, "Ignite",
                    location, Math.round(damage), Constants.PYROMANCER_IGNITE_DURATION));
            return 0f;
        }
        return damage;
    }

    @Override
    public final void getHelpedBy(final Angel angel) {
        angel.helpHero(this);
    }
}

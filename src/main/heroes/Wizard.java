package main.heroes;

import main.angels.Angel;
import main.data.HeroType;
import main.data.Constants;
import main.data.LocationType;
import main.data.Visitable;

/**
 * Class to implement WIZARD logic.
 */
public class Wizard extends Hero implements Visitable {

    public Wizard(final int initCol,
                  final int initLin,
                  final int index) {
        super(initCol, initLin, Constants.WIZARD_INITIAL_HEALTH, 0,
                HeroType.WIZARD, "W", "Wizard", index);
    }

    // returning maximum health as a WIZARD
    @Override
    public final int getMaxHealth() {
        return Constants.WIZARD_INITIAL_HEALTH
                + Constants.WIZARD_HEALTH_RATIO
                * this.getLevel();
    }

    // computing initial DRAIN damage
    @Override
    public final float computeInitialDamage(final LocationType location) {
        float percent = Constants.WIZARD_DRAIN_BASE_PERCENTAGE
                + Constants.WIZARD_DRAIN_LEVEL_SCALING_PERCENTAGE
                * this.getLevel(); // initial percentage
        if (location == LocationType.DESERT) { // adding location bonus
            percent *= Constants.WIZARD_DESERT_BONUS;
        }
        return percent;
    }

    // computing initial DEFLECT damage
    @Override
    public final float computeInitialOvertimeDamage(final LocationType location) {
        float percent = Constants.WIZARD_DEFLECT_BASE_PERCENTAGE
                + Constants.WIZARD_DEFLECT_LEVEL_SCALING_BASE_PERCENTAGE
                * this.getLevel(); // initial percentage
        percent = Math.min(Constants.WIZARD_DEFLECT_MAXIMUM_PERCENTAGE, percent);
        if (location == LocationType.DESERT) { // adding location bonus
            percent *= Constants.WIZARD_DESERT_BONUS;
        }
        return percent;
    }

    // getting attacked as a WIZARD
    @Override
    public final void getAttackedBy(final Hero enemy,
                                    final LocationType location) {
        enemy.attack(this, location, true, false);
    }

    // attacking a WIZARD as a WIZARD
    @Override
    public final float attack(final Wizard enemy,
                              final LocationType location,
                              final boolean addRaceModifier,
                              final boolean isForDeflectPurpose) {
        float percent = computeInitialDamage(location);
        if (addRaceModifier) { // adding race modifier
            percent *= (Constants.WIZARD_DRAIN_BONUS_VERSUS_WIZARD
                    + angelBonus);
        }
        float damage = percent
                * Math.min(Constants.WIZARD_DRAIN_HEALTH_PERCENTAGE
                * enemy.getMaxHealth(), enemy.getHealth());
        if (enemy.takeDamage(Math.round(damage), false, false)) {
            this.fightWon(enemy.getLevel());
            computeObservation(enemy);
        }
        return 0f;
    }

    // attacking a ROGUE as a WIZARD
    @Override
    public final void attack(final Rogue enemy,
                             final LocationType location,
                             final boolean addRaceModifier,
                             final boolean isForDeflectPurpose) {
        float percent = computeInitialDamage(location);
        if (addRaceModifier) { // adding race modifier
            percent *= (Constants.WIZARD_DRAIN_BONUS_VERSUS_ROGUE
                    + angelBonus);
        }
        float damage = percent
                * Math.min(Constants.WIZARD_DRAIN_HEALTH_PERCENTAGE
                * enemy.getMaxHealth(), enemy.getHealth());
        damage = Math.round(damage);
        damage += Math.round(affectOvertime(enemy, location, false, true));
        if (enemy.takeDamage(Math.round(damage), false, false)) {
            this.fightWon(enemy.getLevel());
            computeObservation(enemy);
        }
    }

    // attacking a PYROMANCER as a WIZARD
    @Override
    public final void attack(final Pyromancer enemy,
                             final LocationType location,
                             final boolean addRaceModifier,
                             final boolean isForDeflectPurpose) {
        float percent = computeInitialDamage(location);
        if (addRaceModifier) { // adding race modifier
            percent *= (Constants.WIZARD_DRAIN_BONUS_VERSUS_PYROMANCER
                    + angelBonus);
        }
        float damage = percent
                * Math.min(Constants.WIZARD_DRAIN_HEALTH_PERCENTAGE
                * enemy.getMaxHealth(), enemy.getHealth());
        damage = Math.round(damage);
        damage += Math.round(affectOvertime(enemy, location, false, true));
        if (enemy.takeDamage(Math.round(damage), false, false)) {
            this.fightWon(enemy.getLevel());
            computeObservation(enemy);
        }
    }

    // attacking a KNIGHT as a WIZARD
    @Override
    public final void attack(final Knight enemy,
                             final LocationType location,
                             final boolean addRaceModifier,
                             final boolean isForDeflectPurpose) {
        float percent = computeInitialDamage(location);
        if (addRaceModifier) { // adding race modifier
            percent *= (Constants.WIZARD_DRAIN_BONUS_VERSUS_KNIGHT
                    + angelBonus);
        }
        float damage = percent
                * Math.min(Constants.WIZARD_DRAIN_HEALTH_PERCENTAGE
                * enemy.getMaxHealth(), enemy.getHealth());
        damage = Math.round(damage);
        damage += Math.round(affectOvertime(enemy, location, false, true));
        if (enemy.takeDamage(Math.round(damage), false, false)) {
            this.fightWon(enemy.getLevel());
            computeObservation(enemy);
        }
    }

    @Override
    public final void getAffectedBy(final Hero enemy,
                                    final LocationType location) {
        enemy.affectOvertime(this, location, true, true);
    }

    // WIZARD does not have a deflect ability against WIZARD
    @Override
    public final float affectOvertime(final Wizard enemy,
                                      final LocationType location,
                                      final boolean startNow,
                                      final boolean addRaceModifier) {
        return 0f;
    }

    // computing DEFLECT damage versus a ROGUE
    @Override
    public final float affectOvertime(final Rogue enemy,
                                      final LocationType location,
                                      final boolean startNow,
                                      final boolean addRaceModifier) {
        float enemyDamage = enemy.attack(this, location, false, true);
        float percent = computeInitialOvertimeDamage(location);
        enemyDamage *= percent;
        if (addRaceModifier) { // adding race modifier
            enemyDamage *= (Constants.WIZARD_DEFLECT_BONUS_VERSUS_ROGUE
                    + angelBonus);
        }
        return enemyDamage;
    }

    // computing DEFLECT damage versus a KNIGHT
    @Override
    public final float affectOvertime(final Knight enemy,
                                      final LocationType location,
                                      final boolean startNow,
                                      final boolean addRaceModifier) {
        float enemyDamage = enemy.attack(this, location, false, true);
        float percent = computeInitialOvertimeDamage(location);
        enemyDamage *= percent;
        if (addRaceModifier) { // adding race modifier
            enemyDamage *= (Constants.WIZARD_DEFLECT_BONUS_VERSUS_KNIGHT
                    + angelBonus);
        }
        return enemyDamage;
    }

    // computing DEFLECT damage versus a PYROMANCER
    @Override
    public final float affectOvertime(final Pyromancer enemy,
                                      final LocationType location,
                                      final boolean startNow,
                                      final boolean addRaceModifier) {
        float enemyDamage = enemy.attack(this, location, false, true);
        float percent = computeInitialOvertimeDamage(location);
        enemyDamage *= percent;
        if (addRaceModifier) { // adding race modifier
            enemyDamage *= (Constants.WIZARD_DEFLECT_BONUS_VERSUS_PYROMANCER
                    + angelBonus);
        }
        return enemyDamage;
    }

    @Override
    public final void getHelpedBy(final Angel angel) {
        angel.helpHero(this);
    }

    @Override
    public void lookForStrategy() {

    }
}

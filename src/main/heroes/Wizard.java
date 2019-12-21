package main.heroes;

import main.angels.Angel;
import main.data.HeroType;
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
                HeroType.WIZARD, "W", "Wizard", index);
    }

    // returning maximum health as a WIZARD
    @Override
    public int getMaxHealth() {
        return Constants.WIZARD_INITIAL_HEALTH
                + Constants.WIZARD_HEALTH_RATIO
                * this.getLevel();
    }

    // computing initial DRAIN damage
    @Override
    public float computeInitialDamage(final LocationType location) {
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
    public float computeInitialOvertimeDamage(final LocationType location) {
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
    public void getAttackedBy(final Hero enemy,
                                    final LocationType location) {
        enemy.attack(this, location, true, false);
    }

    // attacking a WIZARD as a WIZARD
    @Override
    public float attack(final Wizard enemy,
                              final LocationType location,
                              final boolean addRaceModifier,
                              final boolean isForDeflectPurpose) {
        float percent = computeInitialDamage(location);
        if (addRaceModifier) { // adding race modifier
            percent *= (Constants.WIZARD_DRAIN_BONUS_VERSUS_WIZARD
                    + getAngelBonus()
                    + getStrategyBonus());
        }
        float damage = percent
                * Math.min(Constants.WIZARD_DRAIN_HEALTH_PERCENTAGE
                * enemy.getMaxHealth(), enemy.getHealth());
        if (enemy.takeDamage(Math.round(damage), false, false)) {
            computeObservation(enemy);
            this.fightWon(enemy.getLevel());
        }
        return 0f;
    }

    // attacking a ROGUE as a WIZARD
    @Override
    public void attack(final Rogue enemy,
                             final LocationType location,
                             final boolean addRaceModifier,
                             final boolean isForDeflectPurpose) {
        float percent = computeInitialDamage(location);
        if (addRaceModifier) { // adding race modifier
            percent *= (Constants.WIZARD_DRAIN_BONUS_VERSUS_ROGUE
                    + getAngelBonus()
                    + getStrategyBonus());
        }
        float damage = percent
                * Math.min(Constants.WIZARD_DRAIN_HEALTH_PERCENTAGE
                * enemy.getMaxHealth(), enemy.getHealth());
        damage = Math.round(damage);
//        System.out.println(damage);

        damage += Math.round(affectOvertime(enemy, location, false, true));
//        System.out.println(damage);

        if (enemy.takeDamage(Math.round(damage), false, false)) {
            computeObservation(enemy);
            this.fightWon(enemy.getLevel());
        }
    }

    // attacking a PYROMANCER as a WIZARD
    @Override
    public void attack(final Pyromancer enemy,
                             final LocationType location,
                             final boolean addRaceModifier,
                             final boolean isForDeflectPurpose) {
        float percent = computeInitialDamage(location);
        if (addRaceModifier) { // adding race modifier
            percent *= (Constants.WIZARD_DRAIN_BONUS_VERSUS_PYROMANCER
                    + getAngelBonus()
                    + getStrategyBonus());
        }
//        System.out.println(percent);
        float damage = percent
                * Math.min(Constants.WIZARD_DRAIN_HEALTH_PERCENTAGE
                * enemy.getMaxHealth(), enemy.getHealth());
        damage = Math.round(damage);
        damage += Math.round(affectOvertime(enemy, location, false, true));
        if (enemy.takeDamage(Math.round(damage), false, false)) {
            computeObservation(enemy);
            this.fightWon(enemy.getLevel());
        }
//        System.out.println(damage);
    }


    // attacking a KNIGHT as a WIZARD
    @Override
    public void attack(final Knight enemy,
                             final LocationType location,
                             final boolean addRaceModifier,
                             final boolean isForDeflectPurpose) {
        float percent = computeInitialDamage(location);
        if (addRaceModifier) { // adding race modifier
            percent *= (Constants.WIZARD_DRAIN_BONUS_VERSUS_KNIGHT
                    + getAngelBonus()
                    + getStrategyBonus());
        }
        float damage = percent
                * Math.min(Constants.WIZARD_DRAIN_HEALTH_PERCENTAGE
                * enemy.getMaxHealth(), enemy.getHealth());
        damage = Math.round(damage);
        damage += Math.round(affectOvertime(enemy, location, false, true));
        if (enemy.takeDamage(Math.round(damage), false, false)) {
            computeObservation(enemy);
            this.fightWon(enemy.getLevel());
        }
//        System.out.println(damage + " de la wizard");
    }

    @Override
    public void getAffectedBy(final Hero enemy,
                                    final LocationType location) {
        enemy.affectOvertime(this, location, true, true);
    }

    // WIZARD does not have a deflect ability against WIZARD
    @Override
    public float affectOvertime(final Wizard enemy,
                                      final LocationType location,
                                      final boolean startNow,
                                      final boolean addRaceModifier) {
        return 0f;
    }

    // computing DEFLECT damage versus a ROGUE
    @Override
    public float affectOvertime(final Rogue enemy,
                                      final LocationType location,
                                      final boolean startNow,
                                      final boolean addRaceModifier) {
        float enemyDamage = enemy.attack(this, location, false, true);
//        System.out.println(enemyDamage);
        float percent = computeInitialOvertimeDamage(location);
//        System.out.println(percent);
        enemyDamage *= percent;
//        System.out.println(enemyDamage);
        if (addRaceModifier) { // adding race modifier
            enemyDamage *= (Constants.WIZARD_DEFLECT_BONUS_VERSUS_ROGUE
                    + getAngelBonus()
                    + getStrategyBonus());
//            System.out.println(strategyBonus);
        }
//        System.out.println(enemyDamage);
        return enemyDamage;
    }

    // computing DEFLECT damage versus a KNIGHT
    @Override
    public float affectOvertime(final Knight enemy,
                                      final LocationType location,
                                      final boolean startNow,
                                      final boolean addRaceModifier) {
        float enemyDamage = enemy.attack(this, location, false, true);
        float percent = computeInitialOvertimeDamage(location);
//        System.out.println(percent);
        enemyDamage *= percent;
        if (addRaceModifier) { // adding race modifier
//            System.out.println("before " + enemyDamage);
            enemyDamage *= (Constants.WIZARD_DEFLECT_BONUS_VERSUS_KNIGHT
                    + getAngelBonus()
                    + getStrategyBonus());
//            System.out.println("after " + enemyDamage);
        }
//        System.out.println(enemyDamage);
        return enemyDamage;
    }

    // computing DEFLECT damage versus a PYROMANCER
    @Override
    public float affectOvertime(final Pyromancer enemy,
                                      final LocationType location,
                                      final boolean startNow,
                                      final boolean addRaceModifier) {
        float enemyDamage = enemy.attack(this, location, false, true);
        float percent = computeInitialOvertimeDamage(location);
//        System.out.println(enemyDamage);
        enemyDamage *= percent;
//        System.out.println(percent);
        if (addRaceModifier) { // adding race modifier
            enemyDamage *= (Constants.WIZARD_DEFLECT_BONUS_VERSUS_PYROMANCER
                    + getAngelBonus()
                    + getStrategyBonus());
        }
//        System.out.println(enemyDamage);
        return enemyDamage;
    }

    @Override
    public void getHelpedBy(final Angel angel) {
        angel.helpHero(this);
    }

    @Override
    public void lookForStrategy() {
        if (getHealth() > Constants.WIZARD_ATTACK_STRATEGY_LOW_MARGIN * getMaxHealth()
                && getHealth() < Constants.WIZARD_ATTACK_STRATEGY_HIGH_MARGIN * getMaxHealth()) {
            setStrategy(new WizardAttackStrategy(this));
//            System.out.println("acilisha");
        } else if (getHealth() < Constants.WIZARD_DEFENCE_STRATEGY_HIGH_MARGIN * getMaxHealth()) {
            setStrategy(new WizardDefenceStrategy(this));
//            System.out.println("ddddddd");
        }
//        if (strategy != null) {
//            strategy.applyStrategy();
//        }
    }
}

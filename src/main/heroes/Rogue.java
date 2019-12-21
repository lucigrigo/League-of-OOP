package main.heroes;

import main.angels.Angel;
import main.data.HeroType;
import main.data.Constants;
import main.data.LocationType;
import main.data.Visitable;
import main.gameplay.OverTimeAbility;
import main.strategies.RogueAttackStrategy;
import main.strategies.RogueDefenceStrategy;

/**
 * Class to implement ROGUE logic.
 */
public class Rogue extends Hero implements Visitable {

    private int backStabCount;
    private boolean appliedBackStabThisRound;

    public Rogue(final int initCol,
                 final int initLin,
                 final int index) {
        super(initCol, initLin, Constants.ROGUE_INITIAL_HEALTH, 0,
                HeroType.ROGUE, "R", "Rogue", index);
        this.backStabCount = 0;
        this.appliedBackStabThisRound = false;
    }

    // returning maximum health as a ROGUE
    @Override
    public final int getMaxHealth() {
        return Constants.ROGUE_INITIAL_HEALTH
                + Constants.ROGUE_HEALTH_RATIO
                * this.getLevel();
    }

    // increasing the count of the backstabs applied if it is the case
    @Override
    public final void doRoundEndingRoutine() {
        super.doRoundEndingRoutine();
        if (appliedBackStabThisRound) {
            this.backStabCount++;
            appliedBackStabThisRound = false;
        }
    }

    // applied backstab this round
    private void hasAppliedBackStab() {
        this.appliedBackStabThisRound = true;
    }

    // computing initial damage as a ROGUE
    @Override
    public final float computeInitialDamage(final LocationType location) {
        float damage = Constants.ROGUE_BACKSTAB_BASE_DAMAGE
                + Constants.ROGUE_BACKSTAB_LEVEL_SCALING_BASE_DAMAGE
                * this.getLevel();
        if (location == LocationType.WOODS) { // adding location bonus
            if (this.backStabCount
                    % Constants.ROGUE_BACKSTAB_CRITICAL_HIT_OCCURENCE == 0) {
                // adding critical hit
                damage *= Constants.ROGUE_BACKSTAB_CRITICAL_HIT_RATIO;
            }
            damage *= Constants.ROGUE_WOODS_BONUS;
        }
        return Math.round(damage);
    }

    // computing initial overtime damage as a ROGUE
    @Override
    public final float computeInitialOvertimeDamage(final LocationType location) {
        float damage = Constants.ROGUE_PARALYSIS_BASE_DAMAGE
                + Constants.ROGUE_PARALYSIS_LEVEL_SCALING_BASE_DAMAGE
                * this.getLevel();
        if (location == LocationType.WOODS) { // adding location bonus
            damage *= Constants.ROGUE_WOODS_BONUS;
        }
        return Math.round(damage);
    }

    // getting attacked as a ROGUE
    @Override
    public final void getAttackedBy(final Hero enemy,
                                    final LocationType location) {
        enemy.attack(this, location, true, false);
    }

    // attacking a WIZARD as a ROGUE
    @Override
    public final float attack(final Wizard enemy,
                              final LocationType location,
                              final boolean addRaceModifier,
                              final boolean isForDeflectPurpose) {
        float damage = computeInitialDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= (Constants.ROGUE_BACKSTAB_BONUS_VERSUS_WIZARD
                    + angelBonus);
        }
        damage = Math.round(damage);
        if (!isForDeflectPurpose) { // if interrogated by a WIZARD
            damage += Math.round(this.affectOvertime(enemy, location,
                    false, true));
            if (enemy.takeDamage(Math.round(damage), false, false)) {
                computeObservation(enemy);
                this.fightWon(enemy.getLevel());
            }
            this.hasAppliedBackStab();
//            return 0f;
        }
        damage += Math.round(this.affectOvertime(enemy, location,
                false, false));
        return damage;
    }

    // attacking a ROGUE as a ROGUE
    @Override
    public final void attack(final Rogue enemy,
                             final LocationType location,
                             final boolean addRaceModifier,
                             final boolean isForDeflectPurpose) {
        float damage = computeInitialDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= (Constants.ROGUE_BACKSTAB_BONUS_VERSUS_ROGUE
                    + angelBonus
                    + strategyBonus);
        }
        // TODO solve fightRRD aproximation issue
        damage = Math.round(damage);
        if (!isForDeflectPurpose) { // if interrogated by a WIZARD
            damage += Math.round(this.affectOvertime(enemy, location,
                    false, true));
//            System.out.println(damage);
            if (enemy.takeDamage(Math.round(damage), false, false)) {
                computeObservation(enemy);
                this.fightWon(enemy.getLevel());
            }
            this.hasAppliedBackStab();
            return;
        }
        damage += Math.round(this.affectOvertime(enemy, location,
                false, false));
    }

    // attacking a PYROMANCER as a ROGUE
    @Override
    public final void attack(final Pyromancer enemy,
                             final LocationType location,
                             final boolean addRaceModifier,
                             final boolean isForDeflectPurpose) {
        float damage = computeInitialDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= (Constants.ROGUE_BACKSTAB_BONUS_VERSUS_PYROMANCER
                    + angelBonus
                    + strategyBonus);
        }
        damage = Math.round(damage);
        if (!isForDeflectPurpose) { // if interrogated by a WIZARD
            damage += Math.round(this.affectOvertime(enemy, location,
                    false, true));
            if (enemy.takeDamage(Math.round(damage), false, false)) {
                computeObservation(enemy);
                this.fightWon(enemy.getLevel());
            }
            this.hasAppliedBackStab();
            return;
        }
        damage += Math.round(this.affectOvertime(enemy, location,
                false, false));
    }

    // attacking a KNIGHT as a ROGUE
    @Override
    public final void attack(final Knight enemy,
                             final LocationType location,
                             final boolean addRaceModifier,
                             final boolean isForDeflectPurpose) {
        float damage = computeInitialDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= (Constants.ROGUE_BACKSTAB_BONUS_VERSUS_KNIGHT
                    + angelBonus
                    + strategyBonus);
        }
        damage = Math.round(damage);
        // TODO solve fightRKD aproximation issue
        if (!isForDeflectPurpose) { // if interrogated by a WIZARD
            damage += Math.round(this.affectOvertime(enemy, location,
                    false, true));
            if (enemy.takeDamage(Math.round(damage), false, false)) {
                computeObservation(enemy);
                this.fightWon(enemy.getLevel());
            }
            this.hasAppliedBackStab();
            return;
        }
        damage += Math.round(this.affectOvertime(enemy, location,
                false, false));
    }

    // getting affected as a ROGUE
    @Override
    public final void getAffectedBy(final Hero enemy,
                                    final LocationType location) {
        enemy.affectOvertime(this, location, true, true);
    }

    // affecting overtime a WIZARD as a ROGUE
    @Override
    public final float affectOvertime(final Wizard enemy,
                                      final LocationType location,
                                      final boolean startNow,
                                      final boolean addRaceModifier) {
        float damage = computeInitialOvertimeDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= (Constants.ROGUE_PARALYSIS_BONUS_VERSUS_WIZARD
                    + angelBonus
                    + strategyBonus);
        }
        if (startNow) { // starting the ability now
            int duration;
            if (location == LocationType.WOODS) { // increasing duration
                duration = Constants.ROGUE_PARALYSIS_ROUNDS_NUMBER_WOODS;
            } else {
                duration = Constants.ROGUE_PARALYSIS_ROUNDS_NUMBER_NORMAL;
            }
            enemy.setAbilityAffectedBy(new OverTimeAbility(this, enemy, "Paralysis",
                    location, Math.round(damage), duration, true));
            return 0f;
        }
        return damage;
    }

    // affecting overtime a ROGUE as a ROGUE
    @Override
    public final float affectOvertime(final Rogue enemy,
                                      final LocationType location,
                                      final boolean startNow,
                                      final boolean addRaceModifier) {
        float damage = computeInitialOvertimeDamage(location);
        if (addRaceModifier) { // adding race modifier
            // TODO solve aproximation issue
            float percent = Constants.ROGUE_PARALYSIS_BONUS_VERSUS_ROGUE
                    + angelBonus
                    + strategyBonus;
            damage = Math.round(percent * damage);
//                    * damage);
        }
//        System.out.println(damage);
        damage = Math.round(damage);
        if (startNow) { // starting the ability now
            int duration;
            if (location == LocationType.WOODS) { // increasing duration
                duration = Constants.ROGUE_PARALYSIS_ROUNDS_NUMBER_WOODS;
            } else {
                duration = Constants.ROGUE_PARALYSIS_ROUNDS_NUMBER_NORMAL;
            }
            enemy.setAbilityAffectedBy(new OverTimeAbility(this, enemy, "Paralysis",
                    location, Math.round(damage), duration, true));
            return 0f;
        }
        return damage;
    }

    // affecting overtime a KNIGHT as a ROGUE
    @Override
    public final float affectOvertime(final Knight enemy,
                                      final LocationType location,
                                      final boolean startNow,
                                      final boolean addRaceModifier) {
        float damage = computeInitialOvertimeDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= (Constants.ROGUE_PARALYSIS_BONUS_VERSUS_KNIGHT
                    + angelBonus
                    + strategyBonus);
        }
        if (startNow) { // starting the ability now
            int duration;
            if (location == LocationType.WOODS) { // increasing duration
                duration = Constants.ROGUE_PARALYSIS_ROUNDS_NUMBER_WOODS;
            } else {
                duration = Constants.ROGUE_PARALYSIS_ROUNDS_NUMBER_NORMAL;
            }
            enemy.setAbilityAffectedBy(new OverTimeAbility(this, enemy, "Paralysis",
                    location, Math.round(damage), duration, true));
            return 0f;
        }
        return damage;
    }

    // affecting overtime a PYROMANCER as a ROGUE
    @Override
    public final float affectOvertime(final Pyromancer enemy,
                                      final LocationType location,
                                      final boolean startNow,
                                      final boolean addRaceModifier) {
        float damage = computeInitialOvertimeDamage(location);
        if (addRaceModifier) { // adding race modifier
            damage *= (Constants.ROGUE_PARALYSIS_BONUS_VERSUS_PYROMANCER
                    + angelBonus
                    + strategyBonus);
        }
        if (startNow) { // starting the ability now
            int duration;
            if (location == LocationType.WOODS) { // increasing duration
                duration = Constants.ROGUE_PARALYSIS_ROUNDS_NUMBER_WOODS;
            } else {
                duration = Constants.ROGUE_PARALYSIS_ROUNDS_NUMBER_NORMAL;
            }
            enemy.setAbilityAffectedBy(new OverTimeAbility(this, enemy, "Paralysis",
                    location, Math.round(damage), duration, true));
            return 0f;
        }
        return damage;
    }

    @Override
    public final void getHelpedBy(final Angel angel) {
        angel.helpHero(this);
    }

    @Override
    public void lookForStrategy() {
        if (getHealth() < Constants.ROGUE_ATTACK_STRATEGY_HIGH_MARGIN * getMaxHealth()
                && getHealth() > Constants.ROGUE_ATTACK_STRATEGY_LOW_MARGIN * getMaxHealth()) {
            strategy = new RogueAttackStrategy(this);
//            System.out.println("atac");
        } else if (getHealth() < Constants.ROGUE_DEFENCE_STRATEGY_HIGH_MARGIN * getMaxHealth()) {
            strategy = new RogueDefenceStrategy(this);
//            System.out.println("difens");
        }
//        if (strategy != null) {
//            strategy.applyStrategy();
//        }
    }
}

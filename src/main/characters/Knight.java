package main.characters;

import main.data.Constants;
import main.data.LocationType;
import main.gameplay.Ability;
import main.gameplay.OverTimeAbility;

public class Knight extends GameCharacter {

    public Knight(final int initCol,
                  final int initLin) {
        super(initCol, initLin, Constants.KNIGHT_INITIAL_HEALTH, 0,
                CharacterType.KNIGHT, "K");
    }

    @Override
    public int getMaxHealth() {
        return Constants.KNIGHT_INITIAL_HEALTH
                + Constants.KNIGHT_HEALTH_RATIO
                * this.getLevel();
    }

    @Override
    public Ability computeDamageAgainst(final GameCharacter enemy,
                                        final LocationType location,
                                        final boolean addRaceModifier) {
        return null;
    }

    @Override
    public float getDamageWithoutRaceModifier(GameCharacter enemy, LocationType location) {
        Ability ability = this.computeDamageAgainst(enemy, location, false);
        return ability.getDamage();
    }

    @Override
    public OverTimeAbility getAbilityOverTime(final GameCharacter enemy,
                                              final LocationType location) {
        return null;
    }

    @Override
    public float computeInitialDamage(final LocationType location) {
        float damage = Constants.KNIGHT_EXECUTE_BASE_DAMAGE
                + Constants.KNIGHT_EXECUTE_LEVEL_SCALING_BASE_DAMAGE
                * this.getLevel();
        if (location == LocationType.LAND) {
            damage *= Constants.KNIGHT_LAND_BONUS;
        }
        return damage;
    }

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

    public float computeInitialOvertimeDamage(final LocationType location) {
        float damage = Constants.KNIGHT_SLAM_BASE_DAMAGE
                + Constants.KNIGHT_SLAM_LEVEL_SCALING_BASE_DAMAGE
                * this.getLevel();
        if (location == LocationType.LAND) {
            damage *= Constants.KNIGHT_LAND_BONUS;
        }
        return damage;
    }

    @Override
    public void getAttackedBy(final GameCharacter enemy,
                              final LocationType location) {
        enemy.attack(this, location, true, false);
    }

    @Override
    public float attack(final Wizard enemy,
                        final LocationType location,
                        final boolean addRaceModifier,
                        final boolean isForDeflectPurpose) {
        if (checkExecutePossibility(enemy.getInitialRoundHealth(), enemy.getMaxHealth())) {
//            System.out.println("current: " + enemy.getHealth());

            if (!isForDeflectPurpose) {
                this.fightWon(enemy.getLevel());
                enemy.hasDied();
            }
            return enemy.getHealth();
        }
        float damage = computeInitialDamage(location);
        if (addRaceModifier) {
            damage *= Constants.KNIGHT_EXECUTE_BONUS_VERSUS_WIZARD;
        }
        damage = Math.round(damage);
        if (!isForDeflectPurpose) {
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

    @Override
    public float attack(final Rogue enemy,
                        final LocationType location,
                        final boolean addRaceModifier,
                        final boolean isForDeflectPurpose) {
        if (checkExecutePossibility(enemy.getInitialRoundHealth(), enemy.getMaxHealth())) {
            this.fightWon(enemy.getLevel());
            enemy.hasDied();
            return 0f;
        }
        float damage = computeInitialDamage(location);
        if (addRaceModifier) {
            damage *= Constants.KNIGHT_EXECUTE_BONUS_VERSUS_ROGUE;
        }
        damage = Math.round(damage);
        if (!isForDeflectPurpose) {
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

    @Override
    public float attack(final Pyromancer enemy,
                        final LocationType location,
                        final boolean addRaceModifier,
                        final boolean isForDeflectPurpose) {
        if (checkExecutePossibility(enemy.getInitialRoundHealth(), enemy.getMaxHealth())) {
            this.fightWon(enemy.getLevel());
            enemy.hasDied();
            return 0f;
        }
        float damage = computeInitialDamage(location);
        if (addRaceModifier) {
            damage *= Constants.KNIGHT_EXECUTE_BONUS_VERSUS_PYROMANCER;
        }
        damage = Math.round(damage);
        if (!isForDeflectPurpose) {
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

    @Override
    public float attack(final Knight enemy,
                        final LocationType location,
                        final boolean addRaceModifier,
                        final boolean isForDeflectPurpose) {
        if (checkExecutePossibility(enemy.getInitialRoundHealth(), enemy.getMaxHealth())) {
            this.fightWon(enemy.getLevel());
            enemy.hasDied();
            return 0f;
        }
        float damage = computeInitialDamage(location);
        if (addRaceModifier) {
            damage *= Constants.KNIGHT_EXECUTE_BONUS_VERSUS_KNIGHT;
        }
        damage = Math.round(damage);
        if (!isForDeflectPurpose) {
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
//        return this.finaliseAttack(enemy, location, isForDeflectPurpose, Math.round(damage));
    }

    @Override
    public void getAffectedBy(final GameCharacter enemy,
                              final LocationType location) {
        enemy.affectOvertime(this, location, true, true);
    }

    @Override
    public float affectOvertime(final Wizard enemy,
                                final LocationType location,
                                final boolean startNow,
                                final boolean addRaceModifier) {
        float damage = computeInitialOvertimeDamage(location);
        if (addRaceModifier) {
            damage *= Constants.KNIGHT_SLAM_BONUS_VERSUS_WIZARD;
        }
        if (startNow) {
            enemy.setAbilityAffectedBy(new OverTimeAbility(this, enemy,
                    "Slam", location, 0, Constants.KNIGHT_SLAM_DURATION,
                    true));
            return 0f;
        }
        return damage;
    }

    @Override
    public float affectOvertime(final Rogue enemy,
                                final LocationType location,
                                final boolean startNow,
                                final boolean addRaceModifier) {
        float damage = computeInitialOvertimeDamage(location);
        if (addRaceModifier) {
            damage *= Constants.KNIGHT_SLAM_BONUS_VERSUS_ROGUE;
        }
        if (startNow) {
            enemy.setAbilityAffectedBy(new OverTimeAbility(this, enemy,
                    "Slam", location, 0, Constants.KNIGHT_SLAM_DURATION,
                    true));
            return 0f;
        }
        return damage;
    }

    @Override
    public float affectOvertime(final Knight enemy,
                                final LocationType location,
                                final boolean startNow,
                                final boolean addRaceModifier) {
        float damage = computeInitialOvertimeDamage(location);
        if (addRaceModifier) {
            damage *= Constants.KNIGHT_SLAM_BONUS_VERSUS_KNIGHT;
        }
        if (startNow) {
            enemy.setAbilityAffectedBy(new OverTimeAbility(this, enemy,
                    "Slam", location, 0, Constants.KNIGHT_SLAM_DURATION,
                    true));
            return 0f;
        }
        return damage;
    }

    @Override
    public float affectOvertime(final Pyromancer enemy,
                                final LocationType location,
                                final boolean startNow,
                                final boolean addRaceModifier) {
        float damage = computeInitialOvertimeDamage(location);
        if (addRaceModifier) {
            damage *= Constants.KNIGHT_SLAM_BONUS_VERSUS_PYROMANCER;
        }
        if (startNow) {
            enemy.setAbilityAffectedBy(new OverTimeAbility(this, enemy,
                    "Slam", location, 0, Constants.KNIGHT_SLAM_DURATION,
                    true));
            return 0f;
        }
        return damage;
    }

//    @Override
//    public float finaliseAttack(final Wizard enemy,
//                                final LocationType location,
//                                final boolean isForDeflectPurpose,
//                                int damage) {
//        if (!isForDeflectPurpose) {
//            damage += Math.round(this.affectOvertime(enemy,
//                    location, false, true));
//            if (enemy.takeDamage(Math.round(damage), false)) {
//                this.fightWon(enemy.getLevel());
//            }
//            return 0f;
//        }
//        damage += Math.round(this.affectOvertime(enemy, location,
//                false, false));
//        return damage;
//    }
}

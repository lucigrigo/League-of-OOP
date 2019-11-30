package main.characters;

import main.data.Constants;
import main.data.LocationType;
import main.gameplay.Ability;
import main.gameplay.OverTimeAbility;

public class Pyromancer extends GameCharacter {

    public Pyromancer(final int initCol,
                      final int initLin) {
        super(initCol, initLin, Constants.PYROMANCER_INITIAL_HEALTH, 0,
                CharacterType.PYROMANCER, "P");
    }

    @Override
    public int getMaxHealth() {
        return Constants.PYROMANCER_INITIAL_HEALTH
                + Constants.PYROMANCER_HEALTH_RATIO
                * this.getLevel();
    }

    @Override
    public Ability computeDamageAgainst(final GameCharacter enemy,
                                        final LocationType location,
                                        final boolean addRaceModifier) {

//        float totalDamage = Constants.getInstance().getPyromancerFireblastBaseDamage()
//                + Constants.getInstance().getPyromancerFireblastLevelScalingBaseDamage()
//                * this.getLevel();
////        System.out.println(totalDamage);
//        if (location == LocationType.VOLCANIC) {
//            totalDamage = totalDamage
//                    + totalDamage
//                    * Constants.getInstance().getPyromancerVolcanicBonus()
//                    * 0.01f;
//        }
////        System.out.println(totalDamage);
//        int damageWithoutRaceModifier = Math.round(totalDamage);
//        if (addRaceModifier) {
//            float raceBonus = 0.0f;
//            switch (enemy.getType()) {
//                case PYROMANCER:
//                    raceBonus = Constants.getInstance().getPyromancerFireblastBonusVersusPyromancer();
//                    break;
//                case ROGUE:
//                    raceBonus = Constants.getInstance().getPyromancerFireblastBonusVersusRogue();
//                    break;
//                case KNIGHT:
//                    raceBonus = Constants.getInstance().getPyromancerFireblastBonusVersusKnight();
//                    break;
//                case WIZARD:
//                    raceBonus = Constants.getInstance().getPyromancerFireblastBonusVersusWizard();
//                    break;
//                default:
//                    break;
//            }
////            totalDamage = totalDamage
////                    + totalDamage
////                    * raceBonus
////                    / 100f;
//            totalDamage = (1f
//                    + raceBonus
//                    * 0.01f)
//                    * totalDamage;
//        }
////        } else {
//////            totalDamage = Math.round(totalDamage);
////        }
//        totalDamage = Math.round(totalDamage);
//        if (addRaceModifier) {
////                System.out.println("aici?");
////            System.out.println("total inainte  " + totalDamage);
//            totalDamage += this.getAbilityOverTime(enemy, location).getInstantDamage();
////            System.out.println("total inainte  " + totalDamage);
//        } else {
////            System.out.println("total inainte  " + totalDamage);
//            totalDamage += this.getAbilityOverTime(enemy, location).getDamageWithoutRaceModifier();
////            System.out.println("total inainte  " + totalDamage);
//        }
////        System.out.println(totalDamage);
//        return new Ability("Fireblast", Math.round(totalDamage), damageWithoutRaceModifier);
        return null;
    }

    @Override
    public float getDamageWithoutRaceModifier(GameCharacter enemy, LocationType location) {
//        return this.computeDamageAgainst(enemy, location, false).getDamageWithoutRaceModifier();
        Ability ability = this.computeDamageAgainst(enemy, location, false);
//        if (ability != null) {
//            System.out.println(ability.getDamage() + " de aicea nane");
        return ability.getDamage();
//        }
//        return 0.0f;
    }

    @Override
    public OverTimeAbility getAbilityOverTime(GameCharacter enemy, LocationType location) {
//        OverTimeAbility ignite = new OverTimeAbility(this, enemy, "Ignite", location);
//        ignite.setDuration(1);
//        ignite.setAbilityToIncapacitate(false);
//        float igniteInstantDamage = Constants.getInstance().getPyromancerIgniteBaseDamage()
//                + Constants.getInstance().getPyromancerIgniteLevelScalingBaseDamage()
//                * this.getLevel();
//        float igniteSuccesiveDamage = Constants.getInstance().getPyromancerIgniteRoundDamage()
//                + Constants.getInstance().getPyromancerIgniteSuccessiveRoundsNumber()
//                * this.getLevel();
//        if (location == LocationType.VOLCANIC) {
////            igniteInstantDamage = igniteInstantDamage
////                    + igniteInstantDamage
////                    * Constants.getInstance().getPyromancerVolcanicBonus()
////                    * 0.01f;
//            igniteInstantDamage = (1f
//                    + Constants.getInstance().getPyromancerVolcanicBonus()
//                    * 0.01f)
//                    * igniteInstantDamage;
////            igniteSuccesiveDamage = igniteSuccesiveDamage
////                    + igniteSuccesiveDamage
////                    * Constants.getInstance().getPyromancerVolcanicBonus()
////                    * 0.01f;
//            igniteSuccesiveDamage = (1f
//                    + Constants.getInstance().getPyromancerVolcanicBonus()
//                    * 0.01f)
//                    * igniteSuccesiveDamage;
//        }
//        int damageWithoutRaceModifier = Math.round(igniteInstantDamage);
//        float raceBonus = 0.0f;
//        switch (enemy.getType()) {
//            case KNIGHT:
//                raceBonus = Constants.getInstance().getPyromancerIgniteBonusVersusKnight();
//                break;
//            case PYROMANCER:
//                raceBonus = Constants.getInstance().getPyromancerIgniteBonusVersusPyromancer();
//                break;
//            case ROGUE:
//                raceBonus = Constants.getInstance().getPyromancerIgniteBonusVersusRogue();
//                break;
//            case WIZARD:
//                raceBonus = Constants.getInstance().getPyromancerIgniteBonusVersusWizard();
//                break;
//            default:
//                break;
//        }
////        raceBonus /= 100f;
////        igniteInstantDamage = igniteInstantDamage
////                + igniteInstantDamage
////                * raceBonus;
//        igniteInstantDamage = (1f
//                + raceBonus
//                * 0.01f)
//                * igniteInstantDamage;
////        igniteSuccesiveDamage = igniteSuccesiveDamage
////                + igniteSuccesiveDamage
////                * raceBonus;
//        igniteSuccesiveDamage = (1f
//                + raceBonus
//                * 0.01f)
//                * igniteSuccesiveDamage;
//        ignite.setInstantDamage(Math.round(igniteInstantDamage));
//        ignite.setOvertimeDamage(Math.round(igniteSuccesiveDamage));
//        ignite.setDamageWithoutRaceModifier(damageWithoutRaceModifier);
////        enemy.takeDamage(new Ability("Ignite", ignite.getOvertimeDamage(), damageWithoutRaceModifier),
////                this, location, true);
//        return ignite;
        return null;
    }

    @Override
    public float computeInitialDamage(final LocationType location) {
        float damage = Constants.PYROMANCER_FIREBLAST_BASE_DAMAGE
                + Constants.PYROMANCER_FIREBLAST_LEVEL_SCALING_BASE_DAMAGE
                * this.getLevel();
        if (location == LocationType.VOLCANIC) {
            damage *= Constants.PYROMANCER_VOLCANIC_BONUS;
        }
        return damage;
    }

    @Override
    public float computeInitialOvertimeDamage(LocationType location) {
        float damage = Constants.PYROMANCER_IGNITE_BASE_DAMAGE
                + Constants.PYROMANCER_IGNITE_LEVEL_SCALING_BASE_DAMAGE
                * this.getLevel();
        if (location == LocationType.VOLCANIC) {
            damage *= Constants.PYROMANCER_VOLCANIC_BONUS;
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
        float damage = computeInitialDamage(location);
        if (addRaceModifier) {
            damage *= Constants.PYROMANCER_FIREBLAST_BONUS_VERSUS_WIZARD;
        }
        damage = Math.round(damage);
        if (!isForDeflectPurpose) {
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

    @Override
    public float attack(final Rogue enemy,
                        final LocationType location,
                        final boolean addRaceModifier,
                        final boolean isForDeflectPurpose) {
        float damage = computeInitialDamage(location);
        if (addRaceModifier) {
            damage *= Constants.PYROMANCER_FIREBLAST_BONUS_VERSUS_ROGUE;
        }
        damage = Math.round(damage);
        if (!isForDeflectPurpose) {
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

    @Override
    public float attack(final Pyromancer enemy,
                        final LocationType location,
                        final boolean addRaceModifier,
                        final boolean isForDeflectPurpose) {
        float damage = computeInitialDamage(location);
        if (addRaceModifier) {
            damage *= Constants.PYROMANCER_FIREBLAST_BONUS_VERSUS_PYROMANCER;
        }
        damage = Math.round(damage);
        if (!isForDeflectPurpose) {
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

    @Override
    public float attack(final Knight enemy,
                        final LocationType location,
                        final boolean addRaceModifier,
                        final boolean isForDeflectPurpose) {

        float damage = computeInitialDamage(location);
        if (addRaceModifier) {
            damage *= Constants.PYROMANCER_FIREBLAST_BONUS_VERSUS_KNIGHT;
        }
        damage = Math.round(damage);
        if (!isForDeflectPurpose) {
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
        float damage;
        if (startNow) {
            damage = Constants.PYROMANCER_IGNITE_ROUND_DAMAGE
                    + Constants.PYROMANCER_IGNITE_LEVEL_SCALING_ROUND_DAMAGE
                    * this.getLevel();
            if (location == LocationType.VOLCANIC) {
                damage *= Constants.PYROMANCER_VOLCANIC_BONUS;
            }
        } else {
            damage = computeInitialOvertimeDamage(location);
        }
        if (addRaceModifier) {
            damage *= Constants.PYROMANCER_IGNITE_BONUS_VERSUS_WIZARD;
        }
        if (startNow) {
            enemy.setAbilityAffectedBy(new OverTimeAbility(this, enemy, "Ignite",
                    location, Math.round(damage), Constants.PYROMANCER_IGNITE_DURATION));
            return 0f;
        }
        return damage;
    }

    @Override
    public float affectOvertime(final Rogue enemy,
                                final LocationType location,
                                final boolean startNow,
                                final boolean addRaceModifier) {
        float damage;
        if (startNow) {
            damage = Constants.PYROMANCER_IGNITE_ROUND_DAMAGE
                    + Constants.PYROMANCER_IGNITE_LEVEL_SCALING_ROUND_DAMAGE
                    * this.getLevel();
            if (location == LocationType.VOLCANIC) {
                damage *= Constants.PYROMANCER_VOLCANIC_BONUS;
            }
        } else {
            damage = computeInitialOvertimeDamage(location);
        }
        if (addRaceModifier) {
            damage *= Constants.PYROMANCER_IGNITE_BONUS_VERSUS_ROGUE;
        }
        if (startNow) {
            enemy.setAbilityAffectedBy(new OverTimeAbility(this, enemy, "Ignite",
                    location, Math.round(damage), Constants.PYROMANCER_IGNITE_DURATION));
            return 0f;
        }
        return damage;
    }

    @Override
    public float affectOvertime(final Knight enemy,
                                final LocationType location,
                                final boolean startNow,
                                final boolean addRaceModifier) {
        float damage;
        if (startNow) {
            damage = Constants.PYROMANCER_IGNITE_ROUND_DAMAGE
                    + Constants.PYROMANCER_IGNITE_LEVEL_SCALING_ROUND_DAMAGE
                    * this.getLevel();
            if (location == LocationType.VOLCANIC) {
                damage *= Constants.PYROMANCER_VOLCANIC_BONUS;
            }
        } else {
            damage = computeInitialOvertimeDamage(location);
        }
        if (addRaceModifier) {
            damage *= Constants.PYROMANCER_IGNITE_BONUS_VERSUS_KNIGHT;
        }
        if (startNow) {
            enemy.setAbilityAffectedBy(new OverTimeAbility(this, enemy, "Ignite",
                    location, Math.round(damage), Constants.PYROMANCER_IGNITE_DURATION));
            return 0f;
        }
        return damage;
    }

    @Override
    public float affectOvertime(final Pyromancer enemy,
                                final LocationType location,
                                final boolean startNow,
                                final boolean addRaceModifier) {
        float damage;
        if (startNow) {
            damage = Constants.PYROMANCER_IGNITE_ROUND_DAMAGE
                    + Constants.PYROMANCER_IGNITE_LEVEL_SCALING_ROUND_DAMAGE
                    * this.getLevel();
            if (location == LocationType.VOLCANIC) {
                damage *= Constants.PYROMANCER_VOLCANIC_BONUS;
            }
        } else {
            damage = computeInitialOvertimeDamage(location);
        }
        if (addRaceModifier) {
            damage *= Constants.PYROMANCER_IGNITE_BONUS_VERSUS_PYROMANCER;
        }
        if (startNow) {
            enemy.setAbilityAffectedBy(new OverTimeAbility(this, enemy, "Ignite",
                    location, Math.round(damage), Constants.PYROMANCER_IGNITE_DURATION));
            return 0f;
        }
        return damage;
    }
}

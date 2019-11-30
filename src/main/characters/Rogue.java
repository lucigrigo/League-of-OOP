package main.characters;

import main.data.Constants;
import main.data.LocationType;
import main.gameplay.Ability;
import main.gameplay.OverTimeAbility;

public class Rogue extends GameCharacter {

    private int backstabCount;
    private boolean appliedBackstabThisRound;

    public Rogue(final int initCol,
                 final int initLin) {
        super(initCol, initLin, Constants.ROGUE_INITIAL_HEALTH, 0,
                CharacterType.ROGUE, "R");
        this.backstabCount = 0;
        this.appliedBackstabThisRound = false;
    }

    private float getBackstabDamage() {
//        return Constants.getInstance().getRogueBackstabBaseDamage()
//                + Constants.getInstance().getRogueBackstabBaseDamage()
//                * Constants.getInstance().getRogueBackstabLevelScalingBaseDamage() / 100f
//                * this.getLevel();
//        return (1f
//                + Constants.getInstance().getRogueBackstabLevelScalingBaseDamage()
//                * 0.01f
//                * this.getLevel())
//                * Constants.getInstance().getRogueBackstabBaseDamage();
        return 0f;
    }

    @Override
    public OverTimeAbility getAbilityOverTime(final GameCharacter enemy,
                                              final LocationType location) {
//        OverTimeAbility paralysis = new OverTimeAbility(this, enemy, "Paralysis", location);
//        paralysis.setAbilityToIncapacitate(true);
//        float overtimeDamage = 0.0f;
////        paralysis.setOvertimeDamage(Constants.getInstance().getRogueParalysisBaseDamage()
////                + Constants.getInstance().getRogueParalysisLevelScalingBaseDamage()
////                * this.getLevel());
//        overtimeDamage = Constants.getInstance().getRogueParalysisBaseDamage()
//                + Constants.getInstance().getRogueParalysisLevelScalingBaseDamage()
//                * this.getLevel();
//        if (location == LocationType.WOODS) {
//            paralysis.setDuration(Constants.getInstance().getRogueParalysisRoundsWoodsNumber());
////            paralysis.setOvertimeDamage(Math.round(paralysis.getOvertimeDamage()
////                    + paralysis.getOvertimeDamage()
////                    * Constants.getInstance().getRogueWoodsBonus()
////                    * 0.01f));
//            overtimeDamage = (1f
//                    + Constants.getInstance().getRogueWoodsBonus()
//                    * 0.01f)
//                    * overtimeDamage;
//        } else {
//            paralysis.setDuration(Constants.getInstance().getRogueParalysisRoundsNormalNumber());
//        }
////        System.out.println("----- " + paralysis.getOvertimeDamage());
//        int damageWithoutRaceModifier = Math.round(overtimeDamage);
////        paralysis.setDamageWithoutRaceModifier(damageWithoutRaceModifier);
//        switch (enemy.getType()) {
//            case ROGUE:
////                paralysis.setOvertimeDamage(Math.round(paralysis.getOvertimeDamage()
////                        + paralysis.getOvertimeDamage()
////                        * Constants.getInstance().getRogueParalysisBonusVersusRogue() / 100f));
//                overtimeDamage = (1f
//                        + Constants.getInstance().getRogueParalysisBonusVersusRogue()
//                        * 0.01f)
//                        * overtimeDamage;
//                break;
//            case KNIGHT:
////                paralysis.setOvertimeDamage(Math.round(paralysis.getOvertimeDamage()
////                        + paralysis.getOvertimeDamage()
////                        * Constants.getInstance().getRogueParalysisBonusVersusKnight() / 100f));
//                overtimeDamage = (1f
//                        + Constants.getInstance().getRogueParalysisBonusVersusKnight()
//                        * 0.01f)
//                        * overtimeDamage;
//                break;
//            case WIZARD:
////                paralysis.setOvertimeDamage(Math.round(paralysis.getOvertimeDamage()
////                        + paralysis.getOvertimeDamage()
////                        * Constants.getInstance().getRogueParalysisBonusVersusWizard() / 100f));
//                overtimeDamage = (1f
//                        + Constants.getInstance().getRogueParalysisBonusVersusWizard()
//                        * 0.01f)
//                        * overtimeDamage;
//                break;
//            case PYROMANCER:
////                paralysis.setOvertimeDamage(Math.round(paralysis.getOvertimeDamage()
////                        + paralysis.getOvertimeDamage()
////                        * Constants.getInstance().getRogueParalysisBonusVersusPyromancer() / 100f));
//                overtimeDamage = (1f
//                        + Constants.getInstance().getRogueParalysisBonusVersusPyromancer()
//                        * 0.01f)
//                        * overtimeDamage;
//                break;
//            default:
//                break;
//        }
//        paralysis.setOvertimeDamage(Math.round(overtimeDamage));
//        paralysis.setDamageWithoutRaceModifier(damageWithoutRaceModifier);
////        enemy.takeDamage(new Ability("Paralysis", paralysis.getOvertimeDamage(), damageWithoutRaceModifier),
////                this, location, true);
//        return paralysis;
        return null;
    }

    @Override
    public int getMaxHealth() {
        return Constants.ROGUE_INITIAL_HEALTH
                + Constants.ROGUE_HEALTH_RATIO
                * this.getLevel();
    }

    public void increaseBackstabCount() {
        this.backstabCount++;
    }

    @Override
    public Ability computeDamageAgainst(final GameCharacter enemy,
                                        final LocationType location,
                                        final boolean addRaceModifier) {
//        float totalDamage = 0.0f;
//        totalDamage += getBackstabDamage();
////        this.backstabCount++;
//        if (location == LocationType.WOODS) {
//            if (this.backstabCount %
//                    Constants.getInstance().getRogueBackstabCriticalHitOccurence() == 0) {
//                System.out.println("Critical damage de la rogue -------------");
//                totalDamage *= Constants.getInstance().getRogueBackstabCriticalHitRatio();
//            }
////            totalDamage += totalDamage * Constants.getInstance().getRogueWoodsBonus() * 0.01f;
//            totalDamage = (1f
//                    + Constants.getInstance().getRogueWoodsBonus()
//                    * 0.01f)
//                    * totalDamage;
//        }
////        totalDamage = Math.round(totalDamage);
//        int damageWithoutRaceModifier = Math.round(totalDamage);
//        if (addRaceModifier) {
//            switch (enemy.getType()) {
//                case ROGUE:
////                    totalDamage += totalDamage
////                            * (Constants.getInstance().getRogueBackstabBonusVersusRogue() / 100f);
//                    totalDamage = (1f
//                            + Constants.getInstance().getRogueBackstabBonusVersusRogue()
//                            * 0.01f)
//                            * totalDamage;
//                    break;
//                case KNIGHT:
////                    totalDamage += totalDamage
////                            * Constants.getInstance().getRogueBackstabBonusVersusKnight() / 100f;
//                    totalDamage = (1f
//                            + Constants.getInstance().getRogueBackstabBonusVersusKnight()
//                            * 0.01f)
//                            * totalDamage;
//                    break;
//                case WIZARD:
////                    totalDamage += totalDamage
////                            * Constants.getInstance().getRogueBackstabBonusVersusWizard() / 100f;
//                    totalDamage = (1f
//                            + Constants.getInstance().getRogueBackstabBonusVersusWizard()
//                            * 0.01f)
//                            * totalDamage;
//                    break;
//                case PYROMANCER:
////                    totalDamage += totalDamage
////                            * Constants.getInstance().getRogueBackstabBonusVersusPyromancer() / 100f;
//                    totalDamage = (1f
//                            + Constants.getInstance().getRogueBackstabBonusVersusPyromancer()
//                            * 0.01f)
//                            * totalDamage;
//                    break;
//                default:
//                    break;
//            }
//        }
////        totalDamage += this.getAbilityOverTime(enemy, location).get();
////        if (((enemy.getAbilityAffectedBy() == null)
////                || (enemy.getAbilityAffectedBy().getCaster() != this))
////                || (!addRaceModifier)) {
//////                && (!addRaceModifier)) {
//        totalDamage = Math.round(totalDamage);
//        if (addRaceModifier) {
////                System.out.println("aici?");
//            totalDamage += this.getAbilityOverTime(enemy, location).getOvertimeDamage();
//        } else {
////            System.out.println("total inainte  " + totalDamage);
//            totalDamage += this.getAbilityOverTime(enemy, location).getDamageWithoutRaceModifier();
////            System.out.println("total dupa " + totalDamage);
////            System.out.println("si dupa? " + totalDamage);
//
//        }
////        }
////        System.out.println("dmg de la backstab? " + totalDamage);
//        return new Ability("Backstab", Math.round(totalDamage), damageWithoutRaceModifier, this);
        return null;
    }

    @Override
    public void doRoundEndingRoutine() {
        super.doRoundEndingRoutine();
        if (appliedBackstabThisRound) {
            this.backstabCount++;
            appliedBackstabThisRound = false;
        }
    }

    public void hasAppliedBackStab() {
        this.appliedBackstabThisRound = true;
    }

    @Override
    public float getDamageWithoutRaceModifier(GameCharacter enemy, LocationType location) {
        Ability ability = this.computeDamageAgainst(enemy, location, false);
//        if (ability != null) {
//        System.out.println("de unde ma ? " + ability.getDamage());
        return ability.getDamage();
//        }
//        return 0.0f;
    }

    @Override
    public float computeInitialDamage(final LocationType location) {
        float damage = Constants.ROGUE_BACKSTAB_BASE_DAMAGE
                + Constants.ROGUE_BACKSTAB_LEVEL_SCALING_BASE_DAMAGE
                * this.getLevel();
        if (location == LocationType.WOODS) {
            if (this.backstabCount
                    % Constants.ROGUE_BACKSTAB_CRITICAL_HIT_OCCURENCE == 0) {
                damage *= Constants.ROGUE_BACKSTAB_CRITICAL_HIT_RATIO;
            }
            damage *= Constants.ROGUE_WOODS_BONUS;
        }
//        this.hasAppliedBackStab();
        return damage;
    }

    @Override
    public float computeInitialOvertimeDamage(final LocationType location) {
        float damage = Constants.ROGUE_PARALYSIS_BASE_DAMAGE
                + Constants.ROGUE_PARALYSIS_LEVEL_SCALING_BASE_DAMAGE
                * this.getLevel();
        if (location == LocationType.WOODS) {
            damage *= Constants.ROGUE_WOODS_BONUS;
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
            damage *= Constants.ROGUE_BACKSTAB_BONUS_VERSUS_WIZARD;
        }
        damage = Math.round(damage);
        if (!isForDeflectPurpose) {
            damage += Math.round(this.affectOvertime(enemy, location,
                    false, true));
            if (enemy.takeDamage(Math.round(damage), false)) {
                this.fightWon(enemy.getLevel());
            }
            this.hasAppliedBackStab();
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
            damage *= Constants.ROGUE_BACKSTAB_BONUS_VERSUS_ROGUE;
        }
        damage = Math.round(damage);
        if (!isForDeflectPurpose) {
            damage += Math.round(this.affectOvertime(enemy, location,
                    false, true));
            if (enemy.takeDamage(Math.round(damage), false)) {
                this.fightWon(enemy.getLevel());
            }
            this.hasAppliedBackStab();
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
            damage *= Constants.ROGUE_BACKSTAB_BONUS_VERSUS_PYROMANCER;
        }
        damage = Math.round(damage);
        if (!isForDeflectPurpose) {
            damage += Math.round(this.affectOvertime(enemy, location,
                    false, true));
            if (enemy.takeDamage(Math.round(damage), false)) {
                this.fightWon(enemy.getLevel());
            }
            this.hasAppliedBackStab();
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
            damage *= Constants.ROGUE_BACKSTAB_BONUS_VERSUS_KNIGHT;
        }
        damage = Math.round(damage);
        if (!isForDeflectPurpose) {
            damage += Math.round(this.affectOvertime(enemy, location,
                    false, true));
            if (enemy.takeDamage(Math.round(damage), false)) {
                this.fightWon(enemy.getLevel());
            }
            this.hasAppliedBackStab();
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
        float damage = computeInitialOvertimeDamage(location);
        if (addRaceModifier) {
            damage *= Constants.ROGUE_PARALYSIS_BONUS_VERSUS_WIZARD;
        }
        if (startNow) {
            int duration;
            if (location == LocationType.WOODS) {
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
    public float affectOvertime(final Rogue enemy,
                                final LocationType location,
                                final boolean startNow,
                                final boolean addRaceModifier) {
        float damage = computeInitialOvertimeDamage(location);
        if (addRaceModifier) {
            damage *= Constants.ROGUE_PARALYSIS_BONUS_VERSUS_ROGUE;
        }
        if (startNow) {
            int duration;
            if (location == LocationType.WOODS) {
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
    public float affectOvertime(final Knight enemy,
                                final LocationType location,
                                final boolean startNow,
                                final boolean addRaceModifier) {
        float damage = computeInitialOvertimeDamage(location);
        if (addRaceModifier) {
            damage *= Constants.ROGUE_PARALYSIS_BONUS_VERSUS_KNIGHT;
        }
        if (startNow) {
            int duration;
            if (location == LocationType.WOODS) {
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
    public float affectOvertime(final Pyromancer enemy,
                                final LocationType location,
                                final boolean startNow,
                                final boolean addRaceModifier) {
        float damage = computeInitialOvertimeDamage(location);
        if (addRaceModifier) {
            damage *= Constants.ROGUE_PARALYSIS_BONUS_VERSUS_PYROMANCER;
        }
        if (startNow) {
            int duration;
            if (location == LocationType.WOODS) {
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
}

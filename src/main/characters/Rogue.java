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
        super(initCol, initLin, Constants.getInstance().getRogueInitialHealth(), 0,
                CharacterType.ROGUE, "R");
        this.backstabCount = 0;
        this.appliedBackstabThisRound = false;
    }

    private float getBackstabDamage() {
        return Constants.getInstance().getRogueBackstabBaseDamage()
                + Constants.getInstance().getRogueBackstabBaseDamage()
                * Constants.getInstance().getRogueBackstabLevelScalingBaseDamage() / 100f
                * this.getLevel();
    }

    @Override
    public OverTimeAbility getAbilityOverTime(final GameCharacter enemy,
                                              final LocationType location) {
        OverTimeAbility paralysis = new OverTimeAbility(this, enemy, "Paralysis", location);
        paralysis.setAbilityToIncapacitate(true);
        paralysis.setOvertimeDamage(Constants.getInstance().getRogueParalysisBaseDamage()
                + Constants.getInstance().getRogueParalysisLevelScalingBaseDamage()
                * this.getLevel());
        if (location == LocationType.WOODS) {
            paralysis.setDuration(Constants.getInstance().getRogueParalysisRoundsWoodsNumber() - 1);
            paralysis.setOvertimeDamage(Math.round(paralysis.getOvertimeDamage()
                    + paralysis.getOvertimeDamage()
                    * Constants.getInstance().getRogueWoodsBonus()
                    / 100f));
        } else {
            paralysis.setDuration(Constants.getInstance().getRogueParalysisRoundsNormalNumber() - 1);
        }
//        System.out.println("----- " + paralysis.getOvertimeDamage());
        int damageWithoutRaceModifier = paralysis.getOvertimeDamage();
        paralysis.setDamageWithoutRaceModifier(damageWithoutRaceModifier);
        switch (enemy.getType()) {
            case ROGUE:
                paralysis.setOvertimeDamage(Math.round(paralysis.getOvertimeDamage()
                        + paralysis.getOvertimeDamage()
                        * Constants.getInstance().getRogueParalysisBonusVersusRogue() / 100f));
                break;
            case KNIGHT:
                paralysis.setOvertimeDamage(Math.round(paralysis.getOvertimeDamage()
                        + paralysis.getOvertimeDamage()
                        * Constants.getInstance().getRogueParalysisBonusVersusKnight() / 100f));
                break;
            case WIZARD:
                paralysis.setOvertimeDamage(Math.round(paralysis.getOvertimeDamage()
                        + paralysis.getOvertimeDamage()
                        * Constants.getInstance().getRogueParalysisBonusVersusWizard() / 100f));
                break;
            case PYROMANCER:
                paralysis.setOvertimeDamage(Math.round(paralysis.getOvertimeDamage()
                        + paralysis.getOvertimeDamage()
                        * Constants.getInstance().getRogueParalysisBonusVersusPyromancer() / 100f));
                break;
            default:
                break;
        }
//        enemy.takeDamage(new Ability("Paralysis", paralysis.getOvertimeDamage(), damageWithoutRaceModifier),
//                this, location, true);
        return paralysis;
    }

    @Override
    public int getMaxHealth() {
        return Constants.getInstance().getRogueInitialHealth()
                + Constants.getInstance().getRogueHealthRatio()
                * this.getLevel();
    }

    public void increaseBackstabCount() {
        this.backstabCount++;
    }

    @Override
    public Ability computeDamageAgainst(final GameCharacter enemy,
                                        final LocationType location,
                                        final boolean addRaceModifier) {
        float totalDamage = 0.0f;
        totalDamage += getBackstabDamage();
//        this.backstabCount++;
        if (location == LocationType.WOODS) {
            if (this.backstabCount %
                    Constants.getInstance().getRogueBackstabCriticalHitOccurence() == 0) {
                System.out.println("Critical damage de la rogue -------------");
                totalDamage *= Constants.getInstance().getRogueBackstabCriticalHitRatio();
            }
//                if (!addRaceModifier) {
////                    this.backstabCount++;
//                }
//            }
////            } else {
////                this.backstabCount++;
////            }
            totalDamage += totalDamage * Constants.getInstance().getRogueWoodsBonus() / 100f;
        }
//        totalDamage = Math.round(totalDamage);
        int damageWithoutRaceModifier = Math.round(totalDamage);
        if (addRaceModifier) {
            switch (enemy.getType()) {
                case ROGUE:
                    totalDamage += totalDamage
                            * (Constants.getInstance().getRogueBackstabBonusVersusRogue() / 100f);
                    break;
                case KNIGHT:
                    totalDamage += totalDamage
                            * Constants.getInstance().getRogueBackstabBonusVersusKnight() / 100f;
                    break;
                case WIZARD:
                    totalDamage += totalDamage
                            * Constants.getInstance().getRogueBackstabBonusVersusWizard() / 100f;
                    break;
                case PYROMANCER:
                    totalDamage += totalDamage
                            * Constants.getInstance().getRogueBackstabBonusVersusPyromancer() / 100f;
                    break;
                default:
                    break;
            }
        }
//        totalDamage += this.getAbilityOverTime(enemy, location).get();
//        if (((enemy.getAbilityAffectedBy() == null)
//                || (enemy.getAbilityAffectedBy().getCaster() != this))
//                || (!addRaceModifier)) {
////                && (!addRaceModifier)) {
        if (addRaceModifier) {
//                System.out.println("aici?");
            totalDamage += this.getAbilityOverTime(enemy, location).getOvertimeDamage();
        } else {
//            System.out.println("total inainte  " + totalDamage);
            totalDamage += this.getAbilityOverTime(enemy, location).getDamageWithoutRaceModifier();
//            System.out.println("total dupa " + totalDamage);
        }
//        }
//        System.out.println("dmg de la backstab? " + totalDamage);

        return new Ability("Backstab", Math.round(totalDamage), damageWithoutRaceModifier, this);
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
}

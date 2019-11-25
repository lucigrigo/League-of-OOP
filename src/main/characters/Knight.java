package main.characters;

import main.data.Constants;
import main.data.LocationType;
import main.gameplay.Ability;
import main.gameplay.OverTimeAbility;

public class Knight extends GameCharacter {

    public Knight(final int initCol,
                  final int initLin) {
        super(initCol, initLin, Constants.getInstance().getKnightInitialHealth(), 0,
                CharacterType.KNIGHT, "K");
    }

    @Override
    public int getMaxHealth() {
        return Constants.getInstance().getKnightInitialHealth()
                + Constants.getInstance().getKnightHealthRatio()
                * this.getLevel();
    }

    @Override
    public Ability computeDamageAgainst(final GameCharacter enemy,
                                        final LocationType location,
                                        final boolean addRaceModifier) {
        float executeLimit = Constants.getInstance().getKnightExecuteHpLimitPercentage();
        executeLimit += this.getLevel();
        executeLimit *= enemy.getHealth();
        executeLimit *= 0.01f;
        if ((enemy.getHealth()
                <= Math.round(executeLimit))
                && (!enemy.isDead())) {
            enemy.hasDied();
            this.fightWon(enemy.getLevel());
            return new Ability("Execute", enemy.getHealth(), enemy.getHealth());
        }
        float totalDamage = Constants.getInstance().getKnightExecuteBaseDamage()
                + Constants.getInstance().getKnightExecuteLevelScalingBaseDamage()
                * this.getLevel();
        if (location == LocationType.LAND) {
//            totalDamage = totalDamage
//                    + totalDamage
//                    * Constants.getInstance().getKnightLandBonus()
//                    * 0.01f;
            totalDamage = (1f
                    + Constants.getInstance().getKnightLandBonus()
                    * 0.01f)
                    * totalDamage;
        }
        int damageWithoutRaceModifier = Math.round(totalDamage);
        if (addRaceModifier) {
            switch (enemy.getType()) {
                case ROGUE:
//                    totalDamage = totalDamage
//                            + totalDamage
//                            * Constants.getInstance().getKnightExecuteBonusVersusRogue()
//                            * 0.01f;
                    totalDamage = (1f
                            + Constants.getInstance().getKnightExecuteBonusVersusRogue()
                            * 0.01f)
                            * totalDamage;
                    break;
                case KNIGHT:
//                    totalDamage = totalDamage
//                            + totalDamage
//                            * Constants.getInstance().getKnightExecuteBonusVersusKnight()
//                            * 0.01f;
                    totalDamage = (1f
                            + Constants.getInstance().getKnightExecuteBonusVersusKnight()
                            * 0.01f)
                            * totalDamage;
                    break;
                case WIZARD:
//                    totalDamage = totalDamage
//                            + totalDamage
//                            * Constants.getInstance().getKnightExecuteBonusVersusWizard()
//                            * 0.01f;
                    totalDamage = (1f
                            + Constants.getInstance().getKnightExecuteBonusVersusWizard()
                            * 0.01f)
                            * totalDamage;
                    break;
                case PYROMANCER:
//                    totalDamage = totalDamage
//                            + totalDamage
//                            * Constants.getInstance().getKnightExecuteBonusVersusPyromancer()
//                            * 0.01f;
                    totalDamage = (1f
                            + Constants.getInstance().getKnightExecuteBonusVersusPyromancer()
                            * 0.01f)
                            * totalDamage;
                    break;
                default:
                    break;
            }
        }
        totalDamage = Math.round(totalDamage);
        if (addRaceModifier) {
            totalDamage += this.getAbilityOverTime(enemy, location)
                    .getTotalDamage();
        } else {
            totalDamage += this.getAbilityOverTime(enemy, location)
                    .getDamageWithoutRaceModifier();
        }
        return new Ability("Execute", Math.round(totalDamage), damageWithoutRaceModifier);
    }

    @Override
    public void doRoundEndingRoutine() {
        super.doRoundEndingRoutine();
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
    public OverTimeAbility getAbilityOverTime(final GameCharacter enemy,
                                              final LocationType location) {

        OverTimeAbility slam = new OverTimeAbility(this, enemy, "Slam", location);
        slam.setAbilityToIncapacitate(true);
        slam.setDuration(1);
        slam.setCaster(this);
        float overtimeDamage = Constants.getInstance().getKnightSlamBaseDamage()
                + Constants.getInstance().getKnightSlamLevelScalingBaseDamage()
                * this.getLevel();
//        slam.setOvertimeDamage(Constants.getInstance().getKnightSlamBaseDamage()
//                + Constants.getInstance().getKnightSlamLevelScalingBaseDamage()
//                * this.getLevel());
        if (location == LocationType.LAND) {
//            slam.setOvertimeDamage(Math.round(slam.getOvertimeDamage()
//                    + slam.getOvertimeDamage()
//                    * Constants.getInstance().getKnightLandBonus()
//                    * 0.01f));
            overtimeDamage = (1f
                    + Constants.getInstance().getKnightLandBonus()
                    * 0.01f)
                    * overtimeDamage;
        }
        int damageWithoutRaceModifier = Math.round(overtimeDamage);
        switch (enemy.getType()) {
            case ROGUE:
//                slam.setOvertimeDamage(Math.round(slam.getOvertimeDamage()
//                        + slam.getOvertimeDamage()
//                        * Constants.getInstance().getKnightSlamBonusVersusRogue()
//                        * 0.01f));
                overtimeDamage = (1f
                        + Constants.getInstance().getKnightSlamBonusVersusRogue()
                        * 0.01f)
                        * overtimeDamage;
                break;
            case KNIGHT:
//                slam.setOvertimeDamage(Math.round(slam.getOvertimeDamage()
//                        + slam.getOvertimeDamage()
//                        * Constants.getInstance().getKnightSlamBonusVersusKnight()
//                        * 0.01f));
                overtimeDamage = (1f
                        + Constants.getInstance().getKnightSlamBonusVersusKnight()
                        * 0.01f)
                        * overtimeDamage;
                break;
            case PYROMANCER:
//                slam.setOvertimeDamage(Math.round(slam.getOvertimeDamage()
//                        + slam.getOvertimeDamage()
//                        * Constants.getInstance().getKnightSlamBonusVersusPyromancer()
//                        * 0.01f));
                overtimeDamage = (1f
                        + Constants.getInstance().getKnightSlamBonusVersusPyromancer()
                        * 0.01f)
                        * overtimeDamage;
                break;
            case WIZARD:
//                slam.setOvertimeDamage(Math.round(slam.getOvertimeDamage()
//                        + slam.getOvertimeDamage()
//                        * Constants.getInstance().getKnightSlamBonusVersusWizard()
//                        * 0.01f));
                overtimeDamage = (1f
                        + Constants.getInstance().getKnightSlamBonusVersusWizard()
                        * 0.01f)
                        * overtimeDamage;
                break;
            default:
                break;
        }
        slam.setDamageWithoutRaceModifier(damageWithoutRaceModifier);
        slam.setTotalDamage(Math.round(overtimeDamage));
//        slam.setTotalDamage(slam.getOvertimeDamage());
        slam.setFirstRound(true);
        slam.setOvertimeDamage(0);
        return slam;
    }
}

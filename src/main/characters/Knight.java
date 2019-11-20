package main.characters;

import main.data.Constants;
import main.data.LocationType;
import main.gameplay.OverTimeAbility;

public class Knight extends GameCharacter {

    public Knight(final int initCol,
                  final int initLin) {
        super(initCol, initLin, Constants.getInstance().getKnightInitialHealth(), 0,
                CharacterType.KNIGHT, "K");
    }

    @Override
    public int getTotalOverTimeDamage(final LocationType location,
                                      final GameCharacter enemy,
                                      final int roundsRemaining) {
        int totalOverTimeDamage = Constants.getInstance().getKnightSlamBaseDamage()
                + Constants.getInstance().getKnightSlamLevelScalingBaseDamage()
                * this.getLevel();
        if (location == LocationType.LAND) {
            totalOverTimeDamage = Math.round(totalOverTimeDamage
                    + totalOverTimeDamage
                    * Constants.getInstance().getKnightLandBonus() / 100f);
        }
        float raceBonus = 0.0f;
        switch (enemy.getType()) {
            case ROGUE:
                raceBonus = Constants.getInstance().getKnightSlamBonusVersusRogue() / 100f;
                break;
            case WIZARD:
                raceBonus = Constants.getInstance().getKnightSlamBonusVersusWizard() / 100f;
                break;
            case PYROMANCER:
                raceBonus = Constants.getInstance().getKnightSlamBonusVersusPyromancer() / 100f;
                break;
            case KNIGHT:
                raceBonus = Constants.getInstance().getKnightSlamBonusVersusKnight() / 100f;
                break;
            default:
                break;
        }
        totalOverTimeDamage = Math.round(totalOverTimeDamage
                + totalOverTimeDamage
                * raceBonus);
        return totalOverTimeDamage;
    }

    @Override
    public int computeDamageAgainst(final GameCharacter enemy,
                                    final LocationType location) {
        float executeLimit = Constants.getInstance().getKnightExecuteHpLimitPercentage();
        executeLimit += this.getLevel();
        executeLimit *= enemy.getHealth();
        executeLimit /= 100f;
        if (enemy.getHealth() <= Math.round(executeLimit)) {
            enemy.hasDied();
            this.fightWon(enemy.getLevel());
            return 0;
        }
        float totalDamage = Math.round(Constants.getInstance().getKnightExecuteBaseDamage()
                + Constants.getInstance().getKnightExecuteLevelScalingBaseDamage()
                * this.getLevel());
        if (location == LocationType.LAND) {
            totalDamage = Math.round(totalDamage
                    + totalDamage
                    * Constants.getInstance().getKnightLandBonus()
                    / 100f);
        }
        switch (enemy.getType()) {
            case ROGUE:
                totalDamage = totalDamage
                        + totalDamage
                        * Constants.getInstance().getKnightExecuteBonusVersusRogue() / 100f;
                break;
            case KNIGHT:
                totalDamage = totalDamage
                        + totalDamage
                        * Constants.getInstance().getKnightExecuteBonusVersusKnight() / 100f;
                break;
            case WIZARD:
                totalDamage = totalDamage
                        + totalDamage
                        * Constants.getInstance().getKnightExecuteBonusVersusWizard() / 100f;
                break;
            case PYROMANCER:
                totalDamage = totalDamage
                        + totalDamage
                        * Constants.getInstance().getKnightExecuteBonusVersusPyromancer() / 100f;
                break;
            default:
                break;
        }

        return Math.round(totalDamage);
    }

    @Override
    public OverTimeAbility getAbilityOverTime(final GameCharacter enemy,
                                              final LocationType location) {

        OverTimeAbility slam = new OverTimeAbility(this, enemy);
        slam.setAbilityToIncapacitate(true);
        slam.setDuration(1);
        slam.setOvertimeDamage(Constants.getInstance().getKnightSlamBaseDamage()
                + Constants.getInstance().getKnightSlamLevelScalingBaseDamage()
                * this.getLevel());
        if (location == LocationType.LAND) {
            slam.setOvertimeDamage(Math.round(slam.getOvertimeDamage()
                    + slam.getOvertimeDamage()
                    * Constants.getInstance().getKnightLandBonus()
                    / 100f));
        }
        switch (enemy.getType()) {
            case ROGUE:
                slam.setOvertimeDamage(Math.round(slam.getOvertimeDamage()
                        + slam.getOvertimeDamage()
                        * Constants.getInstance().getKnightSlamBonusVersusRogue()
                        / 100f));
                break;
            case KNIGHT:
                slam.setOvertimeDamage(Math.round(slam.getOvertimeDamage()
                        + slam.getOvertimeDamage()
                        * Constants.getInstance().getKnightSlamBonusVersusKnight()
                        / 100f));
                break;
            case PYROMANCER:
                slam.setOvertimeDamage(Math.round(slam.getOvertimeDamage()
                        + slam.getOvertimeDamage()
                        * Constants.getInstance().getKnightSlamBonusVersusPyromancer()
                        / 100f));
                break;
            case WIZARD:
                slam.setOvertimeDamage(Math.round(slam.getOvertimeDamage()
                        + slam.getOvertimeDamage()
                        * Constants.getInstance().getKnightSlamBonusVersusWizard()
                        / 100f));
                break;
            default:
                break;
        }
        return slam;
    }
}

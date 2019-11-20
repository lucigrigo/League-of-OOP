package main.characters;

import main.data.Constants;
import main.data.LocationType;
import main.gameplay.OverTimeAbility;

public class Rogue extends GameCharacter {

    private int backstabCount;

    public Rogue(final int initCol,
                 final int initLin) {
        super(initCol, initLin, Constants.getInstance().getRogueInitialHealth(), 0,
                CharacterType.ROGUE, "R");
        this.backstabCount = 0;
    }

    @Override
    public int getTotalOverTimeDamage(final LocationType location,
                                      final GameCharacter enemy,
                                      final int roundsRemaining) {
        int totalOverTimeDamage = Constants.getInstance().getRogueParalysisBaseDamage()
                + Constants.getInstance().getRogueParalysisLevelScalingBaseDamage()
                * this.getLevel();
        if (location == LocationType.WOODS) {
            totalOverTimeDamage = Math.round(totalOverTimeDamage
                    + totalOverTimeDamage
                    * Constants.getInstance().getRogueWoodsBonus());
            totalOverTimeDamage *= 6;
        } else {
            totalOverTimeDamage *= 3;
        }
        float raceBonus = 0.0f;
        switch (enemy.getType()) {
            case ROGUE:
                raceBonus = Constants.getInstance().getRogueParalysisBonusVersusRogue() / 100f;
                break;
            case KNIGHT:
                raceBonus = Constants.getInstance().getRogueParalysisBonusVersusKnight() / 100f;
                break;
            case WIZARD:
                raceBonus = Constants.getInstance().getRogueParalysisBonusVersusWizard() / 100f;
                break;
            case PYROMANCER:
                raceBonus = Constants.getInstance().getRogueParalysisBonusVersusPyromancer() / 100f;
                break;
            default:
                break;
        }
        totalOverTimeDamage = Math.round(totalOverTimeDamage
                + totalOverTimeDamage
                * raceBonus);
        return totalOverTimeDamage;
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
        OverTimeAbility paralysis = new OverTimeAbility(this, enemy);
        paralysis.setAbilityToIncapacitate(true);
        paralysis.setOvertimeDamage(Constants.getInstance().getRogueParalysisBaseDamage()
                + Constants.getInstance().getRogueParalysisLevelScalingBaseDamage()
                * this.getLevel());
        if (location == LocationType.WOODS) {
            paralysis.setDuration(Constants.getInstance().getRogueParalysisRoundsWoodsNumber());
            paralysis.setOvertimeDamage(Math.round(paralysis.getOvertimeDamage()
                    + paralysis.getOvertimeDamage()
                    * Constants.getInstance().getRogueWoodsBonus()
                    / 100f));
        } else {
            paralysis.setDuration(Constants.getInstance().getRogueParalysisRoundsNormalNumber());
        }
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
        return paralysis;
    }

    @Override
    public int computeDamageAgainst(final GameCharacter enemy,
                                    final LocationType location) {
        float totalDamage = 0.0f;
        totalDamage += getBackstabDamage();
        if (location == LocationType.WOODS) {
            if (this.backstabCount ==
                    Constants.getInstance().getRogueBackstabCriticalHitOccurence()) {
                totalDamage *= Constants.getInstance().getRogueBackstabCriticalHitRatio();
                this.backstabCount = 0;
            }
            totalDamage += totalDamage * Constants.getInstance().getRogueWoodsBonus() / 100f;
        }
        totalDamage = Math.round(totalDamage);
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
        return Math.round(totalDamage);
    }
}

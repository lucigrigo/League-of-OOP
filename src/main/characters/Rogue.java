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
        return (Constants.getInstance().getRogueParalysisBaseDamage()
                + Constants.getInstance().getRogueParalysisLevelScalingBaseDamage()
                * this.getLevel())
                * Math.min(roundsRemaining, location == LocationType.WOODS ? 6 : 3);
    }

    private float getBackstabDamage() {
        return Constants.getInstance().getRogueBackstabBaseDamage() +
                Constants.getInstance().getRogueBackstabBaseDamage() *
                        Constants.getInstance().getRogueBackstabLevelScalingBaseDamage() / 100f *
                        this.getLevel();
    }

    @Override
    public OverTimeAbility getAbilityOverTime(final GameCharacter enemy,
                                              final LocationType location) {
        OverTimeAbility paralysis = new OverTimeAbility(this, enemy);
        paralysis.setAbilityToIncapacitate(true);
        paralysis.setDamage(Constants.getInstance().getRogueParalysisBaseDamage() +
                Constants.getInstance().getRogueParalysisLevelScalingBaseDamage() *
                        this.getLevel());
        if (location == LocationType.WOODS) {
            paralysis.setDuration(Constants.getInstance().getRogueParalysisRoundsWoodsNumber());
            paralysis.setDamage(Math.round(paralysis.getDamage() +
                    paralysis.getDamage() *
                            Constants.getInstance().getRogueWoodsBonus() / 100f));
        } else {
            paralysis.setDuration(Constants.getInstance().getRogueParalysisRoundsNormalNumber());
        }
        switch (enemy.getType()) {
            case ROGUE:
//                System.out.println(paralysis.getDamage());
                paralysis.setDamage(Math.round(paralysis.getDamage() +
                        paralysis.getDamage() *
                                Constants.getInstance().getRogueParalysisBonusVersusRogue() / 100f));
//                System.out.println(paralysis.getDamage());
                break;
            case KNIGHT:
                paralysis.setDamage(Math.round(paralysis.getDamage() +
                        paralysis.getDamage() *
                                Constants.getInstance().getRogueParalysisBonusVersusKnight() / 100f));
                break;
            case WIZARD:
                paralysis.setDamage(Math.round(paralysis.getDamage() +
                        paralysis.getDamage() *
                                Constants.getInstance().getRogueParalysisBonusVersusWizard() / 100f));
                break;
            case PYROMANCER:
                paralysis.setDamage(Math.round(paralysis.getDamage() +
                        paralysis.getDamage() *
                                Constants.getInstance().getRogueParalysisBonusVersusPyromancer() / 100f));
                break;
            default:
                break;
        }
        return paralysis;
    }

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
                totalDamage += totalDamage * (Constants.getInstance().getRogueBackstabBonusVersusRogue() / 100f);
                break;
            case KNIGHT:
                totalDamage += totalDamage * Constants.getInstance().getRogueBackstabBonusVersusKnight() / 100f;
                break;
            case WIZARD:
                totalDamage += totalDamage * Constants.getInstance().getRogueBackstabBonusVersusWizard() / 100f;
                break;
            case PYROMANCER:
                totalDamage += totalDamage * Constants.getInstance().getRogueBackstabBonusVersusPyromancer() / 100f;
                break;
            default:
                break;
        }
//        System.out.println(totalDamage);

        return Math.round(totalDamage);
    }
}

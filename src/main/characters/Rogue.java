package main.characters;

import main.data.Constants;
import main.data.LocationType;

public class Rogue extends GameCharacter {

    private int backstabCount;

    public Rogue(final int initCol,
                 final int initLin) {
        super(initCol, initLin, Constants.getInstance().getRogueInitialHealth(), 0,
                CharacterType.ROGUE, "R");
        this.backstabCount = 0;
    }

    private float getBackstabDamage() {
        return Constants.getInstance().getRogueBackstabBaseDamage() *
                Constants.getInstance().getRogueBackstabLevelScalingBaseDamage() / 100f *
                this.getExperience();
    }

    @Override
    public OverTimeAbility getAbilityOverTime(final GameCharacter enemy,
                                              final LocationType location) {
        OverTimeAbility paralysis = new OverTimeAbility(this.getLevel());
        paralysis.setAbilityToIncapacitate(true);
        if (location == LocationType.WOODS) {
            paralysis.setDuration(Constants.getInstance().getRogueParalysisRoundsWoodsNumber());
        } else {
            paralysis.setDuration(Constants.getInstance().getRogueParalysisRoundsNormalNumber());
        }
        paralysis.setDamage(Constants.getInstance().getRogueParalysisBaseDamage() +
                Constants.getInstance().getRogueParalysisLevelScalingBaseDamage() *
                        this.getLevel());
        switch (enemy.getType()) {
            case ROGUE:
                paralysis.setDamage(paralysis.getDamage() *
                        Constants.getInstance().getRogueBackstabBonusVersusRogue() / 100f);
                break;
            case KNIGHT:
                paralysis.setDamage(paralysis.getDamage() *
                        Constants.getInstance().getRogueBackstabBonusVersusKnight() / 100f);
                break;
            case WIZARD:
                paralysis.setDamage(paralysis.getDamage() *
                        Constants.getInstance().getRogueBackstabBonusVersusWizard() / 100f);
                break;
            case PYROMANCER:
                paralysis.setDamage(paralysis.getDamage() *
                        Constants.getInstance().getRogueBackstabBonusVersusPyromancer() / 100f);
                break;
            default:
                break;
        }
        return paralysis;
    }

    public float computeDamageAgainst(final GameCharacter enemy,
                                      final LocationType location) {
        float totalDamage = 0.0f;
        totalDamage += getBackstabDamage();
        if (location == LocationType.WOODS) {
            if (this.backstabCount ==
                    Constants.getInstance().getRogueBackstabCriticalHitOccurence()) {
                totalDamage *= Constants.getInstance().getRogueBackstabCriticalHitRatio();
                this.backstabCount = 0;
            }
            totalDamage *= (Constants.getInstance().getRogueWoodsBonus() / 100f);
        }
        switch (enemy.getType()) {
            case ROGUE:
                totalDamage *= Constants.getInstance().getRogueBackstabBonusVersusRogue() / 100f;
                break;
            case KNIGHT:
                totalDamage *= Constants.getInstance().getRogueBackstabBonusVersusKnight() / 100f;
                break;
            case WIZARD:
                totalDamage *= Constants.getInstance().getRogueBackstabBonusVersusWizard() / 100f;
                break;
            case PYROMANCER:
                totalDamage *=
                        Constants.getInstance().getRogueBackstabBonusVersusPyromancer() / 100f;
                break;
            default:
                break;
        }
        totalDamage += getAbilityOverTime(enemy, location).getDamage();
        return totalDamage;
    }
}

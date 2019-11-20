package main.characters;

import main.data.Constants;
import main.data.LocationType;
import main.gameplay.OverTimeAbility;

public class Pyromancer extends GameCharacter {

    public Pyromancer(final int initCol,
                      final int initLin) {
        super(initCol, initLin, Constants.getInstance().getPyromancerInitialHealth(), 0,
                CharacterType.PYROMANCER, "P");
    }

    @Override
    public int getTotalOverTimeDamage(LocationType location, GameCharacter enemy,
                                      int roundsRemaining) {
        // TODO decide wheter this is important or not
        return 0;
    }

    @Override
    public int computeDamageAgainst(final GameCharacter enemy,
                                    final LocationType location) {
        int totalDamage = Constants.getInstance().getPyromancerFireblastBaseDamage()
                + Constants.getInstance().getPyromancerFireblastLevelScalingBaseDamage()
                * Constants.getInstance().getPyromancerFireblastBaseDamage();
        if (location == LocationType.VOLCANIC) {
            totalDamage = Math.round(totalDamage
                    + totalDamage
                    * Constants.getInstance().getPyromancerVolcanicBonus()
                    / 100f);
        }
        float raceBonus = 0.0f;
        switch (enemy.getType()) {
            case PYROMANCER:
                raceBonus = Constants.getInstance().getPyromancerFireblastBonusVersusPyromancer();
                break;
            case ROGUE:
                raceBonus = Constants.getInstance().getPyromancerFireblastBonusVersusRogue();
                break;
            case KNIGHT:
                raceBonus = Constants.getInstance().getPyromancerFireblastBonusVersusKnight();
                break;
            case WIZARD:
                raceBonus = Constants.getInstance().getPyromancerFireblastBonusVersusWizard();
                break;
            default:
                break;
        }
        totalDamage = Math.round(totalDamage
                + totalDamage
                * raceBonus
                / 100f);
        return totalDamage;
    }

    @Override
    public OverTimeAbility getAbilityOverTime(GameCharacter enemy, LocationType location) {
        OverTimeAbility ignite = new OverTimeAbility(this, enemy);
        ignite.setDuration(3);
        ignite.setAbilityToIncapacitate(false);
        int igniteInstantDamage = Constants.getInstance().getPyromancerIgniteBaseDamage()
                + Constants.getInstance().getPyromancerIgniteLevelScalingBaseDamage()
                * this.getLevel();
        int igniteSuccesiveDamage = Constants.getInstance().getPyromancerIgniteRoundDamage()
                + Constants.getInstance().getPyromancerIgniteSuccessiveRoundsNumber()
                * this.getLevel();
        if (location == LocationType.VOLCANIC) {
            igniteInstantDamage = Math.round(igniteInstantDamage
                    + igniteInstantDamage
                    * Constants.getInstance().getPyromancerVolcanicBonus()
                    / 100f);
            igniteSuccesiveDamage = Math.round(igniteSuccesiveDamage
                    + igniteSuccesiveDamage
                    * Constants.getInstance().getPyromancerVolcanicBonus()
                    / 100f);
        }
        float raceBonus = 0.0f;
        switch (enemy.getType()) {
            case KNIGHT:
                raceBonus = Constants.getInstance().getPyromancerIgniteBonusVersusKnight();
                break;
            case PYROMANCER:
                raceBonus = Constants.getInstance().getPyromancerIgniteBonusVersusPyromancer();
                break;
            case ROGUE:
                raceBonus = Constants.getInstance().getPyromancerIgniteBonusVersusRogue();
                break;
            case WIZARD:
                raceBonus = Constants.getInstance().getPyromancerIgniteBonusVersusWizard();
                break;
            default:
                break;
        }
        raceBonus /= 100f;
        igniteInstantDamage = Math.round(igniteInstantDamage
                + igniteInstantDamage
                * raceBonus);
        igniteSuccesiveDamage = Math.round(igniteSuccesiveDamage
                + igniteSuccesiveDamage
                * raceBonus);
        ignite.setInstantDamage(igniteInstantDamage);
        ignite.setOvertimeDamage(igniteSuccesiveDamage);
        return ignite;
    }
}

package main.characters;

import main.data.Constants;
import main.data.LocationType;
import main.gameplay.Ability;
import main.gameplay.OverTimeAbility;

public class Pyromancer extends GameCharacter {

    public Pyromancer(final int initCol,
                      final int initLin) {
        super(initCol, initLin, Constants.getInstance().getPyromancerInitialHealth(), 0,
                CharacterType.PYROMANCER, "P");
    }

    @Override
    public int getMaxHealth() {
        return Constants.getInstance().getPyromancerInitialHealth()
                + Constants.getInstance().getPyromancerHealthRatio()
                * this.getLevel();
    }

    @Override
    public Ability computeDamageAgainst(final GameCharacter enemy,
                                        final LocationType location,
                                        final boolean addRaceModifier) {

        float totalDamage = Constants.getInstance().getPyromancerFireblastBaseDamage()
                + Constants.getInstance().getPyromancerFireblastLevelScalingBaseDamage()
                * this.getLevel();
//        System.out.println(totalDamage);
        if (location == LocationType.VOLCANIC) {
            totalDamage = totalDamage
                    + totalDamage
                    * Constants.getInstance().getPyromancerVolcanicBonus()
                    / 100f;
        }
//        System.out.println(totalDamage);
        int damageWithoutRaceModifier = Math.round(totalDamage);
        if (addRaceModifier) {
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
            totalDamage = totalDamage
                    + totalDamage
                    * raceBonus
                    / 100f;
        }
//        } else {
////            totalDamage = Math.round(totalDamage);
//        }
        if (addRaceModifier) {
//                System.out.println("aici?");
            totalDamage += this.getAbilityOverTime(enemy, location).getInstantDamage();
        } else {
//            System.out.println("total inainte  " + totalDamage);
            totalDamage += this.getAbilityOverTime(enemy, location).getDamageWithoutRaceModifier();
//            System.out.println("total dupa " + totalDamage);
        }
//        System.out.println(totalDamage);
        return new Ability("Fireblast", Math.round(totalDamage), damageWithoutRaceModifier);
    }

    @Override
    public void doRoundEndingRoutine() {
        super.doRoundEndingRoutine();
        // do nothing
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
        OverTimeAbility ignite = new OverTimeAbility(this, enemy, "Ignite", location);
        ignite.setDuration(1);
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
        int damageWithoutRaceModifier = igniteInstantDamage;
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
        ignite.setDamageWithoutRaceModifier(damageWithoutRaceModifier);
//        enemy.takeDamage(new Ability("Ignite", ignite.getOvertimeDamage(), damageWithoutRaceModifier),
//                this, location, true);
        return ignite;
    }
}

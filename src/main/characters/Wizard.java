package main.characters;

import main.data.Constants;
import main.data.LocationType;
import main.gameplay.Ability;
import main.gameplay.OverTimeAbility;

public class Wizard extends GameCharacter {

//    private List<Ability> abilitiesTakenThisRound;

    public Wizard(final int initCol,
                  final int initLin) {
        super(initCol, initLin, Constants.getInstance().getWizardInitialHealth(), 0,
                CharacterType.WIZARD, "W");
//        this.abilitiesTakenThisRound = new ArrayList<>();
    }

    @Override
    public int getMaxHealth() {
        return Constants.getInstance().getWizardInitialHealth()
                + Constants.getInstance().getWizardHealthRatio()
                * this.getLevel();
    }

    @Override
    public Ability computeDamageAgainst(final GameCharacter enemy,
                                        final LocationType location,
                                        final boolean addRaceModifier) {
        float percentageHealth = (Constants.getInstance().getWizardDrainBasePercentage()
                + Constants.getInstance().getWizardDrainLevelScalingPercentage()
                * this.getLevel())
                / 100f;
        if (location == LocationType.DESERT) {
            percentageHealth = percentageHealth
                    + Constants.getInstance().getWizardDesertBonus()
                    * percentageHealth
                    / 100f;
        }
        if (addRaceModifier) {
            float raceBonus = 0.0f;
            switch (enemy.getType()) {
                case WIZARD:
                    raceBonus = Constants.getInstance().getWizardDrainBonusVersusWizard();
                    break;
                case ROGUE:
                    raceBonus = Constants.getInstance().getWizardDrainBonusVersusRogue();
                    break;
                case PYROMANCER:
                    raceBonus = Constants.getInstance().getWizardDrainBonusVersusPyromancer();
                    break;
                case KNIGHT:
                    raceBonus = Constants.getInstance().getWizardDrainBonusVersusKnight();
                    break;
                default:
                    break;
            }
            raceBonus /= 100f;
            percentageHealth = (percentageHealth
                    + percentageHealth
                    * raceBonus);
        }
//        System.out.println("Wizard da damage de :" + Math.round(percentageHealth
//                * Math.min(Constants.getInstance().getWizardDrainHealthPercentage()
//                * enemy.getMaxHealth(), enemy.getHealth())));
        float totalDamage = percentageHealth
                * Math.min(Constants.getInstance().getWizardDrainHealthPercentage()
                * enemy.getMaxHealth(), enemy.getHealth());
        float enemyDamage = enemy.getDamageWithoutRaceModifier(this, location);
//        System.out.println("si ceas vrei ? " + enemyDamage);
//        totalDamage = Math.round(totalDamage);
        totalDamage += getDeflectDamage(enemy, location, enemyDamage);
//        System.out.println(totalDamage);
//        System.out.println("~~~~~~~");
//        totalDamage += enemyDamage;
//        System.out.println("asdajndaksjda " + totalDamage);
        Ability drainAndDeflect = new Ability("Drain", Math.round(totalDamage),
                0, this);
//        drainAndDeflect.setCaster(this);
        return drainAndDeflect;

    }

    @Override
    public OverTimeAbility getAbilityOverTime(final GameCharacter enemy,
                                              final LocationType location) {
        return new OverTimeAbility(this, enemy, "None", location);
    }

    private float getDeflectDamage(final GameCharacter enemy,
                                   final LocationType location,
                                   final float damage) {
//        System.out.println("damage primit de wizard " + damage);
//        float totalDamage = 0.0f;
        float damagePercent = ((float) Constants.getInstance().getWizardDeflectBasePercentage() +
                (float) Constants.getInstance().getWizardDeflectLevelScalingBasePercentage()
                        * (float) this.getLevel())
                / (float) 100;
        damagePercent = Math.min((float) 0.7, (float) damagePercent);

        if (location == LocationType.DESERT) {
            damagePercent = (float) damagePercent
                    + (float) damagePercent
                    * (float) Constants.getInstance().getWizardDesertBonus()
                    / (float) 100.0;
        }
//        System.out.println("percentaj " + damagePercent);
//                int abilityDeflectDamage = Math.round(damagePercent
//                        * ability.getDamageWithoutRaceModifier());
//        if(ability == null) {
//            System.out.println("ce caca se intampla?");
//        }
        float abilityDeflectDamage = (float) damagePercent
                * (float) damage;
        float raceModifier = (float) 0;

        switch (enemy.getType()) {
            case WIZARD:
                // nu se ajunge aici niciodata
                break;
            case KNIGHT:
                raceModifier = (float) Constants.getInstance().getWizardDeflectBonusVersusKnight();
                break;
            case PYROMANCER:
                raceModifier = (float) Constants.getInstance().getWizardDeflectBonusVersusPyromancer();
                break;
            case ROGUE:
                raceModifier = (float) Constants.getInstance().getWizardDeflectBonusVersusRogue();
                break;
            default:
                break;
        }
        raceModifier /= (float) 100.0;
//                abilityDeflectDamage = Math.round(abilityDeflectDamage
//                        + raceModifier
//                        * abilityDeflectDamage);
//        System.out.println(abilityDeflectDamage + " hai cu doamne agiuta");

        abilityDeflectDamage = Math.round((float) abilityDeflectDamage
                + (float) raceModifier
                * (float) abilityDeflectDamage);
//        System.out.println(abilityDeflectDamage + " hai cu doamne agiuta "
//                + Math.round((float) abilityDeflectDamage));
//        float x = (float) 175 * (float) 1.3;
//        System.out.println(Math.round(x));
//        totalDamage += Math.round(abilityDeflectDamage);
//                totalDamage += abilityDeflectDamage;
        return (float) abilityDeflectDamage;
    }

    @Override
    public void doRoundEndingRoutine() {
        super.doRoundEndingRoutine();
        // do nothing
    }

    @Override
    public float getDamageWithoutRaceModifier(GameCharacter enemy, LocationType location) {
        return 0.0f;
    }
}

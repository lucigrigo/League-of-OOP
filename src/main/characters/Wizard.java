package main.characters;

import main.data.Constants;
import main.data.LocationType;
import main.gameplay.Ability;
import main.gameplay.OverTimeAbility;

public class Wizard extends GameCharacter {

    public Wizard(final int initCol,
                  final int initLin) {
        super(initCol, initLin, Constants.getInstance().getWizardInitialHealth(), 0,
                CharacterType.WIZARD, "W");
    }

    @Override
    public int getTotalOverTimeDamage(final LocationType location,
                                      final GameCharacter enemy,
                                      final int roundsRemaining) {
        // TODO decide whether this is important or not
        return 0;
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
        return new Ability("Drain", Math.round(percentageHealth
                * Math.min(Constants.getInstance().getWizardDrainHealthPercentage()
                * enemy.getMaxHealth(), enemy.getHealth())), 0);

    }

    @Override
    public OverTimeAbility getAbilityOverTime(final GameCharacter enemy,
                                              final LocationType location) {
        return new OverTimeAbility(this, enemy, "None", location);
    }

//    private int reverseRaceModifier(final GameCharacter enemy,
//                                    final int initialDamage,
//                                    final String abilityName) {
//        float damageWithoutRaceModifiers = 0.0f;
//        switch (enemy.getType()) {
//            case ROGUE:
//                if (abilityName.equals("Paralysis")) {
//                    damageWithoutRaceModifiers = Math.round(initialDamage
//                            - initialDamage
//                            * Constants.getInstance().getRogueParalysisBonusVersusWizard()
//                            / 100f);
//                } else {
////                    System.out.println("aici");
//                    damageWithoutRaceModifiers = Math.round(initialDamage
//                            - initialDamage
//                            * Constants.getInstance().getRogueBackstabBonusVersusWizard()
//                            / 100f);
//                }
//                break;
//            case PYROMANCER:
//                if (abilityName.equals("Ignite")) {
//                    damageWithoutRaceModifiers = Math.round(initialDamage
//                            - initialDamage
//                            * Constants.getInstance().getPyromancerFireblastBonusVersusWizard()
//                            / 100f);
//                } else {
//                    damageWithoutRaceModifiers = Math.round(initialDamage
//                            - initialDamage
//                            * Constants.getInstance().getPyromancerIgniteBonusVersusRogue()
//                            / 100f);
//                }
//                break;
//            case KNIGHT:
//                if (abilityName.equals("Slam")) {
//                    damageWithoutRaceModifiers = Math.round(initialDamage
//                            - initialDamage
//                            * Constants.getInstance().getKnightSlamBonusVersusWizard()
//                            / 100f);
//                } else {
//                    damageWithoutRaceModifiers = Math.round(initialDamage
//                            - initialDamage
//                            * Constants.getInstance().getKnightExecuteBonusVersusWizard()
//                            / 100f);
//                }
//                break;
//            case WIZARD:
//                // doi wizard nu isi dau reciproc damage prin deflect
//                break;
//            default:
//                break;
//        }
//        return Math.round(damageWithoutRaceModifiers);
//    }

    @Override
    public void takeDamage(final Ability ability,
                           final GameCharacter enemy,
                           final LocationType location) {
        super.takeDamage(ability, enemy, location);
        if (enemy.getType() != CharacterType.WIZARD) {
            float damagePercent = (Constants.getInstance().getWizardDeflectBasePercentage() +
                    Constants.getInstance().getWizardDeflectLevelScalingBasePercentage()
                            * this.getLevel())
                    / 100f;
            damagePercent = Math.min(0.7f, damagePercent);
//            int damageTakenWithoutRaceModifier = reverseRaceModifier(enemy, ability.getDamage(),
//                    ability.getName());

//            System.out.println("------ " + ability.getName() + " " + ability.getDamageWithoutRaceModifier());
//            System.out.println("AICI " + totalDeflectDamage);
            if (location == LocationType.DESERT) {
                damagePercent = damagePercent
                        + damagePercent
                        * Constants.getInstance().getWizardDesertBonus()
                        / 100f;
            }
            int totalDeflectDamage = Math.round(damagePercent
                    * ability.getDamageWithoutRaceModifier());
            float raceModifier = 0.0f;
            switch (enemy.getType()) {
                case WIZARD:
                    // nu se ajunge aici niciodata
                    break;
                case KNIGHT:
                    raceModifier = Constants.getInstance().getWizardDeflectBonusVersusKnight();
                    break;
                case PYROMANCER:
                    raceModifier = Constants.getInstance().getWizardDeflectBonusVersusPyromancer();
                    break;
                case ROGUE:
                    raceModifier = Constants.getInstance().getWizardDeflectBonusVersusRogue();
                    break;
                default:
                    break;
            }
            raceModifier /= 100f;
            totalDeflectDamage = Math.round(totalDeflectDamage
                    + raceModifier
                    * totalDeflectDamage);
////            int damageTakenWithoutRaceModifier = reverseRaceModifier(enemy, ability.getDamage(),
////                    ability.getName());
////            int deflectDamage = Math.round(damagePercent * damageTakenWithoutRaceModifier);
//            System.out.println("damage primit " + ability.getDamageWithoutRaceModifier());
//            System.out.println("procent de la deflect " + damagePercent);
//            System.out.println("modificator de rasa " + raceModifier);
//            System.out.println("damage de la deflect " + totalDeflectDamage);
            enemy.takeDamage(new Ability("Deflect", totalDeflectDamage, 0), this, location);
        }
    }
}

package main.characters;

import main.data.Constants;
import main.data.LocationType;
import main.gameplay.Ability;
import main.gameplay.OverTimeAbility;

import java.util.ArrayList;
import java.util.List;

public class Wizard extends GameCharacter {

    private List<Ability> abilitiesTakenThisRound;

    public Wizard(final int initCol,
                  final int initLin) {
        super(initCol, initLin, Constants.getInstance().getWizardInitialHealth(), 0,
                CharacterType.WIZARD, "W");
        this.abilitiesTakenThisRound = new ArrayList<>();
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
        return new Ability("Drain", Math.round(percentageHealth
                * Math.min(Constants.getInstance().getWizardDrainHealthPercentage()
                * enemy.getMaxHealth(), enemy.getHealth())), 0);

    }

    @Override
    public OverTimeAbility getAbilityOverTime(final GameCharacter enemy,
                                              final LocationType location) {
        return new OverTimeAbility(this, enemy, "None", location);
    }

    @Override
    public void deflectDamage() {
//        if (this.isDead()) {
//            return;
//        }
        float totalDamage = 0.0f;
        GameCharacter enemy = null;
        LocationType location = null;
        for (Ability ability : this.abilitiesTakenThisRound) {
//            System.out.println(ability.getDamageWithoutRaceModifier());
            enemy = ability.getCaster();
            if ((enemy != null)
                    && (enemy.getType() != CharacterType.WIZARD)) {
                location = ability.getLocation();
                float damagePercent = (Constants.getInstance().getWizardDeflectBasePercentage() +
                        Constants.getInstance().getWizardDeflectLevelScalingBasePercentage()
                                * this.getLevel())
                        / 100f;
                damagePercent = Math.min(0.7f, damagePercent);
                if (location == LocationType.DESERT) {
                    damagePercent = damagePercent
                            + damagePercent
                            * Constants.getInstance().getWizardDesertBonus()
                            / 100f;
                }
//                int abilityDeflectDamage = Math.round(damagePercent
//                        * ability.getDamageWithoutRaceModifier());
                float abilityDeflectDamage = damagePercent
                        * ability.getDamageWithoutRaceModifier();
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
//                abilityDeflectDamage = Math.round(abilityDeflectDamage
//                        + raceModifier
//                        * abilityDeflectDamage);
                abilityDeflectDamage = abilityDeflectDamage
                        + raceModifier
                        * abilityDeflectDamage;
                totalDamage += Math.round(abilityDeflectDamage);
//                totalDamage += abilityDeflectDamage;
            }
        }
        if (enemy != null) {
            System.out.println("Damage de la deflect " + totalDamage);

            enemy.takeDamage(new Ability("Deflect", Math.round(totalDamage),
                    0), this, location, true);
        }
        this.abilitiesTakenThisRound.clear();
//        }
    }

    @Override
    public void takeDamage(final Ability ability,
                           final GameCharacter enemy,
                           final LocationType location,
                           final boolean isOvertimeAbility) {
        super.takeDamage(ability, enemy, location, isOvertimeAbility);
        if (ability != null) {
//                && !isOvertimeAbility) {
            Ability abilityTaken = new Ability(ability.getName(), ability.getDamage(),
                    ability.getDamageWithoutRaceModifier());
            abilityTaken.setLocation(location);
            abilityTaken.setCaster(enemy);
            this.abilitiesTakenThisRound.add(abilityTaken);
        }
    }
}

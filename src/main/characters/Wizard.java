package main.characters;

import main.data.Constants;
import main.data.LocationType;
import main.gameplay.OverTimeAbility;

public class Wizard extends GameCharacter {

//    private int currentHealth;
//    private int currentExperience;

//    public Wizard(final int initCol,
//                  final int initLin) {
//        super(initCol, initLin);
//        this.currentHealth = Constants.getInstance().getWizardInitialHealth();
//        this.currentExperience = 0;
//    }

    public Wizard(final int initCol,
                  final int initLin) {
        super(initCol, initLin, Constants.getInstance().getWizardInitialHealth(), 0,
                CharacterType.WIZARD, "W");
    }

    // TODO implement WIZARD


    @Override
    public int getTotalOverTimeDamage(LocationType location, GameCharacter enemy,
                                      int roundsRemaining) {
        return 0;
    }

    @Override
    public int computeDamageAgainst(GameCharacter enemy, LocationType location) {
        return 0;
    }

    @Override
    public OverTimeAbility getAbilityOverTime(GameCharacter enemy, LocationType location) {
        return null;
    }
}

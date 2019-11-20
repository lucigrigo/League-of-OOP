package main.characters;

import main.data.Constants;

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
}

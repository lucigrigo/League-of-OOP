package main.characters;

import main.data.Constants;

public class Rogue extends GameCharacter {

//    private int currentHealth;
//    private int currentExperience;

//    public Rogue(final int initCol,
//                 final int initLin) {
//        super(initCol, initLin);
//        this.currentHealth = Constants.getInstance().getRogueInitialHealth();
//        this.currentExperience = 0;
//    }

    public Rogue(final int initCol,
                 final int initLin) {
        super(initCol, initLin, Constants.getInstance().getRogueInitialHealth(), 0,"R");
    }


    // TODO implement Rogue
}

package main.characters;

import main.data.Constants;

public class Knight extends GameCharacter {

//    private int currentHealth;
//    private int currentExperience;

    public Knight(final int initCol,
                  final int initLin) {
        super(initCol, initLin, Constants.getInstance().getKnightInitialHealth(), 0);
    }

    // TODO implement KNIGHT
}

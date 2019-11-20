package main.characters;

import main.data.Constants;

public class Pyromancer extends GameCharacter {

    public Pyromancer(final int initCol,
                      final int initLin) {
        super(initCol, initLin, Constants.getInstance().getPyromancerInitialHealth(), 0,
                CharacterType.PYROMANCER, "P");
    }

    // TODO implement PYROMANCER
}

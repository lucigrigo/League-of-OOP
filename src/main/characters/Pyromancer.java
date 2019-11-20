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

    // TODO implement PYROMANCER


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

package main.characters;

import main.data.Constants;
import main.data.LocationType;
import main.gameplay.OverTimeAbility;

public class Knight extends GameCharacter {

//    private int currentHealth;
//    private int currentExperience;

    public Knight(final int initCol,
                  final int initLin) {
        super(initCol, initLin, Constants.getInstance().getKnightInitialHealth(), 0,
                CharacterType.KNIGHT, "K");
    }

    // TODO implement KNIGHT


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

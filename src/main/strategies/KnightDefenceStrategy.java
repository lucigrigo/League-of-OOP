package main.strategies;

import main.data.Constants;
import main.heroes.Hero;

public class KnightDefenceStrategy extends Strategy {

    public KnightDefenceStrategy(final Hero owner) {
        super(Constants.KNIGHT_DEFENCE_STRATEGY_DAMAGE_MODIFIER,
                Constants.KNIGHT_DEFENCE_STRATEGY_HP_MODIFIER, owner);
    }
}

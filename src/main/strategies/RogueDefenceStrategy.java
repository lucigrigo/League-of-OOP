package main.strategies;

import main.data.Constants;
import main.heroes.Hero;

public class RogueDefenceStrategy extends Strategy {

    public RogueDefenceStrategy(final Hero owner) {
        super(Constants.ROGUE_DEFENCE_STRATEGY_DAMAGE_MODIFIER,
                Constants.ROGUE_DEFENCE_STRATEGY_HP_MODIFIER, owner);
    }
}

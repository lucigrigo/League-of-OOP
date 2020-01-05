package main.strategies;

import main.data.Constants;
import main.heroes.Hero;

/**
 * Implements Attack Strategy for a Rogue.
 */
public class RogueAttackStrategy extends Strategy {

    public RogueAttackStrategy(final Hero owner) {
        super(Constants.ROGUE_ATTACK_STRATEGY_DAMAGE_MODIFIER,
                Constants.ROGUE_ATTACK_STRATEGY_HP_MODIFIER, owner);
    }
}

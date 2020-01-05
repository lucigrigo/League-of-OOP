package main.strategies;

import main.data.Constants;
import main.heroes.Hero;

/**
 * Implements Attack Strategy for a Knight.
 */
public class KnightAttackStrategy extends Strategy {

    public KnightAttackStrategy(final Hero owner) {
        super(Constants.KNIGHT_ATTACK_STRATEGY_DAMAGE_MODIFIER,
                Constants.KNIGHT_ATTACK_STRATEGY_HP_MODIFIER, owner);
    }
}

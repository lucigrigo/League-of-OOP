package main.strategies;

import main.data.Constants;
import main.heroes.Hero;

public class PyromancerAttackStrategy extends Strategy {

    public PyromancerAttackStrategy(final Hero owner) {
        super(Constants.PYROMANCER_ATTACK_STRATEGY_DAMAGE_MODIFIER,
                Constants.PYROMANCER_ATTACK_STRATEGY_HP_MODIFIER, owner);
    }
}

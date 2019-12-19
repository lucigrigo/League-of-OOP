package main.strategies;

import main.data.Constants;
import main.heroes.Hero;

public class PyromancerDefenceStrategy extends Strategy {

    public PyromancerDefenceStrategy(final Hero owner) {
        super(Constants.PYROMANCER_DEFENCE_STRATEGY_DAMAGE_MODIFIER,
                Constants.PYROMANCER_DEFENCE_STRATEGY_HP_MODIFIER, owner);
    }
}

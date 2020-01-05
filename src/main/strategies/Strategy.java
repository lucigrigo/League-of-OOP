package main.strategies;

import main.heroes.Hero;

/**
 * Class that creates a template for any type of strategy in the game.
 */
public abstract class Strategy {

    private float modifier;
    private float hpModifier;
    private Hero owner;

    Strategy(final float modifier,
             final float hpModifier,
             final Hero owner) {
        this.modifier = modifier;
        this.hpModifier = hpModifier;
        this.owner = owner;
    }

    /**
     * Applies itself to the owner.
     */
    public final void applyStrategy() {
        owner.addStrategyBonus(modifier);
        owner.increaseHP((int) Math.floor(owner.getHealth()
                * hpModifier));
    }
}


package main.strategies;

import main.data.Constants;
import main.heroes.Hero;

/**
 * Implements Attack Strategy for a Wizard.
 */
public class WizardAttackStrategy extends Strategy {

    public WizardAttackStrategy(final Hero owner) {
        super(Constants.WIZARD_ATTACK_STRATEGY_DAMAGE_MODIFIER,
                Constants.WIZARD_ATTACK_STRATEGY_HP_MODIFIER, owner);
    }
}

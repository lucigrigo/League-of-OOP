package main.strategies;

import main.data.Constants;
import main.heroes.Hero;

/**
 * Implements Defence Strategy for a Wizard.
 */
public class WizardDefenceStrategy extends Strategy {

    public WizardDefenceStrategy(final Hero owner) {
        super(Constants.WIZARD_DEFENCE_STRATEGY_DAMAGE_MODIFIER,
                Constants.WIZARD_DEFENCE_STRATEGY_HP_MODIFIER, owner);
    }
}

package main.angels;

import main.data.Constants;
import main.data.Visitor;
import main.heroes.Hero;
import main.heroes.Knight;
import main.heroes.Pyromancer;
import main.heroes.Rogue;
import main.heroes.Wizard;

/**
 * Class that implements Life Giver logic.
 */
public final class LifeGiver extends Angel implements Visitor {

    public LifeGiver(final int spawnRow,
                     final int spawnCol) {
        super(spawnRow, spawnCol, "LifeGiver");
    }

    /**
     * Life Giver interaction with a wizard.
     *
     * @param wizard influenced wizard
     */
    @Override
    public void helpHero(final Wizard wizard) {
        if (wizard.isDead()) {
            return;
        }
        computeObservation(wizard);
        wizard.increaseHP(Constants.LIFEGIVER_WIZARD_BONUS);
    }

    /**
     * Life Giver interaction with a rogue.
     *
     * @param rogue influenced rogue
     */
    @Override
    public void helpHero(final Rogue rogue) {
        if (rogue.isDead()) {
            return;
        }
        computeObservation(rogue);
        rogue.increaseHP(Constants.LIFEGIVER_ROGUE_BONUS);
    }

    /**
     * Life Giver interaction with a pyromancer.
     *
     * @param pyromancer influenced pyromancer
     */
    @Override
    public void helpHero(final Pyromancer pyromancer) {
        if (pyromancer.isDead()) {
            return;
        }
        computeObservation(pyromancer);
        pyromancer.increaseHP(Constants.LIFEGIVER_PYROMANCER_BONUS);
    }

    /**
     * Life Giver interaction with a knight.
     *
     * @param knight influenced knight
     */
    @Override
    public void helpHero(final Knight knight) {
        if (knight.isDead()) {
            return;
        }
        computeObservation(knight);
        knight.increaseHP(Constants.LIFEGIVER_KNIGHT_BONUS);
    }

    /**
     * Computing Life Giver observation for the observer.
     *
     * @param hero influenced hero
     */
    @Override
    public void computeObservation(final Hero hero) {
        String message = "LifeGiver helped " + hero.getFullName() + " "
                + hero.getIndex() + "\n";
        setChanged();
        notifyObservers(message);
    }
}

package main.angels;

import main.data.Visitor;
import main.heroes.Hero;
import main.heroes.Knight;
import main.heroes.Pyromancer;
import main.heroes.Rogue;
import main.heroes.Wizard;

/**
 * Class that implements The Doomer logic.
 */
public final class TheDoomer extends Angel implements Visitor {

    public TheDoomer(final int spawnRow,
                     final int spawnCol) {
        super(spawnRow, spawnCol, "TheDoomer");
    }

    /**
     * The Doomer killing a wizard.
     *
     * @param wizard killed wizard
     */
    @Override
    public void helpHero(final Wizard wizard) {
        if (wizard.isDead()) {
            return;
        }
        computeObservation(wizard);
        wizard.hasDied(true);
    }

    /**
     * The Doomer killing a rogue.
     *
     * @param rogue killed rogue
     */
    @Override
    public void helpHero(final Rogue rogue) {
        if (rogue.isDead()) {
            return;
        }
        computeObservation(rogue);
        rogue.hasDied(true);
    }

    /**
     * The Doomer killing a pyromancer.
     *
     * @param pyromancer killed pyromancer
     */
    @Override
    public void helpHero(final Pyromancer pyromancer) {
        if (pyromancer.isDead()) {
            return;
        }
        computeObservation(pyromancer);
        pyromancer.hasDied(true);
    }

    /**
     * The Doomer killing a knight.
     *
     * @param knight killed knight
     */
    @Override
    public void helpHero(final Knight knight) {
        if (knight.isDead()) {
            return;
        }
        computeObservation(knight);
        knight.hasDied(true);
    }

    /**
     * Computing The Doomer observation for the observer.
     *
     * @param hero killed hero
     */
    @Override
    public void computeObservation(final Hero hero) {
        String message = "TheDoomer hit " + hero.getFullName() + " "
                + hero.getIndex() + "\n";
        setChanged();
        notifyObservers(message);
    }
}

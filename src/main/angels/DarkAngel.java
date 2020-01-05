package main.angels;

import main.data.Constants;
import main.data.Visitor;
import main.heroes.Hero;
import main.heroes.Knight;
import main.heroes.Pyromancer;
import main.heroes.Rogue;
import main.heroes.Wizard;

/**
 * Class that implements Dark Angel logic.
 */
public final class DarkAngel extends Angel implements Visitor {

    public DarkAngel(final int spawnRow,
                     final int spawnCol) {
        super(spawnRow, spawnCol, "DarkAngel");
    }

    /**
     * Dark Angel interaction with a wizard.
     *
     * @param wizard influenced wizard
     */
    @Override
    public void helpHero(final Wizard wizard) {
        if (wizard.isDead()) {
            return;
        }
        computeObservation(wizard);
        wizard.takeDamage(Constants.DARKANGEL_WIZARD_BONUS, false, true);
    }

    /**
     * Dark Angel interaction with a rogue.
     *
     * @param rogue influenced rogue
     */
    @Override
    public void helpHero(final Rogue rogue) {
        if (rogue.isDead()) {
            return;
        }
        computeObservation(rogue);
        rogue.takeDamage(Constants.DARKANGEL_ROGUE_BONUS, false, true);
    }

    /**
     * Dark Angel interaction with a pyromancer.
     *
     * @param pyromancer influenced pyromancer
     */
    @Override
    public void helpHero(final Pyromancer pyromancer) {
        if (pyromancer.isDead()) {
            return;
        }
        computeObservation(pyromancer);
        pyromancer.takeDamage(Constants.DARKANGEL_PYROMANCER_BONUS, false, true);
    }

    /**
     * Dark Angel interaction with a knight.
     *
     * @param knight influenced knight
     */
    @Override
    public void helpHero(final Knight knight) {
        if (knight.isDead()) {
            return;
        }
        computeObservation(knight);
        knight.takeDamage(Constants.DARKANGEL_KNIGHT_BONUS, false, true);
    }

    /**
     * Computing Dark Angel observation for the observer.
     *
     * @param hero influenced hero
     */
    @Override
    public void computeObservation(final Hero hero) {
        String message = "DarkAngel hit " + hero.getFullName() + " "
                + hero.getIndex() + "\n";
        setChanged();
        notifyObservers(message);
    }
}

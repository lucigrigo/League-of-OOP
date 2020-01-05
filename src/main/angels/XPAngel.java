package main.angels;

import main.data.Constants;
import main.data.Visitor;
import main.heroes.Hero;
import main.heroes.Knight;
import main.heroes.Pyromancer;
import main.heroes.Rogue;
import main.heroes.Wizard;

/**
 * Class that implements XP Angel logic.
 */
public final class XPAngel extends Angel implements Visitor {

    public XPAngel(final int spawnRow,
                   final int spawnCol) {
        super(spawnRow, spawnCol, "XPAngel");
    }

    /**
     * XP Angel interaction with a wizard.
     *
     * @param wizard influenced wizard
     */
    @Override
    public void helpHero(final Wizard wizard) {
        if (wizard.isDead()) {
            return;
        }
        computeObservation(wizard);
        wizard.increaseXP(Constants.XPANGEL_WIZARD_BONUS_XP);
    }

    /**
     * XP Angel interaction with a rogue.
     *
     * @param rogue influenced rogue
     */
    @Override
    public void helpHero(final Rogue rogue) {
        if (rogue.isDead()) {
            return;
        }
        computeObservation(rogue);
        rogue.increaseXP(Constants.XPANGEL_ROGUE_BONUS_XP);
    }

    /**
     * XP Angel interaction with a pyromancer.
     *
     * @param pyromancer influenced pyromancer
     */
    @Override
    public void helpHero(final Pyromancer pyromancer) {
        if (pyromancer.isDead()) {
            return;
        }
        computeObservation(pyromancer);
        pyromancer.increaseXP(Constants.XPANGEL_PYROMANCER_BONUS_XP);
    }

    /**
     * XP Angel interaction with a knight.
     *
     * @param knight influenced knight
     */
    @Override
    public void helpHero(final Knight knight) {
        if (knight.isDead()) {
            return;
        }
        computeObservation(knight);
        knight.increaseXP(Constants.XPANGEL_KNIGHT_BONUS_XP);
    }

    /**
     * Computing XP Angel observation for the observer.
     *
     * @param hero influenced hero
     */
    @Override
    public void computeObservation(final Hero hero) {
        if (hero.isDead()) {
            return;
        }
        String message = "XPAngel helped " + hero.getFullName()
                + " " + hero.getIndex() + "\n";
        setChanged();
        notifyObservers(message);
    }
}

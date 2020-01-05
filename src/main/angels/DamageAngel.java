package main.angels;

import main.data.Constants;
import main.data.Visitor;
import main.heroes.Hero;
import main.heroes.Knight;
import main.heroes.Pyromancer;
import main.heroes.Rogue;
import main.heroes.Wizard;

/**
 * Class that implements Damage Angel logic.
 */
public final class DamageAngel extends Angel implements Visitor {

    public DamageAngel(final int spawnRow,
                       final int spawnCol) {
        super(spawnRow, spawnCol, "DamageAngel");
    }

    /**
     * Damage Angel interaction with a wizard.
     *
     * @param wizard influenced wizard
     */
    @Override
    public void helpHero(final Wizard wizard) {
        if (wizard.isDead()) {
            return;
        }
        computeObservation(wizard);
        wizard.addAngelBonus(Constants.DAMAGEANGEL_WIZARD_BONUS);
    }

    /**
     * Damage Angel interaction with a rogue.
     *
     * @param rogue influenced rogue
     */
    @Override
    public void helpHero(final Rogue rogue) {
        if (rogue.isDead()) {
            return;
        }
        computeObservation(rogue);
        rogue.addAngelBonus(Constants.DAMAGEANGEL_ROGUE_BONUS);
    }

    /**
     * Damage Angel interaction with a pyromancer.
     *
     * @param pyromancer influenced pyromancer
     */
    @Override
    public void helpHero(final Pyromancer pyromancer) {
        if (pyromancer.isDead()) {
            return;
        }
        computeObservation(pyromancer);
        pyromancer.addAngelBonus(Constants.DAMAGEANGEL_PYROMANCER_BONUS);
    }

    /**
     * Damage Angel interaction with a knight.
     *
     * @param knight influenced knight
     */
    @Override
    public void helpHero(final Knight knight) {
        if (knight.isDead()) {
            return;
        }
        computeObservation(knight);
        knight.addAngelBonus(Constants.DAMAGEANGEL_KNIGHT_BONUS);
    }

    /**
     * Computing Damage Angel observation for the observer.
     *
     * @param hero influenced hero
     */
    @Override
    public void computeObservation(final Hero hero) {
        String message = "DamageAngel helped " + hero.getFullName() + " "
                + hero.getIndex() + "\n";
        setChanged();
        notifyObservers(message);
    }
}

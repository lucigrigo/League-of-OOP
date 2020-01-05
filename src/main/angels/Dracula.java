package main.angels;

import main.data.Constants;
import main.data.Visitor;
import main.heroes.Hero;
import main.heroes.Knight;
import main.heroes.Pyromancer;
import main.heroes.Rogue;
import main.heroes.Wizard;

/**
 * Class that implements Dracula logic.
 */
public final class Dracula extends Angel implements Visitor {

    public Dracula(final int spawnRow,
                   final int spawnCol) {
        super(spawnRow, spawnCol, "Dracula");
    }

    /**
     * Dracula interaction with a wizard.
     *
     * @param wizard influenced wizard
     */
    @Override
    public void helpHero(final Wizard wizard) {
        if (wizard.isDead()) {
            return;
        }
        computeObservation(wizard);
        wizard.addAngelBonus(Constants.DRACULA_WIZARD_BONUS_DAMAGE);
        wizard.takeDamage(Constants.DRACULA_WIZARD_BONUS_HP, false, true);
    }

    /**
     * Dracula interaction with a rogue.
     *
     * @param rogue influenced rogue
     */
    @Override
    public void helpHero(final Rogue rogue) {
        if (rogue.isDead()) {
            return;
        }
        computeObservation(rogue);
        rogue.addAngelBonus(Constants.DRACULA_ROGUE_BONUS_DAMAGE);
        rogue.takeDamage(Constants.DRACULA_ROGUE_BONUS_HP, false, true);
    }

    /**
     * Dracula interaction with a pyromancer.
     *
     * @param pyromancer influenced pyromancer
     */
    @Override
    public void helpHero(final Pyromancer pyromancer) {
        if (pyromancer.isDead()) {
            return;
        }
        computeObservation(pyromancer);
        pyromancer.addAngelBonus(Constants.DRACULA_PYROMANCER_BONUS_DAMAGE);
        pyromancer.takeDamage(Constants.DRACULA_PYROMANCER_BONUS_HP, false, true);
    }

    /**
     * Dracula interaction with a knight.
     *
     * @param knight influenced knight
     */
    @Override
    public void helpHero(final Knight knight) {
        if (knight.isDead()) {
            return;
        }
        computeObservation(knight);
        knight.addAngelBonus(Constants.DRACULA_KNIGHT_BONUS_DAMAGE);
        knight.takeDamage(Constants.DRACULA_KNIGHT_BONUS_HP, false, true);
    }

    /**
     * Computing Dracula observation for the observer.
     *
     * @param hero influenced hero
     */
    @Override
    public void computeObservation(final Hero hero) {
        String message = "Dracula hit " + hero.getFullName() + " "
                + hero.getIndex() + "\n";
        setChanged();
        notifyObservers(message);
    }
}

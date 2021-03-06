package main.angels;

import main.data.Constants;
import main.data.Visitor;
import main.heroes.Hero;
import main.heroes.Knight;
import main.heroes.Pyromancer;
import main.heroes.Rogue;
import main.heroes.Wizard;

/**
 * Class that implements Good Boy logic.
 */
public final class GoodBoy extends Angel implements Visitor {

    public GoodBoy(final int spawnRow,
                   final int spawnCol) {
        super(spawnRow, spawnCol, "GoodBoy");
    }

    /**
     * Good Boy interaction with a wizard.
     *
     * @param wizard influenced wizard
     */
    @Override
    public void helpHero(final Wizard wizard) {
        if (wizard.isDead()) {
            return;
        }
        computeObservation(wizard);
        wizard.addAngelBonus(Constants.GOODBOY_WIZARD_BONUS_DAMAGE);
        wizard.increaseHP(Constants.GOODBOY_WIZARD_BONUS_HP);
    }

    /**
     * Good Boy interaction with a rogue.
     *
     * @param rogue influenced rogue
     */
    @Override
    public void helpHero(final Rogue rogue) {
        if (rogue.isDead()) {
            return;
        }
        computeObservation(rogue);
        rogue.addAngelBonus(Constants.GOODBOY_ROGUE_BONUS_DAMAGE);
        rogue.increaseHP(Constants.GOODBOY_ROGUE_BONUS_HP);
    }

    /**
     * Good Boy interaction with a pyromancer.
     *
     * @param pyromancer influenced pyromancer
     */
    @Override
    public void helpHero(final Pyromancer pyromancer) {
        if (pyromancer.isDead()) {
            return;
        }
        computeObservation(pyromancer);
        pyromancer.addAngelBonus(Constants.GOODBOY_PYROMANCER_BONUS_DAMAGE);
        pyromancer.increaseHP(Constants.GOODBOY_PYROMANCER_BONUS_HP);
    }

    /**
     * Good Boy interaction with a knight.
     *
     * @param knight influenced knight
     */
    @Override
    public void helpHero(final Knight knight) {
        if (knight.isDead()) {
            return;
        }
        computeObservation(knight);
        knight.addAngelBonus(Constants.GOODBOY_KNIGHT_BONUS_DAMAGE);
        knight.increaseHP(Constants.GOODBOY_KNIGHT_BONUS_HP);
    }

    /**
     * Computing Good Boy observation for the observer.
     *
     * @param hero influenced hero
     */
    @Override
    public void computeObservation(final Hero hero) {
        String message = "GoodBoy helped " + hero.getFullName() + " "
                + hero.getIndex() + "\n";
        setChanged();
        notifyObservers(message);
    }
}


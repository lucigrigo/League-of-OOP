package main.angels;

import main.data.Constants;
import main.data.Visitor;
import main.heroes.*;

public class LifeGiver extends Angel implements Visitor {

    public LifeGiver(final int spawnRow,
                     final int spawnCol) {
        super(spawnRow, spawnCol, "LifeGiver");
    }

    @Override
    public void helpHero(final Wizard wizard) {
        if (wizard.isDead()) {
            return;
        }
        wizard.increaseHP(Constants.LIFEGIVER_WIZARD_BONUS);
        computeObservation(wizard);
    }

    @Override
    public void helpHero(final Rogue rogue) {
        if (rogue.isDead()) {
            return;
        }
        rogue.increaseHP(Constants.LIFEGIVER_ROGUE_BONUS);
        computeObservation(rogue);
    }

    @Override
    public void helpHero(final Pyromancer pyromancer) {
        if (pyromancer.isDead()) {
            return;
        }
        pyromancer.increaseHP(Constants.LIFEGIVER_PYROMANCER_BONUS);
        computeObservation(pyromancer);
    }

    @Override
    public void helpHero(final Knight knight) {
        if (knight.isDead()) {
            return;
        }
        knight.increaseHP(Constants.LIFEGIVER_KNIGHT_BONUS);
        computeObservation(knight);
    }

    @Override
    public void computeObservation(Hero hero) {
        String message = "LifeGiver helped " + hero.getFullName() + " " + hero.getIndex() + "\n";
        setChanged();
        notifyObservers(message);
    }
}

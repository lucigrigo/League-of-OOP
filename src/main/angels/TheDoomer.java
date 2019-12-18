package main.angels;

import main.data.Visitor;
import main.heroes.*;

public class TheDoomer extends Angel implements Visitor {

    public TheDoomer(final int spawnRow,
                     final int spawnCol) {
        super(spawnRow, spawnCol, "TheDoomer");
    }

    @Override
    public void helpHero(final Wizard wizard) {
        computeObservation(wizard);
        wizard.hasDied(true);
    }

    @Override
    public void helpHero(final Rogue rogue) {
        computeObservation(rogue);
        rogue.hasDied(true);
    }

    @Override
    public void helpHero(final Pyromancer pyromancer) {
        computeObservation(pyromancer);
        pyromancer.hasDied(true);
    }

    @Override
    public void helpHero(final Knight knight) {
        computeObservation(knight);
        knight.hasDied(true);
    }

    @Override
    public void computeObservation(Hero hero) {
        String message = "TheDoomer hit " + hero.getFullName() + " " + hero.getIndex() + "\n";
        setChanged();
        notifyObservers(message);
    }
}

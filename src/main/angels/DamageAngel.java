package main.angels;

import main.data.Visitor;
import main.heroes.*;

public class DamageAngel extends Angel implements Visitor {

    public DamageAngel(final int spawnRow,
                       final int spawnCol) {
        super(spawnRow, spawnCol, "DamageAngel");
    }

    @Override
    public void helpHero(final Wizard wizard) {

    }

    @Override
    public void helpHero(final Rogue rogue) {

    }

    @Override
    public void helpHero(final Pyromancer pyromancer) {

    }

    @Override
    public void helpHero(final Knight knight) {

    }

    @Override
    public void computeObservation(final Hero hero) {
        String message = "DamageAngel helped " + hero.getName() + " " + hero.getIndex() + "\n";
        setChanged();
        notifyObservers(message);
    }
}

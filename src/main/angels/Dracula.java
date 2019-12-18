package main.angels;

import main.data.Visitor;
import main.heroes.*;

public class Dracula extends Angel implements Visitor {

    public Dracula(final int spawnRow,
                   final int spawnCol) {
        super(spawnRow, spawnCol, "Dracula");
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
    public void computeObservation(Hero hero) {
        String message = "Dracula helped " + hero.getName() + " " + hero.getIndex() + "\n";
        setChanged();
        notifyObservers(message);
    }
}

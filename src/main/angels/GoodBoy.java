package main.angels;

import main.data.Visitor;
import main.heroes.*;

public class GoodBoy extends Angel implements Visitor {

    public GoodBoy(final int spawnRow,
                   final int spawnCol) {
        super(spawnRow, spawnCol, "GoodBoy");
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
        String message = "GoodBoy helped " + hero.getName() + " " + hero.getIndex() + "\n";
        setChanged();
        notifyObservers(message);
    }
}


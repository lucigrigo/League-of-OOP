package main.angels;

import main.data.Visitor;
import main.heroes.Knight;
import main.heroes.Pyromancer;
import main.heroes.Rogue;
import main.heroes.Wizard;

public class XPAngel extends Angel implements Visitor {

    public XPAngel(final int spawnRow,
                           final int spawnCol) {
        super(spawnRow, spawnCol);
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
}

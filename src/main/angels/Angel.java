package main.angels;

import main.data.Visitor;
import main.heroes.Knight;
import main.heroes.Pyromancer;
import main.heroes.Rogue;
import main.heroes.Wizard;

public abstract class Angel implements Visitor {

    private int spawnRow;
    private int spawnCol;

    public Angel(final int spawnRow,
                 final int spawnCol) {
        this.spawnRow = spawnRow;
        this.spawnCol = spawnCol;
    }

    @Override
    public abstract void helpHero(Wizard wizard);

    @Override
    public abstract void helpHero(Rogue rogue);

    @Override
    public abstract void helpHero(Pyromancer pyromancer);

    @Override
    public abstract void helpHero(Knight knight);
}

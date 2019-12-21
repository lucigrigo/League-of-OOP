package main.angels;

import main.data.Visitor;
import main.gameplay.GreatSorcerer;
import main.heroes.Hero;
import main.heroes.Knight;
import main.heroes.Pyromancer;
import main.heroes.Rogue;
import main.heroes.Wizard;

import java.util.Observable;

public abstract class Angel extends Observable implements Visitor {

    private int spawnRow;
    private int spawnCol;
    private String name;

    public Angel(final int spawnRow,
                 final int spawnCol,
                 final String name) {
        this.spawnRow = spawnRow;
        this.spawnCol = spawnCol;
        this.name = name;
        addObserver(GreatSorcerer.getInstance());
    }

    @Override
    public abstract void helpHero(Wizard wizard);

    @Override
    public abstract void helpHero(Rogue rogue);

    @Override
    public abstract void helpHero(Pyromancer pyromancer);

    @Override
    public abstract void helpHero(Knight knight);

    public abstract void computeObservation(Hero hero);

    public final int getRow() {
        return spawnRow;
    }

    public final int getCol() {
        return spawnCol;
    }

    public final String getName() {
        return name;
    }

    public final void spawn() {
        String message = "Angel " + name + " was spawned at "
                + spawnRow + " " + spawnCol + "\n";
        setChanged();
        notifyObservers(message);
    }
}

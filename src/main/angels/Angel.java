package main.angels;

import main.data.Visitor;
import main.gameplay.GreatSorcerer;
import main.heroes.Hero;
import main.heroes.Knight;
import main.heroes.Pyromancer;
import main.heroes.Rogue;
import main.heroes.Wizard;

import java.util.Observable;

/**
 * Class that describes a template for any type of angel in the game.
 */
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

        // adding the sole observer of the game
        addObserver(GreatSorcerer.getInstance());
    }

    /**
     * Method to influence a wizard.
     *
     * @param wizard influenced wizard
     */
    @Override
    public abstract void helpHero(Wizard wizard);

    /**
     * Method to influence a rogue.
     *
     * @param rogue influenced rogue
     */
    @Override
    public abstract void helpHero(Rogue rogue);

    /**
     * Method to influence a pyromancer.
     *
     * @param pyromancer influenced pyromancer
     */
    @Override
    public abstract void helpHero(Pyromancer pyromancer);

    /**
     * Method to influence a knight.
     *
     * @param knight influenced knight
     */
    @Override
    public abstract void helpHero(Knight knight);

    /**
     * Computing observation about interaction with a hero.
     *
     * @param hero influenced hero
     */
    public abstract void computeObservation(Hero hero);


    /**
     * @return spawning row
     */
    public final int getRow() {
        return spawnRow;
    }

    /**
     * @return spawning colon
     */
    public final int getCol() {
        return spawnCol;
    }

    /**
     * Computing spawning message for the observer and passing the message to him.
     */
    public final void spawn() {
        String message = "Angel " + name + " was spawned at "
                + spawnRow + " " + spawnCol + "\n";
        setChanged();
        notifyObservers(message);
    }
}

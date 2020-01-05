package main.angels;

import main.data.Constants;
import main.data.Visitor;
import main.heroes.Hero;
import main.heroes.Knight;
import main.heroes.Pyromancer;
import main.heroes.Rogue;
import main.heroes.Wizard;

/**
 * Class to implement Spawner logic.
 */
public final class Spawner extends Angel implements Visitor {

    public Spawner(final int spawnRow,
                   final int spawnCol) {
        super(spawnRow, spawnCol, "Spawner");
    }

    /**
     * Spawner reviving a wizard.
     *
     * @param wizard respawned wizard
     */
    @Override
    public void helpHero(final Wizard wizard) {
        if (!wizard.isDead()) {
            return;
        }
        computeObservation(wizard);
        wizard.revive(Constants.SPAWNER_WIZARD_HP);
    }

    /**
     * Spawner reviving a rogue.
     *
     * @param rogue respawned rogue
     */
    @Override
    public void helpHero(final Rogue rogue) {
        if (!rogue.isDead()) {
            return;
        }
        computeObservation(rogue);
        rogue.revive(Constants.SPAWNER_ROGUE_HP);
    }

    /**
     * Spawner reviving a pyromancer.
     *
     * @param pyromancer respawned pyromancer
     */
    @Override
    public void helpHero(final Pyromancer pyromancer) {
        if (!pyromancer.isDead()) {
            return;
        }
        computeObservation(pyromancer);
        pyromancer.revive(Constants.SPAWNER_PYROMANCER_HP);
    }

    /**
     * Spawner reviving a knight.
     *
     * @param knight respawned knight
     */
    @Override
    public void helpHero(final Knight knight) {
        if (!knight.isDead()) {
            return;
        }
        computeObservation(knight);
        knight.revive(Constants.SPAWNER_KNIGHT_HP);
    }

    /**
     * Computing Spawner observation for the observer.
     *
     * @param hero influenced hero
     */
    @Override
    public void computeObservation(final Hero hero) {
        String message = "Spawner helped " + hero.getFullName() + " "
                + hero.getIndex() + "\n";
        setChanged();
        notifyObservers(message);
    }
}

package main.angels;

import main.data.Constants;
import main.data.Visitor;
import main.heroes.*;

public class Spawner extends Angel implements Visitor {

    public Spawner(final int spawnRow,
                   final int spawnCol) {
        super(spawnRow, spawnCol, "Spawner");
    }

    @Override
    public void helpHero(final Wizard wizard) {
        wizard.revive(Constants.SPAWNER_WIZARD_HP);
    }

    @Override
    public void helpHero(final Rogue rogue) {
        rogue.revive(Constants.SPAWNER_ROGUE_HP);
    }

    @Override
    public void helpHero(final Pyromancer pyromancer) {
        pyromancer.revive(Constants.SPAWNER_PYROMANCER_HP);
    }

    @Override
    public void helpHero(final Knight knight) {
        knight.revive(Constants.SPAWNER_KNIGHT_HP);
    }

    @Override
    public void computeObservation(Hero hero) {
        String message = "Spawner helped " + hero.getName() + " " + hero.getIndex() + "\n";
        setChanged();
        notifyObservers(message);
    }
}

package main.angels;

import main.data.Constants;
import main.data.Visitor;
import main.heroes.Hero;
import main.heroes.Knight;
import main.heroes.Pyromancer;
import main.heroes.Rogue;
import main.heroes.Wizard;

public final class DarkAngel extends Angel implements Visitor {

    public DarkAngel(final int spawnRow,
                     final int spawnCol) {
        super(spawnRow, spawnCol, "DarkAngel");
    }

    @Override
    public void helpHero(final Wizard wizard) {
        if (wizard.isDead()) {
            return;
        }
        computeObservation(wizard);
        wizard.takeDamage(Constants.DARKANGEL_WIZARD_BONUS, false, true);
    }

    @Override
    public void helpHero(final Rogue rogue) {
        if (rogue.isDead()) {
            return;
        }
        computeObservation(rogue);
        rogue.takeDamage(Constants.DARKANGEL_ROGUE_BONUS, false, true);
    }

    @Override
    public void helpHero(final Pyromancer pyromancer) {
        if (pyromancer.isDead()) {
            return;
        }
        computeObservation(pyromancer);
        pyromancer.takeDamage(Constants.DARKANGEL_PYROMANCER_BONUS, false, true);
    }

    @Override
    public void helpHero(final Knight knight) {
        if (knight.isDead()) {
            return;
        }
        computeObservation(knight);
        knight.takeDamage(Constants.DARKANGEL_KNIGHT_BONUS, false, true);
    }

    @Override
    public void computeObservation(final Hero hero) {
        String message = "DarkAngel hit " + hero.getFullName() + " "
                + hero.getIndex() + "\n";
        setChanged();
        notifyObservers(message);
    }
}

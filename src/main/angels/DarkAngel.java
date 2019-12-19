package main.angels;

import main.data.Constants;
import main.data.Visitor;
import main.heroes.*;

public class DarkAngel extends Angel implements Visitor {

    public DarkAngel(final int spawnRow,
                     final int spawnCol) {
        super(spawnRow, spawnCol, "DarkAngel");
    }

    @Override
    public void helpHero(final Wizard wizard) {
        if(wizard.isDead()) {
            return;
        }
        wizard.addAngelBonus(Constants.DARKANGEL_WIZARD_BONUS);
        computeObservation(wizard);
    }

    @Override
    public void helpHero(final Rogue rogue) {
        if(rogue.isDead()) {
            return;
        }
        rogue.addAngelBonus(Constants.DARKANGEL_ROGUE_BONUS);
        computeObservation(rogue);
    }

    @Override
    public void helpHero(final Pyromancer pyromancer) {
        if(pyromancer.isDead()) {
            return;
        }
        pyromancer.addAngelBonus(Constants.DARKANGEL_PYROMANCER_BONUS);
        computeObservation(pyromancer);
    }

    @Override
    public void helpHero(final Knight knight) {
        if(knight.isDead()) {
            return;
        }
        knight.addAngelBonus(Constants.DARKANGEL_KNIGHT_BONUS);
        computeObservation(knight);
    }

    @Override
    public void computeObservation(Hero hero) {
        String message = "DarkAngel hit " + hero.getFullName() + " " + hero.getIndex() + "\n";
        setChanged();
        notifyObservers(message);
    }
}

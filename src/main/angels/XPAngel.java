package main.angels;

import main.data.Constants;
import main.data.Visitor;
import main.heroes.*;

public class XPAngel extends Angel implements Visitor {

    public XPAngel(final int spawnRow,
                   final int spawnCol) {
        super(spawnRow, spawnCol, "XPAngel");
    }

    @Override
    public void helpHero(final Wizard wizard) {
        wizard.increaseXP(Constants.XPANGEL_WIZARD_BONUS_XP);
        computeObservation(wizard);
    }

    @Override
    public void helpHero(final Rogue rogue) {
        rogue.increaseXP(Constants.XPANGEL_ROGUE_BONUS_XP);
        computeObservation(rogue);
    }

    @Override
    public void helpHero(final Pyromancer pyromancer) {
        pyromancer.increaseXP(Constants.XPANGEL_PYROMANCER_BONUS_XP);
        computeObservation(pyromancer);
    }

    @Override
    public void helpHero(final Knight knight) {
        knight.increaseXP(Constants.XPANGEL_KNIGHT_BONUS_XP);
        computeObservation(knight);
    }

    @Override
    public void computeObservation(Hero hero) {
        String message = "XPAngel helped " + hero.getName() + " " + hero.getIndex() + "\n";
        setChanged();
        notifyObservers(message);
    }
}

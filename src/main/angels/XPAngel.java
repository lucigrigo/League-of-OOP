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
        computeObservation(wizard);
        wizard.increaseXP(Constants.XPANGEL_WIZARD_BONUS_XP);
    }

    @Override
    public void helpHero(final Rogue rogue) {
        computeObservation(rogue);
        rogue.increaseXP(Constants.XPANGEL_ROGUE_BONUS_XP);
    }

    @Override
    public void helpHero(final Pyromancer pyromancer) {
        computeObservation(pyromancer);
        pyromancer.increaseXP(Constants.XPANGEL_PYROMANCER_BONUS_XP);
    }

    @Override
    public void helpHero(final Knight knight) {
        computeObservation(knight);
        knight.increaseXP(Constants.XPANGEL_KNIGHT_BONUS_XP);
    }

    @Override
    public void computeObservation(Hero hero) {
        if (hero.isDead()) {
            return;
        }
        String message = "XPAngel helped " + hero.getFullName() + " " + hero.getIndex() + "\n";
        setChanged();
        notifyObservers(message);
    }
}

package main.angels;

import main.data.Constants;
import main.data.Visitor;
import main.heroes.*;

public class DamageAngel extends Angel implements Visitor {

    public DamageAngel(final int spawnRow,
                       final int spawnCol) {
        super(spawnRow, spawnCol, "DamageAngel");
    }

    @Override
    public void helpHero(final Wizard wizard) {
        wizard.addAngelBonus(Constants.DAMAGEANGEL_WIZARD_BONUS);
        computeObservation(wizard);
    }

    @Override
    public void helpHero(final Rogue rogue) {
        rogue.addAngelBonus(Constants.DAMAGEANGEL_ROGUE_BONUS);
        computeObservation(rogue);
    }

    @Override
    public void helpHero(final Pyromancer pyromancer) {
        pyromancer.addAngelBonus(Constants.DAMAGEANGEL_PYROMANCER_BONUS);
        computeObservation(pyromancer);
    }

    @Override
    public void helpHero(final Knight knight) {
        knight.addAngelBonus(Constants.DAMAGEANGEL_KNIGHT_BONUS);
        computeObservation(knight);
    }

    @Override
    public void computeObservation(final Hero hero) {
        String message = "DamageAngel helped " + hero.getFullName() + " " + hero.getIndex() + "\n";
        setChanged();
        notifyObservers(message);
    }
}

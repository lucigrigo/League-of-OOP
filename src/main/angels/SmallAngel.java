package main.angels;

import main.data.Constants;
import main.data.Visitor;
import main.heroes.*;

public class SmallAngel extends Angel implements Visitor {

    public SmallAngel(final int spawnRow,
                      final int spawnCol) {
        super(spawnRow, spawnCol, "SmallAngel");
    }

    @Override
    public void helpHero(final Wizard wizard) {
        wizard.addAngelBonus(Constants.SMALLANGEL_WIZARD_BONUS_DAMAGE);
        wizard.increaseHP(Constants.SMALLANGEL_WIZARD_BONUS_HP);
        computeObservation(wizard);
    }

    @Override
    public void helpHero(final Rogue rogue) {
        rogue.addAngelBonus(Constants.SMALLANGEL_ROGUE_BONUS_DAMAGE);
        rogue.increaseHP(Constants.SMALLANGEL_ROGUE_BONUS_HP);
        computeObservation(rogue);
    }

    @Override
    public void helpHero(final Pyromancer pyromancer) {
        pyromancer.addAngelBonus(Constants.SMALLANGEL_PYROMANCER_BONUS_DAMAGE);
        pyromancer.increaseHP(Constants.SMALLANGEL_PYROMANCER_BONUS_HP);
        computeObservation(pyromancer);
    }

    @Override
    public void helpHero(final Knight knight) {
        knight.addAngelBonus(Constants.SMALLANGEL_KNIGHT_BONUS_DAMAGE);
        knight.increaseHP(Constants.SMALLANGEL_KNIGHT_BONUS_HP);
        computeObservation(knight);
    }

    @Override
    public void computeObservation(Hero hero) {
        String message = "SmallAngel helped " + hero.getFullName() + " " + hero.getIndex() + "\n";
        setChanged();
        notifyObservers(message);
    }
}

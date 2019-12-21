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
        if (wizard.isDead()) {
            return;
        }
        computeObservation(wizard);
        wizard.addAngelBonus(Constants.SMALLANGEL_WIZARD_BONUS_DAMAGE);
        wizard.increaseHP(Constants.SMALLANGEL_WIZARD_BONUS_HP);
    }

    @Override
    public void helpHero(final Rogue rogue) {
        if (rogue.isDead()) {
            return;
        }
        computeObservation(rogue);
        rogue.addAngelBonus(Constants.SMALLANGEL_ROGUE_BONUS_DAMAGE);
        rogue.increaseHP(Constants.SMALLANGEL_ROGUE_BONUS_HP);
    }

    @Override
    public void helpHero(final Pyromancer pyromancer) {
        if (pyromancer.isDead()) {
            return;
        }
        computeObservation(pyromancer);
        pyromancer.addAngelBonus(Constants.SMALLANGEL_PYROMANCER_BONUS_DAMAGE);
        pyromancer.increaseHP(Constants.SMALLANGEL_PYROMANCER_BONUS_HP);
    }

    @Override
    public void helpHero(final Knight knight) {
        if (knight.isDead()) {
            return;
        }
        computeObservation(knight);
        knight.addAngelBonus(Constants.SMALLANGEL_KNIGHT_BONUS_DAMAGE);
        knight.increaseHP(Constants.SMALLANGEL_KNIGHT_BONUS_HP);
    }

    @Override
    public void computeObservation(Hero hero) {
        String message = "SmallAngel helped " + hero.getFullName() + " " + hero.getIndex() + "\n";
        setChanged();
        notifyObservers(message);
    }
}

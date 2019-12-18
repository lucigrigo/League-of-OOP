package main.angels;

import main.data.Constants;
import main.data.Visitor;
import main.heroes.*;

public class Dracula extends Angel implements Visitor {

    public Dracula(final int spawnRow,
                   final int spawnCol) {
        super(spawnRow, spawnCol, "Dracula");
    }

    @Override
    public void helpHero(final Wizard wizard) {
        wizard.addAngelBonus(Constants.DRACULA_WIZARD_BONUS_DAMAGE);
        wizard.takeDamage(Constants.DRACULA_WIZARD_BONUS_HP, false, true);
        computeObservation(wizard);
    }

    @Override
    public void helpHero(final Rogue rogue) {
        rogue.addAngelBonus(Constants.DRACULA_ROGUE_BONUS_DAMAGE);
        rogue.takeDamage(Constants.DRACULA_ROGUE_BONUS_HP, false, true);
        computeObservation(rogue);
    }

    @Override
    public void helpHero(final Pyromancer pyromancer) {
        pyromancer.addAngelBonus(Constants.DRACULA_PYROMANCER_BONUS_DAMAGE);
        pyromancer.takeDamage(Constants.DRACULA_PYROMANCER_BONUS_HP, false, true);
        computeObservation(pyromancer);
    }

    @Override
    public void helpHero(final Knight knight) {
        knight.addAngelBonus(Constants.DRACULA_KNIGHT_BONUS_DAMAGE);
        knight.takeDamage(Constants.DRACULA_KNIGHT_BONUS_HP, false, true);
        computeObservation(knight);
    }

    @Override
    public void computeObservation(Hero hero) {
        String message = "Dracula hit " + hero.getFullName() + " " + hero.getIndex() + "\n";
        setChanged();
        notifyObservers(message);
    }
}

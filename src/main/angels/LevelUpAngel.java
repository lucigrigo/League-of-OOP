package main.angels;

import main.data.Constants;
import main.data.Visitor;
import main.heroes.*;

public class LevelUpAngel extends Angel implements Visitor {

    public LevelUpAngel(final int spawnRow,
                        final int spawnCol) {
        super(spawnRow, spawnCol, "LevelUpAngel");
    }

    @Override
    public void helpHero(final Wizard wizard) {
        computeObservation(wizard);
        wizard.getToNextLevel();
        wizard.addAngelBonus(Constants.LEVELUPANGEL_WIZARD_BONUS);
    }

    @Override
    public void helpHero(final Rogue rogue) {
        computeObservation(rogue);
        rogue.getToNextLevel();
        rogue.addAngelBonus(Constants.LEVELUPANGEL_ROGUE_BONUS);
    }

    @Override
    public void helpHero(final Pyromancer pyromancer) {
        computeObservation(pyromancer);
        pyromancer.getToNextLevel();
        pyromancer.addAngelBonus(Constants.LEVELUPANGEL_PYROMANCER_BONUS);
    }

    @Override
    public void helpHero(final Knight knight) {
        computeObservation(knight);
        knight.getToNextLevel();
        knight.addAngelBonus(Constants.LEVELUPANGEL_KNIGHT_BONUS);
    }

    @Override
    public void computeObservation(Hero hero) {
        if (hero.isDead()) {
            return;
        }
        String message = "LevelUpAngel helped " + hero.getFullName() + " " + hero.getIndex() + "\n";
        setChanged();
        notifyObservers(message);
    }
}

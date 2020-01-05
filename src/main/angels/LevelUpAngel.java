package main.angels;

import main.data.Constants;
import main.data.Visitor;
import main.heroes.Hero;
import main.heroes.Knight;
import main.heroes.Pyromancer;
import main.heroes.Rogue;
import main.heroes.Wizard;

/**
 * Class that implements Level-Up Angel logic.
 */
public final class LevelUpAngel extends Angel implements Visitor {

    public LevelUpAngel(final int spawnRow,
                        final int spawnCol) {
        super(spawnRow, spawnCol, "LevelUpAngel");
    }

    /**
     * Level-Up Angel interaction with a wizard.
     *
     * @param wizard influenced wizard
     */
    @Override
    public void helpHero(final Wizard wizard) {
        if (wizard.isDead()) {
            return;
        }
        computeObservation(wizard);
        wizard.getToNextLevel();
        wizard.addAngelBonus(Constants.LEVELUPANGEL_WIZARD_BONUS);
    }

    /**
     * Level-Up Angel interaction with a rogue.
     *
     * @param rogue influenced rogue
     */
    @Override
    public void helpHero(final Rogue rogue) {
        if (rogue.isDead()) {
            return;
        }
        computeObservation(rogue);
        rogue.getToNextLevel();
        rogue.addAngelBonus(Constants.LEVELUPANGEL_ROGUE_BONUS);
    }

    /**
     * Level-Up Angel interaction with a pyromancer.
     *
     * @param pyromancer influenced pyromancer
     */
    @Override
    public void helpHero(final Pyromancer pyromancer) {
        if (pyromancer.isDead()) {
            return;
        }
        computeObservation(pyromancer);
        pyromancer.getToNextLevel();
        pyromancer.addAngelBonus(Constants.LEVELUPANGEL_PYROMANCER_BONUS);
    }

    /**
     * Level-Up Angel interaction with a knight.
     *
     * @param knight influenced knight
     */
    @Override
    public void helpHero(final Knight knight) {
        if (knight.isDead()) {
            return;
        }
        computeObservation(knight);
        knight.getToNextLevel();
        knight.addAngelBonus(Constants.LEVELUPANGEL_KNIGHT_BONUS);
    }

    /**
     * Computing Level-Up Angel observation for the observer.
     *
     * @param hero influenced hero
     */
    @Override
    public void computeObservation(final Hero hero) {
        if (hero.isDead()) {
            return;
        }
        String message = "LevelUpAngel helped " + hero.getFullName() + " "
                + hero.getIndex() + "\n";
        setChanged();
        notifyObservers(message);
    }
}

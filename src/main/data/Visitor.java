package main.data;

import main.heroes.Knight;
import main.heroes.Pyromancer;
import main.heroes.Rogue;
import main.heroes.Wizard;

/**
 * Interface used by the angels, used to influence different types of heroes.
 */
public interface Visitor {

    /**
     * Influencing a wizard.
     *
     * @param wizard influenced wizard
     */
    void helpHero(Wizard wizard);

    /**
     * Influencing a rogue.
     *
     * @param rogue influenced rogue
     */
    void helpHero(Rogue rogue);

    /**
     * Influencing a pyromancer.
     *
     * @param pyromancer influenced pyromancer
     */
    void helpHero(Pyromancer pyromancer);

    /**
     * Influencing a knight.
     *
     * @param knight influenced knight
     */
    void helpHero(Knight knight);
}

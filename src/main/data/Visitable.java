package main.data;

import main.angels.Angel;

/**
 * Interface used by the heroes, used to interact with the angels.
 */
public interface Visitable {

    /**
     * Function used by any hero that is influenced by any angel.
     *
     * @param angel angel that influences the hero
     */
    void getHelpedBy(Angel angel);
}

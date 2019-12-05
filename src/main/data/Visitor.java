package main.data;

import main.heroes.Knight;
import main.heroes.Pyromancer;
import main.heroes.Rogue;
import main.heroes.Wizard;

public interface Visitor {

    void helpHero(Wizard wizard);

    void helpHero(Rogue rogue);

    void helpHero(Pyromancer pyromancer);

    void helpHero(Knight knight);
}

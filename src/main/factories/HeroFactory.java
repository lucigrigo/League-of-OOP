package main.factories;

import main.data.HeroType;
import main.heroes.Hero;
import main.heroes.Knight;
import main.heroes.Pyromancer;
import main.heroes.Rogue;
import main.heroes.Wizard;

public class HeroFactory {

    public HeroFactory() {
    }

    public final Hero createHero(final HeroType heroType,
                                 final int initRow,
                                 final int initCol,
                                 final int index) {
        switch (heroType) {
            case ROGUE:
                return new Rogue(initCol, initRow, index);
            case WIZARD:
                return new Wizard(initCol, initRow, index);
            case KNIGHT:
                return new Knight(initCol, initRow, index);
            case PYROMANCER:
                return new Pyromancer(initCol, initRow, index);
            default:
                System.out.println("Invalid hero type!");
                System.exit(1);
                return null;
        }
    }
}

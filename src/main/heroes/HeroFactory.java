package main.heroes;

import main.data.HeroType;

public class HeroFactory {

    public HeroFactory() {
    }

    public final Hero createHero(final HeroType heroType,
                           final int initRow,
                           final int initCol,
                           final int index) {
        switch (heroType) {
            case ROGUE:
                return new Rogue(initRow, initCol, index);
            case WIZARD:
                return new Wizard(initRow, initCol, index);
            case KNIGHT:
                return new Knight(initRow, initCol, index);
            case PYROMANCER:
                return new Pyromancer(initRow, initCol, index);
            default:
                System.out.println("Invalid hero type!");
                System.exit(1);
                return null;
        }
    }
}

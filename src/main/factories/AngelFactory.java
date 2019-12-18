package main.factories;

import main.angels.*;
import main.data.AngelType;

public class AngelFactory {

    public AngelFactory() {

    }

    public final Angel createAngel(final AngelType angelType,
                                   final int spawnRow,
                                   final int spawnCol) {
        switch (angelType) {
            case Dracula:
                return new Dracula(spawnRow, spawnCol);
            case Spawner:
                return new Spawner(spawnRow, spawnCol);
            case GoodBoy:
                return new GoodBoy(spawnRow, spawnCol);
            case DarkAngel:
                return new DarkAngel(spawnRow, spawnCol);
            case LifeGiver:
                return new LifeGiver(spawnRow, spawnCol);
            case TheDoomer:
                return new TheDoomer(spawnRow, spawnCol);
            case SmallAngel:
                return new SmallAngel(spawnRow, spawnCol);
            case DamageAngel:
                return new DamageAngel(spawnRow, spawnCol);
            case LevelUpAngel:
                return new LevelUpAngel(spawnRow, spawnCol);
            case XPAngel:
                return new XPAngel(spawnRow, spawnCol);
            default:
                System.out.println("Invalid angel type!");
                return null;
        }
    }
}

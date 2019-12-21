package main.factories;

import main.angels.Angel;
import main.angels.DamageAngel;
import main.angels.DarkAngel;
import main.angels.Dracula;
import main.angels.GoodBoy;
import main.angels.LevelUpAngel;
import main.angels.LifeGiver;
import main.angels.SmallAngel;
import main.angels.Spawner;
import main.angels.TheDoomer;
import main.angels.XPAngel;
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

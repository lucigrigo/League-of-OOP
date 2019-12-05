package main.angels;

import main.data.AngelType;

public class AngelFactory {

    public AngelFactory() {

    }

    public final Angel createAngel(final AngelType angelType,
                             final int spawnRow,
                             final int spawnCol) {
        switch (angelType) {
            case DRACULA:
                return new Dracula(spawnRow, spawnCol);
            case SPAWNER:
                return new Spawner(spawnRow, spawnCol);
            case GOOD_BOY:
                return new GoodBoy(spawnRow, spawnCol);
            case DARK_ANGEL:
                return new DarkAngel(spawnRow, spawnCol);
            case LIFE_GIVER:
                return new LifeGiver(spawnRow, spawnCol);
            case THE_DOOMER:
                return new TheDoomer(spawnRow, spawnCol);
            case SMALL_ANGEL:
                return new SmallAngel(spawnRow, spawnCol);
            case DAMAGE_ANGEL:
                return new DamageAngel(spawnRow, spawnCol);
            case LEVEL_UP_ANGEL:
                return new LevelUpAngel(spawnRow, spawnCol);
            case EXPERIENCE_GIVER:
                return new ExperienceGiver(spawnRow, spawnCol);
            default:
                System.out.println("Invalid hero type!");
                return null;
        }
    }
}

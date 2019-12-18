package main.gameplay;

import main.data.LocationType;

public final class Map {

    private static Map instance = null;
    private LocationType[][] map;

    private Map() {

    }

    public static Map getInstance() {
        if (instance == null) {
            instance = new Map();
        }
        return instance;
    }

    public void setDimensions(final int height,
                              final int width) {
        this.map = new LocationType[height][width];
    }

    public void setMap(final LocationType[][] map) {
        this.map = map;
    }
}

package main.gameplay;

public final class Map {

    private static Map instance = null;

    private Map() {

    }

    public Map getInstance() {
        if (instance == null) {
            instance = new Map();
        }
        return instance;
    }
}

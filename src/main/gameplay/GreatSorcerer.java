package main.gameplay;

public final class GreatSorcerer {
//        implements GameObserver{

    private static GreatSorcerer instance = null;

    private GreatSorcerer() {

    }

    public GreatSorcerer getInstance() {
        if (instance == null) {
            instance = new GreatSorcerer();
        }
        return instance;
    }
}

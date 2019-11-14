package main.data;

public final class Constants {
    private static Constants instance = null;

    private Constants() {
        ;
    }

    public static Constants getInstance() {
        if (instance == null) {
            instance = new Constants();
        }
        return instance;
    }

    private static final int WIZARD_INITIAL_HEALTH = 400;
    private static final int WIZARD_HEALTH_RATIO = 30;
    private static final int ROGUE_INITIAL_HEALTH = 600;
    private static final int ROGUE_HEALTH_RATIO = 40;
    private static final int PYROMANCER_INITIAL_HEALTH = 500;
    private static final int PYROMANCER_HEALTH_RATIO = 50;
    private static final int KNIGHT_INITIAL_HEALTH = 900;
    private static final int KNIGHT_HEALTH_RATIO = 80;

    public int getWizardInitialHealth() {
        return WIZARD_INITIAL_HEALTH;
    }

    public int getWizardHealthRatio() {
        return WIZARD_HEALTH_RATIO;
    }

    public int getRogueInitialHealth() {
        return ROGUE_INITIAL_HEALTH;
    }

    public int getRogueHealthRatio() {
        return ROGUE_HEALTH_RATIO;
    }

    public int getPyromancerInitialHealth() {
        return PYROMANCER_INITIAL_HEALTH;
    }

    public int getPyromancerHealthRatio() {
        return PYROMANCER_HEALTH_RATIO;
    }

    public int getKnightInitialHealth() {
        return KNIGHT_INITIAL_HEALTH;
    }

    public int getKnightHealthRatio() {
        return KNIGHT_HEALTH_RATIO;
    }
}

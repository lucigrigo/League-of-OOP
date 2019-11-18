package main.data;

public final class Constants {
    private static Constants instance = null;

    private Constants() {
        // TODO add anything here if needed
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

    // Abilitatile unui PYROMANCER
    // FIREBLAST
    private static final int PYROMANCER_FIREBLAST_BASE_DAMAGE = 350;
    private static final int PYROMANCER_FIREBLAST_LEVEL_SCALING_BASE_DAMAGE = 50;
    private static final int PYROMANCER_FIREBLAST_BONUS_VERSUS_ROGUE = -20;
    private static final int PYROMANCER_FIREBLAST_BONUS_VERSUS_KNIGHT = 20;
    private static final int PYROMANCER_FIREBLAST_BONUS_VERSUS_PYROMANCER = -10;
    private static final int PYROMANCER_FIREBLAST_BONUS_VERSUS_WIZARD = 5;
    // IGNITE
    private static final int PYROMANCER_IGNITE_BASE_DAMAGE = 150;
    private static final int PYROMANCER_IGNITE_LEVEL_SCALING_BASE_DAMAGE = 20;
    private static final int PYROMANCER_IGNITE_ROUND_DAMAGE = 50;
    private static final int PYROMANCER_IGNITE_LEVEL_SCALING_ROUND_DAMAGE = 30;
    private static final int PYROMANCER_IGNITE_SUCCESSIVE_ROUNDS_NUMBER = 2;
    private static final int PYROMANCER_IGNITE_BONUS_VERSUS_ROGUE = -20;
    private static final int PYROMANCER_IGNITE_BONUS_VERSUS_KNIGHT = 20;
    private static final int PYROMANCER_IGNITE_BONUS_VERSUS_PYROMANCER = -10;
    private static final int PYROMANCER_IGNITE_BONUS_VERSUS_WIZARD = 5;
    // Bonusul pentru VOLCANIC
    private static final int PYROMANCER_VOLCANIC_BONUS = 25;

    // Abilitatile unui KNIGHT
    // EXECUTE
    private static final int KNIGHT_EXECUTE_BASE_DAMAGE = 200;
    private static final int KNIGHT_EXECUTE_LEVEL_SCALING_BASE_DAMAGE = 30;
    private static final int KNIGHT_EXECUTE_HP_LIMIT_PERCENTAGE = 20;
    private static final int KNIGHT_EXECUTE_HP_LIMIT_LEVEL_SCALING_PERCENTAGE = 1;
    private static final int KNIGHT_EXECUTE_MAXIMUM_HP_PERCENTAGE = 40;
    private static final int KNIGHT_EXECUTE_BONUS_VERSUS_ROGUE = 15;
    private static final int KNIGHT_EXECUTE_BONUS_VERSUS_KNIGHT = 0;
    private static final int KNIGHT_EXECUTE_BONUS_VERSUS_PYROMANCER = 10;
    private static final int KNIGHT_EXECUTE_BONUS_VERSUS_WIZARD = -20;
    // SLAM
    private static final int KNIGHT_SLAM_BASE_DAMAGE = 100;
    private static final int KNIGHT_SLAM_LEVEL_SCALING_BASE_DAMAGE = 40;
    private static final int KNIGHT_SLAM_BONUS_VERSUS_ROGUE = -20;
    private static final int KNIGHT_SLAM_BONUS_VERSUS_KNIGHT = 20;
    private static final int KNIGHT_SLAM_BONUS_VERSUS_PYROMANCER = -10;
    private static final int KNIGHT_SLAM_BONUS_VERSUS_WIZARD = 5;
    // Bonusul pentru LAND
    private static final int KNIGHT_LAND_BONUS = 15;

    // Abilitatile unui WIZARD
    // DRAIN
    private static final int WIZARD_DRAIN_BASE_PERCENTAGE = 20;
    private static final int WIZARD_DRAIN_LEVEL_SCALING_PERCENTAGE = 5;
    private static final int WIZARD_DRAIN_BONUS_VERSUS_ROGUE = -20;
    private static final int WIZARD_DRAIN_BONUS_VERSUS_KNIGHT = 20;
    private static final int WIZARD_DRAIN_BONUS_VERSUS_PYROMANCER = -10;
    private static final int WIZARD_DRAIN_BONUS_VERSUS_WIZARD = 5;
    // DEFLECT
    private static final int WIZARD_DEFLECT_BASE_PERCENTAGE = 35;
    private static final int WIZARD_DEFLECT_LEVEL_SCALING_BASE_PERCENTAGE = 2;
    private static final int WIZRD_DEFLECT_MAXIMUM_PERCENTAGE = 70;
    private static final int WIZARD_DEFLECT_BONUS_VERSUS_ROGUE = 20;
    private static final int WIZARD_DEFLECT_BONUS_VERSUS_KNIGHT = 40;
    private static final int WIZARD_DEFLECT_BONUS_VERSUS_PYROMANCER = 30;
    private static final int WIZARD_DEFLECT_BONUS_VERSUS_WIZARD = 0;
    // Bonusul pentru DESERT
    private static final int WIZRD_DESERT_BONUS = 10;

    // Abilitatile unui ROGUE
    // BACKSTAB
    private static final int ROGUE_BACKSTAB_BASE_DAMAGE = 200;
    private static final int ROGUE_BACKSTAB_LEVEL_SCALING_BASE_DAMAGE = 20;
    private static final double ROGUE_BACKSTAB_CRITICAL_HIT_RATIO = 1.5d;
    private static final int ROGUE_BACKSTAB_CRITICAL_HIT_OCCURENCE = 3;
    private static final int ROGUE_BACKSTAB_BONUS_VERSUS_ROGUE = 20;
    private static final int ROGUE_BACKSTAB_BONUS_VERSUS_KNIGHT = -10;
    private static final int ROGUE_BACKSTAB_BONUS_VERSUS_PYROMANCER = 25;
    private static final int ROGUE_BACKSTAB_BONUS_VERSUS_WIZARD = 25;
    // PARALYSIS
    private static final int ROGUE_PARALYSIS_BASE_DAMAGE = 40;
    private static final int ROGUE_PARALYSIS_LEVEL_SCALING_BASE_DAMAGE = 10;
    private static final int ROGUE_PARALYSIS_ROUNDS_NORMAL_NUMBER = 3;
    private static final int ROGUE_PARALYSIS_ROUNDS_WOODS_NUMBER = 6;
    private static final int ROGUE_PARALYSIS_BONUS_VERSUS_ROGUE = -10;
    private static final int ROGUE_PARALYSIS_BONUS_VERSUS_KNIGHT = -20;
    private static final int ROGUE_PARALYSIS_BONUS_VERSUS_PYROMANCER = 25;
    private static final int ROGUE_PARALYSIS_BONUS_VERSUS_WIZARD = 25;
    // Bonusul pentru WOODS
    private static final int ROGUE_WOODS_BONUS = 15;

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

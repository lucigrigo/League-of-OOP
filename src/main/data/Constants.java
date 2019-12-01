package main.data;

/**
 * Class where constants are saved.
 */
public final class Constants {

    private Constants() {
        // just to trigger checkstyle :)
    }

    // level-up
    public static final int WIN_MAGIC_200 = 200;
    public static final int WIN_MAGIC_40 = 40;
    public static final int EXPERIENCE_BASE = 250;
    public static final int EXPERIENCE_SCALING = 50;

    // health and scaling
    public static final int WIZARD_INITIAL_HEALTH = 400;
    public static final int WIZARD_HEALTH_RATIO = 30;
    public static final int ROGUE_INITIAL_HEALTH = 600;
    public static final int ROGUE_HEALTH_RATIO = 40;
    public static final int PYROMANCER_INITIAL_HEALTH = 500;
    public static final int PYROMANCER_HEALTH_RATIO = 50;
    public static final int KNIGHT_INITIAL_HEALTH = 900;
    public static final int KNIGHT_HEALTH_RATIO = 80;

    // PYROMANCER abilities
    // FIREBLAST
    public static final float PYROMANCER_FIREBLAST_BASE_DAMAGE = 350f;
    public static final float PYROMANCER_FIREBLAST_LEVEL_SCALING_BASE_DAMAGE = 50f;
    public static final float PYROMANCER_FIREBLAST_BONUS_VERSUS_ROGUE = 0.8f;
    public static final float PYROMANCER_FIREBLAST_BONUS_VERSUS_KNIGHT = 1.2f;
    public static final float PYROMANCER_FIREBLAST_BONUS_VERSUS_PYROMANCER = 0.9f;
    public static final float PYROMANCER_FIREBLAST_BONUS_VERSUS_WIZARD = 1.05f;
    // IGNITE
    public static final float PYROMANCER_IGNITE_BASE_DAMAGE = 150f;
    public static final float PYROMANCER_IGNITE_LEVEL_SCALING_BASE_DAMAGE = 20f;
    public static final float PYROMANCER_IGNITE_ROUND_DAMAGE = 50f;
    public static final float PYROMANCER_IGNITE_LEVEL_SCALING_ROUND_DAMAGE = 30f;
    public static final int PYROMANCER_IGNITE_DURATION = 2;
    public static final float PYROMANCER_IGNITE_BONUS_VERSUS_ROGUE = 0.8f;
    public static final float PYROMANCER_IGNITE_BONUS_VERSUS_KNIGHT = 1.2f;
    public static final float PYROMANCER_IGNITE_BONUS_VERSUS_PYROMANCER = 0.9f;
    public static final float PYROMANCER_IGNITE_BONUS_VERSUS_WIZARD = 1.05f;
    // VOLCANIC bonus
    public static final float PYROMANCER_VOLCANIC_BONUS = 1.25f;

    // KNIGHT abilities
    // EXECUTE
    public static final float KNIGHT_EXECUTE_BASE_DAMAGE = 200;
    public static final float KNIGHT_EXECUTE_LEVEL_SCALING_BASE_DAMAGE = 30;
    public static final float KNIGHT_EXECUTE_HP_LIMIT_PERCENTAGE = 0.2f;
    public static final float KNIGHT_EXECUTE_HP_LIMIT_LEVEL_SCALING_PERCENTAGE = 0.01f;
    public static final float KNIGHT_EXECUTE_MAXIMUM_HP_PERCENTAGE = 0.4f;
    public static final float KNIGHT_EXECUTE_BONUS_VERSUS_ROGUE = 1.15f;
    public static final float KNIGHT_EXECUTE_BONUS_VERSUS_KNIGHT = 1.0f;
    public static final float KNIGHT_EXECUTE_BONUS_VERSUS_PYROMANCER = 1.1f;
    public static final float KNIGHT_EXECUTE_BONUS_VERSUS_WIZARD = 0.8f;
    // SLAM
    public static final float KNIGHT_SLAM_BASE_DAMAGE = 100;
    public static final float KNIGHT_SLAM_LEVEL_SCALING_BASE_DAMAGE = 40;
    public static final float KNIGHT_SLAM_BONUS_VERSUS_ROGUE = 0.8f;
    public static final float KNIGHT_SLAM_BONUS_VERSUS_KNIGHT = 1.2f;
    public static final float KNIGHT_SLAM_BONUS_VERSUS_PYROMANCER = 0.9f;
    public static final float KNIGHT_SLAM_BONUS_VERSUS_WIZARD = 1.05f;
    public static final int KNIGHT_SLAM_DURATION = 1;
    // LAND bonus
    public static final float KNIGHT_LAND_BONUS = 1.15f;

    // WIZARD abilities
    // DRAIN
    public static final float WIZARD_DRAIN_BASE_PERCENTAGE = 0.2f;
    public static final float WIZARD_DRAIN_LEVEL_SCALING_PERCENTAGE = 0.05f;
    public static final float WIZARD_DRAIN_BONUS_VERSUS_ROGUE = 0.8f;
    public static final float WIZARD_DRAIN_BONUS_VERSUS_KNIGHT = 1.2f;
    public static final float WIZARD_DRAIN_BONUS_VERSUS_PYROMANCER = 0.9f;
    public static final float WIZARD_DRAIN_BONUS_VERSUS_WIZARD = 1.05f;
    public static final float WIZARD_DRAIN_HEALTH_PERCENTAGE = 0.3f;
    // DEFLECT
    public static final float WIZARD_DEFLECT_BASE_PERCENTAGE = 0.35f;
    public static final float WIZARD_DEFLECT_LEVEL_SCALING_BASE_PERCENTAGE = 0.02f;
    public static final float WIZARD_DEFLECT_MAXIMUM_PERCENTAGE = 0.7f;
    public static final float WIZARD_DEFLECT_BONUS_VERSUS_ROGUE = 1.2f;
    public static final float WIZARD_DEFLECT_BONUS_VERSUS_KNIGHT = 1.4f;
    public static final float WIZARD_DEFLECT_BONUS_VERSUS_PYROMANCER = 1.3f;
    // DESERT bonus
    public static final float WIZARD_DESERT_BONUS = 1.1f;

    // ROGUE abilities
    // BACKSTAB
    public static final float ROGUE_BACKSTAB_BASE_DAMAGE = 200;
    public static final float ROGUE_BACKSTAB_LEVEL_SCALING_BASE_DAMAGE = 20;
    public static final float ROGUE_BACKSTAB_CRITICAL_HIT_RATIO = 1.5f;
    public static final int ROGUE_BACKSTAB_CRITICAL_HIT_OCCURENCE = 3;
    public static final float ROGUE_BACKSTAB_BONUS_VERSUS_ROGUE = 1.2f;
    public static final float ROGUE_BACKSTAB_BONUS_VERSUS_KNIGHT = 0.9f;
    public static final float ROGUE_BACKSTAB_BONUS_VERSUS_PYROMANCER = 1.25f;
    public static final float ROGUE_BACKSTAB_BONUS_VERSUS_WIZARD = 1.25f;
    // PARALYSIS
    public static final float ROGUE_PARALYSIS_BASE_DAMAGE = 40;
    public static final float ROGUE_PARALYSIS_LEVEL_SCALING_BASE_DAMAGE = 10;
    public static final int ROGUE_PARALYSIS_ROUNDS_NUMBER_NORMAL = 3;
    public static final int ROGUE_PARALYSIS_ROUNDS_NUMBER_WOODS = 6;
    public static final float ROGUE_PARALYSIS_BONUS_VERSUS_ROGUE = 0.9f;
    public static final float ROGUE_PARALYSIS_BONUS_VERSUS_KNIGHT = 0.8f;
    public static final float ROGUE_PARALYSIS_BONUS_VERSUS_PYROMANCER = 1.2f;
    public static final float ROGUE_PARALYSIS_BONUS_VERSUS_WIZARD = 1.25f;
    // WOODS bonus
    public static final float ROGUE_WOODS_BONUS = 1.15f;
}

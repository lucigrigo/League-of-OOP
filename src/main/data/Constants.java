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

    // --- Angel bonuses ---
    // DamageAngel
    public static final float DAMAGEANGEL_KNIGHT_BONUS = 0.15f;
    public static final float DAMAGEANGEL_ROGUE_BONUS = 0.3f;
    public static final float DAMAGEANGEL_PYROMANCER_BONUS = 0.2f;
    public static final float DAMAGEANGEL_WIZARD_BONUS = 0.4f;

    // DarkAngel
    public static final int DARKANGEL_KNIGHT_BONUS = 40;
    public static final int DARKANGEL_PYROMANCER_BONUS = 30;
    public static final int DARKANGEL_ROGUE_BONUS = 10;
    public static final int DARKANGEL_WIZARD_BONUS = 20;

    // Dracula
    public static final float DRACULA_KNIGHT_BONUS_DAMAGE = -0.2f;
    public static final int DRACULA_KNIGHT_BONUS_HP = 60;
    public static final float DRACULA_PYROMANCER_BONUS_DAMAGE = -0.3f;
    public static final int DRACULA_PYROMANCER_BONUS_HP = 40;
    public static final float DRACULA_ROGUE_BONUS_DAMAGE = -0.1f;
    public static final int DRACULA_ROGUE_BONUS_HP = 35;
    public static final float DRACULA_WIZARD_BONUS_DAMAGE = -0.4f;
    public static final int DRACULA_WIZARD_BONUS_HP = 20;

    // GoodBoy
    public static final float GOODBOY_KNIGHT_BONUS_DAMAGE = 0.4f;
    public static final int GOODBOY_KNIGHT_BONUS_HP = 20;
    public static final float GOODBOY_PYROMANCER_BONUS_DAMAGE = 0.5f;
    public static final int GOODBOY_PYROMANCER_BONUS_HP = 30;
    public static final float GOODBOY_ROGUE_BONUS_DAMAGE = 0.4f;
    public static final int GOODBOY_ROGUE_BONUS_HP = 40;
    public static final float GOODBOY_WIZARD_BONUS_DAMAGE = 0.3f;
    public static final int GOODBOY_WIZARD_BONUS_HP = 50;

    // LevelUpAngel
    public static final float LEVELUPANGEL_KNIGHT_BONUS = 0.1f;
    public static final float LEVELUPANGEL_PYROMANCER_BONUS = 0.2f;
    public static final float LEVELUPANGEL_ROGUE_BONUS = 0.15f;
    public static final float LEVELUPANGEL_WIZARD_BONUS = 0.25f;

    // LifeGiver
    public static final int LIFEGIVER_KNIGHT_BONUS = 100;
    public static final int LIFEGIVER_PYROMANCER_BONUS = 80;
    public static final int LIFEGIVER_ROGUE_BONUS = 90;
    public static final int LIFEGIVER_WIZARD_BONUS = 120;

    // SmallAngel
    public static final float SMALLANGEL_KNIGHT_BONUS_DAMAGE = 0.1f;
    public static final int SMALLANGEL_KNIGHT_BONUS_HP = 10;
    public static final float SMALLANGEL_PYROMANCER_BONUS_DAMAGE = 0.15f;
    public static final int SMALLANGEL_PYROMANCER_BONUS_HP = 15;
    public static final float SMALLANGEL_ROGUE_BONUS_DAMAGE = 0.05f;
    public static final int SMALLANGEL_ROGUE_BONUS_HP = 20;
    public static final float SMALLANGEL_WIZARD_BONUS_DAMAGE = 0.1f;
    public static final int SMALLANGEL_WIZARD_BONUS_HP = 25;

    // Spawner
    public static final int SPAWNER_KNIGHT_HP = 200;
    public static final int SPAWNER_PYROMANCER_HP = 150;
    public static final int SPAWNER_ROGUE_HP = 180;
    public static final int SPAWNER_WIZARD_HP = 120;

    // XPAngel
    public static final int XPANGEL_KNIGHT_BONUS_XP = 45;
    public static final int XPANGEL_PYROMANCER_BONUS_XP = 50;
    public static final int XPANGEL_ROGUE_BONUS_XP = 40;
    public static final int XPANGEL_WIZARD_BONUS_XP = 60;

    // Knight Strategies
    // Attack Strategy
    public static final float KNIGHT_ATTACK_STRATEGY_LOW_MARGIN = 1 / 3f;
    public static final float KNIGHT_ATTACK_STRATEGY_HIGH_MARGIN = 1 / 2f;
    public static final float KNIGHT_ATTACK_STRATEGY_HP_MODIFIER = -1 / 5f;
    public static final float KNIGHT_ATTACK_STRATEGY_DAMAGE_MODIFIER = 0.5f;
    // Defence Strategy
    public static final float KNIGHT_DEFENCE_STRATEGY_HIGH_MARGIN = 1 / 3f;
    public static final float KNIGHT_DEFENCE_STRATEGY_HP_MODIFIER = 1 / 4f;
    public static final float KNIGHT_DEFENCE_STRATEGY_DAMAGE_MODIFIER = -0.2f;

    // Pyromancer Strategies
    // Attack Strategy
    public static final float PYROMANCER_ATTACK_STRATEGY_LOW_MARGIN = 1 / 4f;
    public static final float PYROMANCER_ATTACK_STRATEGY_HIGH_MARGIN = 1 / 2f;
    public static final float PYROMANCER_ATTACK_STRATEGY_HP_MODIFIER = -1 / 4f;
    public static final float PYROMANCER_ATTACK_STRATEGY_DAMAGE_MODIFIER = 0.7f;
    // Defence Strategy
    public static final float PYROMANCER_DEFENCE_STRATEGY_HIGH_MARGIN = 1 / 4f;
    public static final float PYROMANCER_DEFENCE_STRATEGY_HP_MODIFIER = 1 / 3f;
    public static final float PYROMANCER_DEFENCE_STRATEGY_DAMAGE_MODIFIER = -0.3f;

    // Rogue Strategies
    // Attack Strategy
    public static final float ROGUE_ATTACK_STRATEGY_HIGH_MARGIN = 1 / 5f;
    public static final float ROGUE_ATTACK_STRATEGY_LOW_MARGIN = 1 / 7f;
    public static final float ROGUE_ATTACK_STRATEGY_HP_MODIFIER = -1 / 7f;
    public static final float ROGUE_ATTACK_STRATEGY_DAMAGE_MODIFIER = 0.4f;
    // Defence Strategy
    public static final float ROGUE_DEFENCE_STRATEGY_HIGH_MARGIN = 1 / 7f;
    public static final float ROGUE_DEFENCE_STRATEGY_HP_MODIFIER = 1 / 2f;
    public static final float ROGUE_DEFENCE_STRATEGY_DAMAGE_MODIFIER = -0.1f;

    // Wizard Strategies
    // Attack Strategy
    public static final float WIZARD_ATTACK_STRATEGY_HIGH_MARGIN = 1 / 2f;
    public static final float WIZARD_ATTACK_STRATEGY_LOW_MARGIN = 1 / 4f;
    public static final float WIZARD_ATTACK_STRATEGY_HP_MODIFIER = -1 / 10f;
    public static final float WIZARD_ATTACK_STRATEGY_DAMAGE_MODIFIER = 0.6f;
    // Defence Strategy
    public static final float WIZARD_DEFENCE_STRATEGY_HIGH_MARGIN = 1 / 4f;
    public static final float WIZARD_DEFENCE_STRATEGY_HP_MODIFIER = 1 / 5f;
    public static final float WIZARD_DEFENCE_STRATEGY_DAMAGE_MODIFIER = -0.2f;
}

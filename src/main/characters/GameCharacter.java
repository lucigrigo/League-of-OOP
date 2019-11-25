package main.characters;

import main.data.Constants;
import main.data.LocationType;
import main.data.MovementType;
import main.gameplay.Ability;
import main.gameplay.OverTimeAbility;

public abstract class GameCharacter {
    private int colon;
    private int line;
    private int currentHealth;
    private int currentExperience;
    private int level;
    private CharacterType type;
    private boolean isIncapacitated;
    private boolean isCurrentlyFighting;
    private String name;
    private OverTimeAbility abilityAffectedBy;
    private boolean hasMadeAKillThisRound;

//    public GameCharacter(final int initCol,
//                         final int initLin) {
//        this.colon = initCol;
//        this.line = initLin;
//    }

    public GameCharacter(final int initCol,
                         final int initLin,
                         final int currentHealth,
                         final int currentExperience,
                         final CharacterType type,
                         final String name) {
        this.colon = initCol;
        this.line = initLin;
        this.currentHealth = currentHealth;
        this.currentExperience = currentExperience;
        this.level = 0;
        this.type = type;
        this.isIncapacitated = false;
        this.isCurrentlyFighting = false;
        this.name = name;
        this.abilityAffectedBy = null;
        this.hasMadeAKillThisRound = false;
    }

    public boolean isHasMadeAKillThisRound() {
        return hasMadeAKillThisRound;
    }

    public void setHasMadeAKillThisRound(boolean hasMadeAKillThisRound) {
        this.hasMadeAKillThisRound = hasMadeAKillThisRound;
    }

    public final int getColon() {
        return colon;
    }

    public final int getRow() {
        return line;
    }

    public final int getHealth() {
        return currentHealth;
    }

    public final int getExperience() {
        return currentExperience;
    }

    public final CharacterType getType() {
        return type;
    }

    public final String getName() {
        return name;
    }

    public final int getLevel() {
        return level;
    }

    public final boolean isCurrentlyFighting() {
        return isCurrentlyFighting;
    }

    public final boolean isIncapacitated() {
        return this.abilityAffectedBy != null
                && this.abilityAffectedBy.isAbilityToIncapacitate();
    }

    public final void applyMove(MovementType move) {
        if (!this.isDead() ||
                !this.isIncapacitated) {
            switch (move) {
                case DOWN:
                    this.line = this.line + 1;
                    break;
                case LEFT:
                    this.colon = this.colon - 1;
                    break;
                case RIGHT:
                    this.colon = this.colon + 1;
                    break;
                case UP:
                    this.line = this.line - 1;
                    break;
                case NONE:
                    // do nothing
                    break;
                default:
                    System.out.println("Error applying move!");
                    System.exit(0);
                    break;
            }
        }
    }

    public abstract Ability computeDamageAgainst(GameCharacter enemy,
                                                 LocationType location,
                                                 boolean addRaceModifier);

    public abstract OverTimeAbility getAbilityOverTime(GameCharacter enemy,
                                                       LocationType location);

    public abstract int getMaxHealth();

    public abstract float getDamageWithoutRaceModifier(GameCharacter enemy,
                                                       LocationType location);

    public final OverTimeAbility getAbilityAffectedBy() {
        return abilityAffectedBy;
    }

    public void setAbilityAffectedBy(OverTimeAbility abilityAffectedBy) {
        this.abilityAffectedBy = abilityAffectedBy;
    }

    public void takeDamage(final Ability ability,
                           final GameCharacter enemy,
                           final LocationType location,
                           final boolean isOvertimeAbility) {

        if (ability != null) {
            System.out.println(this.name + " are hp " + this.getHealth() + " si ia dmg "
                    + ability.getDamage());
            if (ability.getCaster() != null) {
                System.out.println(" de la "
                        + ability.getCaster().getName()
                        + " cu hp " + ability.getCaster().getHealth()
                        + " la linia " + ability.getCaster().getRow()
                        + " si coloana " + ability.getCaster().getColon());
            }
            this.currentHealth -= ability.getDamage();
            if (this.currentHealth <= 0
                    && !isOvertimeAbility) {
                enemy.setHasMadeAKillThisRound(true);
                enemy.fightWon(this.getLevel());
                this.hasDied();
            }
        }

    }

    public void doRoundEndingRoutine() {
//        System.out.println("acsuihacasca? aaaaaa");
//        System.out.println(this.getAbilityAffectedBy() != null);
        if ((this.getAbilityAffectedBy() != null)
                && this.getAbilityAffectedBy().isFirstRound()) {
            this.getAbilityAffectedBy().setFirstRound(false);
        }
        if (this.hasMadeAKillThisRound) {
            this.checkForLevelUp();
            this.hasMadeAKillThisRound = false;
        }
    }

    public void fightWon(final int loserLevel) {
        System.out.println(this.getName() + " a castigat lupta!");
        this.currentExperience = this.currentExperience
                + Math.max(0, Constants.getInstance().getWinMagic200()
                - (this.getLevel() - loserLevel)
                * Constants.getInstance().getWinMagic40());
//        checkForLevelUp();
    }

    public void checkForLevelUp() {
        if ((this.currentExperience
                >= Constants.getInstance().getExperienceBase()
                + this.getLevel()
                * Constants.getInstance().getExperienceScaling())
                && !this.isDead()) {
            this.level = (this.currentExperience
                    - Constants.getInstance().getExperienceBase())
                    / Constants.getInstance().getExperienceScaling() + 1;
            this.currentHealth = this.getMaxHealth();
        }
        System.out.println("---\n--- " + this.getName()
                + " " + this.getExperience()
                + " " + this.getLevel() + "\n---");
    }

    public void hasDied() {
        this.currentHealth = 0;
    }

    public final boolean isDead() {
        return this.currentHealth <= 0;
    }
}

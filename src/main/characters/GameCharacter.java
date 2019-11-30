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
    private int initialRoundHealth;

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
        this.initialRoundHealth = currentHealth;
        this.currentExperience = currentExperience;
        this.level = 0;
        this.type = type;
        this.isIncapacitated = false;
        this.isCurrentlyFighting = false;
        this.name = name;
        this.abilityAffectedBy = null;
        this.hasMadeAKillThisRound = false;
    }

//    public boolean isHasMadeAKillThisRound() {
//        return hasMadeAKillThisRound;
//    }

    private void setHasMadeAKillThisRound(boolean hasMadeAKillThisRound) {
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

//    public final boolean isCurrentlyFighting() {
//        return isCurrentlyFighting;
//    }

    public final boolean isIncapacitated() {
//        if (this.abilityAffectedBy != null) {
//            System.out.println(this.abilityAffectedBy.isAbilityToIncapacitate() + " " + this.getName());
//        }
//        System.out.println(this.abilityAffectedBy.isAbilityToIncapacitate());
        return this.abilityAffectedBy != null
                && this.abilityAffectedBy.isAbilityToIncapacitate()
                && this.abilityAffectedBy.getIncapacityDuration() > 0;
    }

    public final void applyMove(MovementType move) {
//        System.out.println("hero " + this.getName() + " moves " + move.toString());
        if (!this.isDead()
                && !this.isIncapacitated) {
//                && this.getAbilityAffectedBy() != null
//                && !this.getAbilityAffectedBy().isAbilityToIncapacitate()) {
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

    public abstract float computeInitialDamage(LocationType location);

    public abstract float computeInitialOvertimeDamage(LocationType location);

    public abstract void getAttackedBy(GameCharacter enemy,
                                       LocationType location);

    public abstract float attack(Wizard enemy,
                                 LocationType location,
                                 boolean addRaceModifier,
                                 boolean isForDeflectPurpose);

    public abstract float attack(Rogue enemy,
                                 LocationType location,
                                 boolean addRaceModifier,
                                 boolean isForDeflectPurpose);

    public abstract float attack(Pyromancer enemy,
                                 LocationType location,
                                 boolean addRaceModifier,
                                 boolean isForDeflectPurpose);

    public abstract float attack(Knight enemy,
                                 LocationType location,
                                 boolean addRaceModifier,
                                 boolean isForDeflectPurpose);

    public abstract void getAffectedBy(GameCharacter enemy,
                                       LocationType location);

    public abstract float affectOvertime(Wizard enemy,
                                         LocationType location,
                                         boolean startNow,
                                         boolean addRaceModifier);

    public abstract float affectOvertime(Rogue enemy,
                                         LocationType location,
                                         boolean startNow,
                                         boolean addRaceModifier);

    public abstract float affectOvertime(Knight enemy,
                                         LocationType location,
                                         boolean startNow,
                                         boolean addRaceModifier);

    public abstract float affectOvertime(Pyromancer enemy,
                                         LocationType location,
                                         boolean startNow,
                                         boolean addRaceModifier);

    public final OverTimeAbility getAbilityAffectedBy() {
        return abilityAffectedBy;
    }

    public void setAbilityAffectedBy(OverTimeAbility abilityAffectedBy) {
        this.abilityAffectedBy = abilityAffectedBy;
    }

//    public void takeDamage(final Ability ability,
//                           final GameCharacter enemy,
//                           final LocationType location,
//                           final boolean isOvertimeAbility) {
//
//        if (ability != null) {
////            System.out.println(this.name + " are hp " + this.getHealth() + " si ia dmg "
////                    + ability.getDamage());
//            if (ability.getCaster() != null) {
////                System.out.println(" de la "
////                        + ability.getCaster().getName()
////                        + " cu hp " + ability.getCaster().getHealth()
////                        + " la linia " + ability.getCaster().getRow()
////                        + " si coloana " + ability.getCaster().getColon());
//            }
//            this.currentHealth -= ability.getDamage();
//            if (this.currentHealth <= 0
//                    && !isOvertimeAbility) {
//                enemy.setHasMadeAKillThisRound(true);
//                enemy.fightWon(this.getLevel());
//                this.hasDied();
//            } else if (this.currentHealth <= 0) {
//                this.hasDied();
//            }
//        }
//
//    }

    public boolean takeDamage(final int damage,
                              final boolean isOverTimeAbility) {
        this.currentHealth -= damage;
        if (this.currentHealth <= 0
                && !isOverTimeAbility) {
            this.hasDied();
            return true;
        } else if (this.currentHealth <= 0) {
            this.hasDied();
        }
        return false;
    }

    public void doRoundEndingRoutine() {
//        System.out.println("acsuihacasca? aaaaaa");
//        System.out.println(this.getAbilityAffectedBy() != null);
//        if ((this.getAbilityAffectedBy() != null)
//                && this.getAbilityAffectedBy().isFirstRound()) {
//            this.getAbilityAffectedBy().setFirstRound(false);
//        }
        if (this.hasMadeAKillThisRound) {
            this.checkForLevelUp();
            this.hasMadeAKillThisRound = false;
        }
        this.initialRoundHealth = currentHealth;
    }

    void fightWon(final int loserLevel) {
//        System.out.println(this.getName() + " a castigat lupta!");
        this.currentExperience = this.currentExperience
                + Math.max(0, Constants.WIN_MAGIC_200
                - (this.getLevel() - loserLevel)
                * Constants.WIN_MAGIC_40);
        this.setHasMadeAKillThisRound(true);
//        System.out.println("exp " + this.currentExperience);
//        checkForLevelUp();
    }

    private void checkForLevelUp() {
        if ((this.currentExperience
                >= Constants.EXPERIENCE_BASE
                + this.getLevel()
                * Constants.EXPERIENCE_SCALING)
                && !this.isDead()) {
            this.level = (this.currentExperience
                    - Constants.EXPERIENCE_BASE)
                    / Constants.EXPERIENCE_SCALING + 1;
            this.currentHealth = this.getMaxHealth();
        }
//        System.out.println("---\n--- " + this.getName()
//                + " " + this.getExperience()
//                + " " + this.getLevel() + "\n---");
    }

    void hasDied() {
        this.currentHealth = 0;
    }

    public final boolean isDead() {
        return this.currentHealth <= 0;
    }

    @Override
    public String toString() {
        if (!this.isDead()) {
            return this.getName() + " " + this.getLevel() + " " + this.getExperience() + " "
                    + this.getHealth() + " " + this.getRow() + " " + this.getColon() + "";
        }
        return this.name + " dead";
    }

    int getInitialRoundHealth() {
        return initialRoundHealth;
    }

//    public void setInitialRoundHealth(int initialRoundHealth) {
//        this.initialRoundHealth = initialRoundHealth;
//    }
}

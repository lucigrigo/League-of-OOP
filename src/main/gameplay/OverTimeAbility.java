package main.gameplay;

import main.characters.CharacterType;
import main.characters.GameCharacter;
import main.data.Constants;

public final class OverTimeAbility {

    private int instantDamage;
    private int overtimeDamage;
    private int duration;
    private boolean abilityToIncapacitate;
    private GameCharacter caster;
    private GameCharacter victim;
    private int totalDamage;

    public OverTimeAbility(GameCharacter caster, GameCharacter victim) {
        this.instantDamage = 0;
        this.overtimeDamage = 0;
        this.duration = 0;
        this.abilityToIncapacitate = false;
        this.caster = caster;
        this.victim = victim;
    }

    public int getOvertimeDamage() {
        if (caster.getType() == CharacterType.PYROMANCER) {
            if (duration == Constants.getInstance().getPyromancerIgniteInitialDuration()) {
                return instantDamage;
            }
        }
        return overtimeDamage;
    }

    public void setOvertimeDamage(int overtimeDamage) {
        this.overtimeDamage = overtimeDamage;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isAbilityToIncapacitate() {
        return abilityToIncapacitate;
    }

    public void setAbilityToIncapacitate(boolean abilityToIncapacitate) {
        this.abilityToIncapacitate = abilityToIncapacitate;
    }

    public int getTotalDamage() {
        return totalDamage;
    }

    public void roundPassed() {
        this.duration--;
    }

    public void setTotalDamage(int totalDamage) {
        this.totalDamage = totalDamage;
    }

    public int getInstantDamage() {
        return instantDamage;
    }

    public void setInstantDamage(int instantDamage) {
        this.instantDamage = instantDamage;
    }
}

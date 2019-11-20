package main.gameplay;

import main.characters.GameCharacter;

public final class OverTimeAbility {

    private int damage;
    private int duration;
    private boolean abilityToIncapacitate;
    private GameCharacter caster;
    private GameCharacter victim;
    private int totalDamage;

    public OverTimeAbility(GameCharacter caster, GameCharacter victim) {
        this.damage = 0;
        this.duration = 0;
        this.abilityToIncapacitate = false;
        this.caster = caster;
        this.victim = victim;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
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
}

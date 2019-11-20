package main.characters;

import main.data.Constants;

public class OverTimeAbility {

    private float damage;
    private int duration;
    private boolean abilityToIncapacitate;
    private int characterLevel;

    public OverTimeAbility(int characterLevel) {
        this.damage = 0.0f;
        this.duration = 0;
        this.abilityToIncapacitate = false;
        this.characterLevel = characterLevel;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
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

    public float getTotalDamage() {
        return damage + Constants.getInstance().getRogueParalysisLevelScalingBaseDamage() *
                characterLevel;
    }
}

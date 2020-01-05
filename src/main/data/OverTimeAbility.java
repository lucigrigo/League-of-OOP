package main.data;

import main.heroes.Hero;

/**
 * Class that represents a type of overtime ability.
 */
public final class OverTimeAbility {

    private int overtimeDamage;
    private int duration;
    private boolean abilityToIncapacitate;
    private Hero victim;
    private int incapacityDuration;

    /*
     Constructor for abilities without incapacitation.
     */
    public OverTimeAbility(final Hero victim,
                           final int damage,
                           final int duration) {
        this.overtimeDamage = damage;
        this.victim = victim;
        this.duration = duration;
        this.incapacityDuration = duration;
        this.abilityToIncapacitate = false;
    }

    /*
     Constructor for abilities with incapacitation.
     */
    public OverTimeAbility(final Hero victim,
                           final int damage,
                           final int duration,
                           final boolean abilityToIncapacitate) {
        this.overtimeDamage = damage;
        this.victim = victim;
        this.duration = duration;
        this.incapacityDuration = duration;
        this.abilityToIncapacitate = abilityToIncapacitate;
    }

    /**
     * @return overtime damage.
     */
    public int getOvertimeDamage() {
        return overtimeDamage;
    }

    /**
     * @return the ability to incapacitate a hero
     */
    public boolean isAbilityToIncapacitate() {
        return abilityToIncapacitate;
    }

    /**
     * Reducing damage duration.
     */
    public void damageDealt() {
        this.duration -= 1;

        // checking if the ability ended
        if (this.duration == 0
                && this.incapacityDuration == 0) {
            victim.setAbilityAffectedBy(null);
        }
    }

    /**
     * Reducing incapacity duration.
     */
    public void incapacityUsed() {
        this.incapacityDuration -= 1;

        // checking if the ability ended
        if (this.duration == 0
                && this.incapacityDuration == 0) {
            victim.setAbilityAffectedBy(null);
        }
    }

    /**
     * @return damage duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @return incapacity duration
     */
    public int getIncapacityDuration() {
        return incapacityDuration;
    }
}

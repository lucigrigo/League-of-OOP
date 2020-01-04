package main.data;

import main.heroes.Hero;

/**
 * Class that represents a type of overtime ability.
 */
public final class OverTimeAbility {

    private int overtimeDamage;
    private int duration;
    private boolean abilityToIncapacitate;
    private Hero caster;
    private Hero victim;
    private LocationType location;
    private String name;
    private int incapacityDuration;

    // Constructor for abilities without incapacitation.
    public OverTimeAbility(final Hero caster,
                           final Hero victim,
                           final String name,
                           final LocationType location,
                           final int damage,
                           final int duration) {
        this.overtimeDamage = damage;
        this.caster = caster;
        this.victim = victim;
        this.location = location;
        this.name = name;
        this.duration = duration;
        this.incapacityDuration = duration;
        this.abilityToIncapacitate = false;
    }

    // Constructor for abilities with incapacitation.
    public OverTimeAbility(final Hero caster,
                           final Hero victim,
                           final String name,
                           final LocationType location,
                           final int damage,
                           final int duration,
                           final boolean abilityToIncapacitate) {
        this.overtimeDamage = damage;
        this.caster = caster;
        this.victim = victim;
        this.location = location;
        this.name = name;
        this.duration = duration;
        this.incapacityDuration = duration;
        this.abilityToIncapacitate = abilityToIncapacitate;
    }

    // returns overtime damage
    public int getOvertimeDamage() {
        return overtimeDamage;
    }

    // checks the ability to incapacitate
    public boolean isAbilityToIncapacitate() {
        return abilityToIncapacitate;
    }

    // reducing damage duration
    public void damageDealt() {
        this.duration -= 1;
        if (this.duration == 0
                && this.incapacityDuration == 0) {
            this.getVictim().setAbilityAffectedBy(null);
        }
    }

    // reducing incapacitation duration
    public void incapacityUsed() {
        this.incapacityDuration -= 1;
        if (this.duration == 0
                && this.incapacityDuration == 0) {
            this.getVictim().setAbilityAffectedBy(null);
        }
    }

    // returning victim
    private Hero getVictim() {
        return victim;
    }

    // returning damage duration
    public int getDuration() {
        return duration;
    }

    // returning incapacitation duration
    public int getIncapacityDuration() {
        return incapacityDuration;
    }
}
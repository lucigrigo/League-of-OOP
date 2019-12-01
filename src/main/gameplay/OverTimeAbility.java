package main.gameplay;

import main.characters.GameCharacter;
import main.data.LocationType;

/**
 * Class that represents a type of overtime ability.
 */
public final class OverTimeAbility {

    private int overtimeDamage;
    private int duration;
    private boolean abilityToIncapacitate;
    private GameCharacter caster;
    private GameCharacter victim;
    private LocationType location;
    private String name;
    private int incapacityDuration;

    // Constructor for abilities without incapacitation.
    public OverTimeAbility(final GameCharacter caster,
                           final GameCharacter victim,
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
    public OverTimeAbility(final GameCharacter caster,
                           final GameCharacter victim,
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
    int getOvertimeDamage() {
        return overtimeDamage;
    }

    // checks the ability to incapacitate
    public boolean isAbilityToIncapacitate() {
        return abilityToIncapacitate;
    }

    // reducing damage duration
    void damageDealt() {
        this.duration -= 1;
        if (this.duration == 0
                && this.incapacityDuration == 0) {
            this.getVictim().setAbilityAffectedBy(null);
        }
    }

    // reducing incapacitation duration
    void incapacityUsed() {
        this.incapacityDuration -= 1;
        if (this.duration == 0
                && this.incapacityDuration == 0) {
            this.getVictim().setAbilityAffectedBy(null);
        }
    }

    // returning victim
    private GameCharacter getVictim() {
        return victim;
    }

    // returning damage duration
    int getDuration() {
        return duration;
    }

    // returning incapacitation duration
    public int getIncapacityDuration() {
        return incapacityDuration;
    }
}

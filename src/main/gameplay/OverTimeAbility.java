package main.gameplay;

import main.characters.GameCharacter;
import main.data.LocationType;

public final class OverTimeAbility {

    private int instantDamage;
    private int overtimeDamage;
    private int duration;
    private boolean abilityToIncapacitate;
    private GameCharacter caster;
    private GameCharacter victim;
    private int totalDamage;
    private LocationType location;
    private String name;
    private int damageWithoutRaceModifier;
    //    private boolean firstRound;
    private int incapacityDuration;

    public OverTimeAbility(final GameCharacter caster,
                           final GameCharacter victim,
                           final String name,
                           final LocationType location) {
        this.instantDamage = 0;
        this.overtimeDamage = 0;
        this.duration = 0;
        this.totalDamage = 0;
        this.abilityToIncapacitate = false;
        this.caster = caster;
        this.victim = victim;
        this.location = location;
        this.name = name;
        this.damageWithoutRaceModifier = 0;
//        this.firstRound = true;
//        this.incapacityDuration = duration;
    }

    public OverTimeAbility(final GameCharacter caster,
                           final GameCharacter victim,
                           final String name,
                           final LocationType location,
                           final int damage) {
        this.overtimeDamage = damage;
        this.caster = caster;
        this.victim = victim;
        this.location = location;
        this.name = name;
//        this.firstRound = false;
        this.duration = 0;
//        this.incapacityDuration = duration;
        this.abilityToIncapacitate = false;
    }

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
//        this.firstRound = false;
        this.incapacityDuration = duration;
        this.abilityToIncapacitate = false;

    }

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
//        this.firstRound = false;
        this.abilityToIncapacitate = abilityToIncapacitate;
    }


    int getOvertimeDamage() {
        return overtimeDamage;
    }

    private void setOvertimeDamage(final int overtimeDamage) {
        this.overtimeDamage = overtimeDamage;
    }

    public boolean isAbilityToIncapacitate() {
        return abilityToIncapacitate;
    }

    public void setAbilityToIncapacitate(final boolean abilityToIncapacitate) {
        this.abilityToIncapacitate = abilityToIncapacitate;
    }

    public int getTotalDamage() {
        return totalDamage;
    }

    void damageDealt() {
        this.duration -= 1;
        if (this.duration == 0) {
            this.setOvertimeDamage(0);
        }
        if (this.duration == 0
                && this.incapacityDuration == 0) {
            this.getVictim().setAbilityAffectedBy(null);
        }
    }

    void incapacityUsed() {
        this.incapacityDuration -= 1;
        if (this.duration == 0
                && this.incapacityDuration == 0) {
            this.getVictim().setAbilityAffectedBy(null);
        }
    }

    GameCharacter getCaster() {
        return caster;
    }

    public void setCaster(GameCharacter caster) {
        this.caster = caster;
    }

    private GameCharacter getVictim() {
        return victim;
    }

    int getDuration() {
        return duration;
    }

    public int getIncapacityDuration() {
        return incapacityDuration;
    }

    public boolean isFinished() {
        return this.duration == 0 && this.incapacityDuration == 0;
    }
}

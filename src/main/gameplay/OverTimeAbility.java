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
    private boolean firstRound;

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
        this.firstRound = true;
    }

    public int getOvertimeDamage() {
//        if (caster.getType() == CharacterType.PYROMANCER) {
//            if (duration == Constants.getInstance().getPyromancerIgniteInitialDuration()) {
//                return instantDamage;
//            }
//            this.setDamageWithoutRaceModifier(overtimeDamage);
//        }
        return overtimeDamage;
    }

    public void setOvertimeDamage(final int overtimeDamage) {
        this.overtimeDamage = overtimeDamage;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(final int duration) {
        this.duration = duration;
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

    public void roundPassed() {
        this.duration--;
    }

    public void setTotalDamage(final int totalDamage) {
        this.totalDamage = totalDamage;
    }

    public int getInstantDamage() {
        return instantDamage;
    }

    public void setInstantDamage(final int instantDamage) {
        this.instantDamage = instantDamage;
    }

    public GameCharacter getCaster() {
        return caster;
    }

    public void setCaster(GameCharacter caster) {
        this.caster = caster;
    }

    public GameCharacter getVictim() {
        return victim;
    }

    public void setVictim(GameCharacter victim) {
        this.victim = victim;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(LocationType location) {
        this.location = location;
    }

    public int getDamageWithoutRaceModifier() {
        return damageWithoutRaceModifier;
    }

    public void setDamageWithoutRaceModifier(int damageWithoutRaceModifier) {
        this.damageWithoutRaceModifier = damageWithoutRaceModifier;
    }

    public LocationType getLocation() {
        return location;
    }

    public boolean isFirstRound() {
        return firstRound;
    }

    public void setFirstRound(boolean firstRound) {
        this.firstRound = firstRound;
    }
}

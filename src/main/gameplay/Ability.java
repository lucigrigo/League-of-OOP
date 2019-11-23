package main.gameplay;

import main.characters.CharacterType;
import main.characters.GameCharacter;
import main.characters.Rogue;
import main.data.LocationType;

public final class Ability {

    private String name;
    private int damage;
    private int damageWithoutRaceModifier;
    private GameCharacter caster;
    private LocationType location;
//    private

    public Ability(final String name,
                   final int damage,
                   final int damageWithoutRaceModifier) {
        this.name = name;
        this.damage = damage;
        this.damageWithoutRaceModifier = damageWithoutRaceModifier;
//        this.caster = caster;
//        System.out.println("Abilitatea " + name + " are demegni neubnsa " + damage);
    }

    public Ability(final String name,
                   final int damage,
                   final int damageWithoutRaceModifier,
                   final GameCharacter caster) {
        this.name = name;
        this.damage = damage;
        this.damageWithoutRaceModifier = damageWithoutRaceModifier;
        this.caster = caster;
    }

//    public Ability(final String name,
//                   final int damage,
//                   final int damageWithoutRaceModifier,
//                   final float floatDamage) {
//        this.name = name;
//        this.damage = damage;
//        this.damageWithoutRaceModifier = damageWithoutRaceModifier;
////        this.caster = caster;
////        System.out.println("Abilitatea " + name + " are demegni neubnsa " + damage);
//    }

    public int getDamageWithoutRaceModifier() {
//        System.out.println("demegi e nebun " + this.damageWithoutRaceModifier);
        return this.damageWithoutRaceModifier;
    }

    public void setDamageWithoutRaceModifier(int damageWithoutRaceModifier) {
        this.damageWithoutRaceModifier = damageWithoutRaceModifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        if ((this.getCaster() != null)
                && (this.getCaster().getType() == CharacterType.ROGUE
                && this.getName().equals("Backstab"))) {
            ((Rogue) this.getCaster()).hasAppliedBackStab();
        }
        return damage;
    }

//    public float getFDamage() {
//        return floatDamage;
//    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public GameCharacter getCaster() {
        return caster;
    }

    public void setCaster(GameCharacter caster) {
        this.caster = caster;
    }

    public LocationType getLocation() {
        return location;
    }

    public void setLocation(LocationType location) {
        this.location = location;
    }
}

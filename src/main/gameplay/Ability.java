package main.gameplay;

public final class Ability {

    private String name;
    private int damage;
    private int damageWithoutRaceModifier;

    public Ability(final String name,
                   final int damage,
                   final int damageWithoutRaceModifier) {
        this.name = name;
        this.damage = damage;
        this.damageWithoutRaceModifier = damageWithoutRaceModifier;
//        System.out.println("Abilitatea " + name + " are demegni neubnsa " + damage);
    }

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
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}

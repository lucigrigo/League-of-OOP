package main.characters;

public abstract class GameCharacter {
    private int colon;
    private int line;
    private int currentHealth;
    private int currentExperience;
    private int level;
    private  String type;

//    public GameCharacter(final int initCol,
//                         final int initLin) {
//        this.colon = initCol;
//        this.line = initLin;
//    }

    public GameCharacter(final int initCol,
                         final int initLin,
                         final int currentHealth,
                         final int currentExperience,
                         final String type) {
        this.colon = initCol;
        this.line = initLin;
        this.currentHealth = currentHealth;
        this.currentExperience = currentExperience;
        this.level = 0;
        this.type = type;
    }

    //TODO create the interface for each character class and implement them


    public int getColon() {
        return colon;
    }

    public int getRow() {
        return line;
    }

    public int getHealth() {
        return currentHealth;
    }

    public int getExperience() {
        return currentExperience;
    }

    public String getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }
}

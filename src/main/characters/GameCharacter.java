package main.characters;

public abstract class GameCharacter {
    private int initCol;
    private int initLin;
    private int currentHealth;
    private int currentExperience;

    public GameCharacter(final int initCol,
                         final int initLin) {
        this.initCol = initCol;
        this.initLin = initLin;
    }

    public GameCharacter(final int initCol,
                         final int initLin,
                         final int currentHealth,
                         final int currentExperience) {
        this.initCol = initCol;
        this.initLin = initLin;
        this.currentHealth = currentHealth;
        this.currentExperience = currentExperience;
    }

    //TODO create the interface for each character class and implement them
}

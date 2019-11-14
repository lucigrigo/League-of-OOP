package main;

import main.data.IOAssistant;
import main.data.InputData;
import main.gameplay.Game;
import main.gameplay.Statistics;

public class Main {

    public static void main(String[] args) {
        IOAssistant ioAssistant = new IOAssistant(args[0], args[1]);
        InputData inputData = ioAssistant.readInput();

        Statistics statistics = new Statistics(inputData);

        Game.getInstance().startGame(inputData, statistics);
    }
}
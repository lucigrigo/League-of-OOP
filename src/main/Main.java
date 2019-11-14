package main;

import main.data.IOAssistant;
import main.data.InputData;

public class Main {

    public static void main(String[] args) {
        IOAssistant ioAssistant = new IOAssistant(args[0], args[1]);
        InputData inputData = ioAssistant.readInput();
    }
}
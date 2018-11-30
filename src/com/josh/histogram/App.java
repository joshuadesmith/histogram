package com.josh.histogram;

public class App {

    /**
     * Driver for the generating a word frequency histogram from an input text file.
     * The results are written to a file named output.txt.
     *
     * TODO: Add ability to choose the name of the output file
     */
    public static void main(String[] args) {
        WordHistogram histogram = new WordHistogram();

        String inputFileName = "input.txt";
        if (args.length > 0) {
            inputFileName = args[0];
        }

        histogram.consumeTextFile(inputFileName);
        histogram.sortDescending();
        histogram.writeToTextFile();
    }
}

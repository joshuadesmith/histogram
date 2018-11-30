package com.josh.histogram;

import java.io.*;
import java.util.*;

public class WordHistogram {
    /**
     * Stores the number of times a word is seen/consumed during an instance of a
     * WordHistogram Object's lifespan.
     */
    private Map<String, Integer> histogram;

    /**
     * Determines whether or not non-alphanumeric characters will be removed
     * from strings before they are consumed. By default this is set to true.
     */
    private boolean alphaNumeric = true;

    private int maxWordLength = 0;

    public WordHistogram() {
        this.histogram = new HashMap<>();
    }

    public WordHistogram(Map<String, Integer> histogram) {
        this.histogram = histogram;
    }

    public WordHistogram(boolean alphaNumeric) {
        this.histogram = new HashMap<>();
        this.alphaNumeric = alphaNumeric;
    }

    public WordHistogram(Map<String, Integer> histogram, boolean alphaNumeric) {
        this.histogram = histogram;
        this.alphaNumeric = alphaNumeric;
    }

    public Map<String, Integer> getHistogram() {
        return histogram;
    }

    public boolean isAlphaNumeric() {
        return alphaNumeric;
    }

    public void consumeTextFile(String fileName) {
        executeConsumeTextFile(new File(fileName));
    }

    public void consumeTextFile(File file) {
        executeConsumeTextFile(file);
    }

    /**
     * Count the occurrences of each word in a text file and add them to the current histogram.
     *
     * @param file Text file to be consumed
     */
    private void executeConsumeTextFile(File file) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = reader.readLine()) != null) {
                consumeString(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: file \"" + file + "\" not found.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
     * Count the occurrences of words in a String and add the number of occurrences
     * of each word to the existing histogram.
     *
     * @param s A string to be processed
     */
    public void consumeString(String s) {
        if (s == null || s.length() < 1) {
            return;
        }

        String cleanedString = cleanString(s);
        String[] words = cleanedString.split("\\s+");

        for (String w : words) {
            incrementWord(w);
        }
    }

    /**
     * Increment the count for a word in the histogram. If the word isn't already in the histogram,
     * it is added to the histogram with a count of 1.
     *
     * @param word A string to be incremented in the histogram.
     */
    public void incrementWord(String word) {
        if (word == null || word.length() < 1) {
            return;
        }

        if (histogram.containsKey(word)) {
            histogram.put(word, histogram.get(word) + 1);
        } else {
            histogram.put(word, 1);
            if (word.length() > maxWordLength) {
                maxWordLength = word.length();
            }
        }
    }

    /**
     * Sort the histogram in descending order by word frequency.
     */
    public void sortDescending() {
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(histogram.entrySet());
        entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        Map<String, Integer> sorted = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : entryList) {
            sorted.put(entry.getKey(), entry.getValue());
        }

        histogram = sorted;
    }

    /**
     * Write the contents of the histogram to a file named output.txt.
     */
    public void writeToTextFile() {
        BufferedWriter writer = null;
        try {
            String fileName = "output.txt";
            File outputFile = new File(fileName);
            if (outputFile.exists()) {
                outputFile.delete();
            }

            writer = new BufferedWriter(new FileWriter("output.txt"));
            System.out.println("Max word length is " + Integer.toString(maxWordLength));
            for (Map.Entry<String, Integer> entry : histogram.entrySet()) {
                writer.write(getHistogramEntryString(entry, maxWordLength));
                writer.flush();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage() + " (Occurred while closing BufferedWriter)");
                }
            }
        }
    }

    /**
     * Get a formatted string representation of an map entry.
     *
     * @param entry A map entry to be represented as a string
     * @param reqLen The required length of the formatted entry key. Used to align all pipe characters
     *               the output file.
     * @return
     */
    private String getHistogramEntryString(Map.Entry<String, Integer> entry, int reqLen) {
        StringBuilder sb = new StringBuilder();
        String k = entry.getKey();
        int v = entry.getValue();

        // Pad the word with spaces if necessary
        int lenDiff = reqLen - k.length();
        for (int i = 0; i < lenDiff; i++) {
            sb.append(" ");
        }

        // Word and Pipe separator
        sb.append(k).append(" | ");

        // Number of occurrences
        for (int i = 0; i < v; i++) {
            sb.append('=');
        }
        sb.append("(").append(v).append(")\n");
        return sb.toString();
    }

    /**
     * Clean the string by doing the following:
     * - Remove all non-alphanumeric characters
     * - Convert the string to lowercase
     *
     * @param s String to be processed
     * @return The cleaned string
     */
    private String cleanString(String s) {
        String ret = s.trim();
        if (alphaNumeric)
            ret = s.replaceAll("[^A-Za-z0-9 ]", "");
        return ret.toLowerCase();
    }
}

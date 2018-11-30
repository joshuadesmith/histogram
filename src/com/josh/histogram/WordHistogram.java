package com.josh.histogram;

import java.util.HashMap;
import java.util.Map;

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
        }
    }

    /**
     * Clean the string by doing the following:
     *  - Remove all non-alphanumeric characters
     *  - Convert the string to lowercase
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

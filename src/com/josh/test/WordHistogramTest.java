package com.josh.test;

import com.josh.histogram.WordHistogram;

import org.junit.Test;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class WordHistogramTest {

    @Test
    public void testDefaultAlphaNumericTrue() {
        WordHistogram hist = new WordHistogram();
        Assert.assertTrue(hist.isAlphaNumeric());
    }

    @Test
    public void testDefaultHistogramNotNull() {
        WordHistogram hist = new WordHistogram();
        Assert.assertNotNull(hist.getHistogram());
    }

    @Test
    public void testIncrementWordNotPresent() {
        WordHistogram hist = new WordHistogram();
        String str = "test";
        hist.incrementWord(str);
        int freq = hist.getHistogram().get(str);
        Assert.assertEquals(1, freq);
    }

    @Test
    public void testIncrementWordPresent() {
        Map<String, Integer> map = new HashMap<>();
        String str = "test";
        map.put(str, 2);

        WordHistogram hist = new WordHistogram(map);
        hist.incrementWord(str);
        int freq = hist.getHistogram().get(str);
        Assert.assertEquals(3, freq);
    }

    @Test
    public void testConsumeEmptyString() {
        WordHistogram hist = new WordHistogram();
        String str = "";
        hist.consumeString(str);
        Assert.assertTrue(hist.getHistogram().isEmpty());
    }

    @Test
    public void testConsumeString() {
        WordHistogram hist = new WordHistogram();
        String str = "a aa aa aaa aaa aaa";
        hist.consumeString(str);
        int freq = hist.getHistogram().get("a");
        Assert.assertEquals(1, freq);
        freq = hist.getHistogram().get("aa");
        Assert.assertEquals(2, freq);
        freq = hist.getHistogram().get("aaa");
        Assert.assertEquals(3, freq);
    }

    @Test
    public void testConsumeTextFileAlNumSmall() {
        String filePath = System.getProperty("user.dir") + "/testFiles/test-alphanumeric-small.txt";
        testConsumeTextFileHelper(filePath);
    }

    @Test
    public void testConsumeTextFileNonAlNumSmall() {
        String filePath = System.getProperty("user.dir") + "/testFiles/test-non-alphanumeric-small.txt";
        testConsumeTextFileHelper(filePath);
    }

    private void testConsumeTextFileHelper(String filePath) {
        WordHistogram hist = new WordHistogram();
        hist.consumeTextFile(filePath);
        Map<String, Integer> map = hist.getHistogram();

        Assert.assertFalse(map.isEmpty());
        testKeyValueInMapHelper(map, "the", 4);
        testKeyValueInMapHelper(map, "ran", 2);
        testKeyValueInMapHelper(map, "dock", 2);
        testKeyValueInMapHelper(map, "clock", 2);
        testKeyValueInMapHelper(map, "dickory", 2);
        testKeyValueInMapHelper(map, "hickory", 2);
        testKeyValueInMapHelper(map, "mouse", 2);
        testKeyValueInMapHelper(map, "down", 1);
        testKeyValueInMapHelper(map, "up", 1);
        testKeyValueInMapHelper(map, "struck", 1);
        testKeyValueInMapHelper(map, "one", 1);
    }

    private void testKeyValueInMapHelper(Map<String, Integer> m, String k, int v) {
        Assert.assertTrue(m.containsKey(k));
        int actual = m.get(k);
        Assert.assertEquals(v, actual);
    }
}

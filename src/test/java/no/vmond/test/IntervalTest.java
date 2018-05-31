package no.vmond.test ;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class IntervalTest {

    Interval interval = new Interval();

    @Test
    public void testSinglePair(){
        int[][] includes = interval.validateAndConvert("10-100");
        int[][] excludes = interval.validateAndConvert("20-30");
        String result = interval.getResultPairs(includes, excludes);
        Assert.assertEquals("10-19,31-100", result);
    }

    @Test
    public void testWithNoExclude(){
        int[][] includes = interval.validateAndConvert("50-5000, 10-100");
        int[][] excludes = interval.validateAndConvert("");
        String result = interval.getResultPairs(includes, excludes);
        Assert.assertEquals("10-5000", result);
    }

    @Test
    public void testWithMultipleInclude(){
        int[][] includes = interval.validateAndConvert("10-100, 200-300");
        int[][] excludes = interval.validateAndConvert("95-205");
        String result = interval.getResultPairs(includes, excludes);
        Assert.assertEquals("10-94,206-300", result);
    }

    @Test
    public void testWithMultipleIncludeMultipleExlculde(){
        int[][] includes = interval.validateAndConvert("10-100, 200-300, 400-500");
        int[][] excludes = interval.validateAndConvert("95-205, 410-420");
        String result = interval.getResultPairs(includes, excludes);
        Assert.assertEquals("10-94,206-300,400-409,421-500", result);
    }
}

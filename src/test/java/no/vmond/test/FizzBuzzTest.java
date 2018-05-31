package no.vmond.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FizzBuzzTest {

    FizzBuzz fizzBuzz = new FizzBuzz() ;

    @Test
    public void TestMultipleOfThree(){
        StringBuilder sb = new StringBuilder();
        fizzBuzz.generatePrintSequence(3, sb);
        Assert.assertEquals("Fizz\n", sb.toString());
    }

    @Test
    public void TestMultipleOfFive(){
        StringBuilder sb = new StringBuilder();
        fizzBuzz.generatePrintSequence(5, sb);
        Assert.assertEquals("Buzz\n", sb.toString());
    }


    @Test
    public void TestMultipleOfFiveAndThree(){
        StringBuilder sb = new StringBuilder();
        fizzBuzz.generatePrintSequence(15, sb);
        Assert.assertEquals("FizzBuzz\n", sb.toString());
    }


    @Test
    public void TestMultipleOfTwo(){
        StringBuilder sb = new StringBuilder();
        fizzBuzz.generatePrintSequence(4, sb);
        Assert.assertEquals("4\n", sb.toString());
    }
}

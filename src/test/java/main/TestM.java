package main;

import UTest.TestingMethod;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestM {

    @Test
    public void whenEmptyString_thenAccept() {
        TestingMethod palindromeTester = new TestingMethod();
        assertTrue(palindromeTester.isPalindrome(""));
    }
}

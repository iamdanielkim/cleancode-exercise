package ex4.logmethod;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class FitnessExampleTest {
    FitnessExample fit = null;

    @Before
    public void setup(){
        fit = new FitnessExample();
    }

    @Test(expected = NullPointerException.class)
    public void testX() throws Exception{
        fit.testableHtml(null, false);
    }



}
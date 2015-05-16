package ex3.fitness;

import org.junit.Before;
import org.junit.Test;

public class FitnessExampleTest {
    FitnessExample fit = null;

    @Before
    public void setup(){
        fit = new FitnessExample();
    }

    @Test(expected = NullPointerException.class)
    public void throw_exception_DummyObject() throws Exception{
        fit.testableHtml(null, false);
    }



}
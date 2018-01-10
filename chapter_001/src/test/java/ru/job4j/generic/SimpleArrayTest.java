package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;

public class SimpleArrayTest {

    SimpleArray<String> array;

    @Before
    public void setUp() {
        array = new SimpleArray<>(10);
        for (int i = 0; i < 10; i++) {
            array.add("Object " + i);
        }
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void readAllStringElementsAndAddExtraElement() {
        for (int i = 0; i < 10; i++) {
            assertThat(array.get(i), is("Object " + i));
        }
        array.add("Extra object");
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void writeAndreadAllIntegerElementsAndAddExtraElement() {
        SimpleArray<Integer> ar = new SimpleArray<>(10);
        for (int i = 0; i < 10; i++) {
            ar.add(i);
        }
        for (int i = 0; i < 10; i++) {
            assertThat(ar.get(i), is(i));
        }
        ar.add(100);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void deleteOneElementAndAddAnotherAtEndOfArray() {
        String sixElement = (String) array.get(6);
        array.delete(5);
        assertThat(sixElement, is(array.get(5)));
        array.add("Extra object");
        assertThat("Extra object", is(array.get(9)));
        array.delete(10);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void updateSomeElement() {
        array.update("New object 5", 5);
        assertThat("New object 5", is(array.get(5)));
        assertThat("Object 9", is(array.get(9)));
        array.update("Extra object", 10);
    }


}

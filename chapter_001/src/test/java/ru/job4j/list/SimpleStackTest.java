package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.generic.User;

import java.util.EmptyStackException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;

public class SimpleStackTest {

    SimpleStack<User> stack;

    @Before
    public void setUp() {
        stack =  new SimpleStack<>();
        for (int i = 0; i < 10; i++) {
            stack.push(new User("User " + i));
        }
    }

    @Test
    public void readAndDeleteFirstTwoUser() {
        assertThat(stack.poll(), is(new User("User " + 9)));
        assertThat(stack.poll(), is(new User("User " + 8)));
        assertThat(stack.size(), is(8));

    }

    @Test (expected = EmptyStackException.class)
    public void returnExceptionIfQueueIsEmpty() {
        stack = new SimpleStack<>();
        stack.poll();
    }
}

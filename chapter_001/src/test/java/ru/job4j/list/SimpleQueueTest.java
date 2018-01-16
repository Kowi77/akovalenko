package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.generic.User;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;

public class SimpleQueueTest {

    SimpleQueue<User> queue;

    @Before
    public void setUp() {
        queue =  new SimpleQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.push(new User("User " + i));
        }
    }

    @Test
    public void readAndDeleteFirstTwoUser() {
            assertThat(queue.poll(), is(new User("User " + 0)));
            assertThat(queue.poll(), is(new User("User " + 1)));
            assertThat(queue.size(), is(8));

    }

    @Test
    public void returnNullIfQueueIsEmpty() {
        queue = new SimpleQueue<>();
        assertNull(queue.poll());
    }
}

package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.generic.User;
import ru.job4j.list.SimpleArrayList;

import java.util.ConcurrentModificationException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleSetTest {

    SimpleSet<User> set;

    @Before
    public void setUp() {
        set = new SimpleSet<>();
        for (int i = 0; i < 10; i++) {
            set.add(new User("User " + i));
        }
    }

    @Test
    public void readAllUsersByIteratorAndCheckForUnableAddExistingElement() {
        int i = 0;
        for (User user : set) {
            assertThat(user, is(new User("User " + i++)));
        }
        assertThat(set.size(), is(10));
        set.add(new User("User 1"));
        assertThat(set.size(), is(10));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void checkIteratorIsFailFast() {
        int i = 0;
        for (User user : set) {
            assertThat(user, is(new User("User " + i++)));
            if (i == 5) {
                set.add(new User("New user"));
            }
        }
    }

    @Test
    public void writeMoreUsersAndReadThemForCheckIncreaseOfCapacity() {
        for (int i = 10; i < 20; i++) {
            set.add(new User("User " + i));
        }
        int j = 0;
        for (User user : set) {
            assertThat(user, is(new User("User " + j++)));
        }
        assertThat(set.size(), is(20));
    }
}

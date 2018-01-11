package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.generic.User;

import java.util.ConcurrentModificationException;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;

public class SimpleArrayListTest {

    SimpleArrayList<User> arrayList;

    @Before
    public void setUp() {
        arrayList = new SimpleArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add(new User("User " + i));
        }
    }

    @Test
    public void readAllUsersByIterator() {
        int i = 0;
        for (User user : arrayList) {
            assertThat(user, is(new User("User " + i++)));
        }
        assertThat(i, is(10));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void checkIteratorIsFailFast() {
        int i = 0;
        for (User user : arrayList) {
            assertThat(user, is(new User("User " + i++)));
            if (i == 5) {
                arrayList.add(new User("New user"));
            }
        }
    }

    @Test
    public void readAllUsersByGet() {
        for (int i = 0; i < 10; i++) {
            assertThat(arrayList.get(i), is(new User("User " + i)));
        }
    }


    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void writeMoreUsersAndReadThemForCheckIncreaseOfCapacityAndGetUnableUser() {
        for (int i = 10; i < 20; i++) {
            arrayList.add(new User("User " + i));
        }
        int j = 0;
        for (User user : arrayList) {
            assertThat(user, is(new User("User " + j++)));
        }
        assertThat(arrayList.size, is(20));
        arrayList.get(20);
    }
}

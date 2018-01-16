package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.generic.User;

import java.util.ConcurrentModificationException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleLinkedListTest {

    SimpleLinkedList<User> linkedList;

    @Before
    public void setUp() {
        linkedList =  new SimpleLinkedList<>();
        for (int i = 0; i < 10; i++) {
            linkedList.add(new User("User " + i));
        }
    }

    @Test
    public void readAllUsersByGet() {
        for (int i = 0; i < 10; i++) {
            assertThat(linkedList.get(i), is(new User("User " + i)));
        }
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void writeMoreUsersAndReadThemForCheckIncreaseOfCapacityAndGetUnableUser() {
        for (int i = 10; i < 20; i++) {
            linkedList.add(new User("User " + i));
        }
        int j = 0;
        for (User user : linkedList) {
            assertThat(user, is(new User("User " + j++)));
        }
        assertThat(linkedList.size(), is(20));
        linkedList.get(20);
    }

    @Test(expected = ConcurrentModificationException.class)
    public void checkIteratorIsFailFast() {
        int i = 0;
        for (User user : linkedList) {
            assertThat(user, is(new User("User " + i++)));
            if (i == 5) {
                linkedList.add(new User("New user"));
            }
        }
    }


}

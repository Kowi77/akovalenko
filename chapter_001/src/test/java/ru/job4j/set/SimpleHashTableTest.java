package ru.job4j.set;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.generic.User;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;

public class SimpleHashTableTest {

    SimpleHashTable<User> table;

    private static final int SIZE = 10;

    @Before
    public void setUp() {
        table = new SimpleHashTable<>();
        for (int i = 0; i < SIZE; i++) {
            table.add(new User("User " + i));
        }
    }

    @Test
    public void checkAllUsersAndCalculatePercentageOfCollisionAndCheckQuantityOfElements() {
        int countOfUsers = 0;
        for (int i = 0; i < SIZE; i++) {
            if (table.contains(new User("User " + i))) {
                countOfUsers++;
            }
        }
        System.out.print("Percentage of lost data (collisions): ");
        System.out.println(100 - ((double) countOfUsers/SIZE) * 100);
        MatcherAssert.assertThat(table.size(), is(countOfUsers));
    }

    @Test
    public void checkRemoveAllElements() {
        for (int i = 0; i < SIZE; i++) {
            User user = new User("User " + i);
            if (table.contains(user)) {
                assertTrue(table.remove(user));
            }
            assertFalse(table.remove(user));
            assertFalse(table.contains(user));
        }
        MatcherAssert.assertThat(table.size(), is(0));
    }

}

package ru.job4j.map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class MapTest {

    Map<User, Object> users;
    User user1;
    User user2;

    @Before
    public void setUp() {
        users = new HashMap();
        user1 = new User("First", 1, new GregorianCalendar(2000, 12, 20));
        user2 = new User("First", 1, new GregorianCalendar(2000, 12, 20));
        users.put(user1, new Object());
        users.put(user2, new Object());
    }


    @Test
    public void checkMapWithoutOverrideEqualsAndHashcode() {
        System.out.println(users);
        Assert.assertEquals(users.size(), 2);
    }

    @Test
    public void checkMapWithoutOverrideEquals() {
        System.out.println("Hashcodes: " + user1.hashCode() + ", " +  user2.hashCode());
        System.out.println(users);
        Assert.assertEquals(users.size(), 2);
    }

    @Test
    public void checkMapWithoutOverrideHashcode() {
        System.out.println("Hashcodes: " + user1.hashCode() + ", " +  user2.hashCode());
        System.out.println(users);
        Assert.assertEquals(users.size(), 2);
    }
}

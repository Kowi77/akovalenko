package ru.job4j.map;

import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.set.SimpleHashTable;

import java.util.*;

import static org.hamcrest.Matchers.is;

public class SimpleHashMapTest {

    SimpleHashMap<User, Object> map;

    private static final int SIZE = 8;

    @Before
    public void setUp() {
        map = new SimpleHashMap<>();
        for (int i = 0; i < SIZE; i++) {
            map.insert(new User("User" + i, i, new GregorianCalendar(2000 + i, 1,1)), new Object());
        }
    }

    @Test
    public void checkIncreaseOfMapAndReadAllElements() {
        for (int i = SIZE; i < SIZE * 2; i++) {
            map.insert(new User("User" + i, i, new GregorianCalendar(2000 + i, 1,1)), new Object());
        }
        int count = 0;
        Iterator<Map.Entry<User, Object>> iterator = map.iterator();
        while (iterator.hasNext()) {
            Map.Entry<User, Object> node = iterator.next();
            System.out.println("Key: " + node.getKey() + " Value: " + node.getValue().toString());
            count++;
        }
        Assert.assertEquals(count, SIZE * 2);
    }

    @Test (expected = NoSuchElementException.class)
    public void readAllUsersWithIteratorAndCheckIllegalNext() {
        int count = 0;
        Iterator<Map.Entry<User, Object>> iterator = map.iterator();
        while (iterator.hasNext()) {
            Map.Entry<User, Object> node = iterator.next();
            System.out.println("Key: " + node.getKey() + " Value: " + node.getValue().toString());
            count++;
        }
        Assert.assertEquals(count, SIZE);
        iterator.next();
    }

    @Test
    public void DeleteAllElements() {
        for (int i = SIZE - 1; i >= 0 ; i--) {
            Assert.assertTrue(map.delete(new User("User" + i, i, new GregorianCalendar(2000 + i, 1,1))));
        }
        Assert.assertFalse(map.iterator().hasNext());
        Assert.assertFalse(map.delete(new User("User1", 1, new GregorianCalendar(2001, 1, 1))));
        Assert.assertTrue(map.insert(new User("User1", 1, new GregorianCalendar(2001, 1, 1)), new Object()));
    }


    @Test
    public void checkAddReturnFalseIfKeyExist() {
        Assert.assertFalse(map.insert(new User("User1", 1, new GregorianCalendar(2001, 1, 1)), new Object()));

    }

    @Test (expected = ConcurrentModificationException.class)
    public void checkIteratorIsFailFast() {
        int count = 0;
        Iterator<Map.Entry<User, Object>> iterator = map.iterator();
        while (iterator.hasNext()) {
            if (count == 5) {
                map.insert(new User("qqq", 1, new GregorianCalendar()), new Object());
            }
            Map.Entry<User, Object> node = iterator.next();
            System.out.println("Key: " + node.getKey() + " Value: " + node.getValue().toString());
            count++;
        }
    }


}

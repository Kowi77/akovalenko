package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NodeTest {


    Node first = new Node(1);
    Node two = new Node(2);
    Node third = new Node(3);
    Node four = new Node(4);

    @Before
    public void setUp() {
        first.setNext(two);
        two.setNext(third);
        third.setNext(four);
        four.setNext(first);
    }


    @Test
    public void checkExistingCycleForFirstAndLastElements() {
        assertTrue(Node.hasCycle(first));
    }

    @Test
    public void checkExistingCycleForAnyElements() {
        four.setNext(null);
        third.setNext(two);
        assertTrue(Node.hasCycle(first));
    }

    @Test
    public void checkScheduleWithoutCycle() {
        four.setNext(null);
        assertFalse(Node.hasCycle(first));
    }
}

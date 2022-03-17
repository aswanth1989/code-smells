package com.article.codesmellworkshop.smellycollection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

 class ListTest {
    private List empty;
    private List oneElement;
    private List manyElement;

    @BeforeEach
     void setUp() {
        empty = new List();
        oneElement = new List();
        oneElement.add("sophia");
        manyElement = new List();
        manyElement.add("josh");
        manyElement.add("sasha");
    }

    @Test
     void isEmpty() {
        assertTrue(empty.isEmpty());
        assertTrue(!oneElement.isEmpty());
    }

    @Test
     void contains() {
        assertTrue(manyElement.contains("josh"));
        assertTrue(!manyElement.contains("tracy"));
    }

    @Test
     void size() {
        assertEquals(0, empty.size());
        assertEquals(1, oneElement.size());
        assertTrue(manyElement.size() > 1);
    }

    @Test
     void allowDuplicates() {
        manyElement.add("sasha");
        assertEquals(3, manyElement.size());
    }

    @Test
     void remove() {
        assertTrue(oneElement.remove("sophia"));
        assertEquals(0, oneElement.size());
        assertTrue(manyElement.remove("josh"));
        assertEquals(1, manyElement.size());
    }

    @Test
     void removeCollapsesList() {
        manyElement.add("tracy");
        assertEquals(3, manyElement.size());
        manyElement.remove("sasha");
        assertEquals(2, manyElement.size());
        assertEquals("tracy", manyElement.get(1));
    }

    @Test
     void addAll() {
        oneElement.addAll(manyElement);
        assertEquals(3, oneElement.size());
    }

    @Test
     void addAllWithSet() {
        Set smallSet = new Set();
        smallSet.add("Dave");
        oneElement.addAll(smallSet);
        assertEquals(2, oneElement.size());
    }

    @Test
     void getWhenIndexOutOfBounds() {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> empty.get(12));
    }

    @Test
     void expandability() {
        List expandableList = new List();
        assertEquals(10, expandableList.capacity());
        for (int i = 0; i < 11; i++)
            expandableList.add(i);
        assertEquals(11, expandableList.size());
        assertEquals(20, expandableList.capacity());
    }

    @Test
     void override() {
        oneElement.set(0, "mary");
        assertEquals("mary", oneElement.get(0));
    }

    @Test
     void overrideWhenOutOfBounds() {
        try {
            oneElement.set(8, "mary");
            fail("should have thrown ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException expectedException) {
            assertTrue(true);
        }
    }

    @Test
     void readOnlyOnAdd() {
        oneElement.setReadOnly(true);
        oneElement.add("eva");
        assertEquals(1, oneElement.size());
    }

    @Test
     void readOnlyOnSet() {
        oneElement.setReadOnly(true);
        oneElement.set(0, "eva");
        assertEquals("sophia", oneElement.get(0));
    }

    @Test
     void readOnlyOnRemove() {
        oneElement.setReadOnly(true);
        oneElement.remove("sophia");
        assertEquals(1, oneElement.size());
    }

    @Test
     void readOnlyOnAddAll() {
        oneElement.setReadOnly(true);
        oneElement.addAll(manyElement);
        assertEquals(1, oneElement.size());
    }
}

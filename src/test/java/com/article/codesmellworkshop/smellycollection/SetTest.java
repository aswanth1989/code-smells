package com.article.codesmellworkshop.smellycollection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SetTest {
    private Set empty;
    private Set oneElement;
    private Set manyElement;

    @BeforeEach
    void setUp() {
        empty = new Set();
        oneElement = new Set();
        oneElement.add("aswa");
        manyElement = new Set();
        manyElement.add("neet");
        manyElement.add("zeia");
    }

    @Test
    void isEmpty() {
        assertTrue(empty.isEmpty());
        assertTrue(!oneElement.isEmpty());
    }

    @Test
    void size() {
        assertEquals(0, empty.size());
        assertEquals(1, oneElement.size());
        assertTrue(manyElement.size() > 1);
    }

    @Test
    void contains() {
        assertTrue(manyElement.contains("neet"));
        assertTrue(!manyElement.contains("aishi"));
    }

    @Test
    void noDuplicates() {
        oneElement.add("aswa");
        assertEquals(1, oneElement.size());
    }

    @Test
    void remove() {
        assertTrue(oneElement.remove("aswa"));
        assertEquals(0, oneElement.size());
        assertTrue(manyElement.remove("neet"));
        assertEquals(1, manyElement.size());
    }

    @Test
    void removeCollapsesSet() {
        manyElement.add("aishi");
        assertEquals(3, manyElement.size());
        manyElement.remove("zeia");
        assertEquals(2, manyElement.size());
        assertEquals("aishi", manyElement.getElementAt(1));
    }

    @Test
    void addAll() {
        oneElement.addAll(manyElement);
        assertEquals(3, oneElement.size());
    }

    @Test
    void addAllWithList() {
        List list = new List();
        list.add("aria");
        oneElement.addAll(list);
        assertEquals(2, oneElement.size());
    }

    @Test
    void addAllWithDuplicates() {
        Set newSet = new Set();
        newSet.add("neet");
        manyElement.addAll(newSet);
        assertEquals(2, manyElement.size());
    }

    @Test
    void addAllWithDuplicatesInList() {
        List newList = new List();
        newList.add("neet");
        manyElement.addAll(newList);
        assertEquals(2, manyElement.size());
    }

    @Test
    void getWhenIndexOutOfBounds() {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> empty.getElementAt(12));
    }

    @Test
    void expandability() {
        Set expandableSet = new Set();
        assertEquals(10, expandableSet.capacity());
        for (int i = 0; i < 11; i++)
            expandableSet.add(i);
        assertEquals(11, expandableSet.size());
        assertEquals(20, expandableSet.capacity());
    }

    @Test
    void readOnlyOnAdd() {
        oneElement.setReadOnly(true);
        oneElement.add("eva");
        assertEquals(1, oneElement.size());
    }

    @Test
    void readOnlyOnRemove() {
        oneElement.setReadOnly(true);
        oneElement.remove("aswa");
        assertEquals(1, oneElement.size());
    }

    @Test
    void readOnlyOnAddAll() {
        oneElement.setReadOnly(true);
        oneElement.addAll(manyElement);
        assertEquals(1, oneElement.size());
    }
}

package com.article.codesmellworkshop.smellycollection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

 class MapTest {
    private Map empty;
    private Map oneElement;
    private Map manyElement;

    @BeforeEach
     void setUp() {
        empty = new Map();
        oneElement = new Map();
        oneElement.add("CA", "California");
        manyElement = new Map();
        manyElement.add("NY", "New York");
        manyElement.add("OR", "Oregon");
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
     void getWhenKeyInMap() {
        assertEquals("California", oneElement.get("CA"));
    }

    @Test
     void getWhenKeyNotInMap() {
        assertEquals(null, oneElement.get("AZ"));
    }

    @Test
     void getAfterEntryRemoved() {
        manyElement.remove("NY");
        assertEquals(null, manyElement.get("NY"));
    }

    @Test
     void contains() {
        assertTrue(manyElement.contains("Oregon"));
        assertTrue(!manyElement.contains("California"));
    }

    @Test
     void containsWhenValueIsNull() {
        oneElement.add("MI", null);
        assertTrue(oneElement.contains(null));
    }

    @Test
     void addIgnoresValueWhenKeyIsNull() {
        oneElement.add(null, "No Value");
        assertEquals(null, oneElement.get(null));
    }

    @Test
     void containsKey() {
        assertTrue(manyElement.containsKey("OR"));
        assertTrue(!manyElement.containsKey("CA"));
    }

    @Test
     void addOverridesExistingEntry() {
        oneElement.add("CA", "Calistoga");
        assertEquals(1, oneElement.size());
        assertEquals("Calistoga", oneElement.get("CA"));
    }

    @Test
    void remove() {
        assertTrue(oneElement.remove("CA"));
        assertEquals(0, oneElement.size());
        assertTrue(manyElement.remove("NY"));
        assertEquals(1, manyElement.size());
    }

    @Test
     void addAll() {
        oneElement.addAll(manyElement);
        assertEquals(3, oneElement.size());
    }

    @Test
     void addAllWithDuplicates() {
        Map mapWithDuplicates = new Map();
        mapWithDuplicates.add("NY", "New York");
        manyElement.addAll(mapWithDuplicates);
        assertEquals(2, manyElement.size());
    }

    @Test
     void readOnlyOnAdd() {
        oneElement.setReadOnly(true);
        oneElement.add("WI", "Wisconsin");
        assertEquals(1, oneElement.size());
    }

    @Test
     void readOnlyOnRemove() {
        oneElement.setReadOnly(true);
        oneElement.remove("CA");
        assertEquals(1, oneElement.size());
    }

    @Test
     void readOnlyOnAddAll() {
        oneElement.setReadOnly(true);
        oneElement.addAll(manyElement);
        assertEquals(1, oneElement.size());
    }
}

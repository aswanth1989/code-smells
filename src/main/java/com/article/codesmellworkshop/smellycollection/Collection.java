package com.article.codesmellworkshop.smellycollection;

public interface Collection {
    boolean isEmpty();
    void add(Object element);
    boolean remove(Object element);
    boolean contains(Object element);
    int size();
}

package edu.aitu.oop3.util;

import java.util.List;

public class Page<T> {
    private List<T> items;
    private int totalElements;

    public Page(List<T> items) {
        this.items = items;
        this.totalElements = items.size();
    }

    public List<T> getItems() {
        return items; }

    public int getTotalElements() {
        return totalElements;
    }
}
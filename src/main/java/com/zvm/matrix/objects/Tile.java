package com.zvm.matrix.objects;

public class Tile<T> {

    private final T[] next;

    public Tile(T[] next) {
        this.next = next;
    }

    public T[] getNext() {
        return next;
    }
}

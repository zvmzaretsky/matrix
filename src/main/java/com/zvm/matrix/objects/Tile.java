package com.zvm.matrix.objects;

import java.util.ArrayList;

public class Tile {

    private ArrayList<Tile> next;
    private Matrix matrix;

    public Tile(ArrayList<Tile> next, Matrix matrix) {
        this.next = next;
        this.matrix = matrix;
    }

    public ArrayList<Tile> getNext() {
        return next;
    }

    public void setNext(ArrayList<Tile> next) {
        this.next = next;
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }
}

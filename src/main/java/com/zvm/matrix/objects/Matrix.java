package com.zvm.matrix.objects;

import java.util.ArrayList;
import java.util.Map;

public class Matrix {

    private final Map<String, Integer[]> rows;

    public Matrix(Map<String, Integer[]> rows) {
        this.rows = rows;
    }

    public Map<String, Integer[]> getRows() {
        return rows;
    }
}

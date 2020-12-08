package com.zvm.matrix.web;

import com.zvm.matrix.Database;
import com.zvm.matrix.objects.Matrix;
import com.zvm.matrix.objects.Tile;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@CrossOrigin
public class Controller {

    @GetMapping(path = "/matrix", produces = "application/json")
    public Matrix getMatrix(@RequestParam String path) {

        String[] strings = path.split("-");
        int[] ints = Arrays.stream(strings).mapToInt(Integer::parseInt).toArray();

        Tile tile = Database.tile;

        for (int i = 0; i < 4; i++) {
            if (tile.getNext() == null) {
                tile.setNext(getNewArr());
            }
            tile = tile.getNext().get(ints[i]);
        }

        if (tile.getMatrix() == null) {
            tile.setMatrix(getNewMatrix());
        }
        return tile.getMatrix();
    }

    private ArrayList<Tile> getNewArr() {
        ArrayList<Tile> arr = new ArrayList<>();
        for (int i = 0; i < 4; i++) arr.add(new Tile(null, null));
        return arr;
    }

    private Matrix getNewMatrix() {
        Map<String, Integer[]> map = new HashMap<>();
        map.put("зайці", getRandomInts(0));
        map.put("вовки", getRandomInts(1));
        return new Matrix(map);
    }

    private Integer[] getRandomInts(int x) {
        Random random = new Random(new Date().getTime()+x);
        Integer[] ints = new Integer[12];
        for (int i = 0; i < 12; i++) ints[i] = random.nextInt(50);
        return ints;
    }
}

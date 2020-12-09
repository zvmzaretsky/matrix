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

    @GetMapping(path = "/prediction", produces = "application/json")
    public Map<String, Integer[]> getPrediction(@RequestParam String path, @RequestParam int size) {
        Map<String, Integer[]> map = new HashMap<>();
        Matrix matrix = getMatrix(path);

        Map<String, Integer[]> rows = matrix.getRows();
        for (Map.Entry<String, Integer[]> entry : rows.entrySet()) {
            map.put(entry.getKey(), getPrediction(entry.getValue(), size));
        }

        return map;
    }

    private static ArrayList<Tile> getNewArr() {
        ArrayList<Tile> arr = new ArrayList<>();
        for (int i = 0; i < 4; i++) arr.add(new Tile(null, null));
        return arr;
    }

    private static Matrix getNewMatrix() {
        Map<String, Integer[]> map = new HashMap<>();
        map.put("зайці", getRandomInts(0));
        map.put("вовки", getRandomInts(1));
        return new Matrix(map);
    }

    private static Integer[] getRandomInts(int x) {
        Random random = new Random(new Date().getTime()+x);
        Integer[] ints = new Integer[12];
        for (int i = 0; i < 12; i++) ints[i] = random.nextInt(50);
        return ints;
    }

    private static Integer[] getPrediction(Integer[] model, int size) {

        Random random = new Random(new Date().getTime());
        Arrays.sort(model);
        int x1 = model[0];
        int x2 = model[model.length - 1];

        Integer[] ints = new Integer[size];
        for (int i = 0; i < size; i++) {
            ints[i] = random.nextInt(x2-x1+1+i*2) + x1;
        }

        return ints;
    }

    public static void main(String[] args) {
        Integer[] ints = getPrediction(new Integer[] {12, 15, 12, 14, 7, 22, 31, 7, 9, 10, 11, 34}, 10);
        System.out.println(Arrays.toString(ints));
    }
}

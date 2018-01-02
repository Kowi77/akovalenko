package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator {

    private final int[][] matrix;
    private int x = 0;
    private int y = 0;

    public MatrixIterator(int[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public boolean hasNext() {
        return matrix.length > y && matrix[y].length > x;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int next = matrix[y][x++];
        if (matrix[y].length <= x) {
            y++;
            x = 0;
        }
        return next;
    }
}

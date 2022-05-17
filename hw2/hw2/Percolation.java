package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.IllformedLocaleException;

public class Percolation {
    private WeightedQuickUnionUF UF;
    private WeightedQuickUnionUF UFwithNoBackWash;
    private boolean[][] isOpenCell;
    private int gridSize; /* the total size should be gridSize * gridSize */
    private int openedCells;
    private int top;
    private int bottom;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllformedLocaleException("N must be greater of equal to 1.");
        }
        gridSize = N;
        openedCells = 0;
        top = N * N;
        bottom = N * N + 1;
        UF = new WeightedQuickUnionUF(N*N + 2);
        UFwithNoBackWash = new WeightedQuickUnionUF(N *N + 2);
        isOpenCell = new boolean[N][N];
        for (int i = 0; i < gridSize; i++) {
            UF.union(xyTo1D(0, i), top); /* connect the top node with cells of the first row */
            UFwithNoBackWash.union(xyTo1D(0, i),top);
            UF.union(xyTo1D(gridSize - 1, i),bottom);
        }
    }
    private boolean checkCellValidity (int row, int col){
        boolean valid = true;
        if (row < 0 || row >= gridSize || col< 0 || col >= gridSize) {
            valid = false;
        }
        return valid;
    }

    public void open(int row, int col) {
        if (!checkCellValidity(row, col)) {
            throw  new IndexOutOfBoundsException("index out of bound.");
        }
        if (isOpen(row,col)) {return;}
        isOpenCell[row][col] = true;
        openedCells = openedCells + 1;
        /* needs to connect to surrounding cells*/
        int curr = xyTo1D(row, col);
        int[][] neighbors = new int[][] {{-1,0},{1,0},{0, -1},{0,1}}; /* top, bottom, left, right */

        for (int[] tempArr:neighbors) {
            int newRow = row + tempArr[0];
            int newCol = col + tempArr[1];
            int to1D = xyTo1D(newRow, newCol);
            boolean isValidCell = checkCellValidity(newRow, newCol);
            if (!isValidCell) {
                continue;
            }
            if (isOpen(newRow, newCol) && (UF.find(curr) != UF.find(to1D))) {
                UF.union(curr, to1D);
                UFwithNoBackWash.union(curr, to1D);
            }
        }
    }



    public boolean isOpen(int row, int col){
        if(!checkCellValidity(row, col)) {
            throw  new IndexOutOfBoundsException("index out of bound.");
        }
        return isOpenCell[row][col];
    }

    private int xyTo1D(int row, int col){
        return row * gridSize + col;
    }

    public boolean isFull(int row, int col) {
       if (!checkCellValidity(row,col)) {
           throw  new IndexOutOfBoundsException("index out of bound.");
       }
       if (!isOpen(row, col)){
           return false;
       } else {
           return UFwithNoBackWash.find(top) == UFwithNoBackWash.find(xyTo1D(row,col));
       }
    }

    public int numberOfOpenSites(){
        return openedCells;
    }

    public boolean percolates(){
        return UF.find(top) == UF.find(bottom);
    }



}

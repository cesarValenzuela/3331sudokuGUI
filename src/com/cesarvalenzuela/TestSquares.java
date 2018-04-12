package com.cesarvalenzuela;

import sudoku.model.Board;

import java.util.Arrays;

/**
 * Author: Cesar Valenzuela
 * Date: 4/11/2018
 * Course:
 * Assignment:
 * Instructor:
 * T.A:
 */
public class TestSquares {
    public static void main(String[] args) {
        Board tBoard = new Board(9);
        for (Square s:
             ) {
            
        }
    }

    public boolean checkSudokuStatus(int[][] grid){
        for (int i = 0; i < 9; i++) {

            int[] row = new int[9];
            int[] square = new int[9];
            int[] columnm = grid[i].clone();

            for (int j = 0; j < 9; j++) {
                row[j] = grid[j][i];
                square[j] = grid[(i/3) * 3 + j / 3][i * 3 % 9 + j % 3];
            }
            if (!(validate(columnm) && validate(row) && validate(square))){
                return false;
            }
        }
        return true;
    }

    public boolean validate(int[] check){
        int i = 0;
        Arrays.sort(check);
        for (int number: check) {
            if (number != ++i)
                return false;
        }
        return true;
    }
}

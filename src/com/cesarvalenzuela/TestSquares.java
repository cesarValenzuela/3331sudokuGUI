package com.cesarvalenzuela;

import sudoku.model.Board;

import java.util.*;

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
        Random rand = new Random();

        int[][] SB = new int[9][9];
        TestSquares ts = new TestSquares();
        //ts.sudokuBuilder(9);\

        int answer = (2/3) * 3;
        System.out.println(answer);

        Board tBoard = new Board(9);

        int o = 1;
        for (int i = 0; i < tBoard.size; i++) {
            for (int j = 0; j < tBoard.size; j++) {
                tBoard.getSquare(i,j).value = o;
                o++;
            }
        }
        for (Board.Square s: tBoard.squares()) {
            System.out.print(s.value + " ");
            if (s.value % 9 == 0){
                System.out.println();
            }
        }
    }

    public int[][] sudokuBuilder(int n){
        double startNum;
        int[][] S = new int[n][n];
        List arrList = new ArrayList(8);
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            arrList.add(i);
        }

        Collections.shuffle(arrList);

        for (int r = 0; r < n ; r++) {
            startNum = (Math.sqrt(n)) * (r % (Math.sqrt(n))) + (r / (Math.sqrt(n)));
            for (int c = 0; c < n; c++) {
                S[r][c] = (int) (((startNum + c) % n) + 1);
            }
        }

        for (int row = 0; row < n; row++) {

            S[row][(int) arrList.get(row)] = 0;
        }
        return S;
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

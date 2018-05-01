package com.cesarvalenzuela;

/**
 * Author: Cesar Valenzuela
 * Date: 3/3/2018
 * Course:
 * Assignment:
 * Instructor:
 * T.A:
 */
public class Square {
    public Integer value;

    public int x;

    public int y;

    public Square(int x, int y){
        this.x = x;
        this.y = y;
        this.value = null;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public void clear(){
        this.value = null;
    }

    public String toString(){
        return " " + value + " ";
    }

}

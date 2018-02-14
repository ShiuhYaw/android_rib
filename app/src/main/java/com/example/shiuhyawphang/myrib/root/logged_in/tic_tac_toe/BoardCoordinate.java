package com.example.shiuhyawphang.myrib.root.logged_in.tic_tac_toe;

/**
 * Created by shiuhyawphang on 13/2/18.
 */

class BoardCoordinate {

    private final int x;
    private final int y;

    BoardCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() { return x; }

    int getY() { return y; }
}

package com.example.shiuhyawphang.myrib.root.logged_in.tic_tac_toe;

/**
 * Created by shiuhyawphang on 13/2/18.
 */
import javax.inject.Inject;

class Board {

    static final int ROWS = 3;
    static final int COLS = 3;

    MarkerType[][] cells;
    int currentRow;
    int currentCol;

    @Inject
    Board() {
        cells = new MarkerType[ROWS][COLS];
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col] = null;
            }
        }
    }

    boolean isDraw() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                if (cells[row][col] == null) {
                    return false;
                }
            }
        }
        return !hasWon(MarkerType.CROSS) && !hasWon(MarkerType.NOUGHT);
    }

    boolean hasWon(MarkerType theSeed) {
        return ((cells[currentRow][0] == theSeed
            && cells[currentRow][1] == theSeed
            && cells[currentRow][2] == theSeed)
            || (cells[0][currentCol] == theSeed
            && cells[1][currentCol] == theSeed
            && cells[2][currentCol] == theSeed)
            || (currentRow == currentCol
            && cells[0][0] == theSeed
            && cells[1][1] == theSeed
            && cells[2][2] == theSeed)
            || (currentRow + currentCol == 2
            && cells[0][2] == theSeed
            && cells[1][1] == theSeed
            && cells[2][0] == theSeed));
    }

    enum MarkerType {
        CROSS,
        NOUGHT
    }
}



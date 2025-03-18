package game;

import game.pieces.*;
import util.Position;

public class Board {
    Cell[][] board = new Cell[8][8];

    public void setupInitialPosition(){
        int row = 0;
        int col = 0;
        Color color = Color.BLACK;
        for (int i = 0; i < board.length; i++) {
            // Set up the pawns
            if (i == 1 || i == 6) {
                for (int j = 0; j < board[i].length; j++) {
                    board[i][j] = new Cell(new Position(i, j), new Pawn());
                }
            } else if (i == 0 || i == 7){ // Set up the rest of the pieces
                for (col = 0; col < board[i].length; col++) {
                    if (col == 0 || col == 7) {
                        board[i][col] = new Cell(new Position(row, col), new Rook());
                    } else if (col == 1 || col == 6) {
                        board[i][col] = new Cell(new Position(row, col), new Knight());
                    } else if (col == 2 || col == 5) {
                        board[i][col] = new Cell(new Position(row, col), new Bishop());
                    } else if (col == 3) {
                        board[i][col] = new Cell(new Position(row, col), new Queen());
                    } else if (col == 4) {
                        board[i][col] = new Cell(new Position(row, col), new King());
                    }
                }
                col = 0;
            } else {
                for (col = 0; col < board[i].length; col++) {
                    board[i][col] = new Cell(new Position(row, col), null);

                }

            }
            row++;
        }
    }
}

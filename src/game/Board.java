package game;

import game.pieces.*;
import util.Position;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private Cell[][] board;

    public Board() {
        board = new Cell[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = new Cell(new Position(row, col));
            }
        }
    }

    public Cell[][] getBoard() {
        return board;
    }

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

    public void setupInitialColor(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if ((i + j) % 2 == 0) {
                    board[i][j].setColor(Color.WHITE);
                } else {
                    board[i][j].setColor(Color.BLACK);
                }
            }
        }
    }

    public void setupInitialPieceColor(){
        for (int i = 0; i < board.length; i++) {
            if (i == 0 || i == 1) {
                for (int j = 0; j < board[i].length; j++) {
                    board[i][j].getPiece().setColor(Color.WHITE);
                }
            } else if (i == 6 || i == 7) {
                for (int j = 0; j < board[i].length; j++) {
                    board[i][j].getPiece().setColor(Color.BLACK);
                }
            }
        }
    }

    public List<String> getAllPossibleMoves(Color color){
        List<String> possibleMoves = new ArrayList<>();
        for (Cell[] cells : board) {
            for (Cell cell : cells) {
                if (cell.getPiece() != null && cell.getPiece().getColor() == color) {
                    possibleMoves.addAll(cell.getPiece().possibleMoves(this));
                }
            }
        }
        return possibleMoves;
    }

    public List<String> getAllPossibleMovesExceptThisCell(Color color, Cell cellToClear){
        Board tempBoard = new Board();
        tempBoard.board = this.board;
        tempBoard.board[cellToClear.getPosition().getRow()][cellToClear.getPosition().getCol()].clear();
        return tempBoard.getAllPossibleMoves(color);
    }


}



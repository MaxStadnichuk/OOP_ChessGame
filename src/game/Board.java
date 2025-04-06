package game;

import game.pieces.*;
import util.Position;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private Cell[][] board;

    public Board() {
        board = new Cell[8][8];
        // Initialize an empty board: each cell gets a new Position.
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = new Cell(new Position(row, col));
            }
        }
    }

    public Cell[][] getBoard() {
        return board;
    }

    public void setupInitialPosition() {
        // black pieces at rows 0-1
        // white pieces at rows 6-7.

        // black pawns on row 1
        for (int col = 0; col < 8; col++) {
            Position pos = new Position(1, col);
            board[1][col] = new Cell(pos, new Pawn(pos, Color.BLACK));
        }
        // white pawns on row 6
        for (int col = 0; col < 8; col++) {
            Position pos = new Position(6, col);
            board[6][col] = new Cell(pos, new Pawn(pos, Color.WHITE));
        }

        // black pieces on row 0
        for (int col = 0; col < 8; col++) {
            Position pos = new Position(0, col);
            switch (col) {
                case 0:
                case 7:
                    board[0][col] = new Cell(pos, new Rook(pos, Color.BLACK));
                    break;
                case 1:
                case 6:
                    board[0][col] = new Cell(pos, new Knight(pos, Color.BLACK));
                    break;
                case 2:
                case 5:
                    board[0][col] = new Cell(pos, new Bishop(pos, Color.BLACK));
                    break;
                case 3:
                    board[0][col] = new Cell(pos, new Queen(pos, Color.BLACK));
                    break;
                case 4:
                    board[0][col] = new Cell(pos, new King(pos, Color.BLACK));
                    break;
            }
        }

        // white pieces on row 7
        for (int col = 0; col < 8; col++) {
            Position pos = new Position(7, col);
            switch (col) {
                case 0:
                case 7:
                    board[7][col] = new Cell(pos, new Rook(pos, Color.WHITE));
                    break;
                case 1:
                case 6:
                    board[7][col] = new Cell(pos, new Knight(pos, Color.WHITE));
                    break;
                case 2:
                case 5:
                    board[7][col] = new Cell(pos, new Bishop(pos, Color.WHITE));
                    break;
                case 3:
                    board[7][col] = new Cell(pos, new Queen(pos, Color.WHITE));
                    break;
                case 4:
                    board[7][col] = new Cell(pos, new King(pos, Color.WHITE));
                    break;
            }
        }
    }

    public void setupInitialColor() {
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

    public void setupInitialPieceColor() {
        for (int i = 0; i < board.length; i++) {
            if (i == 0 || i == 1) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j].getPiece() != null) {
                        board[i][j].getPiece().setColor(Color.BLACK);
                    }
                }
            } else if (i == 6 || i == 7) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j].getPiece() != null) {
                        board[i][j].getPiece().setColor(Color.WHITE);
                    }
                }
            }
        }
    }

    public List<Move> getAllPossibleMoves(Color color) {
        List<Move> possibleMoves = new ArrayList<>();
        for (Cell[] cells : board) {
            for (Cell cell : cells) {
                if (cell.getPiece() != null && cell.getPiece().getColor() == color) {
                    possibleMoves.addAll(cell.getPiece().possibleMoves(this));
                }
            }
        }
        return possibleMoves;
    }

    public List<Move> getAllPossibleMovesExceptThisCell(Color color, Cell cellToClear) {
        Board tempBoard = new Board();
        tempBoard.board = this.board; // Note: this is a shallow copy.
        tempBoard.board[cellToClear.getPosition().getRow()][cellToClear.getPosition().getCol()].clear();
        List<Move> result = tempBoard.getAllPossibleMoves(color);
        for (Move move : result) {
            System.out.print(move.getTo().toString() + " ");
        }
        System.out.println("getAllPossibleMovesExceptThisCell count: " + result.size());
        return result;
    }

    public Cell getCell(Position position) {
        return board[position.getRow()][position.getCol()];
    }
}

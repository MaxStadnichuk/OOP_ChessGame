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
                    Position position = new Position(i, col);
                    board[i][j] = new Cell(position, new Pawn(position, color));
                }
            } else if (i == 0 || i == 7){ // Set up the rest of the pieces
                for (col = 0; col < board[i].length; col++) {
                    Position position = new Position(i, col);
                    if (col == 0 || col == 7) {
                        board[i][col] = new Cell(position, new Rook(position, color));
                    } else if (col == 1 || col == 6) {
                        board[i][col] = new Cell(position, new Knight(position, color));
                    } else if (col == 2 || col == 5) {
                        board[i][col] = new Cell(position, new Bishop(position, color));
                    } else if (col == 3) {
                        board[i][col] = new Cell(position, new Queen(position, color));
                    } else if (col == 4) {
                        board[i][col] = new Cell(position, new King(position, color));
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

    public List<Move> getAllPossibleMoves(Color color){
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

    public List<Move> getAllPossibleMovesExceptThisCell(Color color, Cell cellToClear){
        Board tempBoard = new Board();
        tempBoard.board = this.board;
        tempBoard.board[cellToClear.getPosition().getRow()][cellToClear.getPosition().getCol()].clear();
        List<Move> result = tempBoard.getAllPossibleMoves(color);
        for (Move move : result) {
            System.out.print(move.getTo().toString()+" ");
        }
        System.out.println("getAllPossibleMovesExceptThisCell cout: "+result.size());
        return result;
    }


}



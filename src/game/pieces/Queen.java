package game.pieces;

import game.Board;
import game.Color;
import game.Move;
import game.MoveType;
import util.Position;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece{

    public Queen(Position position, Color color) {
        super(position, color);
    }


    @Override
    public List<Move> possibleMoves(Board board) {
        List<Move> possibleMoves = new ArrayList<>();

        boolean rightUp = true;
        boolean rightDown = true;
        boolean leftUp = true;
        boolean leftDown = true;

        // Check possible moves for bishop
        for (int i = 1; i < 8; i++) {
            // check right up
            Position positionToMove = new Position(this.getPosition().getRow() + i, this.getPosition().getCol() + i);
            if ((checkCell(board, positionToMove) || checkCellToAttack(board, positionToMove)) && rightUp) {
                if (checkCellToAttack(board, positionToMove) || !checkCell(board, positionToMove)) {
                    possibleMoves.add(new Move(this.getPosition(), positionToMove, MoveType.CAPTURE, this));
                    rightUp = false;
                } else {
                    possibleMoves.add(new Move(this.getPosition(), positionToMove, MoveType.NORMAL, this));
                }
            }

            // check left up
            positionToMove = new Position(this.getPosition().getRow() + i, this.getPosition().getCol() - i);
            if ((checkCell(board, positionToMove) || checkCellToAttack(board, positionToMove)) && leftUp) {
                if (checkCellToAttack(board, positionToMove) || !checkCell(board, positionToMove)) {
                    possibleMoves.add(new Move(this.getPosition(), positionToMove, MoveType.CAPTURE, this));
                    leftUp = false;
                } else {
                    possibleMoves.add(new Move(this.getPosition(), positionToMove, MoveType.NORMAL, this));
                }
            }

            // check right down
            positionToMove = new Position(this.getPosition().getRow() - i, this.getPosition().getCol() + i);
            if ((checkCell(board, positionToMove) || checkCellToAttack(board, positionToMove)) && rightDown) {
                if (checkCellToAttack(board, positionToMove) || !checkCell(board, positionToMove)) {
                    possibleMoves.add(new Move(this.getPosition(), positionToMove, MoveType.CAPTURE, this));
                    rightDown = false;
                } else {
                    possibleMoves.add(new Move(this.getPosition(), positionToMove, MoveType.NORMAL, this));
                }
            }

            // check left down
            positionToMove = new Position(this.getPosition().getRow() - i, this.getPosition().getCol() - i);
            if ((checkCell(board, positionToMove) || checkCellToAttack(board, positionToMove)) && leftDown) {
                if (checkCellToAttack(board, positionToMove) || !checkCell(board, positionToMove)) {
                    possibleMoves.add(new Move(this.getPosition(), positionToMove, MoveType.CAPTURE, this));
                    leftDown = false;
                } else {
                    possibleMoves.add(new Move(this.getPosition(), positionToMove, MoveType.NORMAL, this));
                }
            }
        }

        Position currentPosition = this.getPosition();
        int currentRow = currentPosition.getRow();
        int currentCol = currentPosition.getCol();

        // Check moves in all four directions:
        // Right
        addMovesInDirection(board, possibleMoves, currentRow, currentCol, 0, 1);
        // Left
        addMovesInDirection(board, possibleMoves, currentRow, currentCol, 0, -1);
        // Up
        addMovesInDirection(board, possibleMoves, currentRow, currentCol, 1, 0);
        // Down
        addMovesInDirection(board, possibleMoves, currentRow, currentCol, -1, 0);

        return possibleMoves;
    }

    private void addMovesInDirection(Board board, List<Move> moves, int startRow, int startCol, int deltaRow, int deltaCol) {
        for (int i = 1; i < 8; i++) {
            int newRow = startRow + deltaRow * i;
            int newCol = startCol + deltaCol * i;
            Position positionToMove = new Position(newRow, newCol);
            // Check board boundaries (assuming board size is 8)
            if (newRow < 0 || newRow >= 8 || newCol < 0 || newCol >= 8) {
                break;
            }
            if (checkCell(board, positionToMove)) {
                moves.add(new Move(this.getPosition(), positionToMove, MoveType.NORMAL, this));
            } else if (checkCellToAttack(board, positionToMove)) {
                moves.add(new Move(this.getPosition(), positionToMove, MoveType.CAPTURE, this));
                break;
            } else {
                break;
            }
        }
    }
}

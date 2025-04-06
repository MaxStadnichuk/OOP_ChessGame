package game.pieces;

import game.Board;
import game.Color;
import game.Move;
import game.MoveType;
import util.Position;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// inheritance
public class Queen extends Piece{

    public Queen(Position position, Color color) {
        super(position, color);
    }
    public Queen() {
        super();
    }


    @Override
    public List<Move> possibleMoves(Board board) {
        List<Move> possibleMoves = new ArrayList<>();

        Position currentPos = this.getPosition();
        int currentRow = currentPos.getRow();
        int currentCol = currentPos.getCol();

        boolean rightUp = true;
        boolean rightDown = true;
        boolean leftUp = true;
        boolean leftDown = true;

        // Check possible moves for bishop
        for (int i = 1; i < 8; i++) {
            // сheck right-up (row + i, col + i)
            if (rightUp) {
                Position posRU = new Position(currentRow + i, currentCol + i);
                if (checkCell(board, posRU)) {
                    possibleMoves.add(new Move(currentPos, posRU, MoveType.NORMAL, this));
                } else if (checkCellToAttack(board, posRU)) {
                    possibleMoves.add(new Move(currentPos, posRU, MoveType.CAPTURE, this));
                    rightUp = false;
                } else {
                    rightUp = false;
                }
            }

            // сheck left-up (row + i, col - i)
            if (leftUp) {
                Position posLU = new Position(currentRow + i, currentCol - i);
                if (checkCell(board, posLU)) {
                    possibleMoves.add(new Move(currentPos, posLU, MoveType.NORMAL, this));
                } else if (checkCellToAttack(board, posLU)) {
                    possibleMoves.add(new Move(currentPos, posLU, MoveType.CAPTURE, this));
                    leftUp = false;
                } else {
                    leftUp = false;
                }
            }

            // сheck right-down (row - i, col + i)
            if (rightDown) {
                Position posRD = new Position(currentRow - i, currentCol + i);
                if (checkCell(board, posRD)) {
                    possibleMoves.add(new Move(currentPos, posRD, MoveType.NORMAL, this));
                } else if (checkCellToAttack(board, posRD)) {
                    possibleMoves.add(new Move(currentPos, posRD, MoveType.CAPTURE, this));
                    rightDown = false;
                } else {
                    rightDown = false;
                }
            }

            // сheck left-down (row - i, col - i)
            if (leftDown) {
                Position posLD = new Position(currentRow - i, currentCol - i);
                if (checkCell(board, posLD)) {
                    possibleMoves.add(new Move(currentPos, posLD, MoveType.NORMAL, this));
                } else if (checkCellToAttack(board, posLD)) {
                    possibleMoves.add(new Move(currentPos, posLD, MoveType.CAPTURE, this));
                    leftDown = false;
                } else {
                    leftDown = false;
                }
            }
        }

        Position currentPosition = this.getPosition();
        currentRow = currentPosition.getRow();
        currentCol = currentPosition.getCol();

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

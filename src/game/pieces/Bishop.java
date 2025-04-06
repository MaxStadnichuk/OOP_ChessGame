package game.pieces;

import game.Board;
import game.Color;
import game.Move;
import game.MoveType;
import util.Position;

import java.util.ArrayList;
import java.util.List;

// inheritance
public class Bishop extends Piece {

    public Bishop(Position position, Color color) {
        super(position, color);
    }

    public Bishop() {
        super();
    }

    // polymorphism
    @Override
    public List<Move> possibleMoves(Board board) {
        List<Move> possibleMoves = new ArrayList<>();

        Position currentPos = this.getPosition();
        int currentRow = currentPos.getRow();
        int currentCol = currentPos.getCol();

        boolean rightUp = true;
        boolean leftUp = true;
        boolean rightDown = true;
        boolean leftDown = true;

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

            // check left-up (row + i, col - i)
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

        return possibleMoves;
    }
}

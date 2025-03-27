package game.pieces;

import game.Board;
import game.Color;
import game.Move;
import game.MoveType;
import util.Position;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(Position position, Color color) {
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
        return possibleMoves;
    }
}

package game.pieces;

import game.Board;
import game.Color;
import game.Move;
import game.MoveType;
import util.Position;

import java.util.ArrayList;
import java.util.List;

// inheritance
public class Knight extends Piece{

    public Knight(Position position, Color color) {
        super(position, color);
    }
    public Knight() {
        super();
    }

    @Override
    public List<Move> possibleMoves(Board board) {
        List<Move> possibleMoves = new ArrayList<>();

        Position positionToMove = new Position(this.getPosition().getRow(), this.getPosition().getCol());
        // Check possible moves for knight

        // check up right
        positionToMove = new Position(this.getPosition().getRow()+2,
                this.getPosition().getCol()+1);

        if (checkCell(board, positionToMove)) {
            possibleMoves.add(new Move(this.getPosition(), positionToMove, MoveType.NORMAL, this));
        } else if (checkCellToAttack(board, positionToMove)) {
            possibleMoves.add(new Move(this.getPosition(), positionToMove, MoveType.CAPTURE, this));
        }

        // check up left
        positionToMove = new Position(this.getPosition().getRow()+2,
                this.getPosition().getCol()-1);

        if (checkCell(board, positionToMove)) {
            possibleMoves.add(new Move(this.getPosition(), positionToMove, MoveType.NORMAL, this));
        } else if (checkCellToAttack(board, positionToMove)) {
            possibleMoves.add(new Move(this.getPosition(), positionToMove, MoveType.CAPTURE, this));
        }

        // check right up
        positionToMove = new Position(this.getPosition().getRow()+1,
                this.getPosition().getCol()+2);

        if (checkCell(board, positionToMove)) {
            possibleMoves.add(new Move(this.getPosition(), positionToMove, MoveType.NORMAL, this));
        } else if (checkCellToAttack(board, positionToMove)) {
            possibleMoves.add(new Move(this.getPosition(), positionToMove, MoveType.CAPTURE, this));
        }

        // check right down
        positionToMove = new Position(this.getPosition().getRow()-1,
                this.getPosition().getCol()+2);

        if (checkCell(board, positionToMove)) {
            possibleMoves.add(new Move(this.getPosition(), positionToMove, MoveType.NORMAL, this));
        } else if (checkCellToAttack(board, positionToMove)) {
            possibleMoves.add(new Move(this.getPosition(), positionToMove, MoveType.CAPTURE, this));
        }

        // check down right
        positionToMove = new Position(this.getPosition().getRow()-2,
                this.getPosition().getCol()+1);

        if (checkCell(board, positionToMove)) {
            possibleMoves.add(new Move(this.getPosition(), positionToMove, MoveType.NORMAL, this));
        } else if (checkCellToAttack(board, positionToMove)) {
            possibleMoves.add(new Move(this.getPosition(), positionToMove, MoveType.CAPTURE, this));
        }

        // check down left
        positionToMove = new Position(this.getPosition().getRow()-2,
                this.getPosition().getCol()-1);

        if (checkCell(board, positionToMove)) {
            possibleMoves.add(new Move(this.getPosition(), positionToMove, MoveType.NORMAL, this));
        } else if (checkCellToAttack(board, positionToMove)) {
            possibleMoves.add(new Move(this.getPosition(), positionToMove, MoveType.CAPTURE, this));
        }

        // check left up
        positionToMove = new Position(this.getPosition().getRow()+1,
                this.getPosition().getCol()-2);

        if (checkCell(board, positionToMove)) {
            possibleMoves.add(new Move(this.getPosition(), positionToMove, MoveType.NORMAL, this));
        } else if (checkCellToAttack(board, positionToMove)) {
            possibleMoves.add(new Move(this.getPosition(), positionToMove, MoveType.CAPTURE, this));
        }

        // check left down
        positionToMove = new Position(this.getPosition().getRow()-1,
                this.getPosition().getCol()-2);

        if (checkCell(board, positionToMove)) {
            possibleMoves.add(new Move(this.getPosition(), positionToMove, MoveType.NORMAL, this));
        } else if (checkCellToAttack(board, positionToMove)) {
            possibleMoves.add(new Move(this.getPosition(), positionToMove, MoveType.CAPTURE, this));
        }

        return possibleMoves;
    }
}

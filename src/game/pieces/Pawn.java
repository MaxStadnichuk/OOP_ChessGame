package game.pieces;

import game.Board;
import game.Color;
import game.Move;
import game.MoveType;
import util.Position;

import java.util.ArrayList;
import java.util.List;

// inheritance
public class Pawn extends Piece{

    public Pawn(Position position, Color color) {
        super(position, color);
    }
    public Pawn() {
        super();
    }

    boolean enPassant = false;

    @Override
    public List<Move> possibleMoves(Board board) {
        List<Move> possibleMoves = new ArrayList<>();

        if (this.getColor() == Color.WHITE) {
            // one step forward for white = row - 1
            Position oneStep = new Position(this.getPosition().getRow() - 1, this.getPosition().getCol());
            if (checkCell(board, oneStep)) {
                possibleMoves.add(new Move(this.getPosition(), oneStep, MoveType.NORMAL, this));
            }
            // diagonal capture moves
            Position diagRight = new Position(this.getPosition().getRow() - 1, this.getPosition().getCol() + 1);
            if (checkCellToAttack(board, diagRight)) {
                possibleMoves.add(new Move(this.getPosition(), diagRight, MoveType.CAPTURE, this));
            }
            Position diagLeft = new Position(this.getPosition().getRow() - 1, this.getPosition().getCol() - 1);
            if (checkCellToAttack(board, diagLeft)) {
                possibleMoves.add(new Move(this.getPosition(), diagLeft, MoveType.CAPTURE, this));
            }
            // two-step forward
            Position twoStep = new Position(this.getPosition().getRow() - 2, this.getPosition().getCol());
            if (checkCell(board, twoStep) && this.getPosition().getRow() == 6) {
                possibleMoves.add(new Move(this.getPosition(), twoStep, MoveType.NORMAL, this));
            }
        } else if (this.getColor() == Color.BLACK) {
            // one step forward for black = row + 1
            Position oneStep = new Position(this.getPosition().getRow() + 1, this.getPosition().getCol());
            if (checkCell(board, oneStep)) {
                possibleMoves.add(new Move(this.getPosition(), oneStep, MoveType.NORMAL, this));
            }
            // diagonal capture moves
            Position diagRight = new Position(this.getPosition().getRow() + 1, this.getPosition().getCol() + 1);
            if (checkCellToAttack(board, diagRight)) {
                possibleMoves.add(new Move(this.getPosition(), diagRight, MoveType.CAPTURE, this));
            }
            Position diagLeft = new Position(this.getPosition().getRow() + 1, this.getPosition().getCol() - 1);
            if (checkCellToAttack(board, diagLeft)) {
                possibleMoves.add(new Move(this.getPosition(), diagLeft, MoveType.CAPTURE, this));
            }
            // two-step forward
            Position twoStep = new Position(this.getPosition().getRow() + 2, this.getPosition().getCol());
            if (checkCell(board, twoStep) && this.getPosition().getRow() == 1) {
                possibleMoves.add(new Move(this.getPosition(), twoStep, MoveType.NORMAL, this));
            }
        }
        return possibleMoves;
    }

    public boolean isEnPassantAvailable() {
        return enPassant;
    }


}

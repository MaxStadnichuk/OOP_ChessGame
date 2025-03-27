package game.pieces;

import game.Board;
import game.Color;
import game.Move;
import game.MoveType;
import util.Position;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public King(Position position, Color color) {
        super(position, color);
    }

    @Override
    public List<Move> possibleMoves(Board board) {
        List<Move> possibleMoves = new ArrayList<>();

        // check up
        Position positionToMove = new Position(this.getPosition().getRow()+1, this.getPosition().getCol());
        checkCellForKing(board, possibleMoves, positionToMove);

        // check down
        positionToMove = new Position(this.getPosition().getRow()-1, this.getPosition().getCol());
        checkCellForKing(board, possibleMoves, positionToMove);

        // check left
        positionToMove = new Position(this.getPosition().getRow(), this.getPosition().getCol()-1);
        checkCellForKing(board, possibleMoves, positionToMove);

        // check right
        positionToMove = new Position(this.getPosition().getRow(), this.getPosition().getCol()+1);
        checkCellForKing(board, possibleMoves, positionToMove);

        // check left up
        positionToMove = new Position(this.getPosition().getRow() + 1, this.getPosition().getCol()-1);
        checkCellForKing(board, possibleMoves, positionToMove);

        // check right up
        positionToMove = new Position(this.getPosition().getRow() + 1, this.getPosition().getCol()+1);
        checkCellForKing(board, possibleMoves, positionToMove);

        // check right down
        positionToMove = new Position(this.getPosition().getRow() - 1, this.getPosition().getCol()+1);
        checkCellForKing(board, possibleMoves, positionToMove);

        // check left down
        positionToMove = new Position(this.getPosition().getRow() - 1, this.getPosition().getCol()-1);
        checkCellForKing(board, possibleMoves, positionToMove);

        return possibleMoves;
    }

    private void checkCellForKing(Board board, List<Move> possibleMoves, Position positionToMove) {
        if (checkCell(board, positionToMove)
                && !containsMove(board.getAllPossibleMovesExceptThisCell(this.getColor().getOpposite(), board.getBoard()[getPosition().getRow()][getPosition().getCol()]), positionToMove)) {
            possibleMoves.add(new Move(this.getPosition(), positionToMove, MoveType.NORMAL, this));
        } else if (checkCellToAttack(board, positionToMove)
                && !containsMove(board.getAllPossibleMovesExceptThisCell(this.getColor().getOpposite(), board.getBoard()[getPosition().getRow()][getPosition().getCol()]), positionToMove)
                && !containsMove(board.getAllPossibleMovesExceptThisCell(this.getColor().getOpposite(), board.getBoard()[positionToMove.getRow()][positionToMove.getCol()]), positionToMove)) {
            possibleMoves.add(new Move(this.getPosition(), positionToMove, MoveType.CAPTURE, this));

        }
    }

    // Helper method: returns true if 'moves' contains a move with the given 'from' and 'to' positions.
    private boolean containsMove(List<Move> moves, Position to) {
        for (Move m : moves) {
            if (m.getTo().equals(to)) {
                return true;
            }
        }
        return false;
    }
}

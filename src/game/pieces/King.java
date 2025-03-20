package game.pieces;

import game.Board;
import util.Position;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    @Override
    public void moveTo(Position position) {

    }

    @Override
    public List<String> possibleMoves(Board board) {
        List<String> possibleMoves = new ArrayList<>();

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

    private void checkCellForKing(Board board, List<String> possibleMoves, Position positionToMove) {
        if (checkCell(board, positionToMove)
                && !board.getAllPossibleMovesExceptThisCell(this.getColor().getOpposite(), board.getBoard()[getPosition().getRow()][getPosition().getCol()]).contains(positionToMove.toString())) {
            possibleMoves.add(positionToMove.toString());
        } else if (checkCellToAttack(board, positionToMove)
                && !board.getAllPossibleMovesExceptThisCell(this.getColor().getOpposite(), board.getBoard()[getPosition().getRow()][getPosition().getCol()]).contains(positionToMove.toString())
                && !board.getAllPossibleMovesExceptThisCell(this.getColor().getOpposite(), board.getBoard()[positionToMove.getRow()][positionToMove.getCol()]).contains(positionToMove.toString())) {
            possibleMoves.add(positionToMove.toString());

        }
    }
}

package game.pieces;

import game.Board;
import game.Color;
import util.Position;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{
    boolean enPassant = false;

    @Override
    public List<String> possibleMoves(Board board) {
        List<String> possibleMoves = new ArrayList<>();


        // Check possible moves for white pawn
        if (this.getColor() == Color.WHITE) {
            Position positionToMove = new Position(this.getPosition().getRow() + 1, this.getPosition().getCol());
            if (checkCell(board, positionToMove)) {
                possibleMoves.add(positionToMove.toString());
            }
            positionToMove.setCol(this.getPosition().getCol() + 1);
            if (checkCellToAttack(board, positionToMove)) {
                possibleMoves.add(positionToMove.toString());
            }
            positionToMove.setCol(this.getPosition().getCol() - 1);
            if (checkCellToAttack(board, positionToMove)) {
                possibleMoves.add(positionToMove.toString());
            }
            //return to initial column
            positionToMove.setCol(this.getPosition().getCol());

            positionToMove.setRow(this.getPosition().getRow() + 2);
            if (checkCell(board, positionToMove) && this.getPosition().getRow() == 1) {
                possibleMoves.add(positionToMove.toString());
            }
        }
        // Check possible moves for black pawn
        if (this.getColor() == Color.BLACK) {
            Position positionToMove = new Position(this.getPosition().getRow() - 1, this.getPosition().getCol());
            if (checkCell(board, positionToMove)) {
                possibleMoves.add(positionToMove.toString());
            }
            positionToMove.setCol(this.getPosition().getCol() + 1);
            if (checkCellToAttack(board, positionToMove)) {
                possibleMoves.add(positionToMove.toString());
            }
            positionToMove.setCol(this.getPosition().getCol() - 1);
            if (checkCellToAttack(board, positionToMove)) {
                possibleMoves.add(positionToMove.toString());
            }
            //return to initial column
            positionToMove.setCol(this.getPosition().getCol());

            positionToMove.setRow(this.getPosition().getRow() - 2);
            if (checkCell(board, positionToMove) && this.getPosition().getRow() == 6) {
                possibleMoves.add(positionToMove.toString());
            }
        }

        return possibleMoves;
    }

    public boolean isEnPassantAvailable() {
        return enPassant;
    }


}

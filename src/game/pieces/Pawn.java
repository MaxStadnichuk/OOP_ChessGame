package game.pieces;

import game.Board;
import game.Color;
import game.Move;
import game.MoveType;
import util.Position;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{

    public Pawn(Position position, Color color) {
        super(position, color);
    }

    boolean enPassant = false;

    @Override
    public List<Move> possibleMoves(Board board) {
        List<Move> possibleMoves = new ArrayList<>();


        // Check possible moves for white pawn
        if (this.getColor() == Color.WHITE) {
            Position positionToMove = new Position(this.getPosition().getRow() + 1, this.getPosition().getCol());
            if (checkCell(board, positionToMove)) {
                possibleMoves.add(new Move(this.getPosition(), positionToMove, MoveType.NORMAL, this));
            }
            checkRightLeftCells(board, positionToMove, possibleMoves);

            positionToMove = new Position(this.getPosition().getRow() + 2, this.getPosition().getCol());
            if (checkCell(board, positionToMove) && this.getPosition().getRow() == 1) {
                possibleMoves.add(new Move(this.getPosition(), positionToMove, MoveType.NORMAL, this));
            }
        }
        // Check possible moves for black pawn
        if (this.getColor() == Color.BLACK) {
            Position positionToMove = new Position(this.getPosition().getRow() - 1, this.getPosition().getCol());
            if (checkCell(board, positionToMove)) {
                System.out.println("Adding move");
                possibleMoves.add(new Move(this.getPosition(), positionToMove, MoveType.NORMAL, this));
                System.out.println(possibleMoves);
            }
            checkRightLeftCells(board, positionToMove, possibleMoves);

            positionToMove = new Position(this.getPosition().getRow() - 2, this.getPosition().getCol());
            if (checkCell(board, positionToMove) && this.getPosition().getRow() == 6) {
                possibleMoves.add(new Move(this.getPosition(), positionToMove, MoveType.NORMAL, this));
            }
        }

        System.out.println(possibleMoves);
        return possibleMoves;
    }

    private void checkRightLeftCells(Board board, Position positionToMove, List<Move> possibleMoves) {
        positionToMove.setCol(this.getPosition().getCol() + 1);
        if (checkCellToAttack(board, positionToMove)) {
            possibleMoves.add(new Move(this.getPosition(), positionToMove, MoveType.CAPTURE, this));
        }
        positionToMove.setCol(this.getPosition().getCol() - 1);
        if (checkCellToAttack(board, positionToMove)) {
            possibleMoves.add(new Move(this.getPosition(), positionToMove, MoveType.CAPTURE, this));
        }
        //return to initial column
        positionToMove.setCol(this.getPosition().getCol());
    }

    public boolean isEnPassantAvailable() {
        return enPassant;
    }


}

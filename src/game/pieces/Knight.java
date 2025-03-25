package game.pieces;

import game.Board;
import util.Position;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece{

    @Override
    public List<String> possibleMoves(Board board) {
        List<String> possibleMoves = new ArrayList<>();

        Position positionToMove = new Position(this.getPosition().getRow(), this.getPosition().getCol());
        // Check possible moves for knight

        // check up right
        positionToMove = new Position(this.getPosition().getRow()+2,
                this.getPosition().getCol()+1);

        if (checkCell(board, positionToMove)) {
            possibleMoves.add(positionToMove.toString());
        } else if (checkCellToAttack(board, positionToMove)) {
            possibleMoves.add(positionToMove.toString());
        }

        // check up left
        positionToMove = new Position(this.getPosition().getRow()+2,
                this.getPosition().getCol()-1);

        if (checkCell(board, positionToMove)) {
            possibleMoves.add(positionToMove.toString());
        } else if (checkCellToAttack(board, positionToMove)) {
            possibleMoves.add(positionToMove.toString());
        }

        // check right up
        positionToMove = new Position(this.getPosition().getRow()+1,
                this.getPosition().getCol()+2);

        if (checkCell(board, positionToMove)) {
            possibleMoves.add(positionToMove.toString());
        } else if (checkCellToAttack(board, positionToMove)) {
            possibleMoves.add(positionToMove.toString());
        }

        // check right down
        positionToMove = new Position(this.getPosition().getRow()-1,
                this.getPosition().getCol()+2);

        if (checkCell(board, positionToMove)) {
            possibleMoves.add(positionToMove.toString());
        } else if (checkCellToAttack(board, positionToMove)) {
            possibleMoves.add(positionToMove.toString());
        }

        // check down right
        positionToMove = new Position(this.getPosition().getRow()-2,
                this.getPosition().getCol()+1);

        if (checkCell(board, positionToMove)) {
            possibleMoves.add(positionToMove.toString());
        } else if (checkCellToAttack(board, positionToMove)) {
            possibleMoves.add(positionToMove.toString());
        }

        // check down left
        positionToMove = new Position(this.getPosition().getRow()-2,
                this.getPosition().getCol()-1);

        if (checkCell(board, positionToMove)) {
            possibleMoves.add(positionToMove.toString());
        } else if (checkCellToAttack(board, positionToMove)) {
            possibleMoves.add(positionToMove.toString());
        }

        // check left up
        positionToMove = new Position(this.getPosition().getRow()+1,
                this.getPosition().getCol()-2);

        if (checkCell(board, positionToMove)) {
            possibleMoves.add(positionToMove.toString());
        } else if (checkCellToAttack(board, positionToMove)) {
            possibleMoves.add(positionToMove.toString());
        }

        // check left down
        positionToMove = new Position(this.getPosition().getRow()-1,
                this.getPosition().getCol()-2);

        if (checkCell(board, positionToMove)) {
            possibleMoves.add(positionToMove.toString());
        } else if (checkCellToAttack(board, positionToMove)) {
            possibleMoves.add(positionToMove.toString());
        }

        return possibleMoves;
    }
}

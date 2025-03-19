package game.pieces;

import game.Board;
import util.Position;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece{
    @Override
    public void moveTo(Position position) {

    }

    @Override
    public List<String> possibleMoves(Board board) {
        List<String> possibleMoves = new ArrayList<>();
        Position currentPosition = this.getPosition();
        int currentRow = currentPosition.getRow();
        int currentCol = currentPosition.getCol();

        // Check possible moves to the right
        for (int col = currentCol + 1; col < 8; col++) {
            Position positionToMove = new Position(currentRow, col);
            if (checkCell(board, positionToMove)) {
                possibleMoves.add(positionToMove.toString());
            } else if (checkCellToAttack(board, positionToMove)) {
                possibleMoves.add(positionToMove.toString());
                break;
            } else {
                break;
            }
        }

        // Check possible moves to the left
        for (int col = currentCol - 1; col >= 0; col--) {
            Position positionToMove = new Position(currentRow, col);
            if (checkCell(board, positionToMove)) {
                possibleMoves.add(positionToMove.toString());
            } else if (checkCellToAttack(board, positionToMove)) {
                possibleMoves.add(positionToMove.toString());
                break;
            } else {
                break;
            }
        }

        // Check possible moves up
        for (int row = currentRow + 1; row < 8; row++) {
            Position positionToMove = new Position(row, currentCol);
            if (checkCell(board, positionToMove)) {
                possibleMoves.add(positionToMove.toString());
            } else if (checkCellToAttack(board, positionToMove)) {
                possibleMoves.add(positionToMove.toString());
                break;
            } else {
                break;
            }
        }

        // Check possible moves down
        for (int row = currentRow - 1; row >= 0; row--) {
            Position positionToMove = new Position(row, currentCol);
            if (checkCell(board, positionToMove)) {
                possibleMoves.add(positionToMove.toString());
            } else if (checkCellToAttack(board, positionToMove)) {
                possibleMoves.add(positionToMove.toString());
                break;
            } else {
                break;
            }
        }

        return possibleMoves;
    }
}

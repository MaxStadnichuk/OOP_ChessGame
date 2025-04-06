package game.pieces;

import game.Board;
import game.Color;
import game.Move;
import game.MoveType;
import util.Position;

import java.util.ArrayList;
import java.util.List;

// inheritance
public class Rook extends Piece {

    // Constructor overloading
    public Rook(Position position, Color color) {
        super(position, color);
    }
    public Rook() {
        super();
    }

    @Override
    public List<Move> possibleMoves(Board board) {
        List<Move> possibleMoves = new ArrayList<>();
        Position currentPosition = this.getPosition();
        int currentRow = currentPosition.getRow();
        int currentCol = currentPosition.getCol();

        // Check moves in all four directions:
        // Right
        addMovesInDirection(board, possibleMoves, currentRow, currentCol, 0, 1);
        // Left
        addMovesInDirection(board, possibleMoves, currentRow, currentCol, 0, -1);
        // Up
        addMovesInDirection(board, possibleMoves, currentRow, currentCol, 1, 0);
        // Down
        addMovesInDirection(board, possibleMoves, currentRow, currentCol, -1, 0);

        return possibleMoves;
    }

    private void addMovesInDirection(Board board, List<Move> moves, int startRow, int startCol, int deltaRow, int deltaCol) {
        for (int i = 1; i < 8; i++) {
            int newRow = startRow + deltaRow * i;
            int newCol = startCol + deltaCol * i;
            Position positionToMove = new Position(newRow, newCol);
            // Check board boundaries (assuming board size is 8)
            if (newRow < 0 || newRow >= 8 || newCol < 0 || newCol >= 8) {
                break;
            }
            if (checkCell(board, positionToMove)) {
                moves.add(new Move(this.getPosition(), positionToMove, MoveType.NORMAL, this));
            } else if (checkCellToAttack(board, positionToMove)) {
                moves.add(new Move(this.getPosition(), positionToMove, MoveType.CAPTURE, this));
                break;
            } else {
                break;
            }
        }
    }
}

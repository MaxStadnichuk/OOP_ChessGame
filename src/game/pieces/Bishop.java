package game.pieces;

import game.Board;
import util.Position;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece{

    @Override
    public List<String> possibleMoves(Board board) {
        List<String> possibleMoves = new ArrayList<>();

        Position positionToMove = new Position(this.getPosition().getRow(), this.getPosition().getCol());
        boolean rightUp = true;
        boolean rightDown = true;
        boolean leftUp = true;
        boolean leftDown = true;
        // Check possible moves for bishop
        for(int i = 1; i < 8; i++){
            //check right up
            positionToMove.setRow(this.getPosition().getRow() + i);
            positionToMove.setCol(this.getPosition().getCol() + i);
            if ((checkCell(board, positionToMove) || checkCellToAttack(board, positionToMove)) && rightUp) {
                possibleMoves.add(positionToMove.toString());
                if (checkCellToAttack(board, positionToMove) || !checkCell(board, positionToMove)) {
                    rightUp = false;
                }
            }
            //check left up
            positionToMove.setRow(this.getPosition().getRow() + i);
            positionToMove.setCol(this.getPosition().getCol() - i);
            if ((checkCell(board, positionToMove) || checkCellToAttack(board, positionToMove)) && leftUp) {
                possibleMoves.add(positionToMove.toString());
                if (checkCellToAttack(board, positionToMove) || !checkCell(board, positionToMove)) {
                    leftUp = false;
                }

            }
            //check right down
            positionToMove.setRow(this.getPosition().getRow() - i);
            positionToMove.setCol(this.getPosition().getCol() + i);
            if ((checkCell(board, positionToMove) || checkCellToAttack(board, positionToMove)) && rightDown) {
                possibleMoves.add(positionToMove.toString());
                if (checkCellToAttack(board, positionToMove) || !checkCell(board, positionToMove)) {
                    rightDown = false;
                }
            }
            //check left down
            positionToMove.setRow(this.getPosition().getRow() - i);
            positionToMove.setCol(this.getPosition().getCol() - i);
            if ((checkCell(board, positionToMove) || checkCellToAttack(board, positionToMove)) && leftDown) {
                possibleMoves.add(positionToMove.toString());
                if (checkCellToAttack(board, positionToMove) || !checkCell(board, positionToMove)) {
                    leftDown = false;
                }
            }
        }

        return possibleMoves;
    }
}
